package com.rentalfilm.msacustomer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalfilm.msacustomer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
