import java.io.Serializable;

public class Joueur implements Serializable {
    private String nom;
    private boolean plusdunepropriete = false;
    private int argent;
    private boolean estPremierJoueur = false;
    private int position;
    public Joueur(String nom) {
        this.nom = nom;
        this.argent = 500;
    }
    public String getNom(){
        return nom;
    }
    public int getPosition(){return position;}
    public void setPosition(int position){this.position = position;}
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
    public boolean estPremierJoueur() {
        return estPremierJoueur;
    }
    public void setPremierJoueur() {
        estPremierJoueur = true;
    }
}
