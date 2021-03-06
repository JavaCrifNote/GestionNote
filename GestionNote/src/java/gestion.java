/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author varvaridaniela
 */
@WebServlet(urlPatterns = {"/gestion"})
public class gestion extends HttpServlet {

   
    String msgErreur=null;
    String urlErreur=null;
    String urlgestionNote=null;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
        //Recupere les matieres
        getMatiere(request,response);
        ArrayList<Integer> idmatieres=new ArrayList();
        ArrayList<Integer> nommatieres=new ArrayList();
        idmatieres=(ArrayList)request.getAttribute("id_matieres");
        nommatieres=(ArrayList)request.getAttribute("nomsMatieres");
        request.setAttribute("id_matiere",idmatieres);
        request.setAttribute("matiere",nommatieres);
        
            String nom1=request.getParameter("nometude");
            String nom2=request.getParameter("matiere");
            String note3=request.getParameter("note");
            if(nom1!=null&&nom2!=null&&note3!=null){
            //Update note
            updateNote(request,response,nommatieres,tabNom,nom1,nom2,note3);
        request.setAttribute("valider","La note a été ajouter");
         request.getRequestDispatcher("valider.jsp").forward(request,response);
         
        }
        
            getServletContext().getRequestDispatcher(urlgestionNote).forward(request,response);
        
      
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
        
        public void updateNote(HttpServletRequest request, HttpServletResponse response,ArrayList matiere,ArrayList eleve,String nom1,String nom2, String note){
               
        MyConnexion c=new MyConnexion();
       Connection con=c.connect();
            try{
            String query="INSERT INTO eleve_matiere (id_eleve,id_matiere,note) value(?,?,?)";
            PreparedStatement stmt=con.prepareStatement(query);
            int id_matiere=0;
            int id_eleve=0;
            int note1=Integer.parseInt(note);
            for(int i=0;i<matiere.size();i++){
                if(nom2==matiere.get(i)){
                    id_matiere=i;
                }
            }
            for(int i=0;i<eleve.size();i++){
                if(nom1==eleve.get(i)){
                    id_eleve=i;
                }
            }
            if(id_matiere!=0&&id_eleve!=0){
            stmt.setInt(1,id_eleve);
            stmt.setInt(2,id_matiere);
            stmt.setInt(3, note1);
            }
            try{
                int statut=stmt.executeUpdate();
            }finally{
                stmt.close();
                
            }
            
        }catch(SQLException ex){
            System.out.println("Erreur connexion insert");
        }
        }
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }
public void init(){
        // on récupère les paramètres d'initialisation
        ServletConfig config=getServletConfig(); 
        urlgestionNote=config.getInitParameter("urlgestionNote");
        // paramètres ok ?
if(urlgestionNote==null ){
msgErreur="Configuration incorrecte";
return;
}
    }
}
