public class Servicepublic extends Propriete {
    private String description;
    public Servicepublic (String type, String proprietaire, String nom, int prix, int loyer, String description){
        super(type, proprietaire, nom, prix, loyer);
        this.description = description;
    }

}
