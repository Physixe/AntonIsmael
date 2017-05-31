package packglad;

import java.util.ArrayList;

public class Retiaire extends Gladiateur {
    
    private static String c_type = "Retiaire";
    private Integer agilite;
    private static Integer c_force = 30;
    private static Integer c_agiliteMax = 50;
    private static ArrayList<Arme> c_armesAccessRetiaire = new ArrayList<Arme>();

    public Retiaire(String nom, Integer agilite, Ethnie ethnie) {
        super(nom,ethnie);
        if (agilite > Retiaire.c_agiliteMax){
        	agilite = Retiaire.c_agiliteMax;
        }
        this.agilite=agilite;
    }
    
    public  Integer getAgilite(){
        return agilite;
    }

    public static void c_setAgiliteMax(Integer a) {
        if (a>0)
            c_agiliteMax=a;
    }

    public String rapporter() {
          String rapport;
          String armes = "";

          for (Arme a : this.declarerArmes()){
              armes += a.getNom() + ", ";
          }

          rapport = "Identifiant : " + this.getIdg()
          + "\nNom : " + this.getNom()
          + "\nEthnie : " + this.getEthnie().getNom()
          + "\nEtat : " + this.getEtat()
          + "\nVie : " + this.getVie()
          + "\nForce : " + this.getForce()
          + "\nAgilitÈ : " + this.getAgilite()
          + "\nArmes : " + armes;

          return rapport;
        }

    public static ArrayList<Arme> c_getArmesDispoRet() {
        return c_armesAccessRetiaire;
    }

    public static void c_setForce(Integer f) {
        c_force=f;
    }

    public void recoitCoups(Gladiateur glad, Arme arme) 
    {
        Integer val_deff = 0;
        for (Arme a : this.declarerArmes()){//accumule la valeur defensive des armes du gladiateur
            val_deff += a.getValDef();
        }
        
        Integer degats = glad.getForce() + arme.getValOff() - val_deff;//calcule la somme des degats et enleve la defense

        if (degats > 0){//empeche des d√©gats n√©gatifs
            this.setVie(this.getVie() - degats);
        }

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
    
    public String saluer(){
        return "Ave Caesar, RÈtiaire N∞"+ this.getIdg()+ " : " + this.getNom() + ", j'appartiens ‡† l'ethnie des " + this.getEthnie().getNom();
    }
    
    public String getType(){
        return c_type;
    }
}
