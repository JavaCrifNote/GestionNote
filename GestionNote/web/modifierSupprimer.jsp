
<%@ page import="java.util.*" %>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Integer> idtab=new ArrayList();
     idtab=(ArrayList)request.getAttribute("id_eleves");
     ArrayList<String> nomtab=new ArrayList();
     nomtab=(ArrayList)request.getAttribute("noms");
     
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
            <tr><td>Choisir un &eacute;tudiant</td><td>
                    
   <select name="nometude" size="1">
       <%for(int i=0;i<nomtab.size();i++){%>
       <option><%=nomtab.get(i)%>
        <%}%>
   </select></td></tr>
            <tr><td></td><td><input type="submit" name="valider" value="Valider"></td></tr>
            <tr><td></td><td><input type="submit" name="modifier" value="Modifier"></td></tr>
            <tr><td></td><td><input type="submit" name="supprimer" value="Supprimer"></td></tr>
        </table>
    </form>
    </body>
</html>
