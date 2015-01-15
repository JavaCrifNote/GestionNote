<%-- 
    Document   : eleveaffichage
    Created on : 8 janv. 2015, 11:01:29
    Author     : Chi Nhan
--%>

<%@ page import="java.util.*" %>

<%
// on récupère les attributs passés par la servlet principale
     ArrayList<Integer> idtab=new ArrayList();
     idtab=(ArrayList)request.getAttribute("id_eleves");
     ArrayList<String> nomtab=new ArrayList();
     nomtab=(ArrayList)request.getAttribute("noms");
     ArrayList notes=(ArrayList)request.getAttribute("notes");
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css"/>
        <title>Ajouter Note</title>
    </head>
    
    <body>
        <div id="global">

            <header>        
            <h1>Gestion note</h1>          
            </header>

            <nav>
            <a href="afficher"><p>Affichage
            </p></a><a href="gestion"><p>Ajout
            </p></a><a href="afficherEleve"><p>Modifier/Supprimer
            </p></a>
            </nav>

            <!--Contenu principal-->

            <section id="content">
        <div id="main">
            <h1>Recherche d'un étudiant</h1>
            <form method="post" action="/GestionNote/listeNote">
            <table>
                 <tr><td>Choisir un &eacute;tudiant</td><td>

                <select name="nometude" size="1">
                    <%for(int i=0;i<nomtab.size();i++){%>
                    <option><%=nomtab.get(i)%>
                     <%}%>
                </select></td></tr>
                 <tr><td></td><td><input type="submit" name="valider" value="Valider"></td></tr>
             </table>
         </form>
        </div>
        </section>

        <!--Footer-->

        <footer>
        </footer>
        </div>      
    </body>
</html>