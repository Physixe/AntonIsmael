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

    public Mirmillon(String nom, Integer poids, Integer idg, Integer ide) {
        super(nom,idg, ide);
        this.poids = poids;
    }
    
    public static void setC_poidsMax(Integer c_poidsMax) {
        Mirmillon.c_poidsMax = c_poidsMax;
    }


    public void recoitCoups(Gladiateur glad, Arme arme) {
        frappe.add(glad);//se souvient du gladiateur qui l'a frappé
        
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
        
        rapport = "Identifiant : " + this.getIdg() + " Nom : " + this.getNom() + " Ethnie : " + this.getIde() + " Etat : " + this.getEtat() + " Vie : " + this.getVie() + 
        " Force : " + this.getForce() + " Poid : " + this.poids + " Armes : " + armes;
        return rapport;
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
