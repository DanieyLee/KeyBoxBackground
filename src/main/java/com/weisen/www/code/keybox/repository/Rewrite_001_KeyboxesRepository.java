package com.weisen.www.code.keybox.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weisen.www.code.keybox.domain.Keyboxes;


/**
 * Spring Data  repository for the Keyboxes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Rewrite_001_KeyboxesRepository extends JpaRepository<Keyboxes, Long> {
	
	Optional<Keyboxes> findByIdAndUsertokenId(Long id,Long usertokenId);
	
	List<Keyboxes> findByUsertokenId(Long usertokenId);
	
	void deleteByIdAndUsertokenId(Long id,Long usertokenId);
	
}
