import java.io.Serializable;

public class Joueur implements Serializable {
    private String nom;
    private boolean plusdunepropriete = false;
    private int argent;
    private boolean estPremierJoueur = false;
    private int position;
    private int dernierePosition;
    private boolean vivant = true;
    public Joueur(String nom) {
        this.nom = nom;
        this.argent = 500;
    }
    public String getNom(){
        return nom;
    }
    public int getPosition(){return position;}

    public int getDernierePosition() {
        return dernierePosition;
    }

    public void setPosition(int position){this.position = position;}

    public void setDernierePosition(int dernierePosition) {
        this.dernierePosition = dernierePosition;
    }

    public boolean getnmbpropriete(){
        return plusdunepropriete;
    }
    public void augmenterlesproprietes(){plusdunepropriete = true;}
    public int getArgent(){
        return argent;
    }
    public void setArgent(int montant) {
        argent = montant;
    }
    public void ajouterArgent(int montant) {
        argent += montant;
    }
    public void retirerArgent(double montant) {
        argent -= montant;
    }
    public boolean estPremierJoueur() {
        return estPremierJoueur;
    }
    public void setPremierJoueur() {
        estPremierJoueur = true;
    }
    public boolean estVivant(){return vivant;}
    public void setMort(){vivant = false;}
}
