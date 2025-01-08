




package SERVLET;

//import ENTITE.Acte;
import ENTITE.Acte;
import ENTITE.DossierHospitalisation;
import ENTITE.JournalActe;
import ENTITE.LigneJournal;

import ENTITE.Service;

import ENTITE.Z_MEDECIN;
import ENTITE.Z_PATIENT;
import ENTITE.Z_USER;
import SESSION.GestionActeLocal;
import SESSION.GestionDossierHospitalisationLocal;
import SESSION.GestionJournalActeLocal;
import SESSION.GestionLigneLocal;
import SESSION.GestionServiceLocal;

import SESSION.Z_USER_BEANLocal;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
                    String role_identifié = user.getRole();
                    Long id_user =  user.getId();

                    // Liste des rôles valables
                    List<String> rolesValables = Arrays.asList("ADMIN", "MEDECIN", "PATIENT");

                    // Si l'authentification retourne un utilisateur, on vérifie le rôle
                    if (rolesValables.contains(role_identifié)) {
                        session.setAttribute("utilisateur2", user_identifié);
                        session.setAttribute("role2", role_identifié);
                        session.setAttribute("id_user", id_user);
                        
                    }

                    // Définir un message de bienvenue
                    String message = "Bienvenue, " + role_identifié + "!";
                    request.setAttribute("message", message);
                } else {
                    // Si l'authentification échoue
                    String message = "Erreur d'authentification. Veuillez vérifier vos identifiants.";
                    request.setAttribute("message", message);
                }
            }
        }
        else if (act.equals("afficherUtilisateurs")) {
            // Action pour afficher les utilisateurs
            jspClient = "/GestionUtilisateur.jsp";
            List<Z_USER> lesUtilisateurs = z_USER_BEAN.trouverTousLesUtilisateurs();
            request.setAttribute("listeUtilisateur", lesUtilisateurs);
            request.setAttribute("message", "Liste des utilisateurs existants");
        }
        else if (act.equals("ajouterUtilisateur")) {
            List <Service> list = gestionService.tousLesServices(); 
            request.setAttribute("listeservices",list); 
            jspClient="/AjouterUtilisateur.jsp"; 
        }
        else if (act.equals("afficherUtilisateursMedecins")){
            jspClient = "/GestionMedecin.jsp";
            List<Z_MEDECIN> lesUtilisateurs = z_USER_BEAN.trouverTousLesUtilisateursMedecins();
            request.setAttribute("listeUtilisateurMedecins", lesUtilisateurs);
            request.setAttribute("message", "Liste des médecins existants");
        }
        else if (act.equals("afficherPatients")){
            jspClient = "/GestionPatient.jsp";
            List<Z_PATIENT> lesUtilisateurs = z_USER_BEAN.trouverTousLesUtilisateursPatients();
            request.setAttribute("listeUtilisateurPatients", lesUtilisateurs);
            request.setAttribute("message", "Liste des patients existants");
        }
        else if (act.equals("afficherDossiers")){
            jspClient = "/GestionDossier.jsp";
            List<DossierHospitalisation> lesDossiers =  gestionDossierHospitalisation.afficherDossier();
            request.setAttribute("listeDossiers", lesDossiers);
            request.setAttribute("message", "Liste des dossiers existants");
//            if (lesDossiers.size() == 0) {
//                System.out.println("LA FACADE A RETOURNE AUCUN DOSSIERS (0)");
//                System.out.println(lesDossiers.get(0).getId());
//            }
//            else {
//                 System.out.println("LA FACADE A RETOURNE UN TRUC (0)");
//                 System.out.println(lesDossiers.size());
//                 
//                
//            }
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
            
        }
        else if (act.equals("afficherFicheUtilisateur")) {
            jspClient = "/ficheUtilisateur.jsp";
            String test = request.getParameter("id_utilisateur");

            Long id_utilisateur = Long.valueOf(test);

            Z_USER user = z_USER_BEAN.trouverUtilisateurParId(id_utilisateur);

            request.setAttribute("utilisateurFicheUtilisateur", user);
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
            request.setAttribute("ficheJournal", journal);
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
//            Là vous vous demander pk ça redirige vers landing_page
//            plûtot que gestionService
//            en fait, il y a une erreur quand l'user appuie sur supprimer'
//            null exception
//            On dirait que l'user est encore sur la page au moement de la suppression
//            mais la page demande les infos du service
//            Bref il faudrait que l'user soit redirigé avant suppression ou inversement
            Long value = Long.valueOf(request.getParameter("supprimerService"));
            gestionService.SupprimerService(value);
            

        }
        else if (act.equals("supprimerUtilisateur")){
            jspClient = "/landing_page.jsp";
            
            long value = Long.parseLong(request.getParameter("supprimerUtilisateur"));
            z_USER_BEAN.supprimerUtilisateur(value);
        }
        else if (act.equals("supprimerPatient")){
            jspClient = "/landing_page.jsp";
// C'est en fait le même cas que la suppression d'un utilisateur normal
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
        else if (act.equals("creerUtilisateur")) {
                jspClient="/landing_page.jsp";
//                Pourquoi pas rediriger vers GestionUtilisateur.jsp ?
//                même problème que pour la suppression d'un service
                String login = request.getParameter("loginAjouterUser");
                String mdp = request.getParameter("passwordAjouterUser");
                String role = request.getParameter("roleAjouterUser");
                if (role.equals("MEDECIN")) {
                    String specialite = request.getParameter("specialiteAjouterUser");
                    z_USER_BEAN.creerMedecin(login, mdp, specialite);
                    System.out.println("ON CREE MEDECIN");
                }
                if (role.equals("ADMIN")){
                    String status = request.getParameter("adminStatusAjouterUser");
                    z_USER_BEAN.creerAdmin(login, mdp, status);
                    System.out.println("ON CREE ADMIN");
                }
                if (role.equals("PATIENT")){
                    String numSecuSoc = request.getParameter("PatientNumSecuSocAjouterUser");
                    z_USER_BEAN.creerPatient(login, mdp, numSecuSoc);
                    System.out.println("ON CREE PATIENT");
                }
                if (role.equals("PERSONNEL")){
                    String serviceparam = request.getParameter("PersonnelServiceAjouterUser");
                    if (serviceparam == null || serviceparam.isEmpty() ) {
                        request.setAttribute("erreur", "Un service doit être sélectionné pour créer un utilisateur de type PERSONNEL.");
                        List<Service> listeServices = gestionService.tousLesServices();
                        request.setAttribute("listeServices", listeServices);
                        request.getRequestDispatcher("AjouterUtilisateur.jsp").forward(request, response);
                        return; 
                    }
                    else{
                    long serviceId = Long.parseLong(serviceparam);
                    Service service = gestionService.trouverServiceParID(serviceId);
               
                    z_USER_BEAN.creerPersonnel(login, mdp, service);
                    System.out.println("Utilisateur PERSONNEL créé avec succès !"); 
                    }
                }
        }      
        else if (act.equals("creerPatient")){
            jspClient="/landing_page.jsp";
            String login = request.getParameter("loginPatient");
            String mdp = request.getParameter("mdpPatient");
            String numSecuPatient = request.getParameter("numSecuPatient");
            if(login.trim().isEmpty() || mdp.trim().isEmpty()){
                String message = "Formulaire de création patient erroné";
                request.setAttribute("message", message);
            } else {
                z_USER_BEAN.creerPatient(login, mdp, numSecuPatient);
                String message = "Formulaire de création patient erroné";
                request.setAttribute("message", message);
            }
            
        }
        else if (act.equals("modifierUtilisateur")) {
            jspClient="/GestionUtilisateur.jsp";
            
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
            List<Z_PATIENT> lesUtilisateurs = z_USER_BEAN.trouverTousLesUtilisateursPatients();
            request.setAttribute("listeUtilisateurPatientsAjoutDossier", lesUtilisateurs);
            
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
            String id_journal = request.getParameter("id_ajouterJournal");
            String role_user = (String) session.getAttribute("role2");
            String user = (String) session.getAttribute("user_identifié");
            Long id_user = Long.valueOf((Long) session.getAttribute("id_user"));
            
            DossierHospitalisation dossier = gestionDossierHospitalisation.trouverDossierParId(Long.parseLong(id_journal));
            Z_USER user_2 = z_USER_BEAN.trouverUtilisateurParId(id_user);
            JournalActe journal = gestionJournalActe.creerJournal(dossier, user_2);
//            String journalId = String.valueOf(journal.getId());
//            request.setAttribute("id_journal", journalId);
            request.setAttribute("journal_object", journal);
            List<Acte> lesActes = gestionActe.trouverTousLesActes();
            request.setAttribute("listeActeJournal", lesActes);

            
            jspClient = "/ficheJournal.jsp";
        }
        else if (act.equals("ajouterLignesJournal")){
            jspClient="/landing_page.jsp";
            
             String[] commentaires_acte = request.getParameterValues("commentaire[]"); 
             String[] date_acte = request.getParameterValues("date[]");
             String[] quantite_acte = request.getParameterValues("quantite[]");
             String[] idActe_acte = request.getParameterValues("id_acte[]");
             String idjournal = (String) request.getParameter("id_journal");
             if(commentaires_acte.length != date_acte.length || commentaires_acte.length != quantite_acte.length || commentaires_acte.length != idActe_acte.length){
                 jspClient="/landing_page.jsp";
                 String message = "ERREUR CHAMPS MANQUANTS";
                 System.out.println("erreur au niveau des lignes, les lignes ne sont pas toutes pleines");
             } else {
                 for(int i = 0; i<commentaires_acte.length;i++) {
                     String commentaire = commentaires_acte[i];
                     String date = date_acte[i];
                     String quantite = quantite_acte[i];
                     String idActe = idActe_acte[i];
                     System.out.println(commentaire);
                     System.out.println(date);
                     System.out.println(quantite);
                     System.out.println(idActe);
                     JournalActe journal = gestionJournalActe.trouverJournalParId(Long.valueOf(idjournal));
                     Acte acte = gestionActe.trouverActeParId(Long.valueOf(idActe));
                     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                     Date dateCreationActe = sdf.parse(date);
                     int quantite2 = Integer.parseInt(quantite);
                     
                     gestionLigne.creerLigne(dateCreationActe, quantite2, commentaire, acte, journal);
                 }
             }
                 
             
             
        }
        else if (act.equals("creerDossierMedical")) {
   
            jspClient="/landing_page.jsp";
            
            String dateHospitalisationStr = request.getParameter("dateHospitalisation");
            String heureArriveeStr = request.getParameter("heureArrivee");
            String heureDepartStr = request.getParameter("heureDepart");
            String serviceIdStr = request.getParameter("serviceId"); 
            
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//             Date dateHospitalisation = null;
//             Date heureArrivee = null;
//             Date heureDepart = null;
             
            Date dateHospitalisation_test = sdf.parse(dateHospitalisationStr);
            Date heureArrivee_test = sdf.parse(heureArriveeStr);
            Date heureDepart_test = sdf.parse(heureDepartStr);
             Z_PATIENT patient = null;
             Long serviceId = Long.valueOf(serviceIdStr);
             Service service = gestionService.trouverServiceParID(serviceId);
             
             if (request.getParameter("newPatientCheckbox") != null) {
                String loginPatient = request.getParameter("loginPatient");
                String mdpPatient = request.getParameter("mdpPatient");
                String numSecuPatient = request.getParameter("numSecuPatient");
                
                
                
                
                
                if (loginPatient.trim().isEmpty() || mdpPatient.trim().isEmpty() || numSecuPatient.trim().isEmpty() ) {
                    jspClient = "/landing_page.jsp";
                    System.out.println("formulaire incomplet");
                }
                else {
                    z_USER_BEAN.creerPatient(loginPatient, mdpPatient, numSecuPatient);
                }
                
                patient = z_USER_BEAN.trouverPatientParNumSecu(numSecuPatient);
                
                
                 gestionDossierHospitalisation.creerDossier(patient, service, dateHospitalisation_test, heureArrivee_test, heureDepart_test);
             }
             else {
                 System.out.println("Chercher patient puis créer dossier");
                System.out.println("===========================================================================");
                System.out.println("===========================================================================");
                System.out.println("===========================================================================");
                 String patientIdStr = request.getParameter("patientId");
                 Long patientId = Long.valueOf(patientIdStr);
                 patient = (Z_PATIENT) z_USER_BEAN.trouverUtilisateurParId(patientId);
                 gestionDossierHospitalisation.creerDossier(patient, service, dateHospitalisation_test, heureArrivee_test, heureDepart_test);
             }
//  
             
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




