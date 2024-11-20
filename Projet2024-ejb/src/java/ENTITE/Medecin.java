/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITE;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author charl
 */
@Entity
public class Medecin extends Utilisateur implements Serializable {

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
        if (!(object instanceof Medecin)) {
            return false;
        }
        Medecin other = (Medecin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ENTITE.Medecin[ id=" + id + " ]";
    }

    private String medecinNom;

    public String getMedecinNom() {
        return medecinNom;
    }

    public void setMedecinNom(String medecinNom) {
        this.medecinNom = medecinNom;
    }

    private String medecinSpecialite;

    public String getMedecinSpecialite() {
        return medecinSpecialite;
    }

    public void setMedecinSpecialite(String medecinSpecialite) {
        this.medecinSpecialite = medecinSpecialite;
    }

//    @ManyToOne
    @ManyToOne
        private Service leService;

    public Service getLeService() {
        return leService;
    }

    public void setLeService(Service leService) {
        this.leService = leService;
    }

    
}
