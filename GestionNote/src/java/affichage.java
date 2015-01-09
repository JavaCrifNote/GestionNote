
import java.io.*; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.*; 
import javax.servlet.http.*; 
import java.util.regex.*; 
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chi Nhan
 */
public class affichage extends HttpServlet{
    
    // variables d'instance
    String msgErreur=null;
    String urlAffichageEleve=null;
    String urlSimulationEleve=null;
    String urlErreur=null; 
    MyConnexion impots=null;
    //-------- GET
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        // l'initialisation s'est-elle bien passée ?
    if(msgErreur!=null){
    // on passe la main à la page d'erreur
    request.setAttribute("msgErreur",msgErreur);
    getServletContext().getRequestDispatcher(urlErreur).forward(request,response);
        }
    // on récupère les simulations précédentes de la session
    HttpSession session=request.getSession();
    ArrayList simulations=(ArrayList)session.getAttribute("simulations");
    if(simulations==null) simulations=new ArrayList();
    // on met les simulations dans la requête courante
    request.setAttribute("simulations",simulations);
    
    // des attributs de la requête
    //String txtEleve=null;
    // on récupère les paramètres de la requête
    String txtNom=request.getParameter("txtNom"); // Nom de l'élève
    if(txtNom==null) txtNom="";
    String txtPrenom=request.getParameter("txtPrenom"); // Prénom de l'élève
    if(txtPrenom==null) txtPrenom="";
    // a-t-on tous les paramètres attendus
    if(txtNom==null || txtPrenom==null){
    // il manque des paramètres

    request.setAttribute("txtNom","");
    request.setAttribute("txtPrenom","");

    // on passe la main à l'url d'affichage de l'impôt
    getServletContext().getRequestDispatcher(urlSimulationEleve).forward(request,response);
    }
    // on a tous les paramètres - on les vérifie
    ArrayList erreurs=new ArrayList();
   
    // s'il y a des erreurs, on les passe en attribut de la requête
    if(erreurs.size()!=0){ request.setAttribute("erreurs",erreurs); txtNom=""; }else{
        // on peut calculer l'impôt à payer
            try{
            //int nbEnfants=Integer.parseInt(txtEnfants);
            //int salaire=Integer.parseInt(txtSalaire);
            //txtImpots=""+impots.calculer(optMarie.equals("oui"),nbEnfants,salaire); 
            // on ajoute le résultat courant aux simulations précédentes
            //String[] simulation={optMarie.equals("oui") ? "oui" : "non",txtEnfants, txtSalaire, txtImpots};
            //simulations.add(simulation);  
            MyConnexion c=new MyConnexion();
            Connection con=c.connect();
             PreparedStatement stmp;
             ResultSet results=null;
             ArrayList<Integer> tabId=new ArrayList();
             ArrayList<String> tabNom=new ArrayList();
             try{
            String query="SELECT * FROM eleve";
            stmp=con.prepareStatement(query);
            try{
                results=stmp.executeQuery();
                try{
                while(results.next()){
                    tabId.add(results.getInt("id_eleve"));
                    tabNom.add(results.getString("nom"));
                }
            }finally{
                results.close();
            }
            }finally{
                stmp.close();
            }
        }catch(SQLException e){
           System.out.println("Erreur Sql");
        }
        request.setAttribute("id_eleves",tabId);
        request.setAttribute("noms",tabNom);
        //Recupere les matieres
        getMatiere(request,response);
        ArrayList<Integer> idmatieres=new ArrayList();
        ArrayList<Integer> nommatieres=new ArrayList();
        idmatieres=(ArrayList)request.getAttribute("id_matieres");
        nommatieres=(ArrayList)request.getAttribute("nomsMatieres");
        request.setAttribute("id_matiere",idmatieres);
        request.setAttribute("matiere",nommatieres);
        getServletContext().getRequestDispatcher(urlSimulationEleve).forward(request,response);
                
                
            // la nouvelle valeur de simulations est remise dans la session
            session.setAttribute("simulations",simulations);
            }catch(Exception ex){}
    }
    // les autres attributs de la requête
    //if(optMarie.equals("oui")){ request.setAttribute("chkoui","checked"); request.setAttribute("chknon",""); }else{ request.setAttribute("chknon","checked"); request.setAttribute("chkoui",""); } request.setAttribute("txtEnfants",txtEnfants); request.setAttribute("txtSalaire",txtSalaire); request.setAttribute("txtImpots",txtImpots);
    // on passe la main à l'url d'affichage de l'impôt
    getServletContext().getRequestDispatcher(urlSimulationEleve).forward(request,response);
    }
    //-------- POST
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{ doGet(request,response); }
    //-------- INIT
    public void init(){
        // on récupère les paramètres d'initialisation
        ServletConfig config=getServletConfig();
        urlSimulationEleve=config.getInitParameter("urlSimulationEleve");
        urlErreur=config.getInitParameter("urlErreur");

        // paramètres ok ?
        if(urlSimulationEleve==null){ msgErreur="Configuration incorrecte"; return;
        }
        // on crée une instance d'impotsJDBC
            try{
            //impots=new MyConnexion(DSNeleve,admeleve,mdpeleve);
            }catch(Exception ex){
            msgErreur=ex.getMessage();
            }
        }
    
     public  void getMatiere(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
            
        MyConnexion c=new MyConnexion();
       Connection con=c.connect();
        PreparedStatement stmp;
        ResultSet results=null;
        ArrayList<Integer> tabId=new ArrayList();
        ArrayList<String> tabNom=new ArrayList();
        try{
            String query="SELECT * FROM matiere";
            stmp=con.prepareStatement(query);
            try{
                results=stmp.executeQuery();
                try{
                while(results.next()){
                    tabId.add(results.getInt("id_matiere"));
                    tabNom.add(results.getString("nom"));
                }
            }finally{
                results.close();
            }
            }finally{
                stmp.close();
            }
        }catch(SQLException e){
           System.out.println("Erreur Sql");
        }
        request.setAttribute("id_matieres",tabId);
        request.setAttribute("nomsMatieres",tabNom);
        }
     
     
}
