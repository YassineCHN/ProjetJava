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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author charl
 */
@Entity
@DiscriminatorValue("MEDECIN")
public class Z_MEDECIN extends Z_PERSONNE {

    @OneToMany(mappedBy = "leMedecin")
    private List<LigneJournal> ligneJournals;

    @ManyToOne
    private Service service;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    
    private String specialite;

    /**
     * Get the value of specialite
     *
     * @return the value of specialite
     */
    public String getSpecialite() {
        return specialite;
    }

    /**
     * Set the value of specialite
     *
     * @param specialite new value of specialite
     */
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public List<LigneJournal> getLigneJournals() {
        return ligneJournals;
    }

    public void setLigneJournals(List<LigneJournal> ligneJournals) {
        this.ligneJournals = ligneJournals;
    }

}
