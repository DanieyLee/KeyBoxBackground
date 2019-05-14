package com.weisen.www.code.keybox.web.rest;

import com.weisen.www.code.keybox.KeyboxApp;

import com.weisen.www.code.keybox.domain.Keyboxes;
import com.weisen.www.code.keybox.repository.KeyboxesRepository;
import com.weisen.www.code.keybox.service.KeyboxesService;
import com.weisen.www.code.keybox.service.dto.KeyboxesDTO;
import com.weisen.www.code.keybox.service.mapper.KeyboxesMapper;
import com.weisen.www.code.keybox.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.weisen.www.code.keybox.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the KeyboxesResource REST controller.
 *
 * @see KeyboxesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KeyboxApp.class)
public class KeyboxesResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOGIN = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORDTEXT = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORDTEXT = "BBBBBBBBBB";

    private static final String DEFAULT_LEVELPASSWORDTEXT = "AAAAAAAAAA";
    private static final String UPDATED_LEVELPASSWORDTEXT = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Long DEFAULT_CREATE_DATE = 1L;
    private static final Long UPDATED_CREATE_DATE = 2L;

    private static final String DEFAULT_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_OTHER = "BBBBBBBBBB";

    @Autowired
    private KeyboxesRepository keyboxesRepository;

    @Autowired
    private KeyboxesMapper keyboxesMapper;

    @Autowired
    private KeyboxesService keyboxesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restKeyboxesMockMvc;

    private Keyboxes keyboxes;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final KeyboxesResource keyboxesResource = new KeyboxesResource(keyboxesService);
        this.restKeyboxesMockMvc = MockMvcBuilders.standaloneSetup(keyboxesResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Keyboxes createEntity(EntityManager em) {
        Keyboxes keyboxes = new Keyboxes()
            .name(DEFAULT_NAME)
            .login(DEFAULT_LOGIN)
            .passwordtext(DEFAULT_PASSWORDTEXT)
            .levelpasswordtext(DEFAULT_LEVELPASSWORDTEXT)
            .address(DEFAULT_ADDRESS)
            .createDate(DEFAULT_CREATE_DATE)
            .other(DEFAULT_OTHER);
        return keyboxes;
    }

    @Before
    public void initTest() {
        keyboxes = createEntity(em);
    }

    @Test
    @Transactional
    public void createKeyboxes() throws Exception {
        int databaseSizeBeforeCreate = keyboxesRepository.findAll().size();

        // Create the Keyboxes
        KeyboxesDTO keyboxesDTO = keyboxesMapper.toDto(keyboxes);
        restKeyboxesMockMvc.perform(post("/api/keyboxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(keyboxesDTO)))
            .andExpect(status().isCreated());

        // Validate the Keyboxes in the database
        List<Keyboxes> keyboxesList = keyboxesRepository.findAll();
        assertThat(keyboxesList).hasSize(databaseSizeBeforeCreate + 1);
        Keyboxes testKeyboxes = keyboxesList.get(keyboxesList.size() - 1);
        assertThat(testKeyboxes.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testKeyboxes.getLogin()).isEqualTo(DEFAULT_LOGIN);
        assertThat(testKeyboxes.getPasswordtext()).isEqualTo(DEFAULT_PASSWORDTEXT);
        assertThat(testKeyboxes.getLevelpasswordtext()).isEqualTo(DEFAULT_LEVELPASSWORDTEXT);
        assertThat(testKeyboxes.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testKeyboxes.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testKeyboxes.getOther()).isEqualTo(DEFAULT_OTHER);
    }

    @Test
    @Transactional
    public void createKeyboxesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = keyboxesRepository.findAll().size();

        // Create the Keyboxes with an existing ID
        keyboxes.setId(1L);
        KeyboxesDTO keyboxesDTO = keyboxesMapper.toDto(keyboxes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKeyboxesMockMvc.perform(post("/api/keyboxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(keyboxesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Keyboxes in the database
        List<Keyboxes> keyboxesList = keyboxesRepository.findAll();
        assertThat(keyboxesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllKeyboxes() throws Exception {
        // Initialize the database
        keyboxesRepository.saveAndFlush(keyboxes);

        // Get all the keyboxesList
        restKeyboxesMockMvc.perform(get("/api/keyboxes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(keyboxes.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].login").value(hasItem(DEFAULT_LOGIN.toString())))
            .andExpect(jsonPath("$.[*].passwordtext").value(hasItem(DEFAULT_PASSWORDTEXT.toString())))
            .andExpect(jsonPath("$.[*].levelpasswordtext").value(hasItem(DEFAULT_LEVELPASSWORDTEXT.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.intValue())))
            .andExpect(jsonPath("$.[*].other").value(hasItem(DEFAULT_OTHER.toString())));
    }
    
    @Test
    @Transactional
    public void getKeyboxes() throws Exception {
        // Initialize the database
        keyboxesRepository.saveAndFlush(keyboxes);

        // Get the keyboxes
        restKeyboxesMockMvc.perform(get("/api/keyboxes/{id}", keyboxes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(keyboxes.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.login").value(DEFAULT_LOGIN.toString()))
            .andExpect(jsonPath("$.passwordtext").value(DEFAULT_PASSWORDTEXT.toString()))
            .andExpect(jsonPath("$.levelpasswordtext").value(DEFAULT_LEVELPASSWORDTEXT.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.intValue()))
            .andExpect(jsonPath("$.other").value(DEFAULT_OTHER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingKeyboxes() throws Exception {
        // Get the keyboxes
        restKeyboxesMockMvc.perform(get("/api/keyboxes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKeyboxes() throws Exception {
        // Initialize the database
        keyboxesRepository.saveAndFlush(keyboxes);

        int databaseSizeBeforeUpdate = keyboxesRepository.findAll().size();

        // Update the keyboxes
        Keyboxes updatedKeyboxes = keyboxesRepository.findById(keyboxes.getId()).get();
        // Disconnect from session so that the updates on updatedKeyboxes are not directly saved in db
        em.detach(updatedKeyboxes);
        updatedKeyboxes
            .name(UPDATED_NAME)
            .login(UPDATED_LOGIN)
            .passwordtext(UPDATED_PASSWORDTEXT)
            .levelpasswordtext(UPDATED_LEVELPASSWORDTEXT)
            .address(UPDATED_ADDRESS)
            .createDate(UPDATED_CREATE_DATE)
            .other(UPDATED_OTHER);
        KeyboxesDTO keyboxesDTO = keyboxesMapper.toDto(updatedKeyboxes);

        restKeyboxesMockMvc.perform(put("/api/keyboxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(keyboxesDTO)))
            .andExpect(status().isOk());

        // Validate the Keyboxes in the database
        List<Keyboxes> keyboxesList = keyboxesRepository.findAll();
        assertThat(keyboxesList).hasSize(databaseSizeBeforeUpdate);
        Keyboxes testKeyboxes = keyboxesList.get(keyboxesList.size() - 1);
        assertThat(testKeyboxes.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testKeyboxes.getLogin()).isEqualTo(UPDATED_LOGIN);
        assertThat(testKeyboxes.getPasswordtext()).isEqualTo(UPDATED_PASSWORDTEXT);
        assertThat(testKeyboxes.getLevelpasswordtext()).isEqualTo(UPDATED_LEVELPASSWORDTEXT);
        assertThat(testKeyboxes.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testKeyboxes.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testKeyboxes.getOther()).isEqualTo(UPDATED_OTHER);
    }

    @Test
    @Transactional
    public void updateNonExistingKeyboxes() throws Exception {
        int databaseSizeBeforeUpdate = keyboxesRepository.findAll().size();

        // Create the Keyboxes
        KeyboxesDTO keyboxesDTO = keyboxesMapper.toDto(keyboxes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKeyboxesMockMvc.perform(put("/api/keyboxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(keyboxesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Keyboxes in the database
        List<Keyboxes> keyboxesList = keyboxesRepository.findAll();
        assertThat(keyboxesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKeyboxes() throws Exception {
        // Initialize the database
        keyboxesRepository.saveAndFlush(keyboxes);

        int databaseSizeBeforeDelete = keyboxesRepository.findAll().size();

        // Delete the keyboxes
        restKeyboxesMockMvc.perform(delete("/api/keyboxes/{id}", keyboxes.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Keyboxes> keyboxesList = keyboxesRepository.findAll();
        assertThat(keyboxesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Keyboxes.class);
        Keyboxes keyboxes1 = new Keyboxes();
        keyboxes1.setId(1L);
        Keyboxes keyboxes2 = new Keyboxes();
        keyboxes2.setId(keyboxes1.getId());
        assertThat(keyboxes1).isEqualTo(keyboxes2);
        keyboxes2.setId(2L);
        assertThat(keyboxes1).isNotEqualTo(keyboxes2);
        keyboxes1.setId(null);
        assertThat(keyboxes1).isNotEqualTo(keyboxes2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(KeyboxesDTO.class);
        KeyboxesDTO keyboxesDTO1 = new KeyboxesDTO();
        keyboxesDTO1.setId(1L);
        KeyboxesDTO keyboxesDTO2 = new KeyboxesDTO();
        assertThat(keyboxesDTO1).isNotEqualTo(keyboxesDTO2);
        keyboxesDTO2.setId(keyboxesDTO1.getId());
        assertThat(keyboxesDTO1).isEqualTo(keyboxesDTO2);
        keyboxesDTO2.setId(2L);
        assertThat(keyboxesDTO1).isNotEqualTo(keyboxesDTO2);
        keyboxesDTO1.setId(null);
        assertThat(keyboxesDTO1).isNotEqualTo(keyboxesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(keyboxesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(keyboxesMapper.fromId(null)).isNull();
    }
}
