package packglad;

import java.util.ArrayList;
import java.util.Collection;

public class Facade {
    public static Integer lancerJeu() {
        //initialise les paramÃ¨tres du jeu avec les valeurs par dÃ©faut
        parametrage(200,30,100,100);
        return 0;
    }
    public static void lancerJeuDEssai() {
        System.out.println("============ Creation des 3 ethnies ============");
        
        Integer ethnie1 = gEthnie.creerEthnie(1, "Gaulois");
        Integer ethnie2 = gEthnie.creerEthnie(2, "Thraces");
        Integer ethnie3 = gEthnie.creerEthnie(3, "Dalmates");
        
        
        System.out.println("============ Creation des 6 Armes  ============");  
        
        Integer arme1 = creerUneArme("Glaive", 80, 0);
        Integer arme2 = creerUneArme("Trident", 100, 0);
        Integer arme3 = creerUneArme("Filet", 40, 20);
        Integer arme4 = creerUneArme("Bouclier", 40, 40);
        Integer arme5 = creerUneArme("Casque", 0, 20);
        Integer arme6 = creerUneArme("Jambiere", 0, 10);
        
        autoriserArmeAuxMirmillons(arme1);
        autoriserArmeAuxRetiaires(arme1);
        autoriserArmeAuxRetiaires(arme2);
        autoriserArmeAuxRetiaires(arme3);
        autoriserArmeAuxMirmillons(arme4);
        autoriserArmeAuxMirmillons(arme5);
        autoriserArmeAuxMirmillons(arme6);
        autoriserArmeAuxRetiaires(arme6);

        
        System.out.println("============ Creation des 6 Gladiateurs  ============");
        
        Integer glad1 = creerRetiaire("Unix", 30, ethnie1);
        Integer glad2 = creerMirmillon("Informatix", 100, ethnie1);
        Integer glad3 = creerRetiaire("Ceplusplus", 40, ethnie2);
        Integer glad4 = creerMirmillon("Pythonus", 60, ethnie2);
        Integer glad5 = creerRetiaire("Szervlet", 50, ethnie3);
        Integer glad6 = creerMirmillon("Ramazmjet", 80, ethnie3);
        
        System.out.println("============ Attribution des 15 armes  ============");
        
        donnerUneArme(arme2, glad1);
        donnerUneArme(arme6, glad1);
        donnerUneArme(arme3, glad1);
        
        donnerUneArme(arme1, glad2);
        donnerUneArme(arme2, glad2);
        donnerUneArme(arme5, glad2);
        donnerUneArme(arme6, glad2);
        
        donnerUneArme(arme2, glad3);
        donnerUneArme(arme6, glad3);
        
        donnerUneArme(arme1, glad4);
        donnerUneArme(arme4, glad4);
        
        donnerUneArme(arme1, glad5);
        donnerUneArme(arme6, glad5);
        
        donnerUneArme(arme4, glad6);
        donnerUneArme(arme5, glad6);
        
    }
    public static void parametrage(Integer vieInit, Integer forceRet, Integer poidsMax, Integer agilMax) {
        //permet de modifier la valeur des paramï¿½tres du jeu
        Gladiateur.c_setVieInitiale(vieInit);
        Retiaire.c_setForce(forceRet);
        Retiaire.c_setAgiliteMax(agilMax);
        Mirmillon.setC_poidsMax(poidsMax);
    }

//Les gladiateurs
    public static Integer creerRetiaire(String nom, Integer agilite, Integer ide) {
        return gGladiateur.nouveauRetiaire(nom, agilite, gEthnie.getEthnie(ide));
    }
    
    public static Integer creerMirmillon(String nom, Integer poids, Integer ide) {
        return gGladiateur.nouveauMirmillion(nom, poids, gEthnie.getEthnie(ide));
    }
    public static Collection<Integer> listerTousGladiateurs() {
        //retourne la liste des idg de tous les gladiateurs
    	ArrayList<Integer> res = new ArrayList<Integer>();
        for(Gladiateur g : gGladiateur.ListerGladiateurs()) {
            res.add(g.getIdg());
        }
        return res;
    }    
    
    public static Collection<Integer> listerAgresseurs(Integer idg) {
        //  retourne la liste des idg des agresseurs du gladiateur idg (si idg est un mirmillon sinon rien)
    	ArrayList<Integer> res = new ArrayList<Integer>();
    	Gladiateur g = gGladiateur.getGladiateur(idg);
    	
    	if (g != null)
    	{
    		if(g.getType()=="Mirmillon") {
                for (Gladiateur glad : ((Mirmillon) gGladiateur.getGladiateur(idg)).listerAgresseurs()) {
                    res.add(glad.getIdg())       ;               
                }
            }
    	}
 
        return res;
        
    }  
    public static String faireSaluerGladiateur(Integer idg) {
        //retourne la phrase de salut : "Ave Caesar...." du gladiateur idg
    	Gladiateur g = gGladiateur.getGladiateur(idg);
    	String res = "L'idg ne correspond a aucun Gladiateur";
    	if (g != null)
    	{
    		res = g.saluer();
    	}
        return res;
    }
    public static String faireRapport(Integer idg) {
        //retourne le rapport du gladiateur idg (cf ï¿½noncï¿½)
    	String res = "L'idg ne correspond à aucun Gladiateur";
    	Gladiateur g = gGladiateur.getGladiateur(idg);
    	if (g != null){
    		res = g.rapporter();
    	}
        return res;
    }
    public static Collection<Integer> declarerArmes(Integer idg) {
        // retourne la liste des ida des armes du gladiateur idg
    	ArrayList<Integer> res = new ArrayList<Integer>();
    	Gladiateur g = gGladiateur.getGladiateur(idg);
    	if (g != null){
    		for (Arme a : g.declarerArmes()) {
                res.add(a.getIda());
            }
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
            }
            else {
                i++;
            }
        }
        return res;
        
    }

// Les armes
    public static Integer creerUneArme(String nom, Integer puissOff, Integer puissDef) {
        return gArme.nouvelleArme(nom, puissOff, puissDef);
    }
    public static Integer autoriserArmeAuxMirmillons(Integer ida) {
    	Arme a = gArme.getArme(ida);
    	Integer res = -1;
    	if (a != null)
    	{
    		res = Mirmillon.c_autoriserArmeMirmillon(a);
    	}
        return res;
    }
    public static Integer autoriserArmeAuxRetiaires(Integer ida) {
    	Arme a = gArme.getArme(ida);
    	Integer res = -1;
    	if (a != null)
    	{
    		res = Retiaire.c_autoriserArmeRetiaire(a);
    	}
        return res;
    }
    public static Integer donnerUneArme(Integer ida, Integer idg) {
        //donne l'arme ida au gladiateur idg
    	Integer res = -1;
    	Arme a = gArme.getArme(ida);
    	Gladiateur g = gGladiateur.getGladiateur(idg);
    	if (g != null && a != null){
    		if (g.getType() == "Mirmillon" && Mirmillon.c_getArmesDispoMir().contains(a))
    		{
    			res =  g.addArme(a);
    		}
    		else if (g.getType() == "Retiaire" && Retiaire.c_getArmesDispoRet().contains(a)){
    			res =  g.addArme(a);
    		}
    	}
        return res;
    }
    public static Collection<Integer> listerArmesDispoMirmillon() {
        //retourne la liste des ida des armes disponibles aux mirmillons
        ArrayList<Integer> res = new ArrayList<Integer>();
         for (Arme a : Mirmillon.c_getArmesDispoMir()) {
            res.add(a.getIda());
         }
         return res;
    }
    public static Collection<Integer> listerArmesDispoRetiaire() {
        //retourne la liste des ida des armes disponibles aux rï¿½tiaires
    	ArrayList<Integer> res = new ArrayList<Integer>();
        for (Arme a : Retiaire.c_getArmesDispoRet()) {
            res.add(a.getIda());
        }
        return res;
    }
    
    public static String decrireArme(Integer ida) {
        //renvoie en String la description de l'arme (cf p4 de l'ï¿½noncï¿½) ida,nom,valOff,ValDef, dispoMir,dispoRet
    	Arme a = gArme.getArme(ida);
    	String res = "L'ida ne correspond a aucune arme.";
    	if (a != null)
    	{
    		res = a.decrire();
    	}
        return res;
    }
	public static String nomDeLArme(Integer ida) {
		//renvoie en String juste le nom de l'arme
		String res = "L'ida ne correspond à aucune arme.";
		Arme a = gArme.getArme(ida);
		if (a != null)
		{
			res = a.getNom();
		}
		return res;
    }

