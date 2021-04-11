package fr.polytech.info4.service.mapper;


import fr.polytech.info4.domain.*;
import fr.polytech.info4.service.dto.CompteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Compte} and its DTO {@link CompteDTO}.
 */
@Mapper(componentModel = "spring", uses = {CourseMapper.class, RolesMapper.class, CooperativeMapper.class})
public interface CompteMapper extends EntityMapper<CompteDTO, Compte> {

    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "roles.id", target = "rolesId")
    @Mapping(source = "cooperative.id", target = "cooperativeId")
    CompteDTO toDto(Compte compte);

    @Mapping(source = "courseId", target = "course")
    @Mapping(target = "paniers", ignore = true)
    @Mapping(target = "removePanier", ignore = true)
    @Mapping(source = "rolesId", target = "roles")
    @Mapping(source = "cooperativeId", target = "cooperative")
    @Mapping(target = "restaurants", ignore = true)
    @Mapping(target = "removeRestaurant", ignore = true)
    @Mapping(target = "systemePaiements", ignore = true)
    @Mapping(target = "removeSystemePaiement", ignore = true)
    Compte toEntity(CompteDTO compteDTO);

    default Compte fromId(Long id) {
        if (id == null) {
            return null;
        }
        Compte compte = new Compte();
        compte.setId(id);
        return compte;
    }
}
