<%-- 
    Document   : eleveaffichage
    Created on : 8 janv. 2015, 11:01:29
    Author     : Chi Nhan
--%>

<%@ page import="java.util.*" %>

<%
// on récupère les attributs passés par la servlet principale
String txtNom=(String)request.getAttribute("txtNom");
String txtPrenom=(String)request.getAttribute("txtPrenom");
ArrayList erreurs=(ArrayList)request.getAttribute("erreurs");
ArrayList affichage=(ArrayList)request.getAttribute("affichage");
%>

import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.util.regex.*; 
import java.util.*;

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <script language="JavaScript" type="text/javascript">
function effacer(){
// raz du formulaire
with(document.frmImpots){
txtNom.value="";
txtPrenom.value="";
}//with
}//effacer
function calculer(){
// vérification des paramètres avant de les envoyer au serveur
with(document.frmImpots){
//nbre d'enfants
champs=/^\s*(\d+)\s*$/.exec(txtNom.value);
if(champs==null){
// le modéle n'est pas vérifié
alert("Le nom est incorrect");
nbEnfants.focus();
return;
}//if
//salaire
champs=/^\s*(\d+)\s*$/.exec(txtPrenom.value);
if(champs==null){
// le modéle n'est pas vérifié
alert("Le prénom est incorrect");
salaire.focus();
return;
}//if
// c'est bon - on envoie le formulaire au serveur
submit();
}//with
}//calculer
</script> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Affichage d'élèves</title>
    </head>
    <body>
     <hr>
<form name="frmImpots" action="/GestionNote/affichage" method="POST">
    <table> <tr> <td>Nom :</td> 
    <td> <input type="text" size="5" name="txtNom" value="<%= txtNom %>"></td> </tr>
        <tr> <td>Prénom :</td> 
    <td> <input type="text" size="5" name="txtPrenom" value="<%= txtPrenom %>"></td> </tr>
    <tr> 
    <td><input type="button" value="Recherche" onclick="calculer()"></td> 
    <td><input type="button" value="Effacer" onclick="effacer()"></td> 
    </tr>         
        </table> 
</form>
        
<%
// y-a-t-il des erreurs
if(erreurs!=null){
// affichage des erreurs
out.println("<hr>");
out.println("<font color=\"red\">");
out.println("Les erreurs suivantes se sont produites<br>");
out.println("<ul>");
for(int i=0;i<erreurs.size();i++){
out.println("<li>"+(String)erreurs.get(i));
}
out.println("</ul>");
out.println("</font>");
}
else if(affichage.size()!=0){
// résultats des simulations
out.println("<h3>Affichage des élèves<h3>");
out.println("<table \"border=\"1\">");
out.println("<tr><td>Marié</td><td>Enfants</td><td>Salaire annuel (F)</td><td>Impôts à payer (F)</td></tr>");
for(int i=0;i<affichage.size();i++){
String[] simulation=(String[])affichage.get(i);
out.println("<tr><td>"+simulation[0]+"</td><td>"+simulation[1]+"</td><td>"+simulation[2]+"</td><td>"+simulation[3]+"</td></tr>");
}
out.println("</table>");
}
%>
    </body>
</html>
