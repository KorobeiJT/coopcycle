package fr.polytech.info4.service.mapper;


import fr.polytech.info4.domain.*;
import fr.polytech.info4.service.dto.PanierDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Panier} and its DTO {@link PanierDTO}.
 */
@Mapper(componentModel = "spring", uses = {CourseMapper.class, ProduitMapper.class, CompteMapper.class})
public interface PanierMapper extends EntityMapper<PanierDTO, Panier> {

    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "compte.id", target = "compteId")
    PanierDTO toDto(Panier panier);

    @Mapping(source = "courseId", target = "course")
    @Mapping(target = "removeProduit", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(source = "compteId", target = "compte")
    Panier toEntity(PanierDTO panierDTO);

    default Panier fromId(Long id) {
        if (id == null) {
            return null;
        }
        Panier panier = new Panier();
        panier.setId(id);
        return panier;
    }
}
