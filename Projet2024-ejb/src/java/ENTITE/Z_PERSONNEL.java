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
public class Z_PERSONNEL extends Z_PERSONNE {

    
    @ManyToOne
    private Service service;
    


    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
    
}
