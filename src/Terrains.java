public class Terrains extends Propriete {
    private int valeur;
    public Terrains(String type, String proprietaire, String nom, int prix, int loyer, int valeur){
        super(type, proprietaire, nom, prix, loyer);
        this.valeur = valeur;
    }
}
