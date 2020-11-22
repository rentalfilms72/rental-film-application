package com.rentalfilm.msauser.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalfilm.msauser.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByEmail(String email);

	Optional<User> findByUsername(String username);

}
