/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Greg
 */
public class MyConnexion {
    
    private String url = "jdbc:mysql://localhost/dbNote";
    private String user = "root";
    private String pass = "";
    
    public MyConnexion(){
        try{
            // Instanciation d'un com.myql.jdbc.Driver.
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("DRIVER MYSQL DEMARRE");
        }
        catch (ClassNotFoundException ex){
            System.out.println("Impossible de trouver le Driver");
        }
    }
    
    public Connection connect(){
        Connection con = null;
        
        try{
           con = DriverManager.getConnection(url, user, pass);
           System.out.println("CONNEXION ETABLIE");
        } catch (SQLException sql){
          System.out.println("Impossible d'établir une connection");  
        }
        
        return con;
    }
    
    public void close(Connection con){
        try{
            con.close();
            System.out.println("Connexion fermé");
        }catch (SQLException sql){
          System.out.println("Pas de fermeture (pas de connexion)"); 
        }
    }
}