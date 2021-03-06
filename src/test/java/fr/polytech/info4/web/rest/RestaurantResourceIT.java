package fr.polytech.info4.web.rest;

import fr.polytech.info4.DmApp;
import fr.polytech.info4.domain.Restaurant;
import fr.polytech.info4.repository.RestaurantRepository;
import fr.polytech.info4.service.RestaurantService;
import fr.polytech.info4.service.dto.RestaurantDTO;
import fr.polytech.info4.service.mapper.RestaurantMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link RestaurantResource} REST controller.
 */
@SpringBootTest(classes = DmApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class RestaurantResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCTS = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCTS = "BBBBBBBBBB";

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Mock
    private RestaurantRepository restaurantRepositoryMock;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Mock
    private RestaurantService restaurantServiceMock;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRestaurantMockMvc;

    private Restaurant restaurant;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Restaurant createEntity(EntityManager em) {
        Restaurant restaurant = new Restaurant()
            .name(DEFAULT_NAME)
            .adress(DEFAULT_ADRESS)
            .products(DEFAULT_PRODUCTS);
        return restaurant;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Restaurant createUpdatedEntity(EntityManager em) {
        Restaurant restaurant = new Restaurant()
            .name(UPDATED_NAME)
            .adress(UPDATED_ADRESS)
            .products(UPDATED_PRODUCTS);
        return restaurant;
    }

    @BeforeEach
    public void initTest() {
        restaurant = createEntity(em);
    }

    @Test
    @Transactional
    public void createRestaurant() throws Exception {
        int databaseSizeBeforeCreate = restaurantRepository.findAll().size();
        // Create the Restaurant
        RestaurantDTO restaurantDTO = restaurantMapper.toDto(restaurant);
        restRestaurantMockMvc.perform(post("/api/restaurants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(restaurantDTO)))
            .andExpect(status().isCreated());

        // Validate the Restaurant in the database
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        assertThat(restaurantList).hasSize(databaseSizeBeforeCreate + 1);
        Restaurant testRestaurant = restaurantList.get(restaurantList.size() - 1);
        assertThat(testRestaurant.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRestaurant.getAdress()).isEqualTo(DEFAULT_ADRESS);
        assertThat(testRestaurant.getProducts()).isEqualTo(DEFAULT_PRODUCTS);
    }

    @Test
    @Transactional
    public void createRestaurantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = restaurantRepository.findAll().size();

        // Create the Restaurant with an existing ID
        restaurant.setId(1L);
        RestaurantDTO restaurantDTO = restaurantMapper.toDto(restaurant);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRestaurantMockMvc.perform(post("/api/restaurants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(restaurantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Restaurant in the database
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        assertThat(restaurantList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = restaurantRepository.findAll().size();
        // set the field null
        restaurant.setName(null);

        // Create the Restaurant, which fails.
        RestaurantDTO restaurantDTO = restaurantMapper.toDto(restaurant);


        restRestaurantMockMvc.perform(post("/api/restaurants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(restaurantDTO)))
            .andExpect(status().isBadRequest());

        List<Restaurant> restaurantList = restaurantRepository.findAll();
        assertThat(restaurantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAdressIsRequired() throws Exception {
        int databaseSizeBeforeTest = restaurantRepository.findAll().size();
        // set the field null
        restaurant.setAdress(null);

        // Create the Restaurant, which fails.
        RestaurantDTO restaurantDTO = restaurantMapper.toDto(restaurant);


        restRestaurantMockMvc.perform(post("/api/restaurants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(restaurantDTO)))
            .andExpect(status().isBadRequest());

        List<Restaurant> restaurantList = restaurantRepository.findAll();
        assertThat(restaurantList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRestaurants() throws Exception {
        // Initialize the database
        restaurantRepository.saveAndFlush(restaurant);

        // Get all the restaurantList
        restRestaurantMockMvc.perform(get("/api/restaurants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(restaurant.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].adress").value(hasItem(DEFAULT_ADRESS)))
            .andExpect(jsonPath("$.[*].products").value(hasItem(DEFAULT_PRODUCTS)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllRestaurantsWithEagerRelationshipsIsEnabled() throws Exception {
        when(restaurantServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restRestaurantMockMvc.perform(get("/api/restaurants?eagerload=true"))
            .andExpect(status().isOk());

        verify(restaurantServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllRestaurantsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(restaurantServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restRestaurantMockMvc.perform(get("/api/restaurants?eagerload=true"))
            .andExpect(status().isOk());

        verify(restaurantServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getRestaurant() throws Exception {
        // Initialize the database
        restaurantRepository.saveAndFlush(restaurant);

        // Get the restaurant
        restRestaurantMockMvc.perform(get("/api/restaurants/{id}", restaurant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(restaurant.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.adress").value(DEFAULT_ADRESS))
            .andExpect(jsonPath("$.products").value(DEFAULT_PRODUCTS));
    }
    @Test
    @Transactional
    public void getNonExistingRestaurant() throws Exception {
        // Get the restaurant
        restRestaurantMockMvc.perform(get("/api/restaurants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRestaurant() throws Exception {
        // Initialize the database
        restaurantRepository.saveAndFlush(restaurant);

        int databaseSizeBeforeUpdate = restaurantRepository.findAll().size();

        // Update the restaurant
        Restaurant updatedRestaurant = restaurantRepository.findById(restaurant.getId()).get();
        // Disconnect from session so that the updates on updatedRestaurant are not directly saved in db
        em.detach(updatedRestaurant);
        updatedRestaurant
            .name(UPDATED_NAME)
            .adress(UPDATED_ADRESS)
            .products(UPDATED_PRODUCTS);
        RestaurantDTO restaurantDTO = restaurantMapper.toDto(updatedRestaurant);

        restRestaurantMockMvc.perform(put("/api/restaurants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(restaurantDTO)))
            .andExpect(status().isOk());

        // Validate the Restaurant in the database
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        assertThat(restaurantList).hasSize(databaseSizeBeforeUpdate);
        Restaurant testRestaurant = restaurantList.get(restaurantList.size() - 1);
        assertThat(testRestaurant.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRestaurant.getAdress()).isEqualTo(UPDATED_ADRESS);
        assertThat(testRestaurant.getProducts()).isEqualTo(UPDATED_PRODUCTS);
    }

    @Test
    @Transactional
    public void updateNonExistingRestaurant() throws Exception {
        int databaseSizeBeforeUpdate = restaurantRepository.findAll().size();

        // Create the Restaurant
        RestaurantDTO restaurantDTO = restaurantMapper.toDto(restaurant);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRestaurantMockMvc.perform(put("/api/restaurants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(restaurantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Restaurant in the database
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        assertThat(restaurantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRestaurant() throws Exception {
        // Initialize the database
        restaurantRepository.saveAndFlush(restaurant);

        int databaseSizeBeforeDelete = restaurantRepository.findAll().size();

        // Delete the restaurant
        restRestaurantMockMvc.perform(delete("/api/restaurants/{id}", restaurant.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        assertThat(restaurantList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
