public abstract class Case {
    private final String type;
    private String nom; int test = 0;
    private int montantdelacase;
    public Case (String type, String nom, int montantapayer){
        this.type = type;
        this.nom = nom;
        this.montantdelacase = montantapayer;
    }
}
