public class Depart extends Case{
    private String description;
    public Depart(String type, String nom, int montantarecevoir, String description){
        super(type, nom, montantarecevoir);
        this.description = description;
    }
}
