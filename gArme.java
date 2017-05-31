package packglad;

import java.util.ArrayList;
import java.util.Collection;

public class gArme {
    static Integer nextIda = 1;

    private static ArrayList<Arme> ttesLesArmes=new ArrayList<Arme>();
    

    public static Integer nouvelleArme(String nom, Integer puissanceOff, Integer puissanceDef) {
            ttesLesArmes.add(new Arme(nom,puissanceOff, puissanceDef));
            nextIda +=1;
            return nextIda;
        }
        

    public static Arme getArme(Integer ida) {
        int i=0;
        boolean fin = false;//booléen vrai si on a trouvé l'arme qu'on veut parmi celles de la collection
        
        while (i < ttesLesArmes.size() && !fin) 
        {
            if(ttesLesArmes.get(i).getIda()==ida)
            {
                fin=true;
            }
            else
                i++;
        }
        
        return ttesLesArmes.get(i);
    }
    
    public static ArrayList<Arme> getArmes()
    {
          return new ArrayList<Arme>(ttesLesArmes);
    } 
}
