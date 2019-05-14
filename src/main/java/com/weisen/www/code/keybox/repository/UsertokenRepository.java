package com.weisen.www.code.keybox.repository;

import com.weisen.www.code.keybox.domain.Usertoken;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Usertoken entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsertokenRepository extends JpaRepository<Usertoken, Long> {

}
