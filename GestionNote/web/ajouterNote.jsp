<%@ page import="java.util.*" %>
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
        <link rel="stylesheet" href="styles.css"/>
        <title>Ajout note</title>
    </head>
    
    <body>
        <div id="global">

        <header>     
        <h1>Gestion note</h1>
        </header>

        <nav>
        <a href=""><p>Affichage
        </p></a><a href="gestion"><p>Ajout
        </p></a><a href="afficherEleve"><p>Modifier/Supprimer
        </p></a>
        </nav>

        <!--Contenu principal-->

        <section id="content">
        <div id="main">

           <h1>Ajouter une note</h1>
                <form method="post" action="/GestionNote/insertNote">
                <table>
                    <tr><td>Choisir un &eacute;tudiant</td><td>

                <select name="nometude" size="1">
                    <%for(int i=0;i<nomtab.size();i++){%>
                    <option><%=nomtab.get(i)%>
                     <%}%>
                </select></td></tr>
                         <tr><td>Choisir la matière</td><td>
                <select name="matiere" size="1">
                  <%for(int i=0;i<nommatiere.size();i++){%>
                    <option><%=nommatiere.get(i)%>
                     <%}%>
                </select></td></tr>
                    <tr><td>Inserer une note</td><td><input type="text" name="note"></td></tr>
                    <tr><td></td><td><input type="submit" name="submit" value="Valider"></td></tr>
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
