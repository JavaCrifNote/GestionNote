
<%@ page import="java.util.*" %>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Integer> idtab=new ArrayList();
     idtab=(ArrayList)request.getAttribute("id_eleves");
     ArrayList<String> nomtab=new ArrayList();
     nomtab=(ArrayList)request.getAttribute("noms");
     ArrayList<Integer> idmatiere=new ArrayList();
     idmatiere=(ArrayList)request.getAttribute("id_matiere");
     ArrayList<String> nommatiere=new ArrayList();
     nommatiere=(ArrayList)request.getAttribute("matiere");
    %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <body>
        <h1>Ajouter une note</h1>
        <form method="POST" action="gestionnote/getion">
        <table>
            <tr><td>Choisir un &eacute;tudiant</td><td>
                    
   <select name="etud" size="1">
       <%for(int i=0;i<nomtab.size();i++){%>
       <option><%=nomtab.get(i)%>
        <%}%>
   </select></td></tr>
            <tr><td>Choisir la matière</td><td>
   <select name="metiere" size="1">
     <%for(int i=0;i<nommatiere.size();i++){%>
       <option><%=nommatiere.get(i)%>
        <%}%>
   </select></td></tr>
            <tr><td></td><td><input type="text" name="note"></td></tr>
            <tr><td></td><td><input type="button" name="submit" value="Valider"></td></tr>
        </table>
    </form>
    </body>
</html>
