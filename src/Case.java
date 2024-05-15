public abstract class Case {
    private final String type;
    private String nom;
    private String description;
    private Joueur proprietaire;
    private int montantdelacase;
    private boolean aUnProprietaire = false;
    public Case (String type, String nom, String description, int valeur, int montantapayer){
        this.type = type;
        this.nom = nom;
        this.description = description;
        this.montantdelacase = montantapayer;
    }
    public String getType() {
        return type;
    }
    public String getNom() {
        return nom;
    }
    public String getDescription() {
        return description;
    }
    public int getMontantdelacase() {
        return montantdelacase;
    }

    public int getPrix() {
        return 0;
    }
    public int getLoyer() {
        return 0;
    }
    public Joueur getProprietaire(){return proprietaire;}
    public void setProprietaire(Joueur joueur){ proprietaire = joueur;};
    public void aUnProprietaire(){aUnProprietaire = true;}
    public boolean getaUnProprietaire() {
        return aUnProprietaire;
    }
    public void payerLoyer(Joueur joueur, int loyer){
        //S'il a assez d'argent
        if (joueur.getArgent() >= loyer){
            joueur.retirerArgent(loyer);
        } // S'il n'a pas assez
        else if (joueur.getArgent() > 0){
            joueur.setArgent(0);
        } else {
            System.out.println(joueur.getNom() + " est éliminé.");
            joueur.setMort();
        }
    }

}
