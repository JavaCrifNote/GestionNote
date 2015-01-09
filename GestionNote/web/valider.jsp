<%-- 
    Document   : valider
    Created on : 8 janv. 2015, 16:34:42
    Author     : varvaridaniela
--%>
<% String reponse=(String)request.getAttribute("valider");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%=reponse%></h1>
        
    </body>
</html>
