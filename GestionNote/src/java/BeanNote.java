/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author Greg
 */
public class BeanNote implements Serializable{
    
    private String note;
    private BeanEleve eleve;
    private BeanMatiere matiere;
    
    public BeanNote(){
        eleve = new BeanEleve();
        matiere = new BeanMatiere();
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the eleve
     */
    public BeanEleve getEleve() {
        return eleve;
    }

    /**
     * @param eleve the eleve to set
     */
    public void setEleve(BeanEleve eleve) {
        this.eleve = eleve;
    }

    /**
     * @return the matiere
     */
    public BeanMatiere getMatiere() {
        return matiere;
    }

    /**
     * @param matiere the matiere to set
     */
    public void setMatiere(BeanMatiere matiere) {
        this.matiere = matiere;
    }
    
}
