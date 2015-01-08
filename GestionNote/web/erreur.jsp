<%
String msgErreur= (String)request.getAttribute("msgErreur"); 
if(msgErreur==null) msgErreur="Erreur non identifiée)";
 %>   

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
<h3>Cycle de vie d'une session</h3>
<hr>
<h3>Application indisponible(<%= msgErreur %>)</h3>
    </body>
</html>
