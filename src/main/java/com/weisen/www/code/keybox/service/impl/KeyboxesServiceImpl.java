package com.weisen.www.code.keybox.service.impl;

import com.weisen.www.code.keybox.service.KeyboxesService;
import com.weisen.www.code.keybox.domain.Keyboxes;
import com.weisen.www.code.keybox.repository.KeyboxesRepository;
import com.weisen.www.code.keybox.service.dto.KeyboxesDTO;
import com.weisen.www.code.keybox.service.mapper.KeyboxesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Keyboxes.
 */
@Service
@Transactional
public class KeyboxesServiceImpl implements KeyboxesService {

    private final Logger log = LoggerFactory.getLogger(KeyboxesServiceImpl.class);

    private final KeyboxesRepository keyboxesRepository;

    private final KeyboxesMapper keyboxesMapper;

    public KeyboxesServiceImpl(KeyboxesRepository keyboxesRepository, KeyboxesMapper keyboxesMapper) {
        this.keyboxesRepository = keyboxesRepository;
        this.keyboxesMapper = keyboxesMapper;
    }

    /**
     * Save a keyboxes.
     *
     * @param keyboxesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public KeyboxesDTO save(KeyboxesDTO keyboxesDTO) {
        log.debug("Request to save Keyboxes : {}", keyboxesDTO);
        Keyboxes keyboxes = keyboxesMapper.toEntity(keyboxesDTO);
        keyboxes = keyboxesRepository.save(keyboxes);
        return keyboxesMapper.toDto(keyboxes);
    }

    /**
     * Get all the keyboxes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<KeyboxesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Keyboxes");
        return keyboxesRepository.findAll(pageable)
            .map(keyboxesMapper::toDto);
    }


    /**
     * Get one keyboxes by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<KeyboxesDTO> findOne(Long id) {
        log.debug("Request to get Keyboxes : {}", id);
        return keyboxesRepository.findById(id)
            .map(keyboxesMapper::toDto);
    }

    /**
     * Delete the keyboxes by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Keyboxes : {}", id);        keyboxesRepository.deleteById(id);
    }
}
