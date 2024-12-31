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
public class Patient extends Utilisateur implements Serializable {

//    @OneToMany(mappedBy = "lePatient")
//    private List<DossierHospitalisation> dossierHospitalisations;

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
        if (!(object instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ENTITE.Patient[ id=" + id + " ]";
    }

    private String patientNom;

    public String getPatientNom() {
        return patientNom;
    }

    public void setPatientNom(String patientNom) {
        this.patientNom = patientNom;
    }

    private String numeroSSPatient;

    public String getNumeroSSPatient() {
        return numeroSSPatient;
    }

    public void setNumeroSSPatient(String numeroSSPatient) {
        this.numeroSSPatient = numeroSSPatient;
    }

    private String patientAdresse;

    public String getPatientAdresse() {
        return patientAdresse;
    }

    public void setPatientAdresse(String patientAdresse) {
        this.patientAdresse = patientAdresse;
    }

}
