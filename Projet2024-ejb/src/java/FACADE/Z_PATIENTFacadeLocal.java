/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package FACADE;

import ENTITE.Z_PATIENT;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author charl
 */
@Local
public interface Z_PATIENTFacadeLocal {

    void create(Z_PATIENT z_PATIENT);

    void edit(Z_PATIENT z_PATIENT);

    void remove(Z_PATIENT z_PATIENT);

    Z_PATIENT find(Object id);

    List<Z_PATIENT> findAll();

    List<Z_PATIENT> findRange(int[] range);

    int count();
    
}
