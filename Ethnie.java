package packglad;

import java.util.ArrayList;


public class Ethnie {
    private Integer ide;
    private String nom;
    private Integer score;

    private static ArrayList<Gladiateur> gladEthnie = new ArrayList<Gladiateur>();


    public  Ethnie(Integer ide, String nom) {
        this.ide=ide;
        this.nom=nom;
        this.score=0;
    }

    public Integer calculerScore() {
        return this.score;
    }

    public Integer getIde() {
        return this.ide;
    }

    public String getNom() {
        return this.nom;
    }

    public ArrayList<Gladiateur> listerGladiateurs() {
        return Ethnie.gladEthnie;
    }
}
