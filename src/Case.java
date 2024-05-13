public abstract class Case {
    private final String type;
    private String nom;
    private String description;
    private int montantdelacase;
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

}
