package uml;

import java.util.Collection;

public class Mirmillon extends Gladiateur {
    /**
     * @attribute
     */
    private static String c_type = "Mirmillon";
    private static Integer c_poidsMax;
    private Integer poids;
    static Collection<Arme> armeAccessMirmillon;
    Collection<Gladiateur> agresseur;

    public Mirmillon(String nom, Integer poids, Integer idg, Integer ide) {
        super(nom,idg, ide);
        this.poids = poids;
    }

    public static void setC_poidsMax(Integer c_poidsMax) {
        Mirmillon.c_poidsMax = c_poidsMax;
    }


    public void recoitCoups(Gladiateur glad, Arme arme) {
        agresseur.add(glad);//se souvient du gladiateur qui l'a frappé

        Integer val_deff = 0;
        for (Arme a : appartient){//accumule la valeur defensive des armes du gladiateur
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

      for (Arme a : appartient){
          armes += a.getNom() + ", ";
      }

      rapport = "Identifiant : " + this.getIdg()
      + "\nNom : " + this.getNom()
      + "\nEthnie : " + getEthnie(this.getIde()).getNom();
      + "\nEtat : " + this.getEtat()
      + "\nVie : " + this.getVie()
      + "\nForce : " + this.getForce()
      + "\nPoids : " + this.poids()
      + "\nArmes : " + armes;

      return rapport;
    }

    public Integer getForce() {
      return (poids/2);
    }

    public Collection<Arme> getArmesDispoMir() {
      return armeAccessMirmillon;
    }

    public static void c_setPoidsMax(Integer p) {
      this.c_poidsMax = p;
    }

    public Collection<Gladiateur> listerAgresseurs() {
      return agresseur;
    }

    public void autoriserArmeMirmillon(Arme arme) {
      if (gArme.getArmes().contains(arme) && !armeAccessMirmillon.contains(arme)){
        armeAccessMirmillon.add(arme);
      }
    }
}
