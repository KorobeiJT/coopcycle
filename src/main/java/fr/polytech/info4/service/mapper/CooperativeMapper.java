package fr.polytech.info4.service.mapper;


import fr.polytech.info4.domain.*;
import fr.polytech.info4.service.dto.CooperativeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cooperative} and its DTO {@link CooperativeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CooperativeMapper extends EntityMapper<CooperativeDTO, Cooperative> {


    @Mapping(target = "comptes", ignore = true)
    @Mapping(target = "removeCompte", ignore = true)
    @Mapping(target = "restaurants", ignore = true)
    @Mapping(target = "removeRestaurant", ignore = true)
    Cooperative toEntity(CooperativeDTO cooperativeDTO);

    default Cooperative fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cooperative cooperative = new Cooperative();
        cooperative.setId(id);
        return cooperative;
    }
}
