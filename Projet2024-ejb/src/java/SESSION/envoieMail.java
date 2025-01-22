/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package SESSION;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
/**
 *
 * @author charl
 */
@Stateless
public class envoieMail implements envoieMailLocal {

    @Resource(name = "mail/MonMailSession")
    private Session mailMonMailSession;
//      ==========================================================================================
//      ==========================================================================================
//      ==========================================================================================
//      ==========================================================================================
//    Au final on a abandonné la fonctionnalité de relance des patients par mail, le paramétrage ne fonctionne pas ...
//      comme vu avec Mme Talens lundi 20/01/2025
//    en gère la relance en affichant dans un écran dédié (FacturesImpayees.jsp)
//    les factures en retard de paiement, c'est à dire en retard de 14 jours par rapport à la date d'émission
//    pour info, le délai légal de paiement est 14 jours à partir de la date de réception d'une facture.
    
//      ==========================================================================================
//      ==========================================================================================
//      ==========================================================================================
//      ==========================================================================================
//      ==========================================================================================
//      ==========================================================================================
//      ==========================================================================================
//      ==========================================================================================
//      ==========================================================================================
//      ==========================================================================================
//      ==========================================================================================
//      ==========================================================================================
    @Resource(lookup = "mail/MonMailSession")
    private Session mailSession;

    @Override
    public void envoyerEmail(String destinataire, String sujet, String contenuTexte)   {
        
        // 1) Construire l'objet MimeMessage
        MimeMessage message = new MimeMessage(mailSession);
        try {
            message.setFrom(new InternetAddress("fatihhmar@gmail.com"));
        } catch (AddressException ex) {
            Logger.getLogger(envoieMail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(envoieMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinataire, false));
        } catch (MessagingException ex) {
            Logger.getLogger(envoieMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            message.setSubject(sujet, "UTF-8");
        } catch (MessagingException ex) {
            Logger.getLogger(envoieMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            message.setText(contenuTexte, "UTF-8");
        } catch (MessagingException ex) {
            Logger.getLogger(envoieMail.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            // 2) Envoyer
            Transport.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(envoieMail.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("[EmailService] E-mail envoyé avec succès à " + destinataire);
    }
    @Override
    public void sendMail(String email, String subject, String body) throws NamingException, MessagingException {
        MimeMessage message = new MimeMessage(mailMonMailSession);
        message.setSubject(subject);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
        message.setText(body);
        Transport.send(message);
    }
    
    
}
