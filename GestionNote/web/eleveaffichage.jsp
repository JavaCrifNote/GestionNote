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

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.util.regex.*; 
import java.util.*;

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
            <a href="affichage"><p>Afficher
            </p></a><a href="gestion"><p>Ajouter
            </p></a><a href="modifier"><p>Modifier
            </p></a><a href="supprimer"><p>Supprimer
            </p></a>
            </nav>

            <!--Contenu principal-->

            <section id="content">
            <div id="main">
                <h1>Recherche d'un étudiant</h1>
                    <form method="POST" action="GestionNote/gestion">
                    <table>
                        <tr><td>Choisir un &eacute;tudiant</td><td>

               <select name="etud" size="1">
                   <%for(int i=0;i<nomtab.size();i++){%>
                   <option><%=nomtab.get(i)%>
                    <%}%>
               </select></td></tr>
                        
                        <tr><td></td><td><input type="button" name="submit" value="Valider"></td></tr>
                    </table>
                </form>

            </div>
            </section>

              <% if(notes.size()!=0){
                // résultats des notes
                out.println("<h3>Affichage des notes<h3>");
                out.println("<table \"border=\"1\">");
                out.println("<tr><td>Français</td><td>Mathématique</td><td>Anglais</td><td>Histoire</td><td>Géographie</td></tr>");
                for(int i=0;i<notes.size();i++){
                String[] note=(String[])notes.get(i);
                out.println("<tr><td>"+note[0]+"</td><td>"+note[1]+"</td><td>"+note[2]+"</td><td>"+note[3]+"</td></tr>");
                }
                out.println("</table>");
                }
                %>
            <!--Footer-->

            <footer>
            
            </footer>

            </div>
        
    </body>
</html>