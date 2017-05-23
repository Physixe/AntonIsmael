package uml;

import java.util.Collection;

public class Mirmillon extends Gladiateur {
    /**
     * @attribute
     */
    private static String c_type = "Mirmillon";

    /**
     * @attribute
     */
    private static Integer c_poidsMax;

    /**
     * @attribute
     */
    private Integer poids;

    /**
     * @associates <{uml.Arme}>
     */
    static Collection armeAccessMirmillon;

    /**
     * @associates <{uml.Gladiateur}>
     */
    Collection frappe;

    public Mirmillon(String nom, Integer poids, Integer idg) {
    }

    public void recoitCoups(Gladiateur idg, Arme ida) {
    }

    public String rapporter() {
    }

    public Integer getForce() {
    }

    public Collection getArmesDispoMir() {
    }

    public static void c_setPoidsMax(Integer p) {
    }

    public Collection listerAgresseurs() {
    }

    public void addArmeDispoMir(Integer ida) {
    }

    public void autoriserArmeMirmillon() {
    }
}
