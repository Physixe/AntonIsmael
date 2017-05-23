package uml;

import java.util.ArrayList;
import java.util.Collection;

public class gGladiateur {
    /**
     * @attribute
     */
    private static Integer nextIdg = 1;

    /**
     * @associates <{uml.Gladiateur}>
     */
    private static Collection<Gladiateur> tsLesGladiateurs;
    
    public static Collection<Gladiateur> ListerGladiateurs() {
        return tsLesGladiateurs;
    }

    public static Collection<Gladiateur> ListerGladiateurs(Integer ideEthnie) {
        return gEthnie.getEthnie(ideEthnie).listerGladiateurs();
    }

    public static Gladiateur getGladiateur(Integer idg) {
        Integer i = 0;            
        boolean continu = true;
        
        while(i < tsLesGladiateurs.size() && continu){
            if (tsLesGladiateurs.toArray()[i] == idg){
                continu = false;
            }
            i++;
        }
    }
}
