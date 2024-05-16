import ca.qc.bdeb.sim202.tp2.DePipe;

public class Servicepublic extends Propriete {
    private int valeur;
    private Joueur proprietaire;
    public Servicepublic (String type, String nom, int valeur){
        super(type, nom, valeur, 0);
        this.valeur = valeur;
    }


    @Override
    public void setUnProprietaire(Joueur joueur) {
        proprietaire = joueur;
    }
}
