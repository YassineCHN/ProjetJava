




package SERVLET;

import ENTITE.RolesUtilisateurs;
import ENTITE.Service;
import ENTITE.Utilisateur;
import ENTITE.Z_MEDECIN;
import ENTITE.Z_PATIENT;
import ENTITE.Z_USER;
import SESSION.GestionServiceLocal;
import SESSION.GestionUtilisateurLocal;
import SESSION.Z_USER_BEANLocal;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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
    private GestionServiceLocal gestionService;

    @EJB
    private Z_USER_BEANLocal z_USER_BEAN;

    @EJB
    private GestionUtilisateurLocal gestionUtilisateur;
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

                    // Liste des rôles valables
                    List<String> rolesValables = Arrays.asList("ADMIN", "MEDECIN", "PATIENT");

                    // Si l'authentification retourne un utilisateur, on vérifie le rôle
                    if (rolesValables.contains(role_identifié)) {
                        session.setAttribute("utilisateur2", user_identifié);
                        session.setAttribute("role2", role_identifié);
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
        else if(act.equals("afficherServices")) {
            // Action pour afficher les services, comme utilisateur
            jspClient = "/GestionService.jsp";
            List<Service> lesServices = gestionService.tousLesServices();
            request.setAttribute("listeService", lesServices);
            request.setAttribute("message", "Liste des Services existants");
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

        RequestDispatcher Rd; // Déclare un RequestDispatcher pour gérer la redirection ou le forwarding
        Rd = getServletContext().getRequestDispatcher(jspClient); // Récupère le dispatcher pour la JSP cible
        Rd.forward(request, response); // Forward la requête et la réponse vers la JSP cible
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}




