public class Stationnement extends Case {
    private String description;
    public Stationnement (String type, String nom, int montantapayer, String description){
        super(type, nom, montantapayer);
        this.description = description;
    }
}
