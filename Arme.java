package packglad;

public class Arme {
	
    private String nom;
    private Integer puissanceOff;
    private Integer puissanceDef;
    private Integer ida;
    

    public Arme(String nom, Integer puissanceOff, Integer puissanceDef, Integer ida) {
        this.nom=nom;
        if (puissanceOff < 0)
        {
        	puissanceOff = 0;
        }
        if (puissanceDef < 0)
        {
        	puissanceDef = 0;
        }
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
    
    public String decrire(){
    	String desc = "";
    	String dispoMir = "Non";
        String dispoRet = "Non";
        if(Mirmillon.c_getArmesDispoMir().contains(this)){
            dispoMir = "Oui";
        }
        if(Retiaire.c_getArmesDispoRet().contains(this)){
            dispoRet = "Oui";
        }
        desc += "Arme "      + ida     + " " 
             +  nom    + " ; " 
             +  "valOff : "   + puissanceOff + " ; "
             +  "ValDef : "   + puissanceDef + " ; "
             +  "dispoMir : " + dispoMir      + " ; "
             +  "dispoRet : " + dispoRet;
        
        return desc;
    }
}
