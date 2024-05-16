public class Taxe extends Case {
    private String description;
    private int taxe;

    public Taxe(String type, String nom, String description, int montantapayer) {
        super(type, nom, description,0, montantapayer);
        this.description = description;
        this.taxe = montantapayer;
    }

    @Override
    public int getTaxe() {
        return taxe;
    }
}
