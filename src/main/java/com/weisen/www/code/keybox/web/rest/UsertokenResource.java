package com.weisen.www.code.keybox.web.rest;
import com.weisen.www.code.keybox.service.UsertokenService;
import com.weisen.www.code.keybox.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.keybox.web.rest.util.HeaderUtil;
import com.weisen.www.code.keybox.web.rest.util.PaginationUtil;
import com.weisen.www.code.keybox.service.dto.UsertokenDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Usertoken.
 */
@RestController
@RequestMapping("/api")
public class UsertokenResource {

    private final Logger log = LoggerFactory.getLogger(UsertokenResource.class);

    private static final String ENTITY_NAME = "usertoken";

    private final UsertokenService usertokenService;

    public UsertokenResource(UsertokenService usertokenService) {
        this.usertokenService = usertokenService;
    }

    /**
     * POST  /usertokens : Create a new usertoken.
     *
     * @param usertokenDTO the usertokenDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new usertokenDTO, or with status 400 (Bad Request) if the usertoken has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/usertokens")
    public ResponseEntity<UsertokenDTO> createUsertoken(@Valid @RequestBody UsertokenDTO usertokenDTO) throws URISyntaxException {
        log.debug("REST request to save Usertoken : {}", usertokenDTO);
        if (usertokenDTO.getId() != null) {
            throw new BadRequestAlertException("A new usertoken cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UsertokenDTO result = usertokenService.save(usertokenDTO);
        return ResponseEntity.created(new URI("/api/usertokens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /usertokens : Updates an existing usertoken.
     *
     * @param usertokenDTO the usertokenDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated usertokenDTO,
     * or with status 400 (Bad Request) if the usertokenDTO is not valid,
     * or with status 500 (Internal Server Error) if the usertokenDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/usertokens")
    public ResponseEntity<UsertokenDTO> updateUsertoken(@Valid @RequestBody UsertokenDTO usertokenDTO) throws URISyntaxException {
        log.debug("REST request to update Usertoken : {}", usertokenDTO);
        if (usertokenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UsertokenDTO result = usertokenService.save(usertokenDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, usertokenDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /usertokens : get all the usertokens.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of usertokens in body
     */
    @GetMapping("/usertokens")
    public ResponseEntity<List<UsertokenDTO>> getAllUsertokens(Pageable pageable) {
        log.debug("REST request to get a page of Usertokens");
        Page<UsertokenDTO> page = usertokenService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/usertokens");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /usertokens/:id : get the "id" usertoken.
     *
     * @param id the id of the usertokenDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the usertokenDTO, or with status 404 (Not Found)
     */
    @GetMapping("/usertokens/{id}")
    public ResponseEntity<UsertokenDTO> getUsertoken(@PathVariable Long id) {
        log.debug("REST request to get Usertoken : {}", id);
        Optional<UsertokenDTO> usertokenDTO = usertokenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(usertokenDTO);
    }

    /**
     * DELETE  /usertokens/:id : delete the "id" usertoken.
     *
     * @param id the id of the usertokenDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/usertokens/{id}")
    public ResponseEntity<Void> deleteUsertoken(@PathVariable Long id) {
        log.debug("REST request to delete Usertoken : {}", id);
        usertokenService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
