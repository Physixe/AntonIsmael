package packglad;

import java.util.ArrayList;


public class Ethnie {

    private Integer ide;
    private String nom;
    private ArrayList<Gladiateur> gladEthnie = new ArrayList<Gladiateur>();//liste des gladiateurs appartenant a l'ethnie courante
    private static boolean c_peutAjouter = true;

    //constructeur
    public  Ethnie(Integer ide, String nom) {
        this.ide=ide;
        this.nom=nom;
    }

    //renvoie le score le l'ethnie
    public Integer calculerScore() {
    	Integer res = 0;

    	for (Gladiateur g : gladEthnie)
    	{
    		if (g.getVie() > 50){
    			res += 10;
    		}
    		else if (g.getVie() >= 10){
    			res += 5;
    		}
    	}

    	return res;
    }

    public Integer getIde() {
        return ide;
    }

    public String getNom() {
        return this.nom;
    }

    public ArrayList<Gladiateur> listerGladiateurs() {
        return this.gladEthnie;
    }

    public Integer ajouterGlad(Gladiateur g)
    {
			if (g==null || gladEthnie==null)
			            throw new IllegalArgumentException("Glad = null");

    	Integer res = -1;
    	if (g != null)//empeche l'acces a un element null
    	{
    		gladEthnie.add(g);
    		res = g.getIdg();
    	}
    	return res;
    }

    public static void c_setPeutAjouter(boolean bool)
    {
    	c_peutAjouter = bool;
    }

    public static boolean c_getPeutAjouter()
    {
    	return c_peutAjouter;
    }

}
