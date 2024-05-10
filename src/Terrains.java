public class Terrains extends Propriete {
    private int valeur;
    public Terrains(String type, String nom, int valeur, int loyer, String proprietaire, int prix){
        super(type, nom ,valeur, loyer, proprietaire,prix);
        this.valeur = valeur;
    }
}
