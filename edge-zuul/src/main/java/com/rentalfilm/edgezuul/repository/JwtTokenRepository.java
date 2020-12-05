package com.rentalfilm.edgezuul.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentalfilm.edgezuul.entity.JwtToken;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken, String> {
}
