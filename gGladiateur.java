package packglad;

import java.util.ArrayList;
import java.util.Collection;

public class gGladiateur {
   
    protected static Integer nextIdg = 1;

    private static ArrayList<Gladiateur> tsLesGladiateurs= new ArrayList<Gladiateur>();
    
    public static ArrayList<Gladiateur> ListerGladiateurs() {
        return tsLesGladiateurs;
    }

    public static ArrayList<Gladiateur> ListerGladiateurs(Integer ethnie) {
        return gEthnie.getEthnie(ethnie).listerGladiateurs();
    }

    public static Gladiateur getGladiateur(Integer idg) 
    {
        Integer i = 0;            
        boolean trouve = false;
        
        while(i < tsLesGladiateurs.size() && !trouve)
        {
            if (tsLesGladiateurs.toArray()[i] == idg)
            {
                trouve = true;
            }
            i++;
        }
        return gGladiateur.tsLesGladiateurs.get(i);
    }
    
    public static Integer nouveauMirmillion(String nom, Integer poids, Ethnie ethnie) {
        Integer res = -1;
        
        if(gEthnie.listerEthnies().contains(ethnie))
        {
            res = new Mirmillon(nom,ethnie,poids).getIdg();
            nextIdg++;
        }
        return res;
    }

    public static Integer nouveauRetiaire(String nom, Integer agilite, Ethnie ethnie) {
        int res = -1;
        if(gEthnie.listerEthnies().contains(ethnie)) 
        {
            res = new Retiaire(nom,agilite, ethnie).getIdg();
            nextIdg++;
        }
        return res;
    }
    
}
