package com.weisen.www.code.keybox.web.rest;

import com.weisen.www.code.keybox.KeyboxApp;

import com.weisen.www.code.keybox.domain.Usertoken;
import com.weisen.www.code.keybox.repository.UsertokenRepository;
import com.weisen.www.code.keybox.service.UsertokenService;
import com.weisen.www.code.keybox.service.dto.UsertokenDTO;
import com.weisen.www.code.keybox.service.mapper.UsertokenMapper;
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
 * Test class for the UsertokenResource REST controller.
 *
 * @see UsertokenResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KeyboxApp.class)
public class UsertokenResourceIntTest {

    private static final String DEFAULT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_USERID = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final Long DEFAULT_END_DATE = 1L;
    private static final Long UPDATED_END_DATE = 2L;

    @Autowired
    private UsertokenRepository usertokenRepository;

    @Autowired
    private UsertokenMapper usertokenMapper;

    @Autowired
    private UsertokenService usertokenService;

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

    private MockMvc restUsertokenMockMvc;

    private Usertoken usertoken;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UsertokenResource usertokenResource = new UsertokenResource(usertokenService);
        this.restUsertokenMockMvc = MockMvcBuilders.standaloneSetup(usertokenResource)
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
    public static Usertoken createEntity(EntityManager em) {
        Usertoken usertoken = new Usertoken()
            .userid(DEFAULT_USERID)
            .state(DEFAULT_STATE)
            .endDate(DEFAULT_END_DATE);
        return usertoken;
    }

    @Before
    public void initTest() {
        usertoken = createEntity(em);
    }

    @Test
    @Transactional
    public void createUsertoken() throws Exception {
        int databaseSizeBeforeCreate = usertokenRepository.findAll().size();

        // Create the Usertoken
        UsertokenDTO usertokenDTO = usertokenMapper.toDto(usertoken);
        restUsertokenMockMvc.perform(post("/api/usertokens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usertokenDTO)))
            .andExpect(status().isCreated());

        // Validate the Usertoken in the database
        List<Usertoken> usertokenList = usertokenRepository.findAll();
        assertThat(usertokenList).hasSize(databaseSizeBeforeCreate + 1);
        Usertoken testUsertoken = usertokenList.get(usertokenList.size() - 1);
        assertThat(testUsertoken.getUserid()).isEqualTo(DEFAULT_USERID);
        assertThat(testUsertoken.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testUsertoken.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    }

    @Test
    @Transactional
    public void createUsertokenWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = usertokenRepository.findAll().size();

        // Create the Usertoken with an existing ID
        usertoken.setId(1L);
        UsertokenDTO usertokenDTO = usertokenMapper.toDto(usertoken);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUsertokenMockMvc.perform(post("/api/usertokens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usertokenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Usertoken in the database
        List<Usertoken> usertokenList = usertokenRepository.findAll();
        assertThat(usertokenList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUseridIsRequired() throws Exception {
        int databaseSizeBeforeTest = usertokenRepository.findAll().size();
        // set the field null
        usertoken.setUserid(null);

        // Create the Usertoken, which fails.
        UsertokenDTO usertokenDTO = usertokenMapper.toDto(usertoken);

        restUsertokenMockMvc.perform(post("/api/usertokens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usertokenDTO)))
            .andExpect(status().isBadRequest());

        List<Usertoken> usertokenList = usertokenRepository.findAll();
        assertThat(usertokenList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUsertokens() throws Exception {
        // Initialize the database
        usertokenRepository.saveAndFlush(usertoken);

        // Get all the usertokenList
        restUsertokenMockMvc.perform(get("/api/usertokens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(usertoken.getId().intValue())))
            .andExpect(jsonPath("$.[*].userid").value(hasItem(DEFAULT_USERID.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.intValue())));
    }
    
    @Test
    @Transactional
    public void getUsertoken() throws Exception {
        // Initialize the database
        usertokenRepository.saveAndFlush(usertoken);

        // Get the usertoken
        restUsertokenMockMvc.perform(get("/api/usertokens/{id}", usertoken.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(usertoken.getId().intValue()))
            .andExpect(jsonPath("$.userid").value(DEFAULT_USERID.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUsertoken() throws Exception {
        // Get the usertoken
        restUsertokenMockMvc.perform(get("/api/usertokens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUsertoken() throws Exception {
        // Initialize the database
        usertokenRepository.saveAndFlush(usertoken);

        int databaseSizeBeforeUpdate = usertokenRepository.findAll().size();

        // Update the usertoken
        Usertoken updatedUsertoken = usertokenRepository.findById(usertoken.getId()).get();
        // Disconnect from session so that the updates on updatedUsertoken are not directly saved in db
        em.detach(updatedUsertoken);
        updatedUsertoken
            .userid(UPDATED_USERID)
            .state(UPDATED_STATE)
            .endDate(UPDATED_END_DATE);
        UsertokenDTO usertokenDTO = usertokenMapper.toDto(updatedUsertoken);

        restUsertokenMockMvc.perform(put("/api/usertokens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usertokenDTO)))
            .andExpect(status().isOk());

        // Validate the Usertoken in the database
        List<Usertoken> usertokenList = usertokenRepository.findAll();
        assertThat(usertokenList).hasSize(databaseSizeBeforeUpdate);
        Usertoken testUsertoken = usertokenList.get(usertokenList.size() - 1);
        assertThat(testUsertoken.getUserid()).isEqualTo(UPDATED_USERID);
        assertThat(testUsertoken.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testUsertoken.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingUsertoken() throws Exception {
        int databaseSizeBeforeUpdate = usertokenRepository.findAll().size();

        // Create the Usertoken
        UsertokenDTO usertokenDTO = usertokenMapper.toDto(usertoken);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUsertokenMockMvc.perform(put("/api/usertokens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usertokenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Usertoken in the database
        List<Usertoken> usertokenList = usertokenRepository.findAll();
        assertThat(usertokenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUsertoken() throws Exception {
        // Initialize the database
        usertokenRepository.saveAndFlush(usertoken);

        int databaseSizeBeforeDelete = usertokenRepository.findAll().size();

        // Delete the usertoken
        restUsertokenMockMvc.perform(delete("/api/usertokens/{id}", usertoken.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Usertoken> usertokenList = usertokenRepository.findAll();
        assertThat(usertokenList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Usertoken.class);
        Usertoken usertoken1 = new Usertoken();
        usertoken1.setId(1L);
        Usertoken usertoken2 = new Usertoken();
        usertoken2.setId(usertoken1.getId());
        assertThat(usertoken1).isEqualTo(usertoken2);
        usertoken2.setId(2L);
        assertThat(usertoken1).isNotEqualTo(usertoken2);
        usertoken1.setId(null);
        assertThat(usertoken1).isNotEqualTo(usertoken2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UsertokenDTO.class);
        UsertokenDTO usertokenDTO1 = new UsertokenDTO();
        usertokenDTO1.setId(1L);
        UsertokenDTO usertokenDTO2 = new UsertokenDTO();
        assertThat(usertokenDTO1).isNotEqualTo(usertokenDTO2);
        usertokenDTO2.setId(usertokenDTO1.getId());
        assertThat(usertokenDTO1).isEqualTo(usertokenDTO2);
        usertokenDTO2.setId(2L);
        assertThat(usertokenDTO1).isNotEqualTo(usertokenDTO2);
        usertokenDTO1.setId(null);
        assertThat(usertokenDTO1).isNotEqualTo(usertokenDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(usertokenMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(usertokenMapper.fromId(null)).isNull();
    }
}
