/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package SERVLET;

import ENTITE.RolesUtilisateurs;
import ENTITE.Utilisateur;
import SESSION.GestionUtilisateurLocal;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author charl
 */
@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

    @EJB
    private GestionUtilisateurLocal gestionUtilisateur;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        HttpSession session = request.getSession(true); // création/récupération de la session utilisateur
        String jspClient = null; // Initialisation de la variable pour stocker le chemin de la JSP à afficher
        String act = request.getParameter("action"); // Récupère l'action à partir des paramètres de la requête

        if ((act == null) || (act.equals("vide"))) {
            // Si l'action est nulle ou vide, rediriger vers la page de destination par défaut
            jspClient = "/landing_page.jsp";
            request.setAttribute("message", "pas d'informations");

        } else if (act.equals("authentificationUtilisateur")) {
            // Action pour authentifier un utilisateur
            jspClient = "/landing_page.jsp";
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            if (login.trim().isEmpty() || password.trim().isEmpty()) {
                // Vérification des champs vides pour le login et le mot de passe
                String message = "L'un des items du formulaire est vide, reessayez!";
                request.setAttribute("message", message);
            } else {
                Object[] user = gestionUtilisateur.authentificationUtilisateur(login, password);

                // Vérification si l'authentification renvoie bien un utilisateur
                // étape importante ci-dessous
                // on vérifie que l'utilisateur retourné n'est pas nul, sinon on a un null exception
                // si les paramètres saisis sont incorrectes alors on ramène l'user sur landing_page
                if (user != null && user[0] != null && user[1] != null) {
                    Utilisateur user_identifié = (Utilisateur) user[0];
                    RolesUtilisateurs role_identifié = (RolesUtilisateurs) user[1];

                    // Si l'authentification retourne un utilisateur, on vérifie le rôle
                    if (role_identifié == RolesUtilisateurs.ADMIN) {
                        session.setAttribute("utilisateur", user_identifié.getUtilisateurLogin());
                        session.setAttribute("role", role_identifié.toString());
                    } else if (role_identifié == RolesUtilisateurs.MEDECIN) {
                        session.setAttribute("utilisateur", user_identifié.getUtilisateurLogin());
                        session.setAttribute("role", role_identifié.toString());
                    } else if (role_identifié == RolesUtilisateurs.PATIENT) {
                        session.setAttribute("utilisateur", user_identifié.getUtilisateurLogin());
                        session.setAttribute("role", role_identifié.toString());
                    }

                    // Définir un message de bienvenue
                    String message = "Bienvenue, " + role_identifié.toString() + "!";
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
            List<Utilisateur> lesUtilisateurs = gestionUtilisateur.trouverTousLesUtilisateurs();
            request.setAttribute("listeUtilisateur", lesUtilisateurs);
            request.setAttribute("message", "Liste des fournisseurs existants");
        } else if (act.equals("afficherFicheUtilisateur")) {
            jspClient = "/ficheUtilisateur.jsp";
            String test = (String) request.getParameter("id_utilisateur");

            System.out.println(test);

            Long id_utilisateur = Long.valueOf(test);

            System.out.println("ID_utilisateur après conversion en long    " + id_utilisateur);

            Utilisateur user = gestionUtilisateur.trouverUtilisateurParId(id_utilisateur);

            System.out.println("ID_utilisateur après conversion en long    " + user.getUtilisateurLogin());

            request.setAttribute("utilisateurFicheUtilisateur", user);
        }

        RequestDispatcher Rd; // Déclare un RequestDispatcher pour gérer la redirection ou le forwarding
        Rd = getServletContext().getRequestDispatcher(jspClient); // Récupère le dispatcher pour la JSP cible
        Rd.forward(request, response); // Forward la requête et la réponse vers la JSP cible
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
