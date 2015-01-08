import java.io.Serializable;
/**
 *
 * @author Greg
 */
public class BeanEleve implements Serializable{
    
    private int id_eleve;
    private String nom;
    private String prenom;
   
    public BeanEleve(){
        
    }

    /**
     * @return the id_eleve
     */
    public int getId_eleve() {
        return id_eleve;
    }

    /**
     * @param id_eleve the id_eleve to set
     */
    public void setId_eleve(int id_eleve) {
        this.id_eleve = id_eleve;
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

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
