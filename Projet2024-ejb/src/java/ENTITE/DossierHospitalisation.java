/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITE;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author charl
 */
@Entity
public class DossierHospitalisation implements Serializable {

    @OneToMany(mappedBy = "leDossier")
    private List<Facture> factures;

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
        if (!(object instanceof DossierHospitalisation)) {
            return false;
        }
        DossierHospitalisation other = (DossierHospitalisation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ENTITE.DossierHospitalisation[ id=" + id + " ]";
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateHospitalisation;

    public Date getDateHospitalisation() {
        return dateHospitalisation;
    }

    public void setDateHospitalisation(Date dateHospitalisation) {
        this.dateHospitalisation = dateHospitalisation;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date heureArrivee;

    public Date getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(Date heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date heureDepart;

    public Date getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(Date heureDepart) {
        this.heureDepart = heureDepart;
    }

    @ManyToOne
    private Z_PATIENT lePatient;

    public Z_PATIENT getLePatient() {
        return lePatient;
    }

    public void setLePatient(Z_PATIENT lePatient) {
        this.lePatient = lePatient;
    }

    @ManyToOne
        private Service leService;

    public Service getLeService() {
        return leService;
    }

    public void setLeService(Service leService) {
        this.leService = leService;
    }

    
    @OneToMany(mappedBy = "leDossier")
    private List<Acte> lesActes;

    public List<Acte> getLesActes() {
        return lesActes;
    }

    public void setLesActes(List<Acte> lesActes) {
        this.lesActes = lesActes;
    }

}

