package fr.polytech.info4.service;

import fr.polytech.info4.domain.SystemePaiement;
import fr.polytech.info4.repository.SystemePaiementRepository;
import fr.polytech.info4.service.dto.SystemePaiementDTO;
import fr.polytech.info4.service.mapper.SystemePaiementMapper;
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

/**
 * Service Implementation for managing {@link SystemePaiement}.
 */
@Service
@Transactional
public class SystemePaiementService {

    private final Logger log = LoggerFactory.getLogger(SystemePaiementService.class);

    private final SystemePaiementRepository systemePaiementRepository;

    private final SystemePaiementMapper systemePaiementMapper;

    public SystemePaiementService(SystemePaiementRepository systemePaiementRepository, SystemePaiementMapper systemePaiementMapper) {
        this.systemePaiementRepository = systemePaiementRepository;
        this.systemePaiementMapper = systemePaiementMapper;
    }

    /**
     * Save a systemePaiement.
     *
     * @param systemePaiementDTO the entity to save.
     * @return the persisted entity.
     */
    public SystemePaiementDTO save(SystemePaiementDTO systemePaiementDTO) {
        log.debug("Request to save SystemePaiement : {}", systemePaiementDTO);
        SystemePaiement systemePaiement = systemePaiementMapper.toEntity(systemePaiementDTO);
        systemePaiement = systemePaiementRepository.save(systemePaiement);
        return systemePaiementMapper.toDto(systemePaiement);
    }

    /**
     * Get all the systemePaiements.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SystemePaiementDTO> findAll() {
        log.debug("Request to get all SystemePaiements");
        return systemePaiementRepository.findAllWithEagerRelationships().stream()
            .map(systemePaiementMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get all the systemePaiements with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<SystemePaiementDTO> findAllWithEagerRelationships(Pageable pageable) {
        return systemePaiementRepository.findAllWithEagerRelationships(pageable).map(systemePaiementMapper::toDto);
    }

    /**
     * Get one systemePaiement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SystemePaiementDTO> findOne(Long id) {
        log.debug("Request to get SystemePaiement : {}", id);
        return systemePaiementRepository.findOneWithEagerRelationships(id)
            .map(systemePaiementMapper::toDto);
    }

    /**
     * Delete the systemePaiement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SystemePaiement : {}", id);
        systemePaiementRepository.deleteById(id);
    }
}
