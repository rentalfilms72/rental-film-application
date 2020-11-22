package com.rentalfilm.msacustomer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalfilm.msacustomer.entity.CustomerIdTable;

public interface CustomerIdTableRepository extends JpaRepository<CustomerIdTable, Long> {

}
