package com.rentalfilm.msacustomer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalfilm.msacustomer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

	Optional<Customer> findByUsername(String username);

}
