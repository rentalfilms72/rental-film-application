package com.rentalfilm.msauserauthority.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentalfilm.msauserauthority.entiry.UserAuthority;
import com.rentalfilm.msauserauthority.entiry.UserAuthorityPK;

@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, UserAuthorityPK> {

}