// Les ethnies 
    public static Collection<Integer> listerEthnies() {
        //retourne la liste des ide de toutes les ethnies
    	ArrayList<Integer> res = new ArrayList<Integer>();
        
        for(Ethnie e : gEthnie.listerEthnies()) {
            res.add(e.getIde());
        }
        return res;
    }
    public static Collection<Integer> listerGladiateursDEthnie(Integer ide) {
        //liste des idg des gladiateurs de l'ethnie ide
    	ArrayList<Integer> res = new ArrayList<Integer>();
        
        for(Gladiateur g : gEthnie.listerGladiateursDEthnie(ide))
            res.add(g.getIdg());
        return res;
    }
    public static String decrireEthnie(Integer ide) {
        //Renvoie la description de l'ethnie : ide,nom,score)
    	String res = "L'ide ne correspons à aucune Ethnie.";
    	Ethnie e = gEthnie.getEthnie(ide);
    	if (e != null)
    	{
    		res = "Ethnie : "+ ide + " ; "
                    + "Nom : " + e.getNom() + " ; "
                    + "Score : " + getScore(ide);
    	}
        return res;
        
    }
    public static Integer getScore(Integer ide) {
        //retourne le score de l'ethnie ide
    	Integer res = -1;
    	Ethnie e = gEthnie.getEthnie(ide);
    	if (e != null)
    	{
    		res = e.calculerScore();
    	}
        return res;
    }
 
//combat 
    public static Integer frapper(Integer idgAgresseur, Integer idgVictime, Integer ida) {
        //le gladiateur idgAgresseur frappe le gladiateur idgVictime ï¿½ l'aide de l'arme ida
    	Integer res = -1;
    	Gladiateur agresseur = gGladiateur.getGladiateur(idgAgresseur);
    	Gladiateur victime = gGladiateur.getGladiateur(idgVictime);
    	Arme a = gArme.getArme(ida);
    	if (agresseur != null && victime != null && a != null)
    	{
    		res = agresseur.frapper(victime, a);
    	}
        return res;
    }
    public static Integer desarmer(Integer idg, Integer ida) {
        //dÃ©pouille le gladiateur idgVictime de son arme ida
    	Integer res = -1;
    	Gladiateur g = gGladiateur.getGladiateur(idg);
    	Arme a = gArme.getArme(ida);
    	if (g != null && a != null)
    	{
    		res = g.perdreArme(ida);
    	}
        return res;
    }
    public static Collection<Integer> vainqueurs() {
        //renvoie le ou les ide de l'ethnie (des ethnies ex aequo) gagnante/s
    	ArrayList<Integer> vainqueurs = new ArrayList<Integer>();
        Integer scoreMax = 0;
        for (int i=0; i< listerEthnies().size(); i++) 
        {
            if(gEthnie.listerEthnies().get(i).calculerScore()>scoreMax)
            {
                scoreMax = gEthnie.listerEthnies().get(i).calculerScore();
            }
        }
        
        for (int i=0; i< listerEthnies().size(); i++) 
        {
            if(gEthnie.listerEthnies().get(i).calculerScore()==scoreMax)
            {
                vainqueurs.add(gEthnie.listerEthnies().get(i).getIde());
            }
        }  
        return vainqueurs;    
    } 
    
//Pour les tests unitaires
    
    public static String nomDuGladiateur(Integer idg) {
        return gGladiateur.getGladiateur(idg).getNom();
    }
    
    public static String nomDeLEthnie(Integer ide) {
        return gEthnie.getEthnie(ide).getNom();
    }  

}