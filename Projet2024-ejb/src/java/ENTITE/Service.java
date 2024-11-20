/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITE;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author charl
 */
@Entity
public class Service implements Serializable {

    @OneToMany(mappedBy = "leService")
    private List<Medecin> medecins;

    @OneToMany(mappedBy = "leService")
    private List<DossierHospitalisation> dossierHospitalisations;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Service)) {
            return false;
        }
        Service other = (Service) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ENTITE.Service[ id=" + id + " ]";
    }

    private String serviceNom;

    public String getServiceNom() {
        return serviceNom;
    }

    public void setServiceNom(String serviceNom) {
        this.serviceNom = serviceNom;
    }

    private String serviceLocalisation;

    /**
     * Get the value of serviceLocalisation
     *
     * @return the value of serviceLocalisation
     */
    public String getServiceLocalisation() {
        return serviceLocalisation;
    }

    /**
     * Set the value of serviceLocalisation
     *
     * @param serviceLocalisation new value of serviceLocalisation
     */
    public void setServiceLocalisation(String serviceLocalisation) {
        this.serviceLocalisation = serviceLocalisation;
    }
    
}
