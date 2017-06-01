package packglad;

import java.util.ArrayList;

public abstract class Gladiateur {
    
    private String nom;
    private Integer vie;
    private static Integer c_vieInitiale = 200;
    private Integer idg;  
    private Ethnie ethnie;
    private static ArrayList<Arme> appartient = new ArrayList<Arme>();

    public Gladiateur(Integer idg, String nom, Ethnie ethnie) {
        this.nom = nom;
        this.idg = idg;
        this.vie = c_vieInitiale;
        this.ethnie= ethnie;
    }

    public String getNom() {
        return nom;
    }

    public Integer getVie() {
        return vie;
    }
    
    public Integer getIdg() {
        return idg;
    }
    
    public Ethnie getEthnie(){
        return ethnie;
    }
    
    public abstract String getType();

    public abstract String rapporter();

    public abstract String saluer();

    

    public Integer frapper(Gladiateur victime, Arme a) {
        Integer res = -1;
        if (victime != null && a != null){
            victime.recoitCoups(this,a);
            res = victime.getIdg();
        }
        
        return res;
    }

    public abstract void recoitCoups(Gladiateur aggresseur, Arme a);

    public static void c_setVieInitiale(Integer v) {
        packglad.Gladiateur.c_vieInitiale = v;
    }

    public Integer perdreArme(Integer ida) {
        Integer i = 0;            
        boolean continu = true;
        
        while(i < appartient.size() && continu){
            if (appartient.toArray()[i] == ida){
                appartient.remove(appartient.toArray()[i]);
                continu = false;
            }
            i++;
        }
        return ida;
    }
    
    public Integer addArme(Arme a) {
        Integer res=-1;
        if(!this.declarerArmes().contains(a) &&gArme.getArmes().contains(a))
        {
            appartient.add(a);
            res = a.getIda();
        }
        return res;
    }

    public abstract Integer getForce();

    public ArrayList<Arme> declarerArmes() {
        //return new ArrayList<Arme>(appartient);
    	return appartient;
    }
    
    public String getEtat() {
        String res = "";
        if (this.vie == 0)
            res = "mort";
        else if (this.vie<10)
            res = "moribond";
        else if (this.vie >= 10 && this.vie <= 50)
            res = "blessé";
        else
            res = "vivant";
        return res;
    }
    
    public void setVie(Integer v) {
        this.vie=v;
    }
    
    public static Integer c_getVieInitiale(){
        return c_vieInitiale;
    }
}