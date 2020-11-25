package com.rentalfilm.msaauthority.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentalfilm.msaauthority.entity.Authority;
import com.rentalfilm.msaauthority.entity.AuthorityEmunType;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

	Optional<Authority> findByAuthorityNameEnum(AuthorityEmunType authorityEmun);

}
