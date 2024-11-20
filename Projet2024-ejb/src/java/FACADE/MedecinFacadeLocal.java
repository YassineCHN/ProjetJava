/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package FACADE;

import ENTITE.Medecin;
import ENTITE.RolesUtilisateurs;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author charl
 */
@Local
public interface MedecinFacadeLocal {

    void create(Medecin medecin);

    void edit(Medecin medecin);

    void remove(Medecin medecin);

    Medecin find(Object id);

    List<Medecin> findAll();

    List<Medecin> findRange(int[] range);

    int count();
    
    
        void creerMedecin(String login, String motDePasse, RolesUtilisateurs role,String nom, String specialite) ;

    void modifierMedecin(String login, String motDePasse, RolesUtilisateurs role,Long id, String nom, String specialite);

    void supprimerMedecin(Long id);

    Medecin trouverMedecinParNom(String nom);

    List<Medecin> trouverTousLesMedecins();
}
