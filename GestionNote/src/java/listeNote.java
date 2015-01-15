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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chi Nhan
 */

@WebServlet(urlPatterns = {"/listeNote"})
public class listeNote extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String eleve=request.getParameter("nometude");
       getEleve(request,response,eleve);
       
       int ideleve=(Integer)request.getAttribute("id");
       HttpSession session = request.getSession(true);
       getNote(request,response,ideleve);
       ArrayList<Integer> tabIdEleve=new ArrayList();
        ArrayList<Integer> tabIdMatiere=new ArrayList();
        ArrayList<Double> tabNote=new ArrayList();
        //Recupere les notes de l'eleve
        tabIdEleve=(ArrayList)session.getAttribute("id_eleve");
        tabIdMatiere=(ArrayList)session.getAttribute("id_matiere");
        tabNote=(ArrayList)request.getAttribute("note");
        
        ArrayList<String> matieres=new ArrayList();
        ArrayList<Integer> idmatiere=new ArrayList();
       
        ArrayList<String> matiereNouv=new ArrayList();
        //Table des matiere dans la base
        getMatiere(request,response);
        matieres=(ArrayList)request.getAttribute("nomsMatieres");
        idmatiere=(ArrayList)request.getAttribute("id_matieres");
        for(int i=0;i<tabIdMatiere.size();i++){
            for(int j=0;j<idmatiere.size();j++){
                System.out.println(idmatiere+"--------"+tabIdMatiere);
                if(tabIdMatiere.get(i).equals(idmatiere.get(j))){
                    int id=Integer.parseInt(idmatiere.get(j).toString());
                    matiereNouv.add(matieres.get(id-1));
                }
                
            }
        }
        System.out.println(eleve+tabNote+"-----------------");
        session.setAttribute("matiere", matiereNouv);
        request.setAttribute("nomEleve",eleve);
        request.setAttribute("notes", tabNote);
        session.setAttribute("id_eleve", tabIdEleve);
        session.setAttribute("id_matiere", tabIdMatiere);
        request.getRequestDispatcher("noteaffichage.jsp").forward(request,response);
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

    public  void getEleve(HttpServletRequest request, HttpServletResponse response,String nom)throws ServletException, IOException{
            
        MyConnexion c=new MyConnexion();
       Connection con=c.connect();
        PreparedStatement stmp;
        ResultSet results=null;
        
        int id_eleve=0;
        try{
            String query="SELECT * FROM eleve WHERE nom="+"'"+nom+"'";
            stmp=con.prepareStatement(query);
            try{
                results=stmp.executeQuery();
                try{
                while(results.next()){
                    id_eleve=results.getInt("id_eleve");
                    
                }
            }finally{
                results.close();
            }
            }finally{
                stmp.close();
            }
        }catch(SQLException e){
           e.printStackTrace();
        }
        request.setAttribute("id",id_eleve);
      
        }
    public  void getNote(HttpServletRequest request, HttpServletResponse response,int id)throws ServletException, IOException{
           HttpSession session = request.getSession(true); 
        MyConnexion c=new MyConnexion();
       Connection con=c.connect();
        PreparedStatement stmp;
        ResultSet results=null;
        ArrayList<Integer> tabIdEleve=new ArrayList();
        ArrayList<Integer> tabIdMatiere=new ArrayList();
        ArrayList<Double> notes=new ArrayList();
        try{
            String query="SELECT * FROM eleve_matiere WHERE id_eleve="+"'"+id+"'";
            stmp=con.prepareStatement(query);
            try{
                results=stmp.executeQuery();
                try{
                while(results.next()){
                    tabIdEleve.add(results.getInt("id_eleve"));
                    tabIdMatiere.add(results.getInt("id_matiere"));
                    notes.add(results.getDouble("note"));
                }
            }finally{
                results.close();
            }
            }finally{
                stmp.close();
            }
        }catch(SQLException e){
           e.printStackTrace();
        }
        session.setAttribute("id_eleve",tabIdEleve);
        session.setAttribute("id_matiere",tabIdMatiere);
        request.setAttribute("note",notes);
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
           e.printStackTrace();
        }
        request.setAttribute("id_matieres",tabId);
        request.setAttribute("nomsMatieres",tabNom);
        }
        
        
}

    

