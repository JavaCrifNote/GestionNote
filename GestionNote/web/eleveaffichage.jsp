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
                <h1>Ajouter une note</h1>
                    <form method="POST" action="GestionNote/gestion">
                    <table>
                        <tr><td>Choisir un &eacute;tudiant</td><td>

               <select name="etud" size="1">
                   <%for(int i=0;i<nomtab.size();i++){%>
                   <option><%=nomtab.get(i)%>
                    <%}%>
               </select></td></tr>
                        <tr><td></td><td><input type="text" name="note"></td></tr>
                        <tr><td></td><td><input type="button" name="submit" value="Valider"></td></tr>
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