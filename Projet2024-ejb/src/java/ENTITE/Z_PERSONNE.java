/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITE;

import java.io.Serializable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author ychen
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) 
    @DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)
public class Z_PERSONNE implements Serializable {

    private String nomPersonne;
    private String prenomPersonne;
    private String AdressePersonne;
    
    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idpers;

    public Long getIdpers() {
        return idpers;
    }

    public void setIdpers(Long idpers) {
        this.idpers = idpers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpers != null ? idpers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Z_PERSONNE)) {
            return false;
        }
        Z_PERSONNE other = (Z_PERSONNE) object;
        if ((this.idpers == null && other.idpers != null) || (this.idpers != null && !this.idpers.equals(other.idpers))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "ENTITE.Z_PERSONNE[ idpers=" + idpers + " ]";
    }
    

    public String getNomPersonne() {
        return nomPersonne;
    }

    public void setNomPersonne(String nomPersonne) {
        this.nomPersonne = nomPersonne;
    }

    public String getPrenomPersonne() {
        return prenomPersonne;
    }

    public void setPrenomPersonne(String prenomPersonne) {
        this.prenomPersonne = prenomPersonne;
    }

    public String getAdressePersonne() {
        return AdressePersonne;
    }

    public void setAdresse(String AdressePersonne) {
        this.AdressePersonne = AdressePersonne;
    }


    public String getTYPE() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }

}
