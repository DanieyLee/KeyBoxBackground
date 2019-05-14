package com.weisen.www.code.keybox.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weisen.www.code.keybox.domain.Keyboxes;
import com.weisen.www.code.keybox.repository.Rewrite_001_KeyboxesRepository;
import com.weisen.www.code.keybox.repository.Rewrite_002_UsertokenRepository;
import com.weisen.www.code.keybox.repository.UserRepository;
import com.weisen.www.code.keybox.security.SecurityUtils;
import com.weisen.www.code.keybox.service.Rewrite_001_KeyboxesService;
import com.weisen.www.code.keybox.service.dto.Rewrite_001_KeyboxesDTO;
import com.weisen.www.code.keybox.service.mapper.Rewrite_001_KeyboxesMapper;
import com.weisen.www.code.keybox.web.rest.errors.InvalidLoginException;

/**
 * Service Implementation for managing Keyboxes.
 */
@Service
@Transactional
public class Rewrite_001_KeyboxesServiceImpl implements Rewrite_001_KeyboxesService {

    private final Logger log = LoggerFactory.getLogger(Rewrite_001_KeyboxesServiceImpl.class);

    private final Rewrite_001_KeyboxesRepository rewrite_001_keyboxesRepository;

    private final Rewrite_001_KeyboxesMapper rewrite_001_keyboxesMapper;
    
    private final UserRepository userRepository;
    
    private final Rewrite_002_UsertokenRepository rewrite_002_UsertokenRepository;

    public Rewrite_001_KeyboxesServiceImpl(Rewrite_002_UsertokenRepository rewrite_002_UsertokenRepository,Rewrite_001_KeyboxesRepository rewrite_001_keyboxesRepository, Rewrite_001_KeyboxesMapper rewrite_001_keyboxesMapper,UserRepository userRepository) {
        this.rewrite_001_keyboxesRepository = rewrite_001_keyboxesRepository;
        this.rewrite_001_keyboxesMapper = rewrite_001_keyboxesMapper;
        this.userRepository = userRepository;
        this.rewrite_002_UsertokenRepository = rewrite_002_UsertokenRepository;
    }
    
    private Long getUsertokenId() {
    	Long userid = SecurityUtils.getCurrentUserLogin().map(userRepository::findOneWithAuthoritiesByLogin).get().get().getId();
    	return rewrite_002_UsertokenRepository.findByUserid(userid.toString()).get().getId();
    }

    /**
     * Save a keyboxes.
     *
     * @param keyboxesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Rewrite_001_KeyboxesDTO save(Rewrite_001_KeyboxesDTO rewrite_001_KeyboxesDTO) {
        log.debug("Request to save Keyboxes : {}", rewrite_001_KeyboxesDTO);
        rewrite_001_KeyboxesDTO.setUsertokenId(getUsertokenId());
        Keyboxes keyboxes = rewrite_001_keyboxesMapper.toEntity(rewrite_001_KeyboxesDTO);
        if (null == keyboxes.getId()) {
        	keyboxes.setCreateDate(System.currentTimeMillis());
        }else {
        	Keyboxes temp = rewrite_001_keyboxesRepository.findByIdAndUsertokenId(keyboxes.getId(),rewrite_001_KeyboxesDTO.getUsertokenId()).get();
        	if (null == temp) {
        		throw new InvalidLoginException();
        	}
        }
        keyboxes = rewrite_001_keyboxesRepository.save(keyboxes);
        return rewrite_001_keyboxesMapper.toDto(keyboxes);
    }

    /**
     * Get all the keyboxes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Rewrite_001_KeyboxesDTO> findAll() {
        log.debug("Request to get all Keyboxes");
        List<Rewrite_001_KeyboxesDTO> temp = new ArrayList<Rewrite_001_KeyboxesDTO>();
        rewrite_001_keyboxesRepository.findByUsertokenId(getUsertokenId()).forEach(i->temp.add(rewrite_001_keyboxesMapper.toDto(i)));
        return temp;
    }


    /**
     * Get one keyboxes by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Rewrite_001_KeyboxesDTO> findOne(Long id) {
        log.debug("Request to get Keyboxes : {}", id);
        return rewrite_001_keyboxesRepository.findByIdAndUsertokenId(id,getUsertokenId())
            .map(rewrite_001_keyboxesMapper::toDto);
    }

    /**
     * Delete the keyboxes by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Keyboxes : {}", id);
        rewrite_001_keyboxesRepository.deleteByIdAndUsertokenId(id,getUsertokenId());
    }
}
