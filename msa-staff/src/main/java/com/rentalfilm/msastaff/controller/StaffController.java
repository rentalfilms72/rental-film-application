package com.rentalfilm.msastaff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.rentalfilm.msastaff.repository.StaffRepository;

@RestController
public class StaffController {
	
	@Autowired
	private StaffRepository staffRepository;

}
