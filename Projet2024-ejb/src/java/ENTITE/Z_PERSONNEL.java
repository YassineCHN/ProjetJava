/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ENTITE;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author ychen
 */
@Entity
@DiscriminatorValue("PERSONNEL")
public class Z_PERSONNEL extends Z_USER {

    
    @ManyToOne
    private Service service;
    

//     private static final long serialVersionUID = 1L;
//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Long id;
// 
//     public Long getId() {
//         return id;
//     }
// 
//     public void setId(Long id) {
//         this.id = id;
//     }
// 
//     @Override
//     public int hashCode() {
//         int hash = 0;
//         hash += (id != null ? id.hashCode() : 0);
//         return hash;
//     }
// 
//     @Override
//     public boolean equals(Object object) {
//         // TODO: Warning - this method won't work in the case the id fields are not set
//         if (!(object instanceof Z_PERSONNEL)) {
//             return false;
//         }
//         Z_PERSONNEL other = (Z_PERSONNEL) object;
//         if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//             return false;
//         }
//         return true;
//     }
// 
//     @Override
//     public String toString() {
//         return "ENTITE.Membre[ id=" + id + " ]";
//     }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
    
}
