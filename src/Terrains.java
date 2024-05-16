public class Terrains extends Propriete {
    private Joueur proprietaire;
    public Terrains(String type, String nom, int valeur, int loyer){
        super(type, nom ,valeur, loyer);
    }

    @Override
    public void setUnProprietaire(Joueur joueur) {
        proprietaire = joueur;
    }
}
