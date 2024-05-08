public class Taxe extends Case {
    private String description;
    public Taxe(String type, String nom, int montantapayer, String description) {
        super(type, nom, montantapayer);
        this.description = description;
    }
}
