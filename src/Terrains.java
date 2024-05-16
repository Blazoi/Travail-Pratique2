public class Terrains extends Propriete {
    private int valeur;
    private Joueur proprietaire;
    public Terrains(String type, String nom, int valeur, int loyer){
        super(type, nom ,valeur, loyer);
        this.valeur = valeur;
    }

    @Override
    public void setUnProprietaire(Joueur joueur) {
        proprietaire = joueur;
    }
}
