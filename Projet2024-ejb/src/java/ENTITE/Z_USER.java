/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITE;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author charl
 */
@Entity
public class Z_USER implements Serializable {

    
    @OneToOne
    @JoinColumn(name = "idpers")
    private Z_PERSONNE personne;
//
//
//
//pour info
//Lors des tests, ne pas saisir un ID user faible (1,2 etc...)
//    car ca rentre en conflit avec la génération auto de l'ID
//    lorsqu'on crée des users via la facade
//       
    
//    public String getRole() {
//        // Utilisation d'une expression JPA pour récupérer la valeur de la colonne discriminante
//        return this.getClass().getAnnotation(DiscriminatorValue.class).value();  }

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
        if (!(object instanceof Z_USER)) {
            return false;
        }
        Z_USER other = (Z_USER) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ENTITE.Z_USER[ id=" + id + " ]";
    }
    
    
    private String login;

    /**
     * Get the value of login
     *
     * @return the value of login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set the value of login
     *
     * @param login new value of login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    private String mdp;

    /**
     * Get the value of mdp
     *
     * @return the value of mdp
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * Set the value of mdp
     *
     * @param mdp new value of mdp
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Enumerated(EnumType.STRING)
    private RoleUSER role;

    public RoleUSER getRole() {
        return role;
    }

    public void setRole(RoleUSER role) {
        this.role = role;
    }
    
    
    public Z_PERSONNE getPersonne() {
        return personne;
    }

    public void setPersonne(Z_PERSONNE personne) {
        this.personne = personne;
    }

    
}
