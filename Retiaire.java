package uml;

import java.util.Collection;

public class Retiaire extends Gladiateur {
    /**
     * @attribute
     */
    private static String c_type = "Retiaire";

    /**
     * @attribute
     */
    private Integer agilite;

    /**
     * @attribute
     */
    private static Integer c_force = 30;

    /**
     * @attribute
     */
    private static Integer c_agiliteMax;

    /**
     * @associates <{uml.Arme}>
     */
    Collection armesAccessRetiaire;

    public Retiaire(String nom, Integer agilite, Integer idg) {
    }

    public static void c_setAgiliteMax(Integer a) {
    }

    public String rapporter() {
    }

    public Collection getArmesDispoRet() {
    }

    public static void c_setForce(Integer f) {
    }

    public void recoitCoups(Gladiateur idg, Arme ida) {
    }

    public Integer getForce() {
    }

    public void addArmeDispoRet(Integer ida) {
    }

    public void autoriserArmeRetiaire() {
    }
}
