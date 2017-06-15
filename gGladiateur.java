package packglad;

import java.util.ArrayList;

public class gGladiateur {
   
    protected static Integer nextIdg = 1;
    private static ArrayList<Gladiateur> tsLesGladiateurs= new ArrayList<Gladiateur>();
    
    //renvoie tous les gladiateurs du jeu
    public static ArrayList<Gladiateur> ListerGladiateurs() {
        return tsLesGladiateurs;
    }

    //renvoie les gladiateurs appartenant a l'ethnie ide
    public static ArrayList<Gladiateur> ListerGladiateurs(Integer ide) {
        return gEthnie.getEthnie(ide).listerGladiateurs();
    }

    public static Gladiateur getGladiateur(Integer idg) 
    {
        Integer i = 0;            
        boolean trouve = false;
        Gladiateur res = null;
        while(i < tsLesGladiateurs.size() && !trouve)
        {
            if (tsLesGladiateurs.get(i).getIdg() == idg)
            {
            	res = tsLesGladiateurs.get(i);
                trouve = true;
            }
            i++;
        }
        return res;
    }
    
    public static Integer nouveauMirmillion(String nom, Integer poids, Ethnie ethnie) {
        Integer res = -1;      
        if(gEthnie.listerEthnies().contains(ethnie))//empeche de creer un mirmillon avec une fausse ethnie
        {
            Mirmillon m = new Mirmillon(nextIdg, nom,ethnie,poids );
            res = m.getIdg();
            nextIdg++;
            tsLesGladiateurs.add(m);
        }
        return res;
    }

    public static Integer nouveauRetiaire(String nom, Integer agilite, Ethnie ethnie) {
        int res = -1;
        if(gEthnie.listerEthnies().contains(ethnie))//empeche de creer un retiaire avec une fausse ethnie
        {
        	Retiaire r = new Retiaire(nextIdg, nom,agilite, ethnie);
            res = r.getIdg();
            nextIdg++;
            tsLesGladiateurs.add(r);
        }
        return res;
    }
    
    
    public static Integer supprimerGlad(Integer idg) {
        int res=-1;
        int i=0;
        boolean trouve = false;
        while (i < gGladiateur.ListerGladiateurs().size() && !trouve)
        {
            if (gGladiateur.ListerGladiateurs().get(i).getIdg() == idg) {
                res=idg;
                gGladiateur.ListerGladiateurs().remove(i);
                trouve=true;
                nextIdg--;
            }
            else {
                i++;
            }
        }
        return res;
        
    }
    
    public static void viderListe() {
        tsLesGladiateurs.clear();
        nextIdg=1;
    }
}