package packglad;

import java.util.ArrayList;
import java.util.Collection;

public class gEthnie {

    private static ArrayList<Ethnie> ttesLesEthnies = new ArrayList<Ethnie>();

    public static Integer creerEthnie (Integer ide, String nom) {
    	Integer res = -1;
    	if (Ethnie.c_getPeutAjouter())//empeche de creer une ethnie pendant le combat (entre le debut et la fin)
    	{
    		ttesLesEthnies.add(new Ethnie(ide, nom));
    		res = ide;
    	}
    	return res;
    }
    
    public static Collection<Gladiateur> listerGladiateursDEthnie(Integer ide) {
        ArrayList<Gladiateur> res = new ArrayList<Gladiateur>();
        for (Gladiateur g : gGladiateur.ListerGladiateurs()){
        	if(g.getEthnie() == getEthnie(ide)){
        		res.add(g);
        	}
        }
        return res;
    }

    public static ArrayList<Ethnie> listerEthnies() {
        return ttesLesEthnies;
    }

    public static Ethnie getEthnie(Integer ide) {
        int i=0;
        Boolean fin = false;
        Ethnie res = null;
        while (i< ttesLesEthnies.size() && !fin) 
        {
            if(ttesLesEthnies.get(i).getIde()==ide)
            {
            	res = ttesLesEthnies.get(i);
                fin=true;
            }
            else
                i++;
        }
        return res;
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
