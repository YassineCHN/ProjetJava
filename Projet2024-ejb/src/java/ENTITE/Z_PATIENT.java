/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITE;

import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorValue;
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
@DiscriminatorValue("PATIENT")
public class Z_PATIENT extends Z_PERSONNE{

    @OneToMany(mappedBy = "lePatient")
    private List<DossierHospitalisation> dossierHospitalisations;

    
    private String numSecuSoc;

    public String getNumSecuSoc() {
        return numSecuSoc;
    }

    public void setNumSecuSoc(String numSecuSoc) {
        this.numSecuSoc = numSecuSoc;
    }

        private String nomMutuelle;

    public String getNomMutuelle() {
        return nomMutuelle;
    }

    public void setNomMutuelle(String nomMutuelle) {
        this.nomMutuelle = nomMutuelle;
    }

        private String adresseMutuelle;

    public String getAdresseMutuelle() {
        return adresseMutuelle;
    }

    public void setAdresseMutuelle(String adresseMutuelle) {
        this.adresseMutuelle = adresseMutuelle;
    }

}
