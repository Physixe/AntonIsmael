package packglad;

import java.util.ArrayList;


public class Ethnie {
    private Integer ide;
    private String nom;
    private ArrayList<Gladiateur> gladEthnie = new ArrayList<Gladiateur>();


    public  Ethnie(Integer ide, String nom) {
        this.ide=ide;
        this.nom=nom;
    }

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
        return this.ide;
    }

    public String getNom() {
        return this.nom;
    }

    public ArrayList<Gladiateur> listerGladiateurs() {
        return this.gladEthnie;
    }
    
    public Integer ajouterGlad(Gladiateur g)
    {
    	Integer res = -1;
    	if (g != null){
    		this.gladEthnie.add(g);
    		res = g.getIdg();
    	}
    	return res;
    }
    
}
