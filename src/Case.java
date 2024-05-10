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
}
