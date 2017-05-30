package packglad;

import java.util.ArrayList;
import java.util.Collection;

public class Mirmillon extends Gladiateur {
    /**
     * @attribute
     */
    private static String c_type = "Mirmillon";
    private static Integer c_poidsMax;
    private Integer poids;
    static ArrayList<Arme> armeAccessMirmillon;
    ArrayList<Gladiateur> agresseur;

    public Mirmillon(String nom, Ethnie ethnie, Integer poids) {
        super(nom, ethnie);
        this.poids = poids;
    }

    public static void setC_poidsMax(Integer c_poidsMax) {
        Mirmillon.c_poidsMax = c_poidsMax;
    }


    public void recoitCoups(Gladiateur glad, Arme arme) {
        agresseur.add(glad);//se souvient du gladiateur qui l'a frappé

        Integer val_deff = 0;
        for (Arme a : this.declarerArmes()){//accumule la valeur defensive des armes du gladiateur
            val_deff += a.getValDef();
        }

        Integer degats = glad.getForce() + arme.getValOff() - val_deff;//calcule la somme des degats et enleve la defense

        if (degats > 0){//empeche des dégats négatifs
            this.setVie(this.getVie() - degats);
        }

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
      + "\nPoids : " + this.poids
      + "\nArmes : " + armes;

      return rapport;
    }

    public Integer getForce() {
      return (poids/2);
    }

    public ArrayList<Arme> getArmesDispoMir() {
      return armeAccessMirmillon;
    }

    public static void c_setPoidsMax(Integer p) {
        c_poidsMax = p;
    }

    public ArrayList<Gladiateur> listerAgresseurs() {
      return agresseur;
    }

    public static Integer c_autoriserArmeMirmillon(Arme arme) {
        int res=-1;
        if (gArme.getArmes().contains(arme) && !armeAccessMirmillon.contains(arme))
        {
            armeAccessMirmillon.add(arme);
            res = arme.getIda();
        }
        return res;
    }
    
    public String saluer(){
        return "Ave Caesar, Mirmillon N°"+ this.getIdg()+ " : " + this.getNom() + ", j'appartiens à l'ethnie des " + this.getEthnie().getNom();
    }
    
    public String getType(){
        return "Retiaire";
    }
}
