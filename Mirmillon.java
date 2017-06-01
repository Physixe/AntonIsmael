package packglad;

import java.util.ArrayList;

public class Mirmillon extends Gladiateur {
 
    private static String c_type = "Mirmillon";
    private static Integer c_poidsMax = 100;
    private Integer poids;
    private static ArrayList<Arme> c_armesAccessMirmillon=new ArrayList<Arme>();
    ArrayList<Gladiateur> agresseur=new ArrayList<Gladiateur>();

    public Mirmillon(Integer idg, String nom, Ethnie ethnie, Integer poids) {
        super(idg, nom, ethnie);
        if (poids > Mirmillon.c_poidsMax)
         {
         poids = Mirmillon.c_poidsMax;
         }
        this.poids = poids;     
    }

    public static void setC_poidsMax(Integer c_poidsMax) {
        Mirmillon.c_poidsMax = c_poidsMax;
    }


    public void recoitCoups(Gladiateur glad, Arme arme) {
        agresseur.add(glad);//se souvient du gladiateur qui l'a frappÔøΩ

        Integer val_deff = 0;
        for (Arme a : this.declarerArmes()){//accumule la valeur defensive des armes du gladiateur
            val_deff += a.getValDef();
        }
        
        //System.out.println(glad);
        //System.out.println(arme);
        
        Integer degats = glad.getForce() + arme.getValOff() - val_deff;//calcule la somme des degats et enleve la defense

        if (degats > 0){//empeche des d√©gats n√©gatifs
            this.setVie(this.getVie() - degats);
        }

    }

    public String rapporter() {
      String rapport;
      String armes = "";

      for (Arme a : this.declarerArmes()){
          armes += a.getNom() + ", ";
      }

      rapport = "Rapport du " + this.getType() + " N∞ " + this.getIdg()
      + " : " + this.getNom()
      + " ; Ethnie : " + this.getEthnie().getNom()
      + " ; Etat : " + this.getEtat()
      + " ; Vie : " + this.getVie()
      + " ; Force : " + this.getForce()
      + " ; Poids : " + this.poids
      + " ; Armes : " + armes;

      return rapport;
    }

    public Integer getForce() {
      return (poids/2);
    }

    public static ArrayList<Arme> c_getArmesDispoMir() {
      return c_armesAccessMirmillon;
    }

    public static void c_setPoidsMax(Integer p) {
        c_poidsMax = p;
    }

    public ArrayList<Gladiateur> listerAgresseurs() {
      return agresseur;
    }

    public static Integer c_autoriserArmeMirmillon(Arme arme) {
        int res=-1;
        if (gArme.getArmes().contains(arme) && !c_armesAccessMirmillon.contains(arme)){
              c_armesAccessMirmillon.add(arme);
              res = arme.getIda();
            }
        
        return res;
    }
    
    public String saluer(){
        return "Ave Caesar, Mirmillon N∞"+ this.getIdg()+ " : " + this.getNom() + ", j'appartiens a l'ethnie des " + this.getEthnie().getNom();
    }
    
    public String getType(){
        return c_type;
    }
}