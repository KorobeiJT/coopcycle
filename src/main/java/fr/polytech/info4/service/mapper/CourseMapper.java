package fr.polytech.info4.service.mapper;


import fr.polytech.info4.domain.*;
import fr.polytech.info4.service.dto.CourseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Course} and its DTO {@link CourseDTO}.
 */
@Mapper(componentModel = "spring", uses = {PanierMapper.class})
public interface CourseMapper extends EntityMapper<CourseDTO, Course> {

    @Mapping(source = "panier.id", target = "panierId")
    CourseDTO toDto(Course course);

    @Mapping(source = "panierId", target = "panier")
    @Mapping(target = "panier", ignore = true)
    @Mapping(target = "compte", ignore = true)
    @Mapping(target = "restaurants", ignore = true)
    @Mapping(target = "removeRestaurant", ignore = true)
    Course toEntity(CourseDTO courseDTO);

    default Course fromId(Long id) {
        if (id == null) {
            return null;
        }
        Course course = new Course();
        course.setId(id);
        return course;
    }
}
