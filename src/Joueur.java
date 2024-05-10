import java.io.Serializable;

public class Joueur implements Serializable {
    private String nom;
    private boolean plusdunepropriete = false;
    private int argent;
    public Joueur(String nom) {
        this.nom = nom;
        this.argent = 500;
    }
    public boolean getnmbpropriete(){
        return plusdunepropriete;
    }
    public void augmenterlesproprietes(){plusdunepropriete = true;}
    public int getArgent(){
        return argent;
    }
    public void setArgent(int montant) {
        argent += montant;
    }
}
