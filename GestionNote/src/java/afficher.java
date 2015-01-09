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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author varvaridaniela
 */
@WebServlet(urlPatterns = {"/supprimer"})
public class afficher extends HttpServlet {

    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
        request.getRequestDispatcher("modifierSupprimer.jsp").forward(request,response);
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

    public void afficherNote (HttpServletRequest request, HttpServletResponse response,ArrayList matiere,ArrayList eleve,String nom1){
               System.out.println(nom1+matiere.get(0)+ eleve.get(0));
        MyConnexion c=new MyConnexion();
       Connection con=c.connect();
            try{
            String query="SELECT e.nom, e.prenom, e_m.note, m.nom FROM eleve as e INNER JOIN (eleve_matiere as e_m INNER JOIN matiere as m ON m.id_matiere=e_m.id_matiere) ON e_m.id_eleve=e.id_eleve WHERE e.nom=?";
            PreparedStatement stmt=con.prepareStatement(query);
            int id_matiere=0;
            int id_eleve=0;
            
            System.out.println("__________________"+id_matiere);
            for(int i=0;i<eleve.size();i++){
                if(nom1.equals(eleve.get(i))){
                    id_eleve=i+1;
                }
            }
            
            stmt.setInt(1,id_eleve);
            stmt.setInt(2,id_matiere);
 
            
            try{
                int statut=stmt.executeUpdate();
            }finally{
                stmt.close();
            }
            
        }catch(SQLException ex){
            System.out.println("Erreur connexion insert");
            ex.printStackTrace();
        }
        
    }
}
