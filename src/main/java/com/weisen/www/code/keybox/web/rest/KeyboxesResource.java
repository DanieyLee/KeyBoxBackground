package com.weisen.www.code.keybox.web.rest;
import com.weisen.www.code.keybox.service.KeyboxesService;
import com.weisen.www.code.keybox.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.keybox.web.rest.util.HeaderUtil;
import com.weisen.www.code.keybox.web.rest.util.PaginationUtil;
import com.weisen.www.code.keybox.service.dto.KeyboxesDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Keyboxes.
 */
@RestController
@RequestMapping("/api")
public class KeyboxesResource {

    private final Logger log = LoggerFactory.getLogger(KeyboxesResource.class);

    private static final String ENTITY_NAME = "keyboxes";

    private final KeyboxesService keyboxesService;

    public KeyboxesResource(KeyboxesService keyboxesService) {
        this.keyboxesService = keyboxesService;
    }

    /**
     * POST  /keyboxes : Create a new keyboxes.
     *
     * @param keyboxesDTO the keyboxesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new keyboxesDTO, or with status 400 (Bad Request) if the keyboxes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/keyboxes")
    public ResponseEntity<KeyboxesDTO> createKeyboxes(@RequestBody KeyboxesDTO keyboxesDTO) throws URISyntaxException {
        log.debug("REST request to save Keyboxes : {}", keyboxesDTO);
        if (keyboxesDTO.getId() != null) {
            throw new BadRequestAlertException("A new keyboxes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KeyboxesDTO result = keyboxesService.save(keyboxesDTO);
        return ResponseEntity.created(new URI("/api/keyboxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /keyboxes : Updates an existing keyboxes.
     *
     * @param keyboxesDTO the keyboxesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated keyboxesDTO,
     * or with status 400 (Bad Request) if the keyboxesDTO is not valid,
     * or with status 500 (Internal Server Error) if the keyboxesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/keyboxes")
    public ResponseEntity<KeyboxesDTO> updateKeyboxes(@RequestBody KeyboxesDTO keyboxesDTO) throws URISyntaxException {
        log.debug("REST request to update Keyboxes : {}", keyboxesDTO);
        if (keyboxesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KeyboxesDTO result = keyboxesService.save(keyboxesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, keyboxesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /keyboxes : get all the keyboxes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of keyboxes in body
     */
    @GetMapping("/keyboxes")
    public ResponseEntity<List<KeyboxesDTO>> getAllKeyboxes(Pageable pageable) {
        log.debug("REST request to get a page of Keyboxes");
        Page<KeyboxesDTO> page = keyboxesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/keyboxes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /keyboxes/:id : get the "id" keyboxes.
     *
     * @param id the id of the keyboxesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the keyboxesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/keyboxes/{id}")
    public ResponseEntity<KeyboxesDTO> getKeyboxes(@PathVariable Long id) {
        log.debug("REST request to get Keyboxes : {}", id);
        Optional<KeyboxesDTO> keyboxesDTO = keyboxesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(keyboxesDTO);
    }

    /**
     * DELETE  /keyboxes/:id : delete the "id" keyboxes.
     *
     * @param id the id of the keyboxesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/keyboxes/{id}")
    public ResponseEntity<Void> deleteKeyboxes(@PathVariable Long id) {
        log.debug("REST request to delete Keyboxes : {}", id);
        keyboxesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
