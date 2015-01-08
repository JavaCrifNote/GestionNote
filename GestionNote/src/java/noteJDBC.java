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
    
     
}
