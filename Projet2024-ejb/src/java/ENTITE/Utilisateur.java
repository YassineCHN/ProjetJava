/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITE;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author charl
 azeazeazeazeazaze
 azezaezaeazeazeza
 
 azezaezaeazeazeza
 
 azezaezaeazeazeza
 
 azezaezaeazeazeza
 
 azezaezaeazeazeza

 
 azezaezaeazeazeza
 
 azezaezaeazeazeza
 
 azezaezaeazeazeza
 
 azezaezaeazeazeza

 
 azezaezaeazeazeza
 
 azezaezaeazeazeza
 */
@Entity
@Inheritance
(strategy=InheritanceType.TABLE_PER_CLASS)
public class Utilisateur implements Serializable {

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
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ENTITE.Utilisateur[ id=" + id + " ]";
    }
        @Column(unique = true)
        private String utilisateurLogin;

    public String getUtilisateurLogin() {
        return utilisateurLogin;
    }

    public void setUtilisateurLogin(String utilisateurLogin) {
        this.utilisateurLogin = utilisateurLogin;
    }

    private String utilisateurMDP;

    public String getUtilisateurMDP() {
        return utilisateurMDP;
    }

    public void setUtilisateurMDP(String utilisateurMDP) {
        this.utilisateurMDP = utilisateurMDP;
    }
    
    @Enumerated(EnumType.STRING)
    private RolesUtilisateurs utilisateurRole;

    public RolesUtilisateurs getUtilisateurRole() {
        return utilisateurRole;
    }

    public void setUtilisateurRole(RolesUtilisateurs utilisateurRole) {
        this.utilisateurRole = utilisateurRole;
    }

}
