package packglad;

public class Arme {
	
    private String nom;
    private Integer puissanceOff;
    private Integer puissanceDef;
    private Integer ida;
    
    //Constructeur
    public Arme(String nom, Integer puissanceOff, Integer puissanceDef, Integer ida) {
        this.nom=nom;
        if (puissanceOff < 0)//empeche d'avoir une puissanceOff negative
        {
        	puissanceOff = 0;
        }
        if (puissanceDef < 0)//empeche d'avoir une puissanceDef negative
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
    
    //renvoie une description de l'arme (Ida, nom, valOff, valDeff, dispoMir, disporet
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
