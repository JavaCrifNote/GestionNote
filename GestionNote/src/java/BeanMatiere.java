/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
/**
 *
 * @author Greg
 */
public class BeanMatiere implements Serializable{
    
    private int id_matiere;
    private String nom;
    
    public BeanMatiere(){
        
    }

    /**
     * @return the id_matiere
     */
    public int getId_matiere() {
        return id_matiere;
    }

    /**
     * @param id_matiere the id_matiere to set
     */
    public void setId_matiere(int id_matiere) {
        this.id_matiere = id_matiere;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
