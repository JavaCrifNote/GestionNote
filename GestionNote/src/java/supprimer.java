/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author varvaridaniela
 */
@WebServlet(urlPatterns = {"/supprimer"})
public class supprimer extends HttpServlet {

    

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
       
        HttpSession session = request.getSession(true);
        ArrayList<String> matiere=new ArrayList();
        if(session.getAttribute("matiere")!=null){
            matiere=(ArrayList<String>)session.getAttribute("matiere");
        }
       
        ArrayList<Integer> idEleve=(ArrayList<Integer>)session.getAttribute("id_eleve");
        ArrayList<Integer> idmatiere=(ArrayList<Integer>)session.getAttribute("id_matiere");
        ArrayList<String> supprimer=new ArrayList();
        for(int i=0;i<matiere.size();i++){
               supprimer.add(request.getParameter("supprimer"+i));
        }
           for(int i=0;i<matiere.size();i++){
             if(supprimer.get(i)!=null){
                   deletNote(Integer.parseInt(idEleve.get(i).toString()),Integer.parseInt(idmatiere.get(i).toString()));
                   
                   
            request.setAttribute("valider","La note a été supprimé");
         request.getRequestDispatcher("valider.jsp").forward(request,response);
               }
           }
          
           ArrayList<String> modifier=new ArrayList();
           for(int i=0;i<matiere.size();i++){
               modifier.add(request.getParameter("modifier"+i));
           }
           ArrayList<Double> note=new ArrayList();
           for(int i=0;i<matiere.size();i++){
               note.add(Double.parseDouble(request.getParameter("note"+i)));
           }
           System.out.println("____________________"+idmatiere+"-------------"+note);
           for(int i=0;i<matiere.size();i++){
               if(modifier.get(i)!=null){
                   modifNote(Integer.parseInt(idEleve.get(i).toString()),Integer.parseInt(idmatiere.get(i).toString()),note.get(i));
                   
                   
            request.setAttribute("valider","La note a été modifier");
         request.getRequestDispatcher("valider.jsp").forward(request,response); 
        
               }
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

   public void deletNote(int idEleve,int idMatiere){
       Connection con;
       MyConnexion c=new MyConnexion();
       con=c.connect();
       try{
            String query="DELETE FROM eleve_matiere WHERE id_eleve=? AND id_matiere=?";
            PreparedStatement stmt=con.prepareStatement(query);
           
            stmt.setInt(1,idEleve);
            stmt.setInt(2,idMatiere);
            try{
               int status=stmt.executeUpdate();
               System.out.println("Delete"+ status);
            }finally{
                stmt.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
   }
   public void modifNote(int idEleve,int idMatiere,double note){
        Connection con;
       MyConnexion c=new MyConnexion();
       con=c.connect();
       try{
             String query="UPDATE eleve_matiere SET note=? WHERE id_eleve=? AND id_matiere=? ";
             PreparedStatement stmt=con.prepareStatement(query);
             
             stmt.setDouble(1,note);
             stmt.setInt(2,idEleve);
             stmt.setInt(3,idMatiere);
             
             try{
                 int status=stmt.executeUpdate();
                 System.out.println("Succes"+status);
             }finally{
                 stmt.close();
             }
         }catch(SQLException e){
             e.printStackTrace();
         }
     }
    
   }

