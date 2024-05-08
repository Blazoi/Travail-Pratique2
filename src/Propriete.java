public abstract class Propriete extends Case{
    private String type;
    private String nom;
    private String proprietaire;
    private int prix;
    private int loyer;
    public Propriete(String type, String proprietaire, String nom, int prix, int loyer){
        super(type, nom, loyer);
        this.prix = prix;
    }

    public int montantloyer(Joueur joueur){
        if (joueur.getnmbpropriete()){
            loyer *= 2;
        }
        return loyer;
    }
}
