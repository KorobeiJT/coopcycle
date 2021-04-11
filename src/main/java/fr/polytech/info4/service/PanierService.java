package fr.polytech.info4.service;

import fr.polytech.info4.domain.Panier;
import fr.polytech.info4.repository.PanierRepository;
import fr.polytech.info4.service.dto.PanierDTO;
import fr.polytech.info4.service.mapper.PanierMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Panier}.
 */
@Service
@Transactional
public class PanierService {

    private final Logger log = LoggerFactory.getLogger(PanierService.class);

    private final PanierRepository panierRepository;

    private final PanierMapper panierMapper;

    public PanierService(PanierRepository panierRepository, PanierMapper panierMapper) {
        this.panierRepository = panierRepository;
        this.panierMapper = panierMapper;
    }

    /**
     * Save a panier.
     *
     * @param panierDTO the entity to save.
     * @return the persisted entity.
     */
    public PanierDTO save(PanierDTO panierDTO) {
        log.debug("Request to save Panier : {}", panierDTO);
        Panier panier = panierMapper.toEntity(panierDTO);
        panier = panierRepository.save(panier);
        return panierMapper.toDto(panier);
    }

    /**
     * Get all the paniers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PanierDTO> findAll() {
        log.debug("Request to get all Paniers");
        return panierRepository.findAllWithEagerRelationships().stream()
            .map(panierMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get all the paniers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<PanierDTO> findAllWithEagerRelationships(Pageable pageable) {
        return panierRepository.findAllWithEagerRelationships(pageable).map(panierMapper::toDto);
    }


    /**
     *  Get all the paniers where Course is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<PanierDTO> findAllWhereCourseIsNull() {
        log.debug("Request to get all paniers where Course is null");
        return StreamSupport
            .stream(panierRepository.findAll().spliterator(), false)
            .filter(panier -> panier.getCourse() == null)
            .map(panierMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one panier by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PanierDTO> findOne(Long id) {
        log.debug("Request to get Panier : {}", id);
        return panierRepository.findOneWithEagerRelationships(id)
            .map(panierMapper::toDto);
    }

    /**
     * Delete the panier by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Panier : {}", id);
        panierRepository.deleteById(id);
    }
}
