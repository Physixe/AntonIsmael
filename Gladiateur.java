package packglad;

import java.util.ArrayList;

public abstract class Gladiateur {

    private String nom;
    private Integer vie;
    private static Integer c_vieInitiale = 200;
    private Integer idg;
    private Ethnie ethnie;
    private ArrayList<Arme> appartient = new ArrayList<Arme>();

    //constructeur
    public Gladiateur(Integer idg, String nom, Ethnie ethnie) {
        this.nom = nom;
        this.idg = idg;
        this.vie = c_vieInitiale;
        this.ethnie= ethnie;
        ethnie.ajouterGlad(this);
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
        if (victime != null && a != null)//empeche l'acces a des elements null
        {
            victime.recoitCoups(this,a);
            res = victime.getIdg();
        }
        else{
-            throw new IllegalArgumentException("L'arme est nulle");
-        }
        return res;
    }

    public abstract void recoitCoups(Gladiateur aggresseur, Arme a);

    public static void c_setVieInitiale(Integer v) {
    	if (v < 0)//empeche de set une vie negative
    	{
    		v = 0;
    	}
        packglad.Gladiateur.c_vieInitiale = v;
    }

    public Integer perdreArme(Integer ida) {
      if (ida==null)
            throw new IllegalArgumentException("L'arme est nulle");
        Integer i = 0;
        boolean continu = true;
        Integer res = -1;
        Arme a = gArme.getArme(ida);
        if (a != null)//empeche l'acces a un elements null
        {
        	while(i < appartient.size() && continu){
            if (appartient.get(i).getIda() == ida){
                appartient.remove(appartient.toArray()[i]);
                continu = false;
                res = ida;
            }
            i++;
        }
        }

        return res;
    }

    public Integer addArme(Arme a) {
        Integer res=-1;
        if (a==null)
                    throw new IllegalArgumentException("L'arme est nulle");
                else if (a != null && !this.declarerArmes().contains(a))//empeche l'acces a un elements null, et d'avoir 2 fois la meme arme        {
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
        if (this.vie <= 0)
            res = "Mort";
        else if (this.vie<10)
            res = "Moribond";
        else if (this.vie >= 10 && this.vie <= 50)
            res = "Blesse";
        else
            res = "Bien portant";
        return res;
    }

    public void setVie(Integer v) {
        this.vie=v;
    }

    public static Integer c_getVieInitiale(){
        return c_vieInitiale;
    }
}
