package com.weisen.www.code.keybox.service.impl;

import com.weisen.www.code.keybox.service.UsertokenService;
import com.weisen.www.code.keybox.domain.Usertoken;
import com.weisen.www.code.keybox.repository.UsertokenRepository;
import com.weisen.www.code.keybox.service.dto.UsertokenDTO;
import com.weisen.www.code.keybox.service.mapper.UsertokenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Usertoken.
 */
@Service
@Transactional
public class UsertokenServiceImpl implements UsertokenService {

    private final Logger log = LoggerFactory.getLogger(UsertokenServiceImpl.class);

    private final UsertokenRepository usertokenRepository;

    private final UsertokenMapper usertokenMapper;

    public UsertokenServiceImpl(UsertokenRepository usertokenRepository, UsertokenMapper usertokenMapper) {
        this.usertokenRepository = usertokenRepository;
        this.usertokenMapper = usertokenMapper;
    }

    /**
     * Save a usertoken.
     *
     * @param usertokenDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UsertokenDTO save(UsertokenDTO usertokenDTO) {
        log.debug("Request to save Usertoken : {}", usertokenDTO);
        Usertoken usertoken = usertokenMapper.toEntity(usertokenDTO);
        usertoken = usertokenRepository.save(usertoken);
        return usertokenMapper.toDto(usertoken);
    }

    /**
     * Get all the usertokens.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UsertokenDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Usertokens");
        return usertokenRepository.findAll(pageable)
            .map(usertokenMapper::toDto);
    }


    /**
     * Get one usertoken by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UsertokenDTO> findOne(Long id) {
        log.debug("Request to get Usertoken : {}", id);
        return usertokenRepository.findById(id)
            .map(usertokenMapper::toDto);
    }

    /**
     * Delete the usertoken by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Usertoken : {}", id);        usertokenRepository.deleteById(id);
    }
}
