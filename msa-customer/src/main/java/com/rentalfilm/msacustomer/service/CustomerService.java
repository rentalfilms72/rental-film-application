package com.rentalfilm.msacustomer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalfilm.msacustomer.entity.Customer;
import com.rentalfilm.msacustomer.entity.CustomerIdTable;
import com.rentalfilm.msacustomer.repository.CustomerIdTableRepository;
import com.rentalfilm.msacustomer.repository.CustomerRepository;


@Service
public class CustomerService {

	@Autowired
	CustomerIdTableRepository customerIdTableRepository;

	@Autowired
	CustomerRepository customerRepository;


	// Variable using in generateUserId function
	String compileString = null;
	String matcherString = null;

	List<String> allIdOfItem = new ArrayList<>();	



	/* GENERATING CUSTOMER ID */
	//public String generateStringId(String className) {
	public String generateStringId() {

		String newId = null; // Content the final result at the end of process
		String classId = null; // Content the latest table id of a selected Table
		String endPartId = "AA"; // Content the lasts letter of an ID if it is set, initial value is "AA" 

		Long longId = 0L;

		// get the CustomerIdTable content ( there is just one rows)
		Optional<CustomerIdTable> idRows = customerIdTableRepository.findById(1L);
		if(!idRows.isPresent()) // RentalFilmIdTable is empty
			return null;

		// CUSxxxxxXX : where x represent digit and X represent Letter , 5 letters + 5 digits 
		classId = idRows.get().getCustomerId();
		compileString = "^( )*CUS\\d+[A-Z]{2}( )*$";
		matcherString = "CUS0000XX";

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
			List<Customer> allCustomer = customerRepository.findAll();
			allIdOfItem = !allCustomer.isEmpty() ? 
					allCustomer.stream().map(e -> e.getCustomerId()).collect(Collectors.toList())
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
				endPartId = classId.split("\\d+")[1]; // ie: input=ACT0000XX output=["ACT", "XX"]
			}

		}


		longId++; // Increment the number in the ID

		// CUSxxxxxXX : where x represent digit and X represent Letter , 5 letters + 5 digits 
		Double doubleResult = Math.pow(10, 5);
		Long longResult = doubleResult.longValue();
		if(longId >= longResult)
			endPartId = generateEndPartId(endPartId);// ie: input="AA" output="AB" (AA, AB, AC,... AZ, BA)

		newId = new String("CUS"+ formatNumber(longId, 5) + endPartId); // Generate a new ID 
		idRows.get().setCustomerId(newId);
		

		// Save the new ID
		customerIdTableRepository.save(idRows.get());
		return newId;
	}

	/**
	 * 
	 * @param endPartId
	 * @return
	 */
	private String generateEndPartId(String endPartId) {

		int first = (endPartId.charAt(0) - 'A') * 26;
		int second = endPartId.charAt(1) - 'A';

		int next = (first + second + 1) % (26*26); 

		return new String(new byte[] {(byte)(next / 26 + 'A'), (byte)(next % 26 + 'A')});
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
