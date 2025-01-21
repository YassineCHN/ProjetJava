<%@page import="ENTITE.Z_USER"%>
<!DOCTYPE html>
<head>
    <link rel="stylesheet" href="newcss.css">
</head>
<html lang="en">
    <div class="navbar">
        <div class="logo"><a href="landing_page.jsp">Finance and Operations</a></div>
        <div class="onglets">
            <ul>
               <%  String utilisateurIdentifie0 = (String) session.getAttribute("utilisateur2");
                 String role_utilisateur = (String) session.getAttribute("role2");
                Z_USER user = (Z_USER) session.getAttribute("utilisateurConnecte");
                String serviceFinancier = (String) session.getAttribute("ServiceFinancier");
                %>
                
            </ul>

        </div>
        <div class="profil">
<!--            On fera en sorte que si l'utilisateur est connectï¿½ il y est une petite image pour le renvoyer vers son profil utilisateur -->
            <%  if (utilisateurIdentifie0 != null) {%>
            <p>Bonjour, <strong><%= utilisateurIdentifie0%></strong> !</p>
                        
            <div class="logout">
                <form action="NewServlet" method="get" style="display: inline;">
                    <input type="hidden" name="action" value="logout">
                    <button type="submit" class="logout-button">Se déconnecter</button>
                </form>
            </div>
            <%  } else { %>
            <p>Vous n'etes pas connecte. <a href="z_user_auth.jsp">Connectez-vous ici</a>.</p>
           <%  } %>
        </div>
    </div>
</html>
<!--<p>/</% / if (utilisateurIdentifie0 != null) { %>
                 Cas pour l'utilisateur ADMIN : voir toutes les navbars 
                /</% / if ("ADMIN".equals(role_utilisateur)) { %>
                /</% /@ include file="navbar_Service.jsp" %>
                /</% /@ include file="navbar_Medecin.jsp" %>
                /</% /@ include file="navbar_Acte.jsp" %>
                /</% /@ include file="navbar_Utilisateur.jsp" %>
                /</% /@ include file="navbar_Patient.jsp" %>
                /</% /@ include file="navbar_GestionPatient.jsp" %>
                /</% / } else if ("MEDECIN".equals(role_utilisateur)) { %>
                 Cas pour l'utilisateur MEDECIN : voir certaines navbars spï¿½cifiques 
                /</% /@ include file="navbar_Service.jsp" %>
                /</% /@ include file="navbar_Medecin.jsp" %>
                /</% /@ include file="navbar_Acte.jsp" %>
                /</% /@ include file="navbar_GestionPatient.jsp" %>
                /</% / } else if ("PATIENT".equals(role_utilisateur)) { %>
                 Cas pour l'utilisateur PATIENT : voir uniquement la navbar Patient 
                /</% /@ include file="navbar_Patient.jsp" %>
                /</% / } %>
                /</% / } %></p>-->