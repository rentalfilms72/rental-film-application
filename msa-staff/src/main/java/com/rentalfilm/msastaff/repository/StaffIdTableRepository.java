package com.rentalfilm.msastaff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentalfilm.msastaff.entity.StaffIdTable;

@Repository
public interface StaffIdTableRepository extends JpaRepository<StaffIdTable, Long>{

}
