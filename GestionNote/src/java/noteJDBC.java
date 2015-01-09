/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Greg
 */
public class noteJDBC {
    
    public List<BeanNote> afficheEleve(Connection con){
        List<BeanNote> listeEleve = new ArrayList();
        //Statement stmt;
        PreparedStatement stmt;
        ResultSet results;
        try{
            String query = "SELECT e.nom, e.prenom, e_m.note, m.nom FROM eleve as e INNER JOIN (eleve_matiere as e_m INNER JOIN matiere as m ON m.id_matiere=e_m.id_matiere) ON e_m.id_eleve=e.id_eleve WHERE e.nom=? AND e.prenom=? ";
            //stmt = con.createStatement();
            stmt = con.prepareStatement(query);
            
            try{
                //ResultSet results = stmt.executeQuery(query);
                results = stmt.executeQuery();
                try{
                    //On boucle sur les résultats
                    while (results.next()){
                        BeanNote eleve = new BeanNote();
                        eleve.getEleve().setNom(results.getString("nom"));
                        eleve.getEleve().setPrenom(results.getString("prenom"));
                        eleve.getMatiere().setNom(results.getString("nom"));
                        eleve.setNote(results.getString("note"));
                        listeEleve.add(eleve);
                    }
                }finally{results.close();}
            }finally {stmt.close();}
        }catch(SQLException sqle){
            
        }     
        return listeEleve;
    }
    
    public void updateNote(Connection con, BeanNote bean){
        try{
            String query = "UPDATE note SET note=? WHERE id_eleve=? AND id_matiere=?";
            //Statement stmt = con.createStatement();
            PreparedStatement stmt = con.prepareStatement(query);
            
            //Binding des paramètres
            //Décortiquage du bean
            String note = bean.getNote();          
            //int eleve = bean.getEleve().getId_eleve();
            //int matiere = bean.getMatiere().getId_matiere();
            
            //Settings du PreparedStatement
            stmt.setString(1, note);
            //stmt.setString(2, legende);
            //stmt.setInt(3, id_news);
            //stmt.setInt(3, categorie);
            
            try{
                // int statut = stmt.executeUpdate(query);
                int statut = stmt.executeUpdate();
            }finally{stmt.close();}
        }catch(SQLException sqle){
            
        } 
    
    }
    
    public void insertNote(Connection con, BeanNote bean){
        try{
            String query = "INSERT INTO eleve_matiere (id_eleve, id_matiere, note) VALUES (?,?,?)";
            //Statement stmt = con.createStatement();
            PreparedStatement stmt = con.prepareStatement(query);
            
            //Binding des paramètres
            //Décortiquage du bean
            int id_eleve = bean.getEleve().getId_eleve();
            int id_matiere = bean.getMatiere().getId_matiere();
            String note = bean.getNote();
            
            //Settings du PreparedStatement
            stmt.setInt(1, id_eleve);
            stmt.setInt(2, id_matiere);
            stmt.setString(3, note);
            
            try{
                //int statut = stmt.executeUpdate(query);
                int statut = stmt.executeUpdate();
            }finally{stmt.close();}
        }catch(SQLException sqle){
            
        }
    }
    
     public void deleteNote(Connection con, BeanNote bean){
        try{
            String query = "DELETE FROM note WHERE id_eleve=? and id_matiere=?";
            //Statement stmt = con.createStatement();
            PreparedStatement stmt = con.prepareStatement(query);
            
            //Binding des paramètres
            //Décortiquage du bean
            int id_eleve = bean.getEleve().getId_eleve();
            int id_matiere = bean.getMatiere().getId_matiere();
            
            //Settings du PreparedStatement
            stmt.setInt(1, id_eleve);
            stmt.setInt(2, id_matiere);
            
            try{
                // int statut = stmt.executeUpdate(query);
                int statut = stmt.executeUpdate();
            }finally{stmt.close();}
        }catch(SQLException sqle){
            
        }
    }
    
}
