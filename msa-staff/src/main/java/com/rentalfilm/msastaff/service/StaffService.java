package com.rentalfilm.msastaff.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalfilm.msastaff.entity.Staff;
import com.rentalfilm.msastaff.entity.StaffIdTable;
import com.rentalfilm.msastaff.repository.StaffIdTableRepository;
import com.rentalfilm.msastaff.repository.StaffRepository;




@Service
public class StaffService {

	@Autowired
	StaffIdTableRepository staffIdTableRepository;

	@Autowired
	StaffRepository staffRepository;


	// Variable using in generateUserId function
	String compileString = null;
	String matcherString = null;

	List<String> allIdOfItem = new ArrayList<>();	



	/* GENERATING STAFF ID */
	public String generateStringId() {

		String newId = null; // Content the final result at the end of process
		String classId = null; // Content the latest table id of a selected Table

		Long longId = 0L;

		// get the StaffIdTable content ( there is just one rows)
		Optional<StaffIdTable> idRows = staffIdTableRepository.findById(1L);
		if(!idRows.isPresent()) // StaffIdTable is empty
			return null;

		// STAFFxxxx : where x represent digit and X represent Letter , 5 letters + 4 digits
		classId = idRows.get().getStaffId();
		compileString = "^( )*STAFF\\d+( )*$";
		matcherString = "STAFF0000";

		// ********** COMMON PART ***************
		if(classId == null || classId.isEmpty()) { //ID OF THE TABLE IS ABSENT
			// There is not ID then it is the first access to this table
			// This part could take must time to execute because of number of record inside data base

			/*
			 * Select all the item from The table staff
			 * Here a result is the List of String who represent the ID
			 * Else the result is empty
			 * THE RESUL IS SAVE IN 'allIdOfItem'
			 */
			List<Staff> allStaff = staffRepository.findAll();
			allIdOfItem = !allStaff.isEmpty() ? 
					allStaff.stream()
					.map(e -> e.getStaffId())
					.collect(Collectors.toList())
					:null;

			longId = allIdOfItem != null ?
					allIdOfItem.stream()
					.map(itemId -> {
						Pattern pattern = Pattern.compile( "\\d+" ); // Pattern to extract a number from Alpha Numeric Id 
						Matcher matcher = pattern.matcher(itemId);
						if(matcher.find())
							return Long.parseLong(matcher.toMatchResult().group());
						else 
							return 0L;
					})
					.max(Long::compare) // Generate the max of all long number
					.get()
					:0L;

		} else { //ID OF THE TABLE IS PRESENT
			// The CustomerIdTable already content the last ID generated
			Pattern pattern = Pattern.compile( "\\d+" ); // Pattern to extract a number from Alpha Numeric Id 
			Matcher matcher = pattern.matcher( classId );

			if(matcher.find()) {
				longId = Long.parseLong(matcher.toMatchResult().group());
			}

		}

		longId++; // Increment the number in the ID

		// STAFFxxxx : where x represent digit and X represent Letter , 5 letters + 4 digits 
		newId = new String("STORE"+ formatNumber(longId, 4) ); // Generate a new ID 
		idRows.get().setStaffId(newId);
		

		// Save the new ID
		staffIdTableRepository.save(idRows.get());
		return newId;
	}


	/**
	 * Format an input number in length digit
	 * example: input (2, 4) -> output "0002" 
	 * input (22366, 4) -> output "22366" 
	 * @param number
	 * @param length
	 * @return
	 */
	public String formatNumber(Long number, int length) {

		String numberToString = "";

		int numberLength = Long.toString(number).length();

		if(numberLength >= length)
			return Long.toString(number);

		// Determine the number of zero to put in front of the number
		int numZeroToAdd = length - numberLength;

		for(int i=0; i<numZeroToAdd; i++)
			numberToString = numberToString + "0";

		// Concat the zeros and the number
		numberToString = numberToString + number;

		return numberToString;
	}

}
