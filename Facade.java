package packglad;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class Facade {
	
    public static Integer lancerJeu() {
        //initialise les parametres du jeu avec les valeurs par defaut
        parametrage(200,30,100,100);
        
        gEthnie.viderListe();
        gGladiateur.viderListe();
        gArme.viderListe();
        Retiaire.c_viderListe();
        Mirmillon.c_viderListe();
        
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
        //donnerUneArme(arme2, glad2);
        donnerUneArme(arme5, glad2);
        donnerUneArme(arme6, glad2);
        donnerUneArme(arme4, glad2);
        
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
        //permet de modifier la valeur des parametres du jeu
        Gladiateur.c_setVieInitiale(vieInit);
        Retiaire.c_setForce(forceRet);
        Retiaire.c_setAgiliteMax(agilMax);
        Mirmillon.setC_poidsMax(poidsMax);
    }
    
    public static int getValOffArme(int ida){
        return gArme.getArme(ida).getValOff();
    }
    
    public static int getValDeffArme(int ida){
        return gArme.getArme(ida).getValDef();
    }
    
    public static int creerEthnie(int ide, String nom) {
        return gEthnie.creerEthnie(ide, nom);
    }
    
    public static int agiliteRetiaire(int idg){
       Retiaire r = (Retiaire)gGladiateur.getGladiateur(idg);
       return r.getAgilite();
    }
    
    
    
    public static int poidsMirmillon(int idg){
        Mirmillon m = (Mirmillon)gGladiateur.getGladiateur(idg);
        return m.getPoids();
    }
    
    public static int vieGladiateur(int idg){
        return gGladiateur.getGladiateur(idg).getVie();
    }
    
    public static int forceGladiateur(int idg){
        return gGladiateur.getGladiateur(idg).getForce();
    }
    
    public static Integer getAgiliteMaxRetiaire()
    {
        return Retiaire.c_getAgiliteMax();
    }
    
    public static Integer getPoidsMaxMirmillon()
    {
        return Mirmillon.c_getPoidsMax();
    }
    

//Les gladiateurs
    public static Integer creerRetiaire(String nom, Integer agilite, Integer ide) {
    	Integer res = -1;
    	Ethnie e = gEthnie.getEthnie(ide);
        if(e == null || nom == null || nom == "" || agilite == null || agilite < 0){//empeche l'acces a un element null
            throw new IllegalArgumentException();
        }
        else
        {
            res = gGladiateur.nouveauRetiaire(nom, agilite, e);
        }
        return res;
    }
    
    public static Integer creerMirmillon(String nom, Integer poids, Integer ide) {
    	Integer res = -1;
    	Ethnie e = gEthnie.getEthnie(ide);
    	    if(e == null || nom == null || nom == "" || poids == null || poids < 0){//empeche l'acces a un element null
                throw new IllegalArgumentException();
    	    }
    	    else
            {
                 res = gGladiateur.nouveauMirmillion(nom, poids, e);
            }
            
    	    return res;
    	}
        
    
    

    
    public static Collection<Integer> listerTousGladiateurs() {
        //retourne la liste des idg de tous les gladiateurs
    	ArrayList<Integer> res = new ArrayList<Integer>();
        for(Gladiateur g : gGladiateur.listerGladiateurs()) {
            res.add(g.getIdg());
        }
        return res;
    }    
    
    public static Collection<Integer> listerAgresseurs(Integer idg) {
        
        if(gGladiateur.getGladiateur(idg).getType() == "Retiaire") {
            throw new IllegalArgumentException();
        }
        else {
            ArrayList<Integer> res = new ArrayList<Integer>();
            Gladiateur g = gGladiateur.getGladiateur(idg);
            
            if (g != null)//empeche l'acces a un element null
            {
                    if(g.getType()=="Mirmillon") //verifie que le gladiateur est un mirmillon car il est le seul a posseder une liste d'agresseurs
                    {
                    for (Gladiateur glad : ((Mirmillon) gGladiateur.getGladiateur(idg)).listerAgresseurs()) {
                        res.add(glad.getIdg())       ;               
                    }
                }
            }
            
            return res;
        }
    }  
    public static String faireSaluerGladiateur(Integer idg) {
        //retourne la phrase de salut : "Ave Caesar...." du gladiateur idg
    	Gladiateur g = gGladiateur.getGladiateur(idg);
    	String res = "L'idg ne correspond a aucun Gladiateur";
    	if (g != null)//empeche l'acces a un element null
    	{
    		res = g.saluer();
    	}
    	Ethnie.c_setPeutAjouter(false);//une ethnie ne pourra plus etre creer jusqu'a la fin du combat
        return res;
    }
    public static String faireRapport(Integer idg) {
        //retourne le rapport du gladiateur idg (cf enonce)
    	String res = "L'idg ne correspond a aucun Gladiateur";
    	Gladiateur g = gGladiateur.getGladiateur(idg);
    	if (g != null)//empeche l'acces a un element null
    	{
    		res = g.rapporter();
    	}
        return res;
    }
    public static Collection<Integer> declarerArmes(Integer idg) {
        // retourne la liste des ida des armes du gladiateur idg
    	ArrayList<Integer> res = new ArrayList<Integer>();
    	Gladiateur g = gGladiateur.getGladiateur(idg);
    	if (g != null)//empeche l'acces a un element null
    	{
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
        while (i < gGladiateur.listerGladiateurs().size() && !trouve)
        {
            if (gGladiateur.listerGladiateurs().get(i).getIdg() == idg) {
                for (Gladiateur m : gGladiateur.listerGladiateurs()){
                    if (m.getType() == "Mirmillon") {
                        if (((Mirmillon)m).listerAgresseurs().contains(gGladiateur.getGladiateur(idg))){
                            ((Mirmillon)m).listerAgresseurs().remove(((Mirmillon)m).listerAgresseurs().indexOf(gGladiateur.getGladiateur(idg)));
                        }
                    }
                }
                
                
                res=idg;
                gGladiateur.listerGladiateurs().remove(i);
                trouve=true;
                
                
                
            }
            else {
                i++;
            }
        }
        return res;
        
    }
    
    public static Integer supprimerEthnie(Integer ide)
    {
        int res=-1;
        int i=0;
        boolean trouve = false;
        while (i < gEthnie.listerEthnies().size() && !trouve)
        {
            if (gEthnie.listerEthnies().get(i).getIde() == ide) {
                res=ide;
                gEthnie.listerEthnies().remove(i);
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
        if (nom==null || nom=="" || puissOff < 0 || puissDef <0)
            throw new IllegalArgumentException();
        for (Arme a : gArme.getArmes())
            if (a.getNom()==nom)
                throw new IllegalArgumentException();
        return gArme.nouvelleArme(nom, puissOff, puissDef);
    }
    
    public static Integer autoriserArmeAuxMirmillons(Integer ida) {
    	Arme a = gArme.getArme(ida);
    	Integer res = -1;
    	if (a != null)//empeche l'acces a un element null
    	{
            
            res = Mirmillon.c_autoriserArmeMirmillon(a);
    	}
        return res;
    }
    public static Integer autoriserArmeAuxRetiaires(Integer ida) {
    	Arme a = gArme.getArme(ida);
    	Integer res = -1;
    	if (a != null)//empeche l'acces a un element null
    	{
    		res = Retiaire.c_autoriserArmeRetiaire(a);
    	}
        return res;
    }
    public static Integer donnerUneArme(Integer ida, Integer idg) {
        //donne l'arme ida au gladiateur idg
        
        if (!gArme.getArmes().contains(gArme.getArme(ida)) || !gGladiateur.listerGladiateurs().contains(gGladiateur.getGladiateur(idg))){
            throw new NoSuchElementException();
        }
        
        Integer res = -1;
                Arme a = gArme.getArme(ida);
                Gladiateur g = gGladiateur.getGladiateur(idg);
                if (g != null && a != null)//empeche l'acces a des elements null
                {
                        if(!g.declarerArmes().contains(a))//Empeche de donner 2 fois la meme arme a un gladiateur
                                {
                                if (g.getType() == "Mirmillon")
                                {

                                        if (Mirmillon.c_getArmesDispoMir().contains(a))//empeche de donner une arme non autorisee
                                        {
                                                if (!g.declarerArmes().contains(a))
                                                {
                                                        res =  g.addArme(a);
                                                }
                                        }else{
                                                System.out.println("Les Mirmillon ne sont pas autorise a recevoir cette arme.");
                                        }
                                }
                                else if (g.getType() == "Retiaire"){
                                        if (Retiaire.c_getArmesDispoRet().contains(a))//empeche de donner une arme non autorisee
                                        {
                                                res =  g.addArme(a);
                                        }else{
                                                System.out.println("Les Retiaire ne sont pas autorise a recevoir cette arme.");
                                        }
                                }
                                }else{
                                        System.out.println(g.getNom() + " possede deja cette arme.");
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
        //retourne la liste des ida des armes disponibles aux retiaires
    	ArrayList<Integer> res = new ArrayList<Integer>();
        for (Arme a : Retiaire.c_getArmesDispoRet()) {
            res.add(a.getIda());
        }
        return res;
    }
    
    public static String decrireArme(Integer ida) {
        //renvoie en String la description de l'arme (cf p4 de l'enonce) ida,nom,valOff,ValDef, dispoMir,dispoRet
    	Arme a = gArme.getArme(ida);
    	String res = "L'ida ne correspond a aucune arme.";
    	if (a != null)//empeche l'acces a un element null
    	{
    		res = a.decrire();
    	}
        return res;
    }
	public static String nomDeLArme(Integer ida) {
		//renvoie en String juste le nom de l'arme
		String res = "L'ida ne correspond a aucune arme.";
		Arme a = gArme.getArme(ida);
		if (a != null)//empeche l'acces a un element null
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
    
    public static Collection<Integer> listerArmes() {
        //retourne la liste de toutes les armes
        ArrayList<Integer> res = new ArrayList<Integer>();
        
        for(Arme a : gArme.getArmes()){
            res.add(a.getIda());
        }
        return res;
    }
    
    public static Collection<Integer> listerArmesDuGlad(Integer idg){
        ArrayList<Integer> res = new ArrayList<Integer>();
    
        for(Arme a : gGladiateur.getGladiateur(idg).declarerArmes())
            res.add(a.getIda());
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
    	String res = "L'ide ne correspons a aucune Ethnie.";
    	Ethnie e = gEthnie.getEthnie(ide);
    	if (e != null)//empeche l'acces a un element null
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
    	if (e != null)//empeche l'acces a un element null
    	{
    		res = e.calculerScore();
    	}
        return res;
    }
 
//combat 
    public static Integer frapper(Integer idgAgresseur, Integer idgVictime, Integer ida) {
        //le gladiateur idgAgresseur frappe le gladiateur idgVictime a l'aide de l'arme ida
    	Integer res = -1;
    	Gladiateur agresseur = gGladiateur.getGladiateur(idgAgresseur);
    	Gladiateur victime = gGladiateur.getGladiateur(idgVictime);
    	Arme a = gArme.getArme(ida);
    	if (agresseur != null && victime != null && a != null)//empeche l'acces a des elements null
    	{
    		if (agresseur.getVie()>0){//empeche un gladiateur mort de frapper
    			if (agresseur.declarerArmes().contains(a))//empeche de un gladiateur de frapper avec une arme qu'il n'a pas
    			{
    				res = agresseur.frapper(victime, a);
    			}else{
    				System.out.println(agresseur.getNom() + " ne possede pas l'arme : " + a.getNom());
    			}
    			
    		}else{
    			System.out.println(agresseur.getNom() + " (N°"+ idgAgresseur + ") est mort...");
    		}
    		
    	}
        return res;
    }
    public static Integer desarmer(Integer idg, Integer ida) {
        //depouille le gladiateur idgVictime de son arme ida
    	Integer res = -1;
    	Gladiateur g = gGladiateur.getGladiateur(idg);
    	Arme a = gArme.getArme(ida);
        
        if ((g.getType() == "Retiaire" && !Retiaire.c_getArmesDispoRet().contains(a)) || (g.getType()=="Mirmillon" && !Mirmillon.c_getArmesDispoMir().contains(a))) {
            throw new IllegalArgumentException();
        }
        
    	else if (g != null && a != null)//empeche l'acces a des elements nuls
    	{
    		if (g.declarerArmes().contains(a))//empeche de desarmer un gladiateur d'une arme qu'il ne possede pas
    		{
    			res = g.perdreArme(ida);
    		}else{
    			System.out.println(g.getNom() + " ne possede pas de " + a.getNom());
                    throw new NoSuchElementException();
    		}
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
        Ethnie.c_setPeutAjouter(true);
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