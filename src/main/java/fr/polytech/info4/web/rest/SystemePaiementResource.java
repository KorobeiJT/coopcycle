package fr.polytech.info4.web.rest;

import fr.polytech.info4.service.SystemePaiementService;
import fr.polytech.info4.web.rest.errors.BadRequestAlertException;
import fr.polytech.info4.service.dto.SystemePaiementDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.polytech.info4.domain.SystemePaiement}.
 */
@RestController
@RequestMapping("/api")
public class SystemePaiementResource {

    private final Logger log = LoggerFactory.getLogger(SystemePaiementResource.class);

    private static final String ENTITY_NAME = "systemePaiement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SystemePaiementService systemePaiementService;

    public SystemePaiementResource(SystemePaiementService systemePaiementService) {
        this.systemePaiementService = systemePaiementService;
    }

    /**
     * {@code POST  /systeme-paiements} : Create a new systemePaiement.
     *
     * @param systemePaiementDTO the systemePaiementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new systemePaiementDTO, or with status {@code 400 (Bad Request)} if the systemePaiement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/systeme-paiements")
    public ResponseEntity<SystemePaiementDTO> createSystemePaiement(@Valid @RequestBody SystemePaiementDTO systemePaiementDTO) throws URISyntaxException {
        log.debug("REST request to save SystemePaiement : {}", systemePaiementDTO);
        if (systemePaiementDTO.getId() != null) {
            throw new BadRequestAlertException("A new systemePaiement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SystemePaiementDTO result = systemePaiementService.save(systemePaiementDTO);
        return ResponseEntity.created(new URI("/api/systeme-paiements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /systeme-paiements} : Updates an existing systemePaiement.
     *
     * @param systemePaiementDTO the systemePaiementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated systemePaiementDTO,
     * or with status {@code 400 (Bad Request)} if the systemePaiementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the systemePaiementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/systeme-paiements")
    public ResponseEntity<SystemePaiementDTO> updateSystemePaiement(@Valid @RequestBody SystemePaiementDTO systemePaiementDTO) throws URISyntaxException {
        log.debug("REST request to update SystemePaiement : {}", systemePaiementDTO);
        if (systemePaiementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SystemePaiementDTO result = systemePaiementService.save(systemePaiementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, systemePaiementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /systeme-paiements} : get all the systemePaiements.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of systemePaiements in body.
     */
    @GetMapping("/systeme-paiements")
    public List<SystemePaiementDTO> getAllSystemePaiements(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all SystemePaiements");
        return systemePaiementService.findAll();
    }

    /**
     * {@code GET  /systeme-paiements/:id} : get the "id" systemePaiement.
     *
     * @param id the id of the systemePaiementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the systemePaiementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/systeme-paiements/{id}")
    public ResponseEntity<SystemePaiementDTO> getSystemePaiement(@PathVariable Long id) {
        log.debug("REST request to get SystemePaiement : {}", id);
        Optional<SystemePaiementDTO> systemePaiementDTO = systemePaiementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(systemePaiementDTO);
    }

    /**
     * {@code DELETE  /systeme-paiements/:id} : delete the "id" systemePaiement.
     *
     * @param id the id of the systemePaiementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/systeme-paiements/{id}")
    public ResponseEntity<Void> deleteSystemePaiement(@PathVariable Long id) {
        log.debug("REST request to delete SystemePaiement : {}", id);
        systemePaiementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
