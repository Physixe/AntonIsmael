package packglad;

import java.util.ArrayList;
import java.util.Collection;

public class Facade {
    public static Integer lancerJeu() {
        //initialise les paramètres du jeu avec les valeurs par défaut
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
        
        donnerUneArme(glad1, arme2);
        donnerUneArme(glad1, arme6);
        donnerUneArme(glad1, arme3);
        
        donnerUneArme(glad2, arme1);
        donnerUneArme(glad2, arme4);
        donnerUneArme(glad2, arme5);
        donnerUneArme(glad2, arme6);
        
        donnerUneArme(glad3, arme2);
        donnerUneArme(glad3, arme6);
        
        donnerUneArme(glad4, arme1);
        donnerUneArme(glad4, arme4);
        
        donnerUneArme(glad5, arme1);
        donnerUneArme(glad5, arme6);
        
        donnerUneArme(glad6, arme4);
        donnerUneArme(glad6, arme5);
        
    }
    public static void parametrage(Integer vieInit, Integer forceRet, Integer poidsMax, Integer agilMax) {
        //permet de modifier la valeur des param�tres du jeu
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
        
        if(gGladiateur.getGladiateur(idg).getType()=="Mirmillon") {
            for (Gladiateur g : ((Mirmillon) gGladiateur.getGladiateur(idg)).listerAgresseurs()) {
                res.add(g.getIdg())       ;               
            }
        }
        
        return res;
        
    }  
    public static String faireSaluerGladiateur(Integer idg) {
        //retourne la phrase de salut : "Ave Caesar...." du gladiateur idg
        return gGladiateur.getGladiateur(idg).saluer();
    }
    public static String faireRapport(Integer idg) {
        //retourne le rapport du gladiateur idg (cf �nonc�)
    	String res = "L'idg ne correspond � aucun Gladiateur";
    	Gladiateur g = gGladiateur.getGladiateur(idg);
    	if (g != null){
    		res = g.rapporter();
    	}
        return res;
    }
    public static Collection<Integer> declarerArmes(Integer idg) {
        // retourne la liste des ida des armes du gladiateur idg
    	ArrayList<Integer> res = new ArrayList<Integer>();
        for (Arme a : gArme.getArmes()) {
            res.add(a.getIda());
        }
        return res;
    }
    
    public static Integer supprimerGlad(Integer idg) {
        int res=-1;
        int i=0;
        boolean trouve = false;
        while (i < gGladiateur.ListerGladiateurs().size() && !!trouve)
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
        return Mirmillon.c_autoriserArmeMirmillon(gArme.getArme(ida));
    }
    public static Integer autoriserArmeAuxRetiaires(Integer ida) {
        return Retiaire.c_autoriserArmeRetiaire(gArme.getArme(ida));
    }
    public static Integer donnerUneArme(Integer ida, Integer idg) {
        //donne l'arme ida au gladiateur idg
    	Integer res = -1;
    	Arme a = gArme.getArme(ida);
    	Gladiateur g = gGladiateur.getGladiateur(idg);
    	if (g != null){
    		res =  g.addArme(a);
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
        //retourne la liste des ida des armes disponibles aux r�tiaires
    	ArrayList<Integer> res = new ArrayList<Integer>();
        for (Arme a : Retiaire.c_getArmesDispoRet()) {
            res.add(a.getIda());
        }
        return res;
    }
    
    public static String decrireArme(Integer ida) {
        //renvoie en String la description de l'arme (cf p4 de l'�nonc�) ida,nom,valOff,ValDef, dispoMir,dispoRet
        String desc = "";
        String dispoMir = "Non";
        String dispoRet = "Non";
        Arme a = gArme.getArme(ida);
        if(Mirmillon.c_getArmesDispoMir().contains(a)){
            dispoMir = "Oui";
        }
        if(Retiaire.c_getArmesDispoRet().contains(a)){
            dispoRet = "Oui";
        }
        desc += "Arme : "      + a.getIda()    + " " 
             +  a.getNom()    + " ; " 
             +  "valOff : "   + a.getValOff() + " ; "
             +  "ValDef : "   + a.getValDef() + " ; "
             +  "dispoMir : " + dispoMir      + " ; "
             +  "dispoRet : " + dispoRet;
        return desc;
    }
	public static String nomDeLArme(Integer ida) {
		//renvoie en String juste le nom de l'arme
            return gArme.getArme(ida).getNom();
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
        return "Ethnie : "+ ide + "\n"
                + "Nom : " + gEthnie.getEthnie(ide).getNom() + "\n"
                + "Score : " + getScore(ide) + "\n";
        
    }
    public static Integer getScore(Integer ide) {
        //retourne le score de l'ethnie ide
        return gEthnie.getEthnie(ide).calculerScore();
    }
 
//combat 
    public static Integer frapper(Integer idgAgresseur, Integer idgVictime, Integer ida) {
        //le gladiateur idgAgresseur frappe le gladiateur idgVictime � l'aide de l'arme ida
        return gGladiateur.getGladiateur(idgAgresseur).frapper(gGladiateur.getGladiateur(idgVictime), gArme.getArme(ida));
    }
    public static Integer desarmer(Integer idgVictime, Integer ida) {
        //dépouille le gladiateur idgVictime de son arme ida
        return gGladiateur.getGladiateur(idgVictime).perdreArme(ida);
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