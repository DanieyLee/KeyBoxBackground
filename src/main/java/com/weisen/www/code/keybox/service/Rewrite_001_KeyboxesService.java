package com.weisen.www.code.keybox.service;

import java.util.List;
import java.util.Optional;

import com.weisen.www.code.keybox.service.dto.Rewrite_001_KeyboxesDTO;

/**
 * Service Interface for managing Keyboxes.
 */
public interface Rewrite_001_KeyboxesService {

	Rewrite_001_KeyboxesDTO save(Rewrite_001_KeyboxesDTO rewrite_001_KeyboxesDTO);

	List<Rewrite_001_KeyboxesDTO> findAll();

	Optional<Rewrite_001_KeyboxesDTO> findOne(Long id);

	void delete(Long id);
}
