package fr.polytech.info4.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link fr.polytech.info4.domain.Restaurant} entity.
 */
public class RestaurantDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String adress;

    private String products;

    private Set<CompteDTO> comptes = new HashSet<>();
    private Set<CourseDTO> courses = new HashSet<>();

    private Long cooperativeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public Set<CompteDTO> getComptes() {
        return comptes;
    }

    public void setComptes(Set<CompteDTO> comptes) {
        this.comptes = comptes;
    }

    public Set<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseDTO> courses) {
        this.courses = courses;
    }

    public Long getCooperativeId() {
        return cooperativeId;
    }

    public void setCooperativeId(Long cooperativeId) {
        this.cooperativeId = cooperativeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RestaurantDTO)) {
            return false;
        }

        return id != null && id.equals(((RestaurantDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RestaurantDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", adress='" + getAdress() + "'" +
            ", products='" + getProducts() + "'" +
            ", comptes='" + getComptes() + "'" +
            ", courses='" + getCourses() + "'" +
            ", cooperativeId=" + getCooperativeId() +
            "}";
    }
}
