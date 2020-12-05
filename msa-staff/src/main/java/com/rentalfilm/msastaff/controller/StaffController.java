package com.rentalfilm.msastaff.controller;

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

import com.rentalfilm.msastaff.entity.Staff;
import com.rentalfilm.msastaff.exception.StaffListEmptyException;
import com.rentalfilm.msastaff.exception.StaffNotFoundException;
import com.rentalfilm.msastaff.payload.request.CreateUserRequest;
import com.rentalfilm.msastaff.payload.request.RegisterStaffRequest;
import com.rentalfilm.msastaff.proxy.UserProxy;
import com.rentalfilm.msastaff.repository.StaffRepository;
import com.rentalfilm.msastaff.service.StaffService;
import com.rentalfilm.msastaff.util.EncrytedPasswordUtils;
import com.rentalfilm.msastaff.validator.RegisterStaffValidator;


@RestController
public class StaffController {

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private StaffService staffService;

	@Autowired
	private UserProxy userProxy;

	@Autowired
	private RegisterStaffValidator registerStaffValidator;

	// Set a form validator
	@InitBinder
	protected void registerStaffInitBinder(WebDataBinder dataBinder) {
		// Form target
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == RegisterStaffRequest.class) {
			dataBinder.setValidator(registerStaffValidator);
		}	
	}

	/*###################
	 # POST METHODS
	 ####################*/
	@Transactional
	@PostMapping("/staff/private/register")
	public ResponseEntity<Staff>  registerStaff( 
			@RequestBody @Validated RegisterStaffRequest registerStaffRequest) {

		// VALIDATION OF DATA


		// Create the new Staff
		Staff newStaff = new Staff();
		String idGenerated = staffService.generateStringId();
		newStaff.setStaffId(idGenerated);
		newStaff.setEmail(registerStaffRequest.getEmail().trim());
		newStaff.setUsername(registerStaffRequest.getUsername().trim());

		String encryptedPassword = "{bcrypt}" + EncrytedPasswordUtils.encrytePassword(registerStaffRequest.getPassword());
		newStaff.setPassword(encryptedPassword);

		newStaff.setFirstName(registerStaffRequest.getFirstName().trim());
		newStaff.setLastName(registerStaffRequest.getLastName().trim());
		newStaff.setEnabled(false);


		//newStaff.setPictureId(registerStaffRequest.getPictureId());
		//newStaff.setAddressId(registerStaffRequest.getAddressId());
//		registerStaffRequest.setStoreId(registerStaffRequest.getStoreId().trim());
//		if(registerStaffRequest.getStoreId().equals(""))
//			registerStaffRequest.setStoreId(null);
		
		newStaff.setStoreId(registerStaffRequest.getStoreId());

		// Save the customer on DATA BASE
		newStaff = staffRepository.save(newStaff);


		// Call the User microservice to create also a User
		CreateUserRequest createUserRequest = new CreateUserRequest();
		createUserRequest.setUserId(idGenerated);
		createUserRequest.setEmail(registerStaffRequest.getEmail().trim());
		createUserRequest.setUsername(registerStaffRequest.getUsername().trim());
		createUserRequest.setPassword(encryptedPassword);

		//createUserRequest.setPictureId(registerStaffRequest.getPictureId());
		createUserRequest.setAuthorityName("ROLE_STAFF");
		userProxy.createUser(createUserRequest);


		return new ResponseEntity<Staff>(newStaff, HttpStatus.OK);
	}


	/*######################
	 # GET METHODS (READ)
	 #######################*/

	// Proxy method
	@GetMapping("/staff/public/get-by-id/{staffId}")
	public ResponseEntity<Staff>  getOneStaff(@PathVariable(name = "staffId") String staffId) {

		Optional<Staff> staffFound = staffRepository.findById(staffId);
		if(!staffFound.isPresent())
			throw new StaffNotFoundException("Staff with id '" + staffId + "' do not exist.");


		return new ResponseEntity<Staff>(staffFound.get(), HttpStatus.OK);
	}

	// Proxy method
	@GetMapping("/staff/public/get-all")
	public ResponseEntity<List<Staff>>  getAllStaff(){

		List<Staff> staffList = staffRepository.findAll();
		if(staffList.isEmpty())
			throw new StaffListEmptyException("Staff list empty.");

		return new ResponseEntity<List<Staff>>(staffList, HttpStatus.OK);
	}

	@GetMapping("/staff/public/staff-exist/{staffId}")
	public boolean staffExist(@PathVariable("staffId") String staffId) {

		Optional<Staff> staffFound = staffRepository.findById(staffId);
		if(!staffFound.isPresent()) 
			return false;

		return true;
	}

	

	/*######################
	 # PUT METHODS (UPDATE)
	 #######################*/
	
	@PutMapping("/staff/public/update-storeId/{staffId}/{storeId}")
	public ResponseEntity<Staff>  updateStoreIdOfStaff(
			@PathVariable(name = "staffId") String staffId,
			@PathVariable(name = "storeId") String storeId
			){
		
		// Control data
		
		Optional<Staff> staffFound = staffRepository.findById(staffId);
		if(!staffFound.isPresent())
			throw new StaffNotFoundException("Staff with id '" + staffId + "' do not exist.");
		
		staffFound.get().setStoreId(storeId);
		
		staffRepository.save(staffFound.get());
		
		return new ResponseEntity<Staff>(staffFound.get(), HttpStatus.OK);
	}

	@PutMapping("/staff/public/enable/{staffId}")
	public boolean enableStaff(@PathVariable(name = "staffId") String staffId){

		Optional<Staff> staffFound = staffRepository.findById(staffId);
		if(staffFound.isPresent()) {
			// TODO
			//enable the customer after a confermation mail
			//...
			staffFound.get().setEnabled(true);
			staffRepository.save(staffFound.get());
			userProxy.enableUser(staffId);
			return true;
		}


		return false;
	}


	@PutMapping("/staff/public/disable/{staffId}")
	public boolean disableStaff(@PathVariable(name = "staffId") String staffId){

		Optional<Staff> staffFound = staffRepository.findById(staffId);
		if(staffFound.isPresent()) {
			staffFound.get().setEnabled(false);
			staffRepository.save(staffFound.get());
			userProxy.disableUser(staffId);
			return true;
		}


		return false;
	}





}
