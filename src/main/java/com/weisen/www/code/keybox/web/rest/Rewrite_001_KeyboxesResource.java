package com.weisen.www.code.keybox.web.rest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weisen.www.code.keybox.service.Rewrite_001_KeyboxesService;
import com.weisen.www.code.keybox.service.dto.Rewrite_001_KeyboxesDTO;
import com.weisen.www.code.keybox.service.dto.UserDTO;
import com.weisen.www.code.keybox.web.rest.errors.BadRequestAlertException;
import com.weisen.www.code.keybox.web.rest.util.HeaderUtil;
import com.weisen.www.code.keybox.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST controller for managing Keyboxes.
 */
@RestController
@RequestMapping("/api/rewrite")
@Api(tags={"001_密码盒子操作接口"})
public class Rewrite_001_KeyboxesResource {

    private final Logger log = LoggerFactory.getLogger(Rewrite_001_KeyboxesResource.class);

    private static final String ENTITY_NAME = "keyboxes";

    private final Rewrite_001_KeyboxesService rewrite_001_keyboxesService;

    public Rewrite_001_KeyboxesResource(Rewrite_001_KeyboxesService rewrite_001_keyboxesService) {
        this.rewrite_001_keyboxesService = rewrite_001_keyboxesService;
    }

    /**
     * POST  /keyboxes : Create a new keyboxes.
     *
     * @param keyboxesDTO the keyboxesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new keyboxesDTO, or with status 400 (Bad Request) if the keyboxes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/keyboxes")
    @ApiOperation(value="增加一个项目",notes="传入一个密码盒子的对象")
    public ResponseEntity<Rewrite_001_KeyboxesDTO> createKeyboxes(@RequestBody Rewrite_001_KeyboxesDTO rewrite_001_KeyboxesDTO) throws URISyntaxException {
        log.debug("REST request to save Keyboxes : {}", rewrite_001_KeyboxesDTO);
        if (rewrite_001_KeyboxesDTO.getId() != null) {
            throw new BadRequestAlertException("A new keyboxes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Rewrite_001_KeyboxesDTO result = rewrite_001_keyboxesService.save(rewrite_001_KeyboxesDTO);
        return ResponseEntity.created(new URI("/api/rewrite/keyboxes/" + result.getId()))
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
    @ApiOperation(value="修改一个项目",notes="传入修改后的对象")
    public ResponseEntity<Rewrite_001_KeyboxesDTO> updateKeyboxes(@RequestBody Rewrite_001_KeyboxesDTO rewrite_001_KeyboxesDTO) throws URISyntaxException {
        log.debug("REST request to update Keyboxes : {}", rewrite_001_KeyboxesDTO);
        if (rewrite_001_KeyboxesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Rewrite_001_KeyboxesDTO result = rewrite_001_keyboxesService.save(rewrite_001_KeyboxesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rewrite_001_KeyboxesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /keyboxes : get all the keyboxes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of keyboxes in body
     */
    @GetMapping("/keyboxes")
    @ApiOperation(value="查询所有项目",notes="查询当前登陆id下的所有项目")
    public ResponseEntity<List<Rewrite_001_KeyboxesDTO>> getAllKeyboxes() {
        log.debug("REST request to get a page of Keyboxes");
        List<Rewrite_001_KeyboxesDTO> rewrite_001_KeyboxesDTO = rewrite_001_keyboxesService.findAll();
        return new ResponseEntity<>(rewrite_001_KeyboxesDTO, HttpStatus.OK);
    }

    /**
     * GET  /keyboxes/:id : get the "id" keyboxes.
     *
     * @param id the id of the keyboxesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the keyboxesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/keyboxes/{id}")
    @ApiOperation(value="查询单个项目",notes="传入项目id值")
    public ResponseEntity<Rewrite_001_KeyboxesDTO> getKeyboxes(@PathVariable Long id) {
        log.debug("REST request to get Keyboxes : {}", id);
        Optional<Rewrite_001_KeyboxesDTO> rewrite_001_KeyboxesDTO = rewrite_001_keyboxesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rewrite_001_KeyboxesDTO);
    }

    /**
     * DELETE  /keyboxes/:id : delete the "id" keyboxes.
     *
     * @param id the id of the keyboxesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/keyboxes/{id}")
    @ApiOperation(value="删除某个项目",notes="传入项目id值，就会删除这个项目")
    public ResponseEntity<Void> deleteKeyboxes(@PathVariable Long id) {
        log.debug("REST request to delete Keyboxes : {}", id);
        rewrite_001_keyboxesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
