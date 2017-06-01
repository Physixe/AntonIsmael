package packglad;

import java.util.ArrayList;

public class Retiaire extends Gladiateur {
    
    private static String c_type = "Retiaire";
    private Integer agilite;
    private static Integer c_force = 30;
    private static Integer c_agiliteMax = 50;
    private static ArrayList<Arme> c_armesAccessRetiaire = new ArrayList<Arme>();

    public Retiaire(Integer idg, String nom, Integer agilite, Ethnie ethnie) {
        super(idg, nom,ethnie);
        if (agilite > Retiaire.c_agiliteMax){//empeche de depasser l'agilite max
        	agilite = Retiaire.c_agiliteMax;
        }else if(agilite < 0){//empeche d'avoir une agilite negative
        	agilite = 0;
        }
        this.agilite=agilite;
    }
    
    public  Integer getAgilite(){
        return agilite;
    }

    public static void c_setAgiliteMax(Integer a) {
        if (a < 0)//empeche une agilite max negative
        {
        	a = 0;
        }
        c_agiliteMax=a;
    }
    
    public static Integer c_getAgiliteMax(){
        return c_agiliteMax;
    }

    public String rapporter() {
          String rapport;
          String armes = "";

          if (this.declarerArmes().isEmpty()){
        	  armes = "Aucune arme";
          }else
          {
              for (Arme a : this.declarerArmes()){
                  armes += a.getNom() + ", ";
              } 
          }


          rapport = "Rapport du " + this.getType() + " N� " + this.getIdg()
          + " : " + this.getNom()
          + " ; Ethnie : " + this.getEthnie().getNom()
          + " ; Etat : " + this.getEtat()
          + " ; Vie : " + this.getVie()
          + " ; Force : " + this.getForce()
          + " ; Agilite : " + this.agilite
          + " ; Armes : " + armes;

          return rapport;
        }

    public static ArrayList<Arme> c_getArmesDispoRet() {
        return c_armesAccessRetiaire;
    }
    
    public ArrayList<Arme> getArmesDispoRet() {
        return c_armesAccessRetiaire;
    }

    public static void c_setForce(Integer f) {
    	if (f < 0)//empeche une force negative
    	{
    		f = 0;
    	}
        c_force=f;
    }

    public void recoitCoups(Gladiateur glad, Arme arme) 
    {
        Integer val_deff = 0;
        for (Arme a : this.declarerArmes()){//accumule la valeur defensive des armes du gladiateur
            val_deff += a.getValDef();
        }
        
        Integer degats = glad.getForce() + arme.getValOff() - val_deff - agilite;//calcule la somme des degats et enleve la defense

        if (degats > 0){//empeche des degats negatifs
            this.setVie(this.getVie() - degats);//applique les degats
            if (this.getVie() < 0 ){//empeche une vie negative
            	this.setVie(0);
            }
        }
    }


    public static Integer c_getForce() {
        return c_force;
    }
    
    public Integer getForce() {
        return c_force;
    }


    public static Integer c_autoriserArmeRetiaire(Arme arme) {
        int res=-1;
        if (gArme.getArmes().contains(arme) && !c_armesAccessRetiaire.contains(arme))
        {
            c_armesAccessRetiaire.add(arme);
            res = arme.getIda();
        }
        return res;
    }
    
    public boolean armeEstAutorisee(Arme arme){
        boolean res= false;
        if (c_armesAccessRetiaire.contains(arme))
        {
           res = true;
        }
        return res;
    }
    
    public String saluer(){
        return "Ave Caesar, Retiaire N�"+ this.getIdg()+ " : " + this.getNom() + ", j'appartiens a l'ethnie des " + this.getEthnie().getNom();
    }
    
    public String getType(){
        return c_type;
    }
    
    public static String c_getType(){
        return c_type;
    }
    
    public static void c_setType(String s){
        c_type = s;
    }
}
