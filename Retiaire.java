package uml;

import java.util.Collection;

public class Retiaire extends Gladiateur {
    /**
     * @attribute
     */
    private static String c_type = "Retiaire";
    private Integer agilite;
    private static Integer c_force = 30;
    private static Integer c_agiliteMax;
    Collection<Arme> armesAccessRetiaire;

    public Retiaire(String nom, Integer agilite, Integer idg) {
      super(nom, ethnie);
      this.agilite = agilite;
    }

    public static void c_setAgiliteMax(Integer a) {
        if (a>0)
            c_agiliteMax = agilite;
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
      + "\nAgilité : " + this.getAgilite()
      + "\nArmes : " + armes;

      return rapport;
    }

    public Collection<Arme> getArmesDispoRet() {
      return armesAccessRetiaire;
    }

    public static void c_setForce(Integer f) {
      this.force = f;
    }

    public void recoitCoups(Gladiateur glad, Arme arme) {

      Integer val_deff = 0;
      for (Arme a : appartient){//accumule la valeur defensive des armes du gladiateur
          val_deff += a.getValDef();
      }

      Integer degats = glad.getForce() + arme.getValOff() - val_deff;//calcule la somme des degats et enleve la defense

      if (degats > 0){//empeche des dégats négatifs
          this.setVie(this.getVie() - degats);
      }

    }

    public Integer getForce() {
      return this.force;
    }

    public void autoriserArmeRetiaire(Arme arme) {
      this.armesAccessRetiaire.add(arme);
    }
}
