package com.weisen.www.code.keybox.repository;

import com.weisen.www.code.keybox.domain.Keyboxes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Keyboxes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KeyboxesRepository extends JpaRepository<Keyboxes, Long> {

}
