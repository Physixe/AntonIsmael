package packglad;

public class Arme {
	
    private String nom;
    private Integer puissanceOff;
    private Integer puissanceDef;
    private Integer ida;
    

    public Arme(String nom, Integer puissanceOff, Integer puissanceDef, Integer ida) {
        this.nom=nom;
        this.puissanceOff= puissanceOff;
        this.puissanceDef=puissanceDef;
        this.ida=ida;
    }

    public String getNom() {
        return this.nom;
    }

    public Integer getValOff() {
        return this.puissanceOff;
    }

    public Integer getValDef() {
        return this.puissanceDef;
    }

    public Integer getIda() {
        return this.ida;
    }
}
