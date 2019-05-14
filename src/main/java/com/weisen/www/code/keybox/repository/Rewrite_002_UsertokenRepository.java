package com.weisen.www.code.keybox.repository;

import com.weisen.www.code.keybox.domain.User;
import com.weisen.www.code.keybox.domain.Usertoken;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Usertoken entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Rewrite_002_UsertokenRepository extends JpaRepository<Usertoken, Long> {

	Optional<Usertoken> findByUserid(String userid);
	
}
