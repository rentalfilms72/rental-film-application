package com.rentalfilm.msastore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalfilm.msastore.entity.Store;
import com.rentalfilm.msastore.entity.StoreIdTable;
import com.rentalfilm.msastore.repository.StoreIdTableRepository;
import com.rentalfilm.msastore.repository.StoreRepository;



@Service
public class StoreService {

	@Autowired
	StoreIdTableRepository storeIdTableRepository;

	@Autowired
	StoreRepository storeRepository;


	// Variable using in generateUserId function
	String compileString = null;
	String matcherString = null;

	List<String> allIdOfItem = new ArrayList<>();	



	/* GENERATING STORE ID */
	//public String generateStringId(String className) {
	public String generateStringId() {

		String newId = null; // Content the final result at the end of process
		String classId = null; // Content the latest table id of a selected Table

		Long longId = 0L;

		// get the CustomerIdTable content ( there is just one rows)
		Optional<StoreIdTable> idRows = storeIdTableRepository.findById(1L);
		if(!idRows.isPresent()) // StoreIdTable is empty
			return null;

		// STORExxx : where x represent digit and X represent Letter , 5 letters + 3 digits
		classId = idRows.get().getStoreId();
		compileString = "^( )*STORE\\d+( )*$";
		matcherString = "STORE000";

		// ********** COMMON PART ***************
		if(classId == null || classId.isEmpty()) { //ID OF THE TABLE IS ABSENT
			// There is not ID then it is the first access to this table
			// This part could take must time to execute because of number of record inside data base

			/*
			 * Select all the item from The table customer
			 * Here a result is the List of String who represent the ID
			 * Else the result is empty
			 * THE RESUL IS SAVE IN 'allIdOfItem'
			 */
			List<Store> allStore = storeRepository.findAll();
			allIdOfItem = !allStore.isEmpty() ? 
					allStore.stream().map(e -> e.getStoreId()).collect(Collectors.toList())
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

		// STORExxx : where x represent digit and X represent Letter , 5 letters + 3 digits 
		newId = new String("STORE"+ formatNumber(longId, 3) ); // Generate a new ID 
		idRows.get().setStoreId(newId);
		

		// Save the new ID
		storeIdTableRepository.save(idRows.get());
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
