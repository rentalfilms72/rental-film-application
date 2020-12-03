package com.rentalfilm.msaadmin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalfilm.msaadmin.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {

	Optional<Admin> findByUsername(String username);

}
