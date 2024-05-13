public abstract class Propriete extends Case {
    private String type;
    private String nom;
    private String proprietaire;
    int valeur;
    private int prix;
    private int loyer;

    public Propriete(String type, String nom, int valeur, int loyer) {
        super(type, nom, "", valeur, loyer);
        this.valeur = valeur;
        this.prix = prix;
    }

    public int montantloyer(Joueur joueur) {
        if (joueur.getnmbpropriete()) {
            loyer *= 2;
        }
        return loyer;
    }

    public int getPrix() {
        return prix;
    }

    public int getLoyer() {
        return loyer;
    }
}
