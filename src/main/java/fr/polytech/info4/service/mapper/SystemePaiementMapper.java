package fr.polytech.info4.service.mapper;


import fr.polytech.info4.domain.*;
import fr.polytech.info4.service.dto.SystemePaiementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SystemePaiement} and its DTO {@link SystemePaiementDTO}.
 */
@Mapper(componentModel = "spring", uses = {CompteMapper.class})
public interface SystemePaiementMapper extends EntityMapper<SystemePaiementDTO, SystemePaiement> {


    @Mapping(target = "removeCompte", ignore = true)

    default SystemePaiement fromId(Long id) {
        if (id == null) {
            return null;
        }
        SystemePaiement systemePaiement = new SystemePaiement();
        systemePaiement.setId(id);
        return systemePaiement;
    }
}
