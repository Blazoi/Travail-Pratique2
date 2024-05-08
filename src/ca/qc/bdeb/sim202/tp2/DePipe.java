package ca.qc.bdeb.sim202.tp2;
import java.util.Random;

/**
 *
 * @author Pierre Prades
 */
public class DePipe {

    private static Random random = new Random(7);
    
    /**
     * Il n'est pas nécessaire d'instancier la classe ca.qc.bdeb.sim202.tp2.DePipe pour utiliser cette méthode
     * exemple: DePipe.lancer();
     * @return entier entre 1 et 6
     */    
    public static int lancer(){
        return random.nextInt(1,7);
    }
}
