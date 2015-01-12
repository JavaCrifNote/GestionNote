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
        <link rel="stylesheet" href="styles.css"/>
        <title>Gestion Note</title>
    </head>
    <body>
         <div id="global">

        <header>     
        <h1>Gestion note</h1>
        </header>

        <nav>
        <a href=""><p>Affichage
        </p></a><a href="gestion"><p>Ajout
        </p></a><a href=""><p>Modifier/Supprimer
        </p></a>
        </nav>

        <!--Contenu principal-->

        <section id="content">
        <div id="main">
           <h1><%=reponse%></h1>
        </div>
        </section>

        <!--Footer-->

        <footer>
        </footer>

        </div>    
    </body>
</html>
