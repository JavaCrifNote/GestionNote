
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
        // on passe la main à la page d'erreur 
        request.setAttribute("msgErreur",msgErreur); 
        
        getServletContext().getRequestDispatcher(urlErreur).forward(request,response);
        }
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
     
}
