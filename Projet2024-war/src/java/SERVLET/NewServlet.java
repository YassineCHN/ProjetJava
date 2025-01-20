




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
import SESSION.GestionActeLocal;
import SESSION.GestionDossierHospitalisation;
import SESSION.GestionDossierHospitalisationLocal;
import SESSION.GestionFactureLocal;
import SESSION.GestionJournalActeLocal;
import SESSION.GestionLigneLocal;
import SESSION.GestionPaiementLocal;
import SESSION.GestionServiceLocal;

import SESSION.Z_USER_BEANLocal;
import SESSION.envoieMailLocal;
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
    
    
////    J'abandonne
//    @Resource(name = "mail/mySession")
//    private Session mailMonMailSession;

    @EJB
    private envoieMailLocal envoieMail;

    

    @EJB
    private GestionPaiementLocal gestionPaiement;

    @EJB
    private GestionFactureLocal gestionFacture;

    @EJB
    private GestionLigneLocal gestionLigne;

    @EJB
    private GestionJournalActeLocal gestionJournalActe;

    @EJB
    private GestionActeLocal gestionActe;

    @EJB
    private GestionDossierHospitalisationLocal gestionDossierHospitalisation;

    @EJB
    private GestionServiceLocal gestionService;

    @EJB
    private Z_USER_BEANLocal z_USER_BEAN;
   
    
    

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
            String login = request.getParameter("loginHeritage");
            String password = request.getParameter("passwordHeritage");
            
            
                
            
            
            
            if (login.trim().isEmpty() || password.trim().isEmpty()) {
                // Vérification des champs vides pour le login et le mot de passe
                String message = "L'un des items du formulaire est vide, reessayez!";
                request.setAttribute("message", message);
            } else {
                Z_USER user = z_USER_BEAN.Z_authentificationUtilisateur(login, password);
                // Vérification si l'authentification renvoie bien un utilisateur
                if (user != null) {
                    String user_identifié = user.getLogin();
                    RoleUSER role_identifié = user.getRole();
                    Long id_user =  user.getId();
                    
                    if (role_identifié != null) {
                        session.setAttribute("utilisateur2", user_identifié);
                        session.setAttribute("role2", role_identifié.name());
                        session.setAttribute("id_user", id_user);
                        request.setAttribute("message", "Bienvenue, " + role_identifié.name() + "!");
                    }

                    // Définir un message de bienvenue
                    String message = "Bienvenue, " + role_identifié.name() + "!";
                    request.setAttribute("message", message);
                } else {
                    // Si l'authentification échoue
                    String message = "Erreur d'authentification. Veuillez vérifier vos identifiants.";
                    request.setAttribute("message", message);
                }
            }
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
            List<Z_USER> lesUtilisateurs = z_USER_BEAN.trouverTousLesUtilisateurs();
            request.setAttribute("listeUtilisateur", lesUtilisateurs);
            request.setAttribute("message", "Liste des utilisateurs existants");
        }
        else if (act.equals("afficherPersonnes")) {
            jspClient = "/GestionPersonne.jsp";
            List<Z_PERSONNE> lesPersonnes = z_USER_BEAN.trouverToutesLesPersonnes();
            request.setAttribute("listePersonnes", lesPersonnes);
            request.setAttribute("message", "Liste des Personnes existants");
        }
        else if (act.equals("ajouterUtilisateur")) {
            List <Z_PERSONNE> list = z_USER_BEAN.trouverPersonnesSansUtilisateur(); 
            request.setAttribute("listepersonnes",list); 
            jspClient="/AjouterUtilisateur.jsp"; 
        }
        else if (act.equals("ajouterPersonne")) {
            List <Service> list = gestionService.tousLesServices(); 
            request.setAttribute("listeServices",list); 
            jspClient="/AjouterPersonne.jsp"; 
        }
        else if (act.equals("afficherMedecins")){
            jspClient = "/GestionMedecin.jsp";
            List<Z_MEDECIN> lesMedecins = z_USER_BEAN.trouverTousLesMedecins();
            request.setAttribute("listeMedecins", lesMedecins);
            request.setAttribute("message", "Liste des médecins existants");
        }
        else if (act.equals("afficherPatients")){
            jspClient = "/GestionPatient.jsp";
            List<Z_PATIENT> lesPatients = z_USER_BEAN.trouverTousLesPatients();
            request.setAttribute("listePatients", lesPatients);
            request.setAttribute("message", "Liste des patients existants");
        }
        else if (act.equals("afficherPersonnels")){
            jspClient = "/GestionPersonnel.jsp";
            List<Z_PERSONNEL> lesPersonnels = z_USER_BEAN.trouverTousLesPersonnels();
            request.setAttribute("listePersonnels", lesPersonnels);
            request.setAttribute("message", "Liste des membres du personnels existants");
        }
        else if (act.equals("afficherDossiers")){
            jspClient = "/GestionDossier.jsp";
            String utilisateurIdentifie = (String) session.getAttribute("utilisateur2");
            if (utilisateurIdentifie != null) {
                Z_USER user = z_USER_BEAN.trouverUserParLogin(utilisateurIdentifie);
                if(user!=null){
                    Z_PERSONNE personne = user.getPersonne();
                    request.setAttribute("personne", personne);
                    RoleUSER role = user.getRole();
                    request.setAttribute("role", role);
                    List<DossierHospitalisation> lesDossiers=null;
                    if(role==RoleUSER.ADMIN){
                        lesDossiers =  gestionDossierHospitalisation.afficherDossier();
                    } else if (role==RoleUSER.PATIENT){
                        Z_PATIENT patient=(Z_PATIENT) personne;
                        lesDossiers = gestionDossierHospitalisation.trouverTousLesDossiersUnPatient(patient);
                    } else if (role==RoleUSER.PERSONNEL){
                        Z_PERSONNEL pers=(Z_PERSONNEL) personne;
                        Service serv=pers.getService();
                        lesDossiers=gestionDossierHospitalisation.trouverTousLesDossiersUnService(serv);
                        // trouver tous les dossiers du même service que la personne connecté
                    } else if (role==RoleUSER.MEDECIN){
                        Z_MEDECIN med=(Z_MEDECIN) personne;
                        Service serv=med.getService();
                        lesDossiers=gestionDossierHospitalisation.trouverTousLesDossiersUnService(serv);
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
            List<Service> lesServices = gestionService.tousLesServices();
            request.setAttribute("listeService", lesServices);
            request.setAttribute("message", "Liste des Services existants");
        }
        else if (act.equals("afficherActes")){
            jspClient = "/GestionActe.jsp";
            List<Acte> lesActes = gestionActe.trouverTousLesActes();
            request.setAttribute("listeActe", lesActes);
            request.setAttribute("message", "Liste des Actes existants");
        }
        else if (act.equals("afficherFactures")) {
            jspClient = "/GestionFacturation.jsp";
            List<Facture> factures = (List<Facture>) gestionFacture.trouverToutesFactures();
            request.setAttribute("listeFacture", factures);
            
        }
        else if (act.equals("afficherFicheFacture")){
            jspClient ="/ficheFacture.jsp";
            String id_facture = (String) request.getParameter("id_Facture");
            Facture facture = gestionFacture.trouverFactureParID(Long.parseLong(id_facture));
            request.setAttribute("facture", facture);
        }
        else if (act.equals("afficherFicheUtilisateur")) {
            jspClient = "/ficheUtilisateur.jsp";
            String test = request.getParameter("id_utilisateur");

            Long id_utilisateur = Long.valueOf(test);

            Z_USER user = z_USER_BEAN.trouverUtilisateurParId(id_utilisateur);

            request.setAttribute("utilisateurFicheUtilisateur", user);
            List <Z_PERSONNE> list = z_USER_BEAN.trouverToutesLesPersonnes() ; 
            request.setAttribute("listepersonnes",list);
        }
        else if (act.equals("afficherFichePersonne")) {
            jspClient = "/fichePersonne.jsp";
            String test = request.getParameter("id_personne");

            Long id_personne = Long.valueOf(test);

            Z_PERSONNE pers = z_USER_BEAN.trouverPersonneParId(id_personne);
            List<Service> listeServices = gestionService.tousLesServices();
            request.setAttribute("listeServices", listeServices);
            request.setAttribute("personneFichePersonne", pers);
        }
        else if (act.equals("afficherFicheService")){
                    jspClient = "/ficheService.jsp";
            String test = request.getParameter("id_service");

            Long id_service = Long.valueOf(test);

            Service service = gestionService.trouverServiceParID(id_service);
            
            request.setAttribute("ficheService", service);
            
        }
        else if (act.equals("afficherFicheDossier")){
            jspClient = "/ficheDossier.jsp";
            String test = request.getParameter("id_dossier");
            Long id_dossier = Long.valueOf(test);
            DossierHospitalisation dossier = gestionDossierHospitalisation.trouverDossierParId(id_dossier);
            request.setAttribute("ficheDossier", dossier);
            
            String utilisateurIdentifie = (String) session.getAttribute("utilisateur2");
            if (utilisateurIdentifie != null) {
                Z_USER user = z_USER_BEAN.trouverUserParLogin(utilisateurIdentifie);
                if (user != null) {
                    Z_PERSONNE personne = user.getPersonne();
                    request.setAttribute("personne", personne);
                    RoleUSER role = user.getRole();
                    request.setAttribute("role", role);
                }
            }
        }
        else if (act.equals("afficherFicheActe")){
           jspClient = "/ficheActe.jsp";
            
            String test = request.getParameter("id_Acte");

            Long id_acte = Long.valueOf(test);
            
            Acte acte = gestionActe.trouverActeParId(id_acte);
          
            request.setAttribute("ficheActe", acte);
       }
        else if (act.equals(("afficherFicheJournalActe"))){
            jspClient="/ficheJournal.jsp";
            String id_journal = request.getParameter("id_journal_1");
            Long id_journal_bis = Long.valueOf(id_journal);
            
            JournalActe journal = gestionJournalActe.trouverJournalParId(id_journal_bis);
            request.setAttribute("journal_object", journal);
            
            List<Acte> lesActes = gestionActe.trouverTousLesActes();
            request.setAttribute("listeActeJournal", lesActes);
            
            List<LigneJournal> lignes = gestionLigne.listerLignesParJournal(journal.getId());
        request.setAttribute("lignes_journals", lignes);
        }
        else if (act.equals("afficherLignes")) {
            jspClient = "/afficherLignes.jsp";
            List<LigneJournal> lignes = gestionLigne.trouverToutesLignes();
            request.setAttribute("lignes", lignes);
        }
        else if (act.equals("afficherJournaux")) {
            jspClient = "/GestionJournal.jsp";
            List<JournalActe> journaux = (List<JournalActe>) gestionJournalActe.trouverTousLesJournaux();
            request.setAttribute("lesJournaux", journaux);
        }
        
        else if (act.equals("supprimerService")) {
            jspClient = "/landing_page.jsp";
            Long idService = Long.valueOf(request.getParameter("supprimerService"));
            Service serv=gestionService.trouverServiceParID(idService);
            if(serv != null){
                if(serv.getLesPersonnels().isEmpty()&& serv.getLesMedecins().isEmpty()){
                    if(serv.getDossierHospitalisations().isEmpty()){
                       gestionService.SupprimerService(idService);
                   request.setAttribute("message", "Service supprimé avec succès.");
                   } else {
                        request.setAttribute("message", "Impossible de supprimer le service car des dossiers y sont encore liés");
                    }
                } else {
                    request.setAttribute("message", "Impossible de supprimer le service car des membres du personnel ou médecins y sont encore liés");
                }   
            }
        }
        else if (act.equals("supprimerUtilisateur")){
            jspClient = "/landing_page.jsp";
            Long value = Long.parseLong(request.getParameter("supprimerUtilisateur"));
            Z_USER utilisateur = z_USER_BEAN.trouverUtilisateurParId(value);
            if (utilisateur != null) {
                z_USER_BEAN.supprimerUtilisateur(value);
                request.setAttribute("message", "Utilisateur supprimé avec succès.");
            } else {
                request.setAttribute("message", "Utilisateur non trouvé.");
            }
        }
       
        else if (act.equals("supprimerPersonne")){
            jspClient = "/landing_page.jsp";
            Long idPersonne = Long.parseLong(request.getParameter("supprimerPersonne"));
            Z_USER utilisateur = z_USER_BEAN.trouverUtilisateurParPers(idPersonne);
            if (utilisateur != null) {
                z_USER_BEAN.supprimerUtilisateur(utilisateur.getId());
            } 
            z_USER_BEAN.supprimerPersonne(idPersonne);
            request.setAttribute("message", "Personne supprimée avec succès.");   
        }
        else if (act.equals("supprimerDossier")) {
            jspClient="/landing_page.jsp";
            Long value = Long.parseLong(request.getParameter("id_supprimerDossier"));
            gestionDossierHospitalisation.supprimerDossier(value);
        }
        else if (act.equals("supprimerActe")){
            jspClient="/landing_page.jsp";
            Long value = Long.parseLong(request.getParameter("id_supprimerActe"));
            gestionActe.supprimerActe(value);
        }
        else if (act.equals("annulerDossier")) {
            jspClient="/landing_page.jsp";
            Long value = Long.parseLong(request.getParameter("id_annulerDossier"));
            gestionDossierHospitalisation.annulerDossierHospitalisation(value);
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
                    pers=z_USER_BEAN.trouverPersonneParId(persId);
                    
                    // Validation pour vérifier si la personne a déjà un utilisateur
                    if (pers != null && z_USER_BEAN.personneHasUser(persId)) {
                        request.setAttribute("erreur", "Cette personne a déjà un utilisateur associé.");
                        List<Z_PERSONNE> listePersonnes = z_USER_BEAN.trouverPersonnesSansUtilisateur();
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
                        List<Z_PERSONNE> listePersonnes = z_USER_BEAN.trouverToutesLesPersonnes(); // Charger la liste des personnes
                        request.setAttribute("listepersonnes", listePersonnes);
                        request.getRequestDispatcher("/AjouterUtilisateur.jsp").forward(request, response);
                        return;
                    }
                }
                
                boolean utilisateurCree = z_USER_BEAN.creerUtilisateur(login, mdp, role, pers);
                 if (!utilisateurCree) {        // Si l'utilisateur n'a pas été créé, afficher un message d'erreur
                     request.setAttribute("erreur", "Ce login existe déjà.");
                     List<Z_PERSONNE> listePersonnes = z_USER_BEAN.trouverToutesLesPersonnes();
                     request.setAttribute("listepersonnes", listePersonnes);
                     request.getRequestDispatcher("/AjouterUtilisateur.jsp").forward(request, response);
                     return;
    }

    if (!utilisateurCree) {
        // Si l'utilisateur n'a pas été créé, afficher un message d'erreur
        request.setAttribute("erreur", "Ce login existe déjà.");
        List<Z_PERSONNE> listePersonnes = z_USER_BEAN.trouverToutesLesPersonnes();
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
                        List<Service> listeServices = gestionService.tousLesServices();
                        request.setAttribute("listeServices", listeServices);
                        request.getRequestDispatcher("AjouterUtilisateur.jsp").forward(request, response);
                        return; 
                    }else{
                        Long serviceId = Long.parseLong(serviceparam);
                        Service service = gestionService.trouverServiceParID(serviceId);
                        z_USER_BEAN.creerMedecin(nom, prenom, adresse, specialite,service);
                        System.out.println("Utilisateur MEDECIN créé avec succès !");
                     }
                }
                if (typePersonne.equals("PATIENT")){
                    String numSecuSoc = request.getParameter("numSecuPatient");
                    String mutuelle=request.getParameter("nomMutuelle");
                    String adresseMut=request.getParameter("adresseMutuelle");
                    z_USER_BEAN.creerPatient(nom, prenom, adresse, numSecuSoc, mutuelle, adresseMut);
                    System.out.println("ON CREE PATIENT");
                }
                if (typePersonne.equals("PERSONNEL")){
                    String serviceparam = request.getParameter("PersonnelServiceAjouterPersonne");
                    if (serviceparam == null || serviceparam.isEmpty() ) {
                        request.setAttribute("erreur", "Un service doit être sélectionné pour créer un utilisateur de type PERSONNEL.");
                        List<Service> listeServices = gestionService.tousLesServices();
                        request.setAttribute("listeServices", listeServices);
                        request.getRequestDispatcher("AjouterUtilisateur.jsp").forward(request, response);
                        return; 
                    }
                    else{
                    Long serviceId = Long.parseLong(serviceparam);
                    Service service = gestionService.trouverServiceParID(serviceId);
               
                    z_USER_BEAN.creerPersonnel(nom, prenom, adresse, service);
                    System.out.println("Utilisateur PERSONNEL créé avec succès !"); 
                    }
                }
        }      
        else if (act.equals("creerPatient")){
            jspClient="/landing_page.jsp";
            String nom = request.getParameter("nomPatient");
            String prenom = request.getParameter("prenomPatient");
            String adresse = request.getParameter("adressePatient");
            String numSecuPatient = request.getParameter("numSecuPatient");
            String nomMut = request.getParameter("nomMutuelle");
            String adresseMut  = request.getParameter("adresseMutuelle");
            if(nom.trim().isEmpty() || prenom.trim().isEmpty() || numSecuPatient.trim().isEmpty() ){
                String message = "Formulaire de création patient erroné";
                request.setAttribute("message", message);
            } else {
                z_USER_BEAN.creerPatient(nom, prenom, adresse, numSecuPatient, nomMut, adresseMut);
                String message = "Patient créé";
                request.setAttribute("message", message);
            }
            
        }
        else if (act.equals("modifierService")) {
            jspClient="/landing_page.jsp";
            String idServiceStr = request.getParameter("id_service");
            if (idServiceStr != null && !idServiceStr.isEmpty()) {
        try {
            Long idService = Long.parseLong(idServiceStr);  
            String loc = request.getParameter("localisation");
            String nomService = request.getParameter("nomService");

            Service serv = gestionService.trouverServiceParID(idService);
            if (serv != null) {
                serv.setServiceLocalisation(loc);
                serv.setServiceNom(nomService);
                gestionService.modifierService(serv);
            } else {
                request.setAttribute("message", "Service non trouvé.");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "ID du service invalide.");
        }
            } else {
                request.setAttribute("message", "ID du service manquant.");
            }
        }

        else if (act.equals("modifierUtilisateur")) {
            jspClient="/landing_page.jsp";
            Long idUtilisateur= Long.parseLong(request.getParameter("id_utilisateurFicheUtilisateur"));
            String login = request.getParameter("login_ficheUtilisateur");
            String mdp = request.getParameter("password_ficheUtilisateur");
            String role= request.getParameter("role_ficheUtilisateur");
            String idpersonne=request.getParameter("UserPersonne");
            
            Z_USER user=z_USER_BEAN.trouverUtilisateurParId(idUtilisateur);
            Z_PERSONNE pers=null;
            if(user !=null){
                if(idpersonne!=null&& !idpersonne.trim().isEmpty()){
                    Long persId= Long.parseLong(idpersonne);
                    pers=z_USER_BEAN.trouverPersonneParId(persId);
                }
                user.setLogin(login);
                user.setMdp(mdp);
                user.setPersonne(pers);
                z_USER_BEAN.modifierUtilisateur(user);
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
            Z_PERSONNE personne = z_USER_BEAN.trouverPersonneParId(idPersonne);
            if (personne != null) {
                personne.setNomPersonne(nom);
                personne.setPrenomPersonne(prenom);
                personne.setAdresse(adresse);
                // En fonction du type de personne, vous pouvez faire des mises à jour supplémentaires
                if (typePersonne.equals("MEDECIN")) {
                    String specialite = request.getParameter("specialiteMedecin");
                    ((Z_MEDECIN) personne).setSpecialite(specialite);
                    String serviceparamM = request.getParameter("serviceMedecin");
                    Service service = gestionService.trouverServiceParID(Long.parseLong(serviceparamM));
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
                    Service service = gestionService.trouverServiceParID(Long.parseLong(serviceparamP));
                    ((Z_PERSONNEL) personne).setService(service);
                }// Appelez la méthode pour modifier la personne dans la base de données
                z_USER_BEAN.modifierPersonne(personne);
                request.setAttribute("message", "Personne modifiée avec succès.");} else {
                request.setAttribute("message", "Personne non trouvée.");
            } 
        }
        else if (act.equals("modifierDossier")){
            jspClient="/landing_page.jsp";
            Long idDossier= Long.parseLong(request.getParameter("id_dossierFiche"));
            String statutDossier = request.getParameter("StatutDossier");
            String dateHospitalisationStr = request.getParameter("DateHospitalisation_ficheDossier");
            String dateArriveeStr = request.getParameter("DateArrivee_ficheDossier");
            String dateDepartStr = request.getParameter("DateDepart_ficheDossier");
            DossierHospitalisation dossier = gestionDossierHospitalisation.trouverDossierParId(idDossier);
            if (dossier != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                if (dateHospitalisationStr != null && !dateHospitalisationStr.isEmpty()) {
                    dossier.setDateHospitalisation(sdf.parse(dateHospitalisationStr));
                }
                if (dateArriveeStr != null && !dateArriveeStr.isEmpty()) {
                    dossier.setHeureArrivee(sdf.parse(dateArriveeStr));
                }
                if (dateDepartStr != null && !dateDepartStr.isEmpty()) {
                    dossier.setHeureDepart(sdf.parse(dateDepartStr));
                }
                gestionDossierHospitalisation.modifierDossier(dossier);
                request.setAttribute("message", "Dossier modifié avec succès.");
                System.out.println("Modification réussie : " + dossier.getId());
            } else {
                request.setAttribute("message", "Dossier introuvable.");
            }
        }
        else if (act.equals("afficherInfosPerso")) {
            String utilisateurIdentifie = (String) session.getAttribute("utilisateur2");
            if (utilisateurIdentifie != null) {
                Z_USER user = z_USER_BEAN.trouverUserParLogin(utilisateurIdentifie);
                if (user != null) {
                    Z_PERSONNE personne = user.getPersonne();
                    request.setAttribute("personne", personne);
                    RoleUSER role = user.getRole();
                    request.setAttribute("role", role);
                    jspClient = "/EspacePersonnel.jsp";
                } else {
                    jspClient = "/login.jsp";
                    request.setAttribute("message", "Utilisateur introuvable. Veuillez vous reconnecter.");
                }
            } else {
                jspClient = "/login.jsp";
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
                gestionService.CreerService( nomService, localisationService);
            String message = "Service créé avec succès.";
                request.setAttribute("message", message);
        
            }
            
            }
        else if (act.equals("ajouterDossierForm")){
            jspClient = "/AjouterDossier.jsp";
            List<Z_PATIENT> lesPatients = z_USER_BEAN.trouverTousLesPatients();
            request.setAttribute("listePatientsAjoutDossier", lesPatients);
            
            List<Service> lesServices = gestionService.tousLesServices();
            request.setAttribute("listeServicesAjoutDossier", lesServices);
        }
        else if (act.equals("creerActe")){
            jspClient="/landing_page.jsp";
            String acteNom = request.getParameter("acteNom");
            String acteDescription = request.getParameter("acteDescription");
            String actePrix = request.getParameter("actePrix");
            if(acteNom.trim().isEmpty() || acteDescription.trim().isEmpty() || actePrix.trim().isEmpty()){
                String message = "Formulaire de création patient erroné";
                request.setAttribute("message", message);
            } else {
                double prix = Double.parseDouble(actePrix);
                gestionActe.creerActe(actePrix, acteNom, prix);
                String message = "Acte créé";
                request.setAttribute("message", message);
            }
        }
        else if (act.equals("ajouterJournal")){
            
//            ci-dessous, c'est l'ID du dossier médical propre à l'ajout d'un journal
            String id_dossier = request.getParameter("id_ajouterJournal");
            String role_user = (String) session.getAttribute("role2");
            String user = (String) session.getAttribute("user_identifié");
            Long id_user = Long.valueOf((Long) session.getAttribute("id_user"));
            Z_USER user_2 = z_USER_BEAN.trouverUtilisateurParId(id_user);
            
            DossierHospitalisation dossier = gestionDossierHospitalisation.trouverDossierParId(Long.parseLong(id_dossier));
            JournalActe JournalExistant = gestionJournalActe.trouverJournalParDossier(dossier);
            
            if (JournalExistant != null) {
                // Le journal existe déjà, on ne le recrée pas
        request.setAttribute("journal_object", JournalExistant);
        System.out.println("Le journal existe déjà, on ne le recrée pas");
                // On récupère les lignes du journal
        List<LigneJournal> lignes = gestionLigne.listerLignesParJournal(JournalExistant.getId());
        request.setAttribute("lignes_journals", lignes);
        
        
            } else {
//                journal existe pas déjà, on le crée
                JournalActe journal = gestionJournalActe.creerJournal(dossier, user_2);
                request.setAttribute("journal_object", journal);
                System.out.println("journal existe pas déjà, on le crée");
                request.setAttribute("lignes_journals", null); 
            }
            List<Acte> lesActes = gestionActe.trouverTousLesActes();
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
             String idjournal = (String) request.getParameter("id_journal");
             JournalActe journal2 = gestionJournalActe.trouverJournalParId(Long.valueOf(idjournal));
//             il faut que toutes les lignes soient saisies, c'est à dire que le nombre de champs remplis par type de champ soit égale

             if(commentaires_acte.length != date_acte.length || commentaires_acte.length != quantite_acte.length || commentaires_acte.length != idActe_acte.length || journal2.getStatut() == statutJournal.Validé){
                 jspClient="/landing_page.jsp";
                 String message = "ERREUR CHAMPS MANQUANTS OU LE JOURNAL N'EST PLUS MODIFIABLE";
                 System.out.println("erreur au niveau des lignes, les lignes ne sont pas toutes pleines");
                 System.out.println("Sinon c'est que le journal est déjà validé et que donc l'ajout de ligne est interdit");
             } else {
                 for(int i = 0; i<commentaires_acte.length;i++) {
                     
                     
                     
                     String commentaire = commentaires_acte[i];
                     System.out.println(commentaire);
                     String date = date_acte[i];
                     System.out.println(date);
                     String quantite = quantite_acte[i];
                     System.out.println(quantite);
                     String idActe = idActe_acte[i];
                     System.out.println(idActe);
//                     String id_ligne2 = id_ligne[i];
                    
                     
                     String idLigneStr = (id_ligne != null && i < id_ligne.length) ? id_ligne[i] : null;
                      System.out.println(idLigneStr);
                     
                     
                     
                     
                     
                     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                     Date dateCreationActe = sdf.parse(date);
                     int quantite2 = Integer.parseInt(quantite);
                     
                     JournalActe journal = gestionJournalActe.trouverJournalParId(Long.valueOf(idjournal));
                     Acte acte = gestionActe.trouverActeParId(Long.valueOf(idActe));
                     LigneJournal existingLigne = gestionLigne.trouverLigneParId(Long.valueOf(idLigneStr));
                     
//                     Si la ligne existe déjà, on la modifie
//                       sinon on la crée
                        System.out.println("ligne existante ????");
                        
                     if (existingLigne != null){
                         System.out.println("ligne existante, on modifie");
//                         mettre à jour la ligne eixstante
                        existingLigne.setCommentaire(commentaire);
                        existingLigne.setDate_acte(dateCreationActe);
                        existingLigne.setQuantité_Acte(quantite2);
                        existingLigne.setId_acte(acte);

                        // Méthode "update" ou "edit"
                        gestionLigne.mettreAJourLigne(existingLigne);
                     } else {
                         System.out.println("ligne INEXISTANTE, on CREE");
                         gestionLigne.creerLigne(dateCreationActe, quantite2, commentaire, acte, journal);
                     }
                     
                 }
             }
        }
        else if (act.equals("validerJournal")){
            jspClient = "/landing_page.jsp";
            String id_journal = request.getParameter("id_journalValidation");
           JournalActe journal = gestionJournalActe.trouverJournalParId(Long.parseLong(id_journal));
           if (journal == null){
               jspClient="/landing_page.jsp";
               request.setAttribute("message", "le journal sélectionné pour validation n'existe pas");
           } else {
               gestionJournalActe.validerJournal(journal);
               request.setAttribute("message", "journal validé");
           }
            
        }
        
        
        
        
        
        else if (act.equals("creerDossierMedical")) {

            jspClient = "/landing_page.jsp";

            String dateHospitalisationStr = request.getParameter("dateHospitalisation");
            String serviceIdStr = request.getParameter("serviceId"); 
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
             
            Date dateHospitalisation_test = null;
            Date heureArrivee_test = null;
            Date heureDepart_test = null;
            if (dateHospitalisationStr != null && !dateHospitalisationStr.trim().isEmpty()) {
                dateHospitalisation_test = sdf.parse(dateHospitalisationStr);
            }

            
            Z_PATIENT patient = null;
            Long serviceId = Long.valueOf(serviceIdStr);
            Service service = gestionService.trouverServiceParID(serviceId);
            
            if (dateHospitalisation_test == null) {
                jspClient = "/landing_page.jsp";
                request.setAttribute("message", "Date d'hospitalisation obligatoire !");
            } else {
                if (request.getParameter("newPatientCheckbox") != null) {
                    String nomPatient = request.getParameter("nomPersonne");
                    String prenomPatient = request.getParameter("prenomPersonne");
                    String adressePatient = request.getParameter("adressePersonne");
                    String numSecuPatient = request.getParameter("numSecuPatient");
                    String nomMut = request.getParameter("nomMutuelle");
                    String adresseMut = request.getParameter("adresseMutuelle");
                    if (nomPatient.trim().isEmpty() || prenomPatient.trim().isEmpty() || numSecuPatient.trim().isEmpty()) {
                        jspClient = "/landing_page.jsp";
                        System.out.println("formulaire incomplet");
                    } else {
                        z_USER_BEAN.creerPatient(nomPatient, prenomPatient, adressePatient, numSecuPatient, nomMut, adresseMut);
                    }
                    patient = z_USER_BEAN.trouverPatientParNumSecu(numSecuPatient);
                    gestionDossierHospitalisation.creerDossier(patient, service, dateHospitalisation_test, heureArrivee_test, heureDepart_test);
                } else {
                    System.out.println("Chercher patient puis créer dossier");
                    String patientIdStr = request.getParameter("patientId");
                    Long patientId = Long.valueOf(patientIdStr);
                    patient = (Z_PATIENT) z_USER_BEAN.trouverPersonneParId(patientId);
                    gestionDossierHospitalisation.creerDossier(patient, service, dateHospitalisation_test, heureArrivee_test, heureDepart_test);
                }
            }
        }

        else if (act.equals("payerFacture")) {
            jspClient = "/landing_page.jsp";
            String id_facture = request.getParameter("id_payerFacture");
            String mode_paiement = request.getParameter("mode_paiement");
            Facture laFacture = gestionFacture.trouverFactureParID(Long.parseLong(id_facture));

            if (laFacture != null && !laFacture.isFacturePayee()) {
                try {
                    // Conversion de la chaîne en Enum 
                    ModePaiement mode = ModePaiement.valueOf(mode_paiement.trim().toUpperCase());

                    // Si la conversion réussit, on peut enregistrer le paiement
                    Paiement paiement = gestionPaiement.enregistrerPaiement(laFacture.getFactureMontant(),mode, laFacture);
                    // Puis valider la facture
                    gestionFacture.validerFacturePaiement(laFacture);

                    request.setAttribute("message", "La facture a été payée avec succès en mode : " + mode);

                } catch (IllegalArgumentException e) {
                    // Si mode_paiement n'existe pas dans l'énum ModePaiement
                    request.setAttribute("message", "Le mode de paiement '" + mode_paiement + "' est invalide.");
                    System.out.println("Mode de paiement invalide : " + mode_paiement);
                    
                    jspClient = "/landing_page.jsp";
                }
            } else {
                request.setAttribute("message", "Facture introuvable ou déjà payée.");
            }
        }
        else if (act.equals("creerFacture")) { 
            
            jspClient = "/ficheFacture.jsp";
            String idDossier = request.getParameter("id_dossierCreerFacture");
            Long idJournal = Long.valueOf(request.getParameter("id_journalcreerFacture"));
            System.out.println(idJournal);
            System.out.println(idDossier);

            DossierHospitalisation dossier = gestionDossierHospitalisation.trouverDossierParId(Long.parseLong(idDossier));
            
            Facture factureExistant = gestionFacture.trouverFactureParDossier(dossier);
            
//            La facture existe déjà, on la recrée pas
            if(factureExistant != null){
                
                Facture factureCreee = factureExistant;
                request.setAttribute("facture", factureCreee);
                System.out.println("FACTURE EXISTE DEJA");
            }
//            La facture n'existe pas, on la crée
            else {
                Facture factureCreee = gestionFacture.creerFacturePourJournal(idJournal);
                System.out.println("CREER FACTURE");
                
                if (factureCreee != null) {
                    System.out.println("la facture est bien cree, != null");
                    System.out.println(factureCreee);
                    request.setAttribute("message", "Facture créée avec succès ! Montant = " + factureCreee.getFactureMontant());
                    request.setAttribute("facture", factureCreee);
                    jspClient = "/ficheFacture.jsp";
                    

                } else {
                    System.out.println("FACTURE CREE EST NULL");
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




