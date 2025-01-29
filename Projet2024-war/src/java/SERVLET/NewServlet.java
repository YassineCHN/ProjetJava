




package SERVLET;

//import ENTITE.Acte;
import ENTITE.Acte;
import ENTITE.DossierHospitalisation;
import ENTITE.Facture;
import ENTITE.JournalActe;
import ENTITE.LigneJournal;
import ENTITE.ModePaiement;
import ENTITE.Paiement;
import ENTITE.RoleUSER;

import ENTITE.Service;

import ENTITE.Z_MEDECIN;
import ENTITE.Z_PATIENT;
import ENTITE.Z_PERSONNE;
import ENTITE.Z_PERSONNEL;
import ENTITE.Z_USER;
import ENTITE.statutJournal;
import SESSION.SUPERSessionLocal;
import SESSION.SessionADMINLocal;
import SESSION.SessionMEDECINLocal;
import SESSION.SessionPATIENTLocal;
import SESSION.SessionPERSONNELLocal;
import SESSION.SessionPersonnelFinancierLocal;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

    @EJB
    private SUPERSessionLocal sUPERSession;

    @EJB
    private SessionPERSONNELLocal sessionPERSONNEL;

    @EJB
    private SessionPATIENTLocal sessionPATIENT;

    @EJB
    private SessionMEDECINLocal sessionMEDECIN;

    @EJB
    private SessionADMINLocal sessionADMIN;

    @EJB
    private SessionPersonnelFinancierLocal sessionPersonnelFinancier;
    
    
    
    
   
    
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {

        HttpSession session = request.getSession(true); // création/récupération de la session utilisateur
        String jspClient = null; // Initialisation de la variable pour stocker le chemin de la JSP à afficher
        String act = request.getParameter("action"); // Récupère l'action à partir des paramètres de la requête

        if ((act == null) || (act.equals("vide"))) {
            // Si l'action est nulle ou vide, rediriger vers la page de destination par défaut
            jspClient = "/landing_page.jsp";
            request.setAttribute("message", "pas d'informations");

        } 
        
        
        else if (act.equals("authentificationUtilisateur_HERITAGE")) {
            jspClient = "/landing_page.jsp";

            // Récupération des paramètres du formulaire
            String login = request.getParameter("loginHeritage");
            String password = request.getParameter("passwordHeritage");

            // Appel du Bean Session pour l'authentification
            String message = sUPERSession.authentifierUtilisateur(login, password, session, request);

            // Ajout du message dans la requête
            request.setAttribute("message", message);
        }

        else if (act.equals("logout")) {
            jspClient = "/landing_page.jsp";
            request.setAttribute("message", "Vous avez été déconnecté avec succès.");
            if (session != null) {
                session.invalidate(); // Invalide la session
            }    
        }
        else if (act.equals("afficherUtilisateurs")) {
            // Action pour afficher les utilisateurs
            jspClient = "/GestionUtilisateur.jsp";
            List<Z_USER> lesUtilisateurs = sessionADMIN.trouverTousLesUtilisateurs();
            request.setAttribute("listeUtilisateur", lesUtilisateurs);
            request.setAttribute("message", "Liste des utilisateurs existants");
        }
        else if (act.equals("afficherPersonnes")) {
            jspClient = "/GestionPersonne.jsp";
            List<Z_PERSONNE> lesPersonnes = sessionADMIN.trouverToutesLesPersonnes();
            request.setAttribute("listePersonnes", lesPersonnes);
            request.setAttribute("message", "Liste des Personnes existants");
        }
        else if (act.equals("ajouterUtilisateur")) {
            List <Z_PERSONNE> list = sessionADMIN.trouverPersonnesSansUtilisateur(); 
            request.setAttribute("listepersonnes",list); 
            jspClient="/AjouterUtilisateur.jsp"; 
        }
        else if (act.equals("ajouterPersonne")) {
            List <Service> list = sessionADMIN.tousLesServices(); 
            request.setAttribute("listeServices",list); 
            jspClient="/AjouterPersonne.jsp"; 
        }
        else if (act.equals("afficherMedecins")){
            jspClient = "/GestionMedecin.jsp";
            List<Z_MEDECIN> lesMedecins = sessionADMIN.trouverTousLesMedecins();
            request.setAttribute("listeMedecins", lesMedecins);
            request.setAttribute("message", "Liste des médecins existants");
        }
        else if (act.equals("afficherPatients")){
            jspClient = "/GestionPatient.jsp";
            List<Z_PATIENT> lesPatients = sessionADMIN.trouverTousLesPatients();
            request.setAttribute("listePatients", lesPatients);
            request.setAttribute("message", "Liste des patients existants");
        }
        else if (act.equals("afficherPersonnels")){
            jspClient = "/GestionPersonnel.jsp";
            List<Z_PERSONNEL> lesPersonnels = sessionADMIN.trouverTousLesPersonnels();
            request.setAttribute("listePersonnels", lesPersonnels);
            request.setAttribute("message", "Liste des membres du personnels existants");
        }
        else if (act.equals("afficherDossiers")){
            jspClient = "/GestionDossier.jsp";
            String utilisateurIdentifie = (String) session.getAttribute("utilisateur2");
            if (utilisateurIdentifie != null) {
                Z_USER user = sUPERSession.trouverUserParLogin(utilisateurIdentifie);
                if(user!=null){
                    Z_PERSONNE personne = user.getPersonne();
                    request.setAttribute("personne", personne);
                    RoleUSER role = user.getRole();
                    request.setAttribute("role", role);
                    List<DossierHospitalisation> lesDossiers=null;
                    if(role==RoleUSER.ADMIN){
                        lesDossiers =  sessionADMIN.afficherDossier();
                    } else if (role==RoleUSER.PATIENT){
                        Z_PATIENT patient=(Z_PATIENT) personne;
                        lesDossiers = sessionPATIENT.trouverTousLesDossiersUnPatient(patient);
                    } else if (role==RoleUSER.PERSONNEL){
                        Z_PERSONNEL pers=(Z_PERSONNEL) personne;
                        Service serv=pers.getService();
                        lesDossiers=sessionPERSONNEL.trouverTousLesDossiersUnService(serv);
                        // trouver tous les dossiers du même service que la personne connecté
                    } else if (role==RoleUSER.MEDECIN){
                        Z_MEDECIN med=(Z_MEDECIN) personne;
                        Service serv=med.getService();
                        lesDossiers=sessionMEDECIN.trouverTousLesDossiersUnService(serv);
                        // trouver tous les dossiers du même service que la personne connecté
                    }
                    else {
                        jspClient="/landing_page.jsp";
                        request.setAttribute("message","l'utilisateur connecté ne possède pas un rôle connu");
                    }
                request.setAttribute("listeDossiers", lesDossiers);
                request.setAttribute("message", "Liste des dossiers existants");
                }
            }
        }
        else if(act.equals("afficherServices")) {
            // Action pour afficher les services, comme utilisateur
            jspClient = "/GestionService.jsp";
            List<Service> lesServices = sessionADMIN.tousLesServices();
            request.setAttribute("listeService", lesServices);
            request.setAttribute("message", "Liste des Services existants");
        }
        else if (act.equals("afficherActes")){
            jspClient = "/GestionActe.jsp";
            List<Acte> lesActes = sessionADMIN.trouverTousLesActes();
            request.setAttribute("listeActe", lesActes);
            request.setAttribute("message", "Liste des Actes existants");
        }
        else if (act.equals("afficherFactures")) {
            jspClient = "/GestionFacturation.jsp";
            String utilisateurIdentifie = (String) session.getAttribute("utilisateur2");
            List<Facture> factures = null;
            if (utilisateurIdentifie != null) {
                Z_USER user = sUPERSession.trouverUserParLogin(utilisateurIdentifie);
                if(user!=null){
                    Z_PERSONNE personne = user.getPersonne();
                    request.setAttribute("personne", personne);
                    RoleUSER role = user.getRole();
                    request.setAttribute("role", role);
                    
                    
                    if (role==RoleUSER.PATIENT) {
                        Z_PATIENT patient=(Z_PATIENT) personne;
                        factures = sessionPATIENT.trouverFacturesPatient(patient);
                    }
                    else {
                        factures = sessionPersonnelFinancier.trouverToutesFactures();
                    }
                } else {
                    jspClient = "/landing_page.jsp";
                    request.setAttribute("message", "erreur dans l'affichage des factures");
                }
            }
            request.setAttribute("listeFacture", factures);
            
        }
        else if (act.equals("afficherFicheFacture")){
            jspClient ="/ficheFacture.jsp";
            String id_facture = (String) request.getParameter("id_Facture");
            Facture facture = sessionPersonnelFinancier.trouverFactureParID(Long.parseLong(id_facture));
            String utilisateurIdentifie = (String) session.getAttribute("utilisateur2");
            if (utilisateurIdentifie != null) {
                Z_USER user = sUPERSession.trouverUserParLogin(utilisateurIdentifie);
                if(user!=null){
                    Z_PERSONNE personne = user.getPersonne();
                    request.setAttribute("personne", personne);
                    RoleUSER role = user.getRole();
                    request.setAttribute("role", role);
                    request.setAttribute("facture", facture);
                }
            }
        }
            
        else if (act.equals("afficherFicheUtilisateur")) {
            jspClient = "/ficheUtilisateur.jsp";
            String test = request.getParameter("id_utilisateur");

            Long id_utilisateur = Long.valueOf(test);

            Z_USER user = sessionADMIN.trouverUtilisateurParId(id_utilisateur);

            request.setAttribute("utilisateurFicheUtilisateur", user);
            List <Z_PERSONNE> list = sessionADMIN.trouverPersonnesSansUtilisateur(); 
            request.setAttribute("listepersonnes",list);
        }
        else if (act.equals("afficherFichePersonne")) {
            jspClient = "/fichePersonne.jsp";
            String test = request.getParameter("id_personne");

            Long id_personne = Long.valueOf(test);

            Z_PERSONNE pers = sessionADMIN.trouverPersonneParId(id_personne);
            List<Service> listeServices = sessionADMIN.tousLesServices();
            request.setAttribute("listeServices", listeServices);
            request.setAttribute("personneFichePersonne", pers);
        }
        else if (act.equals("afficherFicheService")){
                    jspClient = "/ficheService.jsp";
            String test = request.getParameter("id_service");

            Long id_service = Long.valueOf(test);

            Service service = sessionADMIN.trouverServiceParID(id_service);
            
            request.setAttribute("ficheService", service);
            
        }
        else if (act.equals("afficherFicheDossier")){
            jspClient = "/ficheDossier.jsp";
            String test = request.getParameter("id_dossier");
            Long id_dossier = Long.valueOf(test);
            String utilisateurIdentifie = (String) session.getAttribute("utilisateur2");
            DossierHospitalisation dossier =null;
            if (utilisateurIdentifie != null) {
                Z_USER user = sUPERSession.trouverUserParLogin(utilisateurIdentifie);
                if (user != null) {
                    Z_PERSONNE personne = user.getPersonne();
                    request.setAttribute("personne", personne);
                    RoleUSER role = user.getRole();
                    request.setAttribute("role", role);
                    if (role==RoleUSER.MEDECIN){dossier = sessionMEDECIN.trouverDossierParId(id_dossier);} 
                    else if (role==RoleUSER.PERSONNEL){dossier = sessionPERSONNEL.trouverDossierParId(id_dossier);} 
                    else if (role==RoleUSER.PATIENT){dossier = sessionPATIENT.trouverDossierParId(id_dossier);}
                }
            }
            request.setAttribute("ficheDossier", dossier);
        }
        
        else if (act.equals("afficherFicheActe")){
           jspClient = "/ficheActe.jsp";
            
            String test = request.getParameter("id_Acte");

            Long id_acte = Long.valueOf(test);
            
            Acte acte = sessionADMIN.trouverActeParId(id_acte);
          
            request.setAttribute("ficheActe", acte);
       }
        else if (act.equals(("afficherFicheJournalActe"))){
            jspClient="/ficheJournal.jsp";
            String id_journal = request.getParameter("id_journal_1");
            Long id_journal_bis = Long.valueOf(id_journal);
            String utilisateurIdentifie = (String) session.getAttribute("utilisateur2");
            if (utilisateurIdentifie != null) {
                Z_USER user = sUPERSession.trouverUserParLogin(utilisateurIdentifie);
                if (user != null) {
                    RoleUSER role = user.getRole();
                    request.setAttribute("role", role);
                    if (role == RoleUSER.MEDECIN) {
                        JournalActe journal = sessionMEDECIN.trouverJournalParId(id_journal_bis);
                        request.setAttribute("journal_object", journal);
                        List<Acte> lesActes = sessionMEDECIN.trouverTousLesActes();
                        request.setAttribute("listeActeJournal", lesActes);
                        List<LigneJournal> lignes = sessionMEDECIN.listerLignesParJournal(journal.getId());
                        request.setAttribute("lignes_journals", lignes);            
                        List<Z_MEDECIN> lesMedecins2 = sessionMEDECIN.trouverTousLesMedecins();
                        request.setAttribute("listeMedecins", lesMedecins2);
                    } else if (role==RoleUSER.PERSONNEL) {
                        JournalActe journal = sessionPersonnelFinancier.trouverJournalParId(id_journal_bis);
                        request.setAttribute("journal_object", journal);
                        List<Acte> lesActes = sessionPersonnelFinancier.trouverTousLesActes();
                        request.setAttribute("listeActeJournal", lesActes);
                        List<LigneJournal> lignes = sessionPersonnelFinancier.listerLignesParJournal(journal.getId());
                        request.setAttribute("lignes_journals", lignes);            
                        List<Z_MEDECIN> lesMedecins2 = sessionPersonnelFinancier.trouverTousLesMedecins();
                        request.setAttribute("listeMedecins", lesMedecins2);
                    }
                }
            }
        }
        else if (act.equals("afficherLignes")) {
            jspClient = "/afficherLignes.jsp";
            List<LigneJournal> lignes = sessionPersonnelFinancier.trouverToutesLignes();
            request.setAttribute("lignes", lignes);
        }
        else if (act.equals("afficherJournaux")) {
            jspClient = "/GestionJournal.jsp";
            List<JournalActe> journaux = (List<JournalActe>) sessionPersonnelFinancier.trouverTousLesJournaux();
            request.setAttribute("lesJournaux", journaux);
        }
        else if (act.equals("afficherFacturesRetard")) {
            jspClient ="/FacturesImpayees.jsp";
            List<Facture> facturesRetard = sessionPersonnelFinancier.trouverFacturesNonPayeesAvecEmissionDepassee();
            if (facturesRetard == null) {
                jspClient="/landing_page.jsp";
                request.setAttribute("message", "Il n'y a aucunes factures en retard à afficher.");
            } else {
                request.setAttribute("facturesEnRetard", facturesRetard);
            }
        }
        
        else if (act.equals("supprimerService")) {
            jspClient = "/landing_page.jsp";
            Long idService = Long.valueOf(request.getParameter("id_supprimerService"));
            if(sessionADMIN.SupprimerService(idService)){
               request.setAttribute("message", "Service supprimé avec succès."); 
            }else {
                request.setAttribute("message", "Suppression Impossible : Service non trouvé ou encore lié à un Dossier, un Médecin ou un Personnel");
            }
        }
        else if (act.equals("supprimerUtilisateur")){
            jspClient = "/landing_page.jsp";
            Long idUser = Long.parseLong(request.getParameter("id_supprimerUtilisateur"));
            if(sessionADMIN.supprimerUtilisateur(idUser)){
                request.setAttribute("message", "Utilisateur supprimé avec succès.");
            } else {
                request.setAttribute("message", "Suppression impossible : Utilisateur non trouvé.");
            }
        }
        else if (act.equals("supprimerPersonne")){
            jspClient = "/landing_page.jsp";
            Long idPersonne = Long.parseLong(request.getParameter("id_supprimerPersonne"));
            Z_USER utilisateur = sessionADMIN.trouverUtilisateurParPers(idPersonne);
            if (sessionADMIN.supprimerPersonne(idPersonne)) {
                request.setAttribute("message", "Personne supprimée avec succès.");
            } else{
                request.setAttribute("message", "Suppression Impossible : Personne non trouvé ou encore lié à un Dossier(PATIENT), un Service(MEDECIN/PERSONNEL) ou un Journal(MEDECIN)");
            }  
        }
        else if (act.equals("supprimerActe")){
            jspClient="/landing_page.jsp";
            Long idActe = Long.parseLong(request.getParameter("id_supprimerActe"));
            if(sessionADMIN.supprimerActe(idActe)){
               request.setAttribute("message", "Acte supprimé avec succès."); 
            }else {
                request.setAttribute("message", "Suppression Impossible : Acte non trouvé ou encore lié à une ligne de journal");
            }
            
        }
        else if (act.equals("annulerDossier")) {
            jspClient="/landing_page.jsp";
            Long value = Long.parseLong(request.getParameter("id_annulerDossier"));
            String utilisateurIdentifie = (String) session.getAttribute("utilisateur2");
            if (utilisateurIdentifie != null) {
                Z_USER user = sUPERSession.trouverUserParLogin(utilisateurIdentifie);
                if (user != null) {
                    RoleUSER role = user.getRole();
                    request.setAttribute("role", role);
                    if (role==RoleUSER.PATIENT){
                        sessionPATIENT.annulerDossierHospitalisation(value);
                    } else if (role==RoleUSER.PERSONNEL){
                        sessionPERSONNEL.annulerDossierHospitalisation(value);
                    } else if (role==RoleUSER.MEDECIN){
                        sessionMEDECIN.annulerDossierHospitalisation(value);
                    }
                    else {
                        jspClient="/landing_page.jsp";
                        request.setAttribute("message","l'utilisateur connecté ne peux pas annuler un dossier");
                    }
                request.setAttribute("message", "Dossier Annulé");
                }
            }
        }
        else if (act.equals("creerUtilisateur")) {
                jspClient="/landing_page.jsp";
                String login = request.getParameter("loginAjouterUser");
                String mdp = request.getParameter("passwordAjouterUser");
                String roleparam = request.getParameter("roleAjouterUser");
                String persparam=request.getParameter("UserAjouterPersonne");
                RoleUSER role=RoleUSER.valueOf(roleparam.toUpperCase());
                Z_PERSONNE pers=null;
                if(persparam!=null&& !persparam.trim().isEmpty()){
                    Long persId= Long.parseLong(persparam);
                    pers=sessionADMIN.trouverPersonneParId(persId);
                    
                    // Validation pour vérifier si la personne a déjà un utilisateur
                    if (pers != null && sessionADMIN.personneHasUser(persId)) {
                        request.setAttribute("erreur", "Cette personne a déjà un utilisateur associé.");
                        List<Z_PERSONNE> listePersonnes = sessionADMIN.trouverPersonnesSansUtilisateur();
                        request.setAttribute("listepersonnes", listePersonnes);
                        request.getRequestDispatcher("/AjouterUtilisateur.jsp").forward(request, response);
                        return;
                    }
                }
                if (role != RoleUSER.ADMIN && pers != null) {
                    String typePersonne = pers.getTYPE(); 
                    if ((role == RoleUSER.MEDECIN && !"MEDECIN".equals(typePersonne)) ||
                        (role == RoleUSER.PATIENT && !"PATIENT".equals(typePersonne)) ||
                        (role == RoleUSER.PERSONNEL && !"PERSONNEL".equals(typePersonne))) 
                    {
                        request.setAttribute("erreur", "Le rôle sélectionné ne correspond pas au type de la personne.");
                        List<Z_PERSONNE> listePersonnes = sessionADMIN.trouverPersonnesSansUtilisateur(); // Charger la liste des personnes
                        request.setAttribute("listepersonnes", listePersonnes);
                        request.getRequestDispatcher("/AjouterUtilisateur.jsp").forward(request, response);
                        return;
                    }
                }
                
                boolean utilisateurCree = sessionADMIN.creerUtilisateur(login, mdp, role, pers);
                 if (!utilisateurCree) {        // Si l'utilisateur n'a pas été créé, afficher un message d'erreur
                     request.setAttribute("erreur", "Ce login existe déjà.");
                     List<Z_PERSONNE> listePersonnes = sessionADMIN.trouverPersonnesSansUtilisateur();
                     request.setAttribute("listepersonnes", listePersonnes);
                     request.getRequestDispatcher("/AjouterUtilisateur.jsp").forward(request, response);
                     return;
                 }
                 

        }
        else if (act.equals("creerPersonne")) {
                jspClient="/landing_page.jsp";
                    String nom = request.getParameter("nomPersonne");
                    String prenom = request.getParameter("prenomPersonne");
                    String adresse = request.getParameter("adressePersonne");
                    String typePersonne = request.getParameter("typePersonne");
                    
                if (typePersonne.equals("MEDECIN")) {
                    String specialite = request.getParameter("specialiteMedecin");
                    String serviceparam = request.getParameter("MedecinServiceAjouterPersonne");
                    if (serviceparam == null || serviceparam.isEmpty() ) {
                        request.setAttribute("erreur", "Un service doit être sélectionné pour créer un utilisateur de type PERSONNEL.");
                        List<Service> listeServices = sessionADMIN.tousLesServices();
                        request.setAttribute("listeServices", listeServices);
                        request.getRequestDispatcher("AjouterUtilisateur.jsp").forward(request, response);
                        return; 
                    }else{
                        Long serviceId = Long.parseLong(serviceparam);
                        Service service = sessionADMIN.trouverServiceParID(serviceId);
                        sessionADMIN.creerMedecin(nom, prenom, adresse, specialite,service);
                        System.out.println("Utilisateur MEDECIN créé avec succès !");
                     }
                }
                if (typePersonne.equals("PATIENT")){
                    String numSecuSoc = request.getParameter("numSecuPatient");
                    String mutuelle=request.getParameter("nomMutuelle");
                    String adresseMut=request.getParameter("adresseMutuelle");
                    sessionADMIN.creerPatient(nom, prenom, adresse, numSecuSoc, mutuelle, adresseMut);
                    System.out.println("ON CREE PATIENT");
                }
                if (typePersonne.equals("PERSONNEL")){
                    String serviceparam = request.getParameter("PersonnelServiceAjouterPersonne");
                    if (serviceparam == null || serviceparam.isEmpty() ) {
                        request.setAttribute("erreur", "Un service doit être sélectionné pour créer un utilisateur de type PERSONNEL.");
                        List<Service> listeServices = sessionADMIN.tousLesServices();
                        request.setAttribute("listeServices", listeServices);
                        request.getRequestDispatcher("AjouterUtilisateur.jsp").forward(request, response);
                        return; 
                    }
                    else{
                    Long serviceId = Long.parseLong(serviceparam);
                    Service service = sessionADMIN.trouverServiceParID(serviceId);
               
                    sessionADMIN.creerPersonnel(nom, prenom, adresse, service);
                    System.out.println("Utilisateur PERSONNEL créé avec succès !"); 
                    }
                }
        }      
        
        
        else if (act.equals("modifierService")) {
            jspClient = "/landing_page.jsp";

            // Récupération des paramètres du formulaire
            String idServiceStr = request.getParameter("id_service");
            String localisation = request.getParameter("localisation");
            String nomService = request.getParameter("nomService");

            // Appel du Bean Session pour la mise à jour
            String message = sessionADMIN.modifierServiceParID(idServiceStr, localisation, nomService);

            // Ajout du message dans la requête
            request.setAttribute("message", message);
        }


        else if (act.equals("modifierUtilisateur")) {
            jspClient="/landing_page.jsp";
            Long idUtilisateur= Long.parseLong(request.getParameter("id_utilisateurFicheUtilisateur"));
            String login = request.getParameter("login_ficheUtilisateur");
            String mdp = request.getParameter("password_ficheUtilisateur");
            String role= request.getParameter("role_ficheUtilisateur");
            String idpersonne=request.getParameter("UserPersonne");
            
            Z_USER user=sessionADMIN.trouverUtilisateurParId(idUtilisateur);
            Z_PERSONNE pers=null;
            if(user !=null){
                if(idpersonne!=null&& !idpersonne.trim().isEmpty()){
                    Long persId= Long.parseLong(idpersonne);
                    pers=sessionADMIN.trouverPersonneParId(persId);
                }
                user.setLogin(login);
                user.setMdp(mdp);
                user.setPersonne(pers);
                sessionADMIN.modifierUtilisateur(user);
                request.setAttribute("message", "Personne modifiée avec succès.");
            } else {request.setAttribute("message", "Personne non trouvée.");}
            
        }
        else if (act.equals("modifierPersonne")) {
            jspClient="/landing_page.jsp";
            Long idPersonne = Long.parseLong(request.getParameter("id_personne"));
            String nom = request.getParameter("nomPersonne");
            String prenom = request.getParameter("prenomPersonne");
            String adresse = request.getParameter("adressePersonne");
            String typePersonne = request.getParameter("typePersonne");     
            // Identifiez le type de la personne et mettez à jour les informations spécifiques
            Z_PERSONNE personne = sUPERSession.trouverPersonneParId(idPersonne);
            if (personne != null) {
                personne.setNomPersonne(nom);
                personne.setPrenomPersonne(prenom);
                personne.setAdresse(adresse);
                // En fonction du type de personne, vous pouvez faire des mises à jour supplémentaires
                if (typePersonne.equals("MEDECIN")) {
                    String specialite = request.getParameter("specialiteMedecin");
                    ((Z_MEDECIN) personne).setSpecialite(specialite);
                    String serviceparamM = request.getParameter("serviceMedecin");
                    Service service = sessionMEDECIN.trouverServiceParID(Long.parseLong(serviceparamM));
                    ((Z_MEDECIN) personne).setService(service);
                } else if (typePersonne.equals("PATIENT")) {
                    String numSecuSoc = request.getParameter("numSecuPatient");
                    String mutuelle = request.getParameter("nomMutuelle");
                    String adresseMutuelle = request.getParameter("adresseMutuelle");
                    ((Z_PATIENT) personne).setNumSecuSoc(numSecuSoc);
                    ((Z_PATIENT) personne).setNomMutuelle(mutuelle);
                    ((Z_PATIENT) personne).setAdresseMutuelle(adresseMutuelle);
                } else if (typePersonne.equals("PERSONNEL")) {
                    String serviceparamP = request.getParameter("servicePersonnel");
                    Service service = sessionPERSONNEL.trouverServiceParID(Long.parseLong(serviceparamP));
                    ((Z_PERSONNEL) personne).setService(service);
                }// Appelez la méthode pour modifier la personne dans la base de données
                sUPERSession.modifierPersonne(personne);
                request.setAttribute("message", "Personne modifiée avec succès.");} else {
                request.setAttribute("message", "Personne non trouvée.");
            } 
        }
        else if (act.equals("modifierDossierMedecin")){
            jspClient="/landing_page.jsp";
            Long idDossier= Long.parseLong(request.getParameter("id_dossierFiche"));
            String dateHospitalisationStr = request.getParameter("DateHospitalisation_ficheDossier");
            DossierHospitalisation dossier = sessionMEDECIN.trouverDossierParId(idDossier);
            if (dossier != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                if (dateHospitalisationStr != null && !dateHospitalisationStr.isEmpty()) {
                    dossier.setDateHospitalisation(sdf.parse(dateHospitalisationStr));
                }
                sessionMEDECIN.modifierDossier(dossier);
                request.setAttribute("message", "Dossier modifié avec succès.");
                System.out.println("Modification réussie : " + dossier.getId());
            } else {
                request.setAttribute("message", "Dossier introuvable.");
            }
        } 
        else if (act.equals("modifierDossierPersonnel")){
            jspClient="/landing_page.jsp";
            Long idDossier= Long.parseLong(request.getParameter("id_dossierFiche"));
            String dateArriveeStr = request.getParameter("DateArrivee_ficheDossier");
            String dateDepartStr = request.getParameter("DateDepart_ficheDossier");
            DossierHospitalisation dossier = sessionPERSONNEL.trouverDossierParId(idDossier);
            if (dossier != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                if (dateArriveeStr != null && !dateArriveeStr.isEmpty()) {
                    dossier.setHeureArrivee(sdf.parse(dateArriveeStr));
                }
                if (dateDepartStr != null && !dateDepartStr.isEmpty()) {
                    dossier.setHeureDepart(sdf.parse(dateDepartStr));
                }
                sessionPERSONNEL.modifierDossier(dossier);
                request.setAttribute("message", "Dossier modifié avec succès.");
                System.out.println("Modification réussie : " + dossier.getId());
            } else {
                request.setAttribute("message", "Dossier introuvable.");
            }
        } 
        else if (act.equals("modifierActe")){
            jspClient="/landing_page.jsp";
            Long idActe=Long.parseLong(request.getParameter("id_acte"));
            String nomActe=request.getParameter("acte_nom");
            String descriptionActe=request.getParameter("acte_description");
            String prixActe=request.getParameter("acte_prix");
            String coefSecu = request.getParameter("coefSecu");
            String coefMutuelle = request.getParameter("coefMutuelle");
            
            Acte acte=sessionADMIN.trouverActeParId(idActe);
            if(acte!=null){
                if(nomActe.trim().isEmpty() || descriptionActe.trim().isEmpty() || prixActe.trim().isEmpty() || coefSecu.trim().isEmpty() || coefMutuelle.trim().isEmpty()){    request.setAttribute("message", "ERREUR dans le formulaire de modification, l'un des champs est vide");
                
                }
                else{
                    acte.setActeNom(nomActe);
                acte.setActeDescription(descriptionActe);
                acte.setActePrix(Double.parseDouble(prixActe));
                acte.setCoefficient_SecuriteSociale(Double.parseDouble(coefSecu));
                acte.setCoefficient_Mutuelle(Double.parseDouble(coefMutuelle));
                sessionADMIN.modifierActe(acte);
                request.setAttribute("message", "Acte modifié avec succès.");
                System.out.println("Modification réussie : " + acte.getId());
                }
            }else {
                request.setAttribute("message", "Acte introuvable.");
            }
        }
        else if (act.equals("afficherInfosPerso")) {
            String utilisateurIdentifie = (String) session.getAttribute("utilisateur2");
            if (utilisateurIdentifie != null) {
                Z_USER user = sUPERSession.trouverUserParLogin(utilisateurIdentifie);
                if (user != null) {
                    Z_PERSONNE personne = user.getPersonne();
                    request.setAttribute("personne", personne);
                    RoleUSER role = user.getRole();
                    request.setAttribute("role", role);
                    jspClient = "/EspacePersonnel.jsp";
                } else {
                    jspClient = "/z_user_auth.jsp";
                    request.setAttribute("message", "Utilisateur introuvable. Veuillez vous reconnecter.");
                }
            } else {
                jspClient = "/z_user_auth.jsp";
                request.setAttribute("message", "Veuillez vous connecter pour accéder à votre espace.");
            }
        }
        else if (act.equals("creerService")) {
            jspClient="/landing_page.jsp";
            String nomService = request.getParameter("nomService");
            String localisationService = request.getParameter("localisationService");
            if (nomService.trim().isEmpty() || localisationService.trim().isEmpty()) {
                String message = "Formulaire incomplet..";
                request.setAttribute("message", message);
            } else {
                sessionADMIN.CreerService( nomService, localisationService);
            String message = "Service créé avec succès.";
                request.setAttribute("message", message);
        
            }
            
            }
        else if (act.equals("ajouterDossierForm")){
            jspClient = "/AjouterDossier.jsp";
            List<Z_PATIENT> lesPatients = sessionMEDECIN.trouverTousLesPatients();
            request.setAttribute("listePatientsAjoutDossier", lesPatients);
            
            List<Service> lesServices = sessionMEDECIN.tousLesServices();
            request.setAttribute("listeServicesAjoutDossier", lesServices);
        }
        else if (act.equals("creerActe")){
            jspClient="/landing_page.jsp";
            String acteNom = request.getParameter("acteNom");
            String acteDescription = request.getParameter("acteDescription");
            String actePrix = request.getParameter("actePrix");
            String coefSecu = request.getParameter("coefSecu");
            String coefMutuelle = request.getParameter("coefMutuelle");
            if(acteNom.trim().isEmpty() || acteDescription.trim().isEmpty() || actePrix.trim().isEmpty() || coefSecu.trim().isEmpty() || coefMutuelle.trim().isEmpty()){
                String message = "Formulaire de création patient erroné";
                request.setAttribute("message", message);
            } else {
                double prix = Double.parseDouble(actePrix);
                double coefSecu1 = Double.parseDouble(coefSecu);
                double coefMutuelle2 = Double.parseDouble(coefMutuelle);
                sessionADMIN.creerActe(acteNom,acteDescription, prix, coefSecu1, coefMutuelle2);
                String message = "Acte créé";
                request.setAttribute("message", message);
            }
        }
        else if (act.equals("ajouterJournal")){
            
//            ci-dessous, c'est l'ID du dossier médical propre à l'ajout d'un journal
            String id_dossier = request.getParameter("id_ajouterJournal");
            String role_user = (String) session.getAttribute("role2");
            String user = (String) session.getAttribute("user_identifié");
            Long id_user = Long.parseLong((String) session.getAttribute("id_user"));
            
            DossierHospitalisation dossier = sessionMEDECIN.trouverDossierParId(Long.parseLong(id_dossier));
            JournalActe JournalExistant = sessionMEDECIN.trouverJournalParDossier(dossier);
            List<Z_MEDECIN> lesMedecins2 = sessionMEDECIN.trouverTousLesMedecins();
            request.setAttribute("listeMedecins", lesMedecins2);
            if (JournalExistant != null) {
                // Le journal existe déjà, on ne le recrée pas
        request.setAttribute("journal_object", JournalExistant);
        System.out.println("Le journal existe déjà, on ne le recrée pas");
                // On récupère les lignes du journal
        List<LigneJournal> lignes = sessionMEDECIN.listerLignesParJournal(JournalExistant.getId());
        request.setAttribute("lignes_journals", lignes);
        
        
            } else {
//                journal existe pas déjà, on le crée
                JournalActe journal = sessionMEDECIN.creerJournal(dossier);
                request.setAttribute("journal_object", journal);
                System.out.println("journal existe pas déjà, on le crée");
                request.setAttribute("lignes_journals", null); 
            }
            List<Acte> lesActes = sessionMEDECIN.trouverTousLesActes();
            request.setAttribute("listeActeJournal", lesActes);
            jspClient = "/ficheJournal.jsp";
        }
        
        else if (act.equals("ajouterLignesJournal")){
            jspClient="/landing_page.jsp";
//            on récupère dans chaque variable plusieurs strings
             String[] id_ligne = request.getParameterValues("idligne[]"); 
             String[] commentaires_acte = request.getParameterValues("commentaire[]"); 
             String[] date_acte = request.getParameterValues("date[]");
             String[] quantite_acte = request.getParameterValues("quantite[]");
             String[] idActe_acte = request.getParameterValues("id_acte[]");
             String[] idMedecin = request.getParameterValues("id_medecin[]");
             String idjournal = (String) request.getParameter("id_journal");
//              Récupération du rôle utilisateur
            String role_user = (String) session.getAttribute("role2");
           JournalActe journal2 =  role_user.equals("MEDECIN") ?  sessionMEDECIN.trouverJournalParId(Long.valueOf(idjournal)) :
                                    role_user.equals("PERSONNEL") ? sessionPersonnelFinancier.trouverJournalParId(Long.valueOf(idjournal)) : null;
             
//             il faut que toutes les lignes soient saisies, c'est à dire que le nombre de champs remplis par type de champ soit égale

             if(commentaires_acte.length != date_acte.length ||
                     commentaires_acte.length != quantite_acte.length ||
                     commentaires_acte.length != idActe_acte.length ||
                     idMedecin.length != commentaires_acte.length ||
                     journal2.getStatut() == statutJournal.Validé || journal2.getStatut()==null){
                 jspClient="/landing_page.jsp";
                 String message = "ERREUR CHAMPS MANQUANTS OU LE JOURNAL N'EST PLUS MODIFIABLE";
                 
             } else {
                 for(int i = 0; i<commentaires_acte.length;i++) {
                     
                     String commentaire = commentaires_acte[i];
                     String date = date_acte[i];                     
                     String quantite = quantite_acte[i];
                     String idActe = idActe_acte[i];
                     String idMedecin2 = idMedecin[i];                     
                     String idLigneStr = (id_ligne != null && i < id_ligne.length) ? id_ligne[i] : null;
                     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                     Date dateCreationActe = sdf.parse(date);
                     int quantite2 = Integer.parseInt(quantite);
                     if (role_user.equals("MEDECIN")) { 
//                         JournalActe journal = sessionMEDECIN.trouverJournalParId(Long.valueOf(idjournal)); tester avec cette ligne pour le debuggage
                         JournalActe journal = journal2;
                         Acte acte = sessionMEDECIN.trouverActeParId(Long.valueOf(idActe));
                         LigneJournal existingLigne = sessionMEDECIN.trouverLigneParId(Long.valueOf(idLigneStr));
                         Z_MEDECIN medecin = (Z_MEDECIN) sessionMEDECIN.trouverPersonneParId(Long.valueOf(idMedecin2));
                         if (existingLigne !=null) {
                             System.out.println("ligne existante, on modifie");
//                         mettre à jour la ligne eixstante
                        existingLigne.setCommentaire(commentaire);
                        existingLigne.setDate_acte(dateCreationActe);
                        existingLigne.setQuantité_Acte(quantite2);
                        existingLigne.setId_acte(acte);

                        // Méthode "update" ou "edit"
                        sessionMEDECIN.mettreAJourLigne(existingLigne);
                         } else {
                             sessionMEDECIN.creerLigne(dateCreationActe, quantite2, commentaire, acte, journal, medecin);
                         }
                     }else if (role_user.equals("PERSONNEL")) {
//                         JournalActe journal = sessionPersonnelFinancier.trouverJournalParId(Long.valueOf(idjournal));
                         JournalActe journal = journal2;
                         Acte acte = sessionPersonnelFinancier.trouverActeParId(Long.valueOf(idActe));
                         LigneJournal existingLigne = sessionPersonnelFinancier.trouverLigneParId(Long.valueOf(idLigneStr));
                         Z_MEDECIN medecin = (Z_MEDECIN) sessionPersonnelFinancier.trouverPersonneParId(Long.valueOf(idMedecin2));
                         if (existingLigne !=null) {
                             System.out.println("ligne existante, on modifie");
//                         mettre à jour la ligne eixstante
                        existingLigne.setCommentaire(commentaire);
                        existingLigne.setDate_acte(dateCreationActe);
                        existingLigne.setQuantité_Acte(quantite2);
                        existingLigne.setId_acte(acte);

                        // Méthode "update" ou "edit"
                        sessionPersonnelFinancier.mettreAJourLigne(existingLigne);
                         } else {
                             sessionPersonnelFinancier.creerLigne(dateCreationActe, quantite2, commentaire, acte, journal, medecin);
                         }
                     }
                 }
             }
        }
        
        
        else if (act.equals("validerJournal")){
            jspClient = "/landing_page.jsp";
            Long id_journal = Long.parseLong(request.getParameter("id_journalValidation"));
            String utilisateurIdentifie = (String) session.getAttribute("utilisateur2");
            if (utilisateurIdentifie != null) {
                Z_USER user = sUPERSession.trouverUserParLogin(utilisateurIdentifie);
                if (user != null) {
                    RoleUSER role = user.getRole();
                    request.setAttribute("role", role);
                    if (role == RoleUSER.PERSONNEL) {
                        JournalActe journal = sessionPersonnelFinancier.trouverJournalParId(id_journal);
                        if (journal == null) {
                            jspClient = "/landing_page.jsp";
                            request.setAttribute("message", "le journal sélectionné pour validation n'existe pas");
                        }
                        else {
                            sessionPersonnelFinancier.validerJournal(journal);
                            request.setAttribute("message", "journal validé");
                        }
                    } else if (role==RoleUSER.MEDECIN){
                        JournalActe journal = sessionMEDECIN.trouverJournalParId(id_journal);
                        if (journal == null) {
                            jspClient = "/landing_page.jsp";
                            request.setAttribute("message", "le journal sélectionné pour validation n'existe pas");
                        }
                        else {
                            sessionMEDECIN.validerJournal(journal);
                            request.setAttribute("message", "journal validé");
                        }
                    }   
                    else {
                        jspClient="/landing_page.jsp";
                        request.setAttribute("message","l'utilisateur connecté ne peux pas valider un journal");
                    }
                }
            }
        }        
        else if (act.equals("creerDossierMedical")) {
            jspClient = "/landing_page.jsp";

            String dateHospitalisationStr = request.getParameter("dateHospitalisation");
            String serviceIdStr = request.getParameter("serviceId");
            String patientIdStr = request.getParameter("patientId");
            boolean isNewPatient = request.getParameter("newPatientCheckbox") != null;

            String nomPatient = request.getParameter("nomPersonne");
            String prenomPatient = request.getParameter("prenomPersonne");
            String adressePatient = request.getParameter("adressePersonne");
            String numSecuPatient = request.getParameter("numSecuPatient");
            String nomMutuelle = request.getParameter("nomMutuelle");
            String adresseMutuelle = request.getParameter("adresseMutuelle");

            // Appel du Bean Session
            String message = sessionMEDECIN.creerDossierMedical(
                    dateHospitalisationStr, serviceIdStr, patientIdStr, isNewPatient,
                    nomPatient, prenomPatient, adressePatient, numSecuPatient, nomMutuelle, adresseMutuelle
            );

            request.setAttribute("message", message);
        }


        else if (act.equals("payerFacture")) {
            jspClient = "/landing_page.jsp";
            String id_facture = request.getParameter("id_payerFacture");
            String mode_paiement = request.getParameter("mode_paiement");
            String utilisateurIdentifie = (String) session.getAttribute("utilisateur2");

            if (utilisateurIdentifie != null) {
                Z_USER user = sUPERSession.trouverUserParLogin(utilisateurIdentifie);
                if (user != null) {
                    // Appel du bean session en fonction du rôle
                    String message;
                    if (user.getRole() == RoleUSER.PERSONNEL) {
                        message = sessionPersonnelFinancier.payerFacture(Long.parseLong(id_facture), mode_paiement, user);
                    } else if (user.getRole() == RoleUSER.PATIENT) {
                        message = sessionPATIENT.payerFacture(Long.parseLong(id_facture), mode_paiement, user);
                    } else {
                        message = "Rôle utilisateur non autorisé pour cette action.";
                    }

                    // Ajouter le message dans la requête
                    request.setAttribute("message", message);
                } else {
                    request.setAttribute("message", "Utilisateur introuvable.");
                }
            } else {
                request.setAttribute("message", "Utilisateur non identifié.");
            }
        }

        
            
        else if (act.equals("creerFacture")) { 
            
            jspClient = "/ficheFacture.jsp";
            String idDossier = request.getParameter("id_dossierCreerFacture");
            Long idJournal = Long.valueOf(request.getParameter("id_journalcreerFacture"));

            DossierHospitalisation dossier = sessionPersonnelFinancier.trouverDossierParId(Long.parseLong(idDossier));
            
            Facture factureExistant = sessionPersonnelFinancier.trouverFactureParDossier(dossier);
            
//            La facture existe déjà, on la recrée pas
            if(factureExistant != null){
                
                Facture factureCreee = factureExistant;
                request.setAttribute("facture", factureCreee);
            }
//            La facture n'existe pas, on la crée
            else {
                Facture factureCreee = sessionPersonnelFinancier.creerFacturePourJournal(idJournal);
                System.out.println("CREER FACTURE");
                
                if (factureCreee != null) {
                    request.setAttribute("message", "Facture créée avec succès ! Montant = " + factureCreee.getFactureMontant());
                    request.setAttribute("facture", factureCreee);
                    jspClient = "/ficheFacture.jsp";

                } else {
                    request.setAttribute("message", "Impossible de créer la facture (journal invalide ou non VALIDE).");
                    jspClient = "/landing_page.jsp";
                }
                
            }  
        }


        RequestDispatcher Rd; // Déclare un RequestDispatcher pour gérer la redirection ou le forwarding
        Rd = getServletContext().getRequestDispatcher(jspClient); // Récupère le dispatcher pour la JSP cible
        Rd.forward(request, response); // Forward la requête et la réponse vers la JSP cible
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}




