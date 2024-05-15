public class Stationnement extends Case {
    private String description;
    private int stationnement;
    public Stationnement (String type, String nom, String description, int montantapayer){
        super(type, nom, description, 0, montantapayer);
        this.description = description;
        this.stationnement = montantapayer;
    }

    @Override
    public void payerStationnement(Joueur joueur) {
        joueur.retirerArgent(stationnement);
    }
}
