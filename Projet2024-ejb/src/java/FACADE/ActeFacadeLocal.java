/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package FACADE;

import ENTITE.Acte;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author charl
 */
@Local
public interface ActeFacadeLocal {

    void create(Acte acte);

    void edit(Acte acte);

    void remove(Acte acte);

    Acte find(Object id);

    List<Acte> findAll();

    List<Acte> findRange(int[] range);

    int count();

    void creerActe(String nom, String description, double prix,double coefSecu, double coefMutuelle);

    void modifierActe(Acte acte,String nom, String description, Double prix, Double coeffSS, Double coeffMut);

    void supprimerActe(Acte acteSupp);

    Acte trouverActeParNom(String nom);

    List<Acte> trouverTousLesActes();

    Acte trouverActeParId(long id);
}
