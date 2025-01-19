/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package SESSION;

import javax.ejb.Local;
import javax.mail.MessagingException;
import javax.naming.NamingException;

/**
 *
 * @author charl
 */
@Local
public interface envoieMailLocal {

    void envoyerEmail(String destinataire, String sujet, String contenuTexte);
    void sendMail(String email, String subject, String body) throws NamingException, MessagingException ;
    
}
