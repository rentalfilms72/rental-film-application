package com.rentalfilm.msastore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentalfilm.msastore.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, String>{

	Optional<Store> findByStoreName(String storeName);

	Optional<Store> findByManagerStaffId(String managerStaffId);

}
