package com.weisen.www.code.keybox.service;

import com.weisen.www.code.keybox.service.dto.KeyboxesDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Keyboxes.
 */
public interface KeyboxesService {

    /**
     * Save a keyboxes.
     *
     * @param keyboxesDTO the entity to save
     * @return the persisted entity
     */
    KeyboxesDTO save(KeyboxesDTO keyboxesDTO);

    /**
     * Get all the keyboxes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<KeyboxesDTO> findAll(Pageable pageable);


    /**
     * Get the "id" keyboxes.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<KeyboxesDTO> findOne(Long id);

    /**
     * Delete the "id" keyboxes.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
