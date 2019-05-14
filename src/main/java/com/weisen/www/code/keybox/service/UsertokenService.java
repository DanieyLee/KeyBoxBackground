package com.weisen.www.code.keybox.service;

import com.weisen.www.code.keybox.service.dto.UsertokenDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Usertoken.
 */
public interface UsertokenService {

    /**
     * Save a usertoken.
     *
     * @param usertokenDTO the entity to save
     * @return the persisted entity
     */
    UsertokenDTO save(UsertokenDTO usertokenDTO);

    /**
     * Get all the usertokens.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UsertokenDTO> findAll(Pageable pageable);


    /**
     * Get the "id" usertoken.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UsertokenDTO> findOne(Long id);

    /**
     * Delete the "id" usertoken.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
