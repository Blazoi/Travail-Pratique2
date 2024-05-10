public class Stationnement extends Case {
    private String description;
    public Stationnement (String type, String nom, String description, int montantapayer){
        super(type, nom, description, 0, montantapayer);
        this.description = description;
    }
}
