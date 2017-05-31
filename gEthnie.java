package packglad;

import java.util.ArrayList;
import java.util.Collection;

public class gEthnie {
    /**
     * @associates <{uml.Ethnie}>
     */
    private static ArrayList<Ethnie> ttesLesEthnies = new ArrayList<Ethnie>();

    public static Integer creerEthnie (Integer ide, String nom) {
            ttesLesEthnies.add(new Ethnie(ide, nom));
            return ide;
        }
    
    public static Collection<Gladiateur> listerGladiateursDEthnie(Integer ide) {
        int i=0;
        Boolean fin = false;//booléen vrai si on a trouvé l'ethnie qu'on veut parmi celles de la collection
        while (i< ttesLesEthnies.size() && !fin) 
        {
            if(ttesLesEthnies.get(i).getIde()==ide)
            {
                fin=true;
            }
            else
                i++;
        }
        
        return ttesLesEthnies.get(i).listerGladiateurs();
    }

    public static ArrayList<Ethnie> listerEthnies() {
        return ttesLesEthnies;
    }

    public static Ethnie getEthnie(Integer ide) {
        int i=0;
        Boolean fin = false;
        while (i< ttesLesEthnies.size() && !fin) 
        {
            if(ttesLesEthnies.get(i).getIde()==ide)
            {
                fin=true;
            }
            else
                i++;
        }
        
        return ttesLesEthnies.get(i);
    }

    public static Integer getScore(Integer ide) {
        int i=0;
        Boolean fin = false;
        while (i< ttesLesEthnies.size() && !fin) 
        {
            if(ttesLesEthnies.get(i).getIde()==ide)
            {
                fin=true;
            }
            else
                i++;
        }
        
        return ttesLesEthnies.get(i).calculerScore();
    }
}
