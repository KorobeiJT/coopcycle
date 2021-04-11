package fr.polytech.info4.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link fr.polytech.info4.domain.SystemePaiement} entity.
 */
public class SystemePaiementDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String crediteur;

    @NotNull
    private String debiteur;

    @NotNull
    private String method;

    private Set<CompteDTO> comptes = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrediteur() {
        return crediteur;
    }

    public void setCrediteur(String crediteur) {
        this.crediteur = crediteur;
    }

    public String getDebiteur() {
        return debiteur;
    }

    public void setDebiteur(String debiteur) {
        this.debiteur = debiteur;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Set<CompteDTO> getComptes() {
        return comptes;
    }

    public void setComptes(Set<CompteDTO> comptes) {
        this.comptes = comptes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SystemePaiementDTO)) {
            return false;
        }

        return id != null && id.equals(((SystemePaiementDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SystemePaiementDTO{" +
            "id=" + getId() +
            ", crediteur='" + getCrediteur() + "'" +
            ", debiteur='" + getDebiteur() + "'" +
            ", method='" + getMethod() + "'" +
            ", comptes='" + getComptes() + "'" +
            "}";
    }
}
