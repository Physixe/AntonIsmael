package uml;

import java.util.Collection;

public abstract class Gladiateur {
    /**
     * @attribute
     */
    private String nom;
    private Integer vie;
    private static Integer c_vieInitiale = 200;
    private Integer idg;
    Collection<Arme> appartient;

    public Gladiateur(String nom, Integer idg) {
        this.nom = nom;
        this.idg = idg;
        this.vie = c_vieInitiale;
    }

    public String getNom() {
        return nom;
    }

    public Integer getVie() {
        return vie;
    }

    public abstract String rapporter();

    public abstract String saluer();

    public void addArme(Integer ida) {
        appartient.add(ida);
    }

    public void frapper(Gladiateur victime, Arme a) {
        victime.recoitCoups(this, a);
    }

    public abstract void recoitCoups(Gladiateur aggresseur, Arme a);

    public static void c_setVieInitiale(Integer v) {
        uml.Gladiateur.c_vieInitiale = v;
    }

    public void perdreArme(Integer ida) {
        Integer i = 0;
        boolean continu = true;

        while(i < appartient.size() && continu){
            if (appartient.toArray()[i] == ida){
                appartient.remove(appartient.toArray()[i]);
                continu = false;
            }
            i++;
        }
    }

    public abstract Integer getForce();

    public Collection declarerArmes() {
        return appartient;
    }
}
