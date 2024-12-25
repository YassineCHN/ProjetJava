/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package FACADE;

import ENTITE.Z_MEDECIN;
import ENTITE.Z_USER;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author charl
 */
@Local
public interface Z_USERFacadeLocal {

    void create(Z_USER z_USER);

    void edit(Z_USER z_USER);

    void remove(Z_USER z_USER);

    Z_USER find(Object id);

    List<Z_USER> findAll();

    List<Z_USER> findRange(int[] range);

    int count();

    Z_USER authentification(String login, String mdp);

    void creerUtilisateur(String login, String mdp);

    void mettreAJourUtilisateur(Z_USER user);

    void supprimerUtilisateur(Z_USER user);

    Z_USER trouverUtilisateurParId(long id);

    List<Z_USER> trouverTousLesUtilisateurs();

    List<Z_MEDECIN> trouverTousLesUtilisateursMedecin();

    
}
