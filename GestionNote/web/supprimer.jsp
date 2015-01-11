<%-- 
    Document   : supprimer
    Created on : 9 janv. 2015, 15:07:13
    Author     : varvaridaniela
--%>
<%@page import="java.util.ArrayList"%>
<%
    int nb=0;
    String listnom="";
    ArrayList<String> listmatiere=new ArrayList();
    ArrayList<Double> listnote=new ArrayList();
    listnom=(String)request.getAttribute("nomEleve");
    listmatiere=(ArrayList)session.getAttribute("matiere");
    listnote=(ArrayList)request.getAttribute("notes");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Supprimer ou modifier</h1>
        <form method="post" action="/gestionnote/supprimer">
        <table>
            <tr><td>Nom etudiant :</td><td>
                <td><input type="text" name="nom" value="<%=listnom%>"></td> </tr>      
                 
       <%for(int i=0;i<listmatiere.size();i++){%>
       <tr><td> Matiere</td>
           <td>
       <input type="text" name="matiere" value="<%=listmatiere.get(i)%>">
           </td>
           <td>
               
               <input type="text" name="note<%=i%>" value="<%=listnote.get(i)%>">
               
           </td>
     
           <td><input type="submit" name="supprimer<%=i%>" value="Supprimer"> </td>
           <td><input type="submit" name="modifier<%=i%>" value="Modifier"> </td></tr>
       <%}%>
        </table>
        </form>
    </body>
</html>
