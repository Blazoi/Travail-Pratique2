public class Depart extends Case {
    private String description;

    public Depart(String type, String nom, String description, int montantarecevoir) {
        super(type, nom, description, 0, montantarecevoir);
//        this.description = description;
    }

    @Override
    public void setUnProprietaire(Joueur joueur) {

    }

    @Override
    public void setProprietaire(Joueur joueur) {

    }
}
