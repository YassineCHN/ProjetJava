/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITE;

import java.io.Serializable;
import java.util.Date;
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
public class LigneJournal implements Serializable {

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
        if (!(object instanceof LigneJournal)) {
            return false;
        }
        LigneJournal other = (LigneJournal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ENTITE.LigneJournal[ id=" + id + " ]";
    }
    
    
    
        private Date Date_acte;

    public Date getDate_acte() {
        return Date_acte;
    }

    public void setDate_acte(Date Date_acte) {
        this.Date_acte = Date_acte;
    }

    private int Quantité_Acte;

    public int getQuantité_Acte() {
        return Quantité_Acte;
    }

    public void setQuantité_Acte(int Quantité_Acte) {
        this.Quantité_Acte = Quantité_Acte;
    }
    
    private String Commentaire;

    public String getCommentaire() {
        return Commentaire;
    }

    public void setCommentaire(String Commentaire) {
        this.Commentaire = Commentaire;
    }

    @ManyToOne
    private Acte id_acte;

    public Acte getId_acte() {
        return id_acte;
    }

    public void setId_acte(Acte id_acte) {
        this.id_acte = id_acte;
    }
    
    @ManyToOne
        private JournalActe id_journal;

    public JournalActe getId_journal() {
        return id_journal;
    }

    public void setId_journal(JournalActe id_journal) {
        this.id_journal = id_journal;
    }
    


}
