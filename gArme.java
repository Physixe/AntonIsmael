package packglad;

import java.util.ArrayList;

public class gArme {
	
    protected static Integer nextIda = 1;
    private static ArrayList<Arme> ttesLesArmes=new ArrayList<Arme>();
    

    public static Integer nouvelleArme(String nom, Integer puissanceOff, Integer puissanceDef) {
            ttesLesArmes.add(new Arme(nom,puissanceOff, puissanceDef, nextIda));
            Integer res = nextIda;
            nextIda++;
            return res;  
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
        return res;
    }
    
    public static ArrayList<Arme> getArmes()
    {
          return new ArrayList<Arme>(ttesLesArmes);
    } 
}
