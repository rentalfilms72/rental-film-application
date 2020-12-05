package com.rentalfilm.msaadmin.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rentalfilm.msaadmin.entity.Admin;
import com.rentalfilm.msaadmin.exception.AdminListEmptyException;
import com.rentalfilm.msaadmin.exception.AdminNotFoundException;
import com.rentalfilm.msaadmin.payload.request.CreateUserRequest;
import com.rentalfilm.msaadmin.payload.request.RegisterAdminRequest;
import com.rentalfilm.msaadmin.proxy.UserProxy;
import com.rentalfilm.msaadmin.repository.AdminRepository;
import com.rentalfilm.msaadmin.service.AdminService;
import com.rentalfilm.msaadmin.util.EncrytedPasswordUtils;
import com.rentalfilm.msaadmin.validator.RegisterAdminValidator;




@RestController
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserProxy userProxy;
	
	@Autowired
	private RegisterAdminValidator registerAdminValidator;

	// Set a form validator
	@InitBinder
	protected void registerAdminInitBinder(WebDataBinder dataBinder) {
		// Form target
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == RegisterAdminRequest.class) {
			dataBinder.setValidator(registerAdminValidator);
		}	
	}

	@Transactional
	@PostMapping("/admin/private/register")
	public ResponseEntity<Admin>  registerAdmin( 
			@RequestBody @Validated RegisterAdminRequest registerAdminRequest) {
		
		// VALIDATION OF DATA
		
		
		// Create the new Admin
		Admin newAdmin = new Admin();
		String idGenerated = adminService.generateStringId();
		newAdmin.setAdminId(idGenerated);
		newAdmin.setEmail(registerAdminRequest.getEmail().trim());
		newAdmin.setUsername(registerAdminRequest.getUsername().trim());
		
		String encryptedPassword = "{bcrypt}" + EncrytedPasswordUtils.encrytePassword(registerAdminRequest.getPassword());
		newAdmin.setPassword(encryptedPassword);
		
		newAdmin.setFirstName(registerAdminRequest.getFirstName().trim());
		newAdmin.setLastName(registerAdminRequest.getLastName().trim());
		newAdmin.setEnabled(false);
		
		
		//newAdmin.setPictureId(registerAdminRequest.getPictureId());
		//newAdmin.setAddressId(registerAdminRequest.getAddressId());
		//newAdmin.setStoreId(registerAdminRequest.getStoreId());
		
		// Save the customer on DATA BASE
		newAdmin = adminRepository.save(newAdmin);
		
		
		// Call the User microservice to create also a User
		CreateUserRequest createUserRequest = new CreateUserRequest();
		createUserRequest.setUserId(idGenerated);
		createUserRequest.setEmail(registerAdminRequest.getEmail().trim());
		createUserRequest.setUsername(registerAdminRequest.getUsername().trim());
		createUserRequest.setPassword(encryptedPassword);
		
		//createUserRequest.setPictureId(registerAdminRequest.getPictureId());
		createUserRequest.setAuthorityName("ROLE_ADMIN");
		userProxy.createUser(createUserRequest);
		

		return new ResponseEntity<Admin>(newAdmin, HttpStatus.OK);
	}
	
	@PutMapping("/admin/public/enable/{adminId}")
	public boolean enableAdmin(@PathVariable(name = "adminId") String adminId){
		
		Optional<Admin> adminFound = adminRepository.findById(adminId);
		if(adminFound.isPresent()) {
			// TODO
			//enable the admin after a confermation mail
			//...
			adminFound.get().setEnabled(true);
			adminRepository.save(adminFound.get());
			userProxy.enableUser(adminId);
			return true;
		}
		
		
		return false;
	}
	
	
	@PutMapping("/admin/public/disable/{adminId}")
	public boolean disableAdmin(@PathVariable(name = "adminId") String adminId){
		
		Optional<Admin> adminFound = adminRepository.findById(adminId);
		if(adminFound.isPresent()) {
			adminFound.get().setEnabled(false);
			adminRepository.save(adminFound.get());
			userProxy.disableUser(adminId);
			return true;
		}
		
		
		return false;
	}

	
	// Proxy method
	@GetMapping("/admin/public/get-by-id/{adminId}")
	public ResponseEntity<Admin>  getOneAdmin(@PathVariable(name = "adminId") String adminId) {

		Optional<Admin> adminFound = adminRepository.findById(adminId);
		if(!adminFound.isPresent())
			throw new AdminNotFoundException("Admin with id '" + adminId + "' do not exist.");


		return new ResponseEntity<Admin>(adminFound.get(), HttpStatus.OK);
	}
	
	// Proxy method
	@GetMapping("/admin/public/get-all")
	public ResponseEntity<List<Admin>>  getAllAdmin(){

		List<Admin> adminList = adminRepository.findAll();
		if(adminList.isEmpty())
			throw new AdminListEmptyException("Admin list empty.");

		return new ResponseEntity<List<Admin>>(adminList, HttpStatus.OK);
	}


}
