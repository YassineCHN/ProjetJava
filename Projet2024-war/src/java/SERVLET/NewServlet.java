///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package SERVLET;
//
//import ENTITE.RolesUtilisateurs;
//import ENTITE.Utilisateur;
//import ENTITE.Z_USER;
//import SESSION.GestionUtilisateurLocal;
//import SESSION.Z_USER_BEANLocal;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//import javax.ejb.EJB;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// *
// * @author charl
// */
//@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"})
//public class NewServlet extends HttpServlet {
//
//    @EJB
//    private Z_USER_BEANLocal z_USER_BEAN;
//
//    @EJB
//    private GestionUtilisateurLocal gestionUtilisateur;
//
//   
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        HttpSession session = request.getSession(true); // création/récupération de la session utilisateur
//        String jspClient = null; // Initialisation de la variable pour stocker le chemin de la JSP à afficher
//        String act = request.getParameter("action"); // Récupère l'action à partir des paramètres de la requête
//
//        if ((act == null) || (act.equals("vide"))) {
//            // Si l'action est nulle ou vide, rediriger vers la page de destination par défaut
//            jspClient = "/landing_page.jsp";
//            request.setAttribute("message", "pas d'informations");
//
//        } else if (act.equals("authentificationUtilisateur")) {
//            // Action pour authentifier un utilisateur
//            jspClient = "/landing_page.jsp";
//            String login = request.getParameter("login");
//            String password = request.getParameter("password");
//
//            if (login.trim().isEmpty() || password.trim().isEmpty()) {
//                // Vérification des champs vides pour le login et le mot de passe
//                String message = "L'un des items du formulaire est vide, reessayez!";
//                request.setAttribute("message", message);
//            } else {
//                Object[] user = gestionUtilisateur.authentificationUtilisateur(login, password);
//
//                // Vérification si l'authentification renvoie bien un utilisateur
//                // étape importante ci-dessous
//                // on vérifie que l'utilisateur retourné n'est pas nul, sinon on a un null exception
//                // si les paramètres saisis sont incorrectes alors on ramène l'user sur landing_page
//                if (user != null && user[0] != null && user[1] != null) {
//                    Utilisateur user_identifié = (Utilisateur) user[0];
//                    RolesUtilisateurs role_identifié = (RolesUtilisateurs) user[1];
//                    
//
//                    // Si l'authentification retourne un utilisateur, on vérifie le rôle
//                    if (role_identifié == RolesUtilisateurs.ADMIN) {
//                        session.setAttribute("utilisateur", user_identifié.getUtilisateurLogin());
//                        session.setAttribute("role", role_identifié.toString());
//                    } else if (role_identifié == RolesUtilisateurs.MEDECIN) {
//                        session.setAttribute("utilisateur", user_identifié.getUtilisateurLogin());
//                        session.setAttribute("role", role_identifié.toString());
//                    } else if (role_identifié == RolesUtilisateurs.PATIENT) {
//                        session.setAttribute("utilisateur", user_identifié.getUtilisateurLogin());
//                        session.setAttribute("role", role_identifié.toString());
//                    }
//
//                    // Définir un message de bienvenue
//                    String message = "Bienvenue, " + role_identifié.toString() + "!";
//                    request.setAttribute("message", message);
//                } else {
//                    // Si l'authentification échoue
//                    String message = "Erreur d'authentification. Veuillez vérifier vos identifiants.";
//                    request.setAttribute("message", message);
//                }
//            }
//        } else if (act.equals("afficherUtilisateurs")) {
//            // Action pour afficher les fournisseurs
//            jspClient = "/GestionUtilisateur.jsp";
//            List<Utilisateur> lesUtilisateurs = gestionUtilisateur.trouverTousLesUtilisateurs();
//            request.setAttribute("listeUtilisateur", lesUtilisateurs);
//            request.setAttribute("message", "Liste des fournisseurs existants");
//        } else if (act.equals("afficherFicheUtilisateur")) {
//            jspClient = "/ficheUtilisateur.jsp";
//            String test = (String) request.getParameter("id_utilisateur");
//
//            System.out.println(test);
//
//            Long id_utilisateur = Long.valueOf(test);
//
//            System.out.println("ID_utilisateur après conversion en long    " + id_utilisateur);
//
//            Utilisateur user = gestionUtilisateur.trouverUtilisateurParId(id_utilisateur);
//
//            System.out.println("ID_utilisateur après conversion en long    " + user.getUtilisateurLogin());
//
//            request.setAttribute("utilisateurFicheUtilisateur", user);
//        } else if (act.equals("authentificationUtilisateur_HERITAGE")) {
//            jspClient = "/landing_page.jsp";
//            String login = request.getParameter("loginHeritage");
//            String password = request.getParameter("passwordHeritage");
//            if (login.trim().isEmpty() || password.trim().isEmpty()) {
//                // Vérification des champs vides pour le login et le mot de passe
//                String message = "L'un des items du formulaire est vide, reessayez!";
//                request.setAttribute("message", message);
//            } else {
//                Z_USER user = z_USER_BEAN.Z_authentificationUtilisateur(login, password);
//                // Vérification si l'authentification renvoie bien un utilisateur
//                // étape importante ci-dessous
//                // on vérifie que l'utilisateur retourné n'est pas nul, sinon on a un null exception
//                // si les paramètres saisis sont incorrectes alors on ramène l'user sur landing_page
//                System.out.println("RESULT DIRECT DU BEAN");
//                System.out.println("========================================================");
//                System.out.println(user.getId());
//                System.out.println(user.getMdp());
//                System.out.println("========================================================");
//                        
//                if (user != null) {
//                    String user_identifié = user.getLogin();
//                    String role_identifié = user.getRole();
//                    System.out.println("RESULTAT DANS JSP DE BEAN AVANT OBJECT");
//                        System.out.println("========================================================");
//                        System.out.println(user_identifié);
//                        System.out.println(role_identifié);
//                        System.out.println("========================================================");
//
//                    // Si l'authentification retourne un utilisateur, on vérifie le rôle
//                    if (role_identifié == "ADMIN" || role_identifié=="MEDECIN" || role_identifié=="PATIENT") {
//                        session.setAttribute("utilisateur2", user_identifié);
//                        session.setAttribute("role2", role_identifié);
//                        System.out.println("LE OBJET RECUPERE DE BEAN");
//                        System.out.println("========================================================");
//                        System.out.println(user_identifié);
//                        System.out.println(role_identifié);
//                        System.out.println("========================================================");
//                        
//                        
//                    }
//
//                    // Définir un message de bienvenue
//                    String message = "Bienvenue, " + role_identifié + "!";
//                    request.setAttribute("message", message);
//                } else {
//                    // Si l'authentification échoue
//                    String message = "Erreur d'authentification. Veuillez vérifier vos identifiants.";
//                    request.setAttribute("message", message);
//                }
//            }
//        }
//
//        RequestDispatcher Rd; // Déclare un RequestDispatcher pour gérer la redirection ou le forwarding
//        Rd = getServletContext().getRequestDispatcher(jspClient); // Récupère le dispatcher pour la JSP cible
//        Rd.forward(request, response); // Forward la requête et la réponse vers la JSP cible
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}




package SERVLET;

import ENTITE.RolesUtilisateurs;
import ENTITE.Service;
import ENTITE.Utilisateur;
import ENTITE.Z_MEDECIN;
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

        } else if (act.equals("authentificationUtilisateur_HERITAGE")) {
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
        } else if (act.equals("afficherUtilisateurs")) {
            // Action pour afficher les fournisseurs
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
        else if(act.equals("afficherServices")) {
            // Action pour afficher les services, comme utilisateur
            jspClient = "/GestionService.jsp";
            List<Service> lesServices = gestionService.tousLesServices();
            request.setAttribute("listeService", lesServices);
            request.setAttribute("message", "Liste des Services existants");
        }
//        else if(act.equals("afficherMedecins")) {
//            // Action pour afficher les services, comme utilisateur
//            jspClient = "/GestionMedecin.jsp";
//            List<Z_USER> lesServices = z_USER_BEAN.;
//            request.setAttribute("listeService", lesServices);
//            request.setAttribute("message", "Liste des Services existants");
//        }
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
            }
        else if (act.equals("modifierUtilisateur")) {
            jspClient="/GestionUtilisateur.jsp";
            
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
