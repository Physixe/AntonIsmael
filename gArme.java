package packglad;

import java.util.ArrayList;
import java.util.Collection;

public class gArme {
	
    protected static Integer nextIda = 1;
    private static ArrayList<Arme> ttesLesArmes=new ArrayList<Arme>();
    

    public static Integer nouvelleArme(String nom, Integer puissanceOff, Integer puissanceDef) {
            ttesLesArmes.add(new Arme(nom,puissanceOff, puissanceDef));
            nextIda ++;
            return nextIda;
        }
        

    public static Arme getArme(Integer ida) {
        Integer i=0;
        boolean fin = false;//booléen vrai si on a trouvé l'arme qu'on veut parmi celles de la liste
        Arme res = null;
        while (i < ttesLesArmes.size() && !fin) 
        {
            if(ttesLesArmes.get(i).getIda()==ida)
            {
            	res = ttesLesArmes.get(i);
                fin=true;
            }
            else
                i++;
        }
        for (Arme a : ttesLesArmes){
        	System.out.println(a.toString());
        }
        System.out.println("#######");
        return res;
    }
    
    public static ArrayList<Arme> getArmes()
    {
          return new ArrayList<Arme>(ttesLesArmes);
    } 
}
