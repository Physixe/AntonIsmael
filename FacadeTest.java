package packglad;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.either;



public class FacadeTest {


    @Before
    //Lance le jeu au avant chaque test pour vider toutes les listes
    public void setUp() {
        Facade.lancerJeu();
    }

    @Test
    //1.a creerRetiaire
    public void testCreerRetiaire() {
        int ide = 4;
        gEthnie.creerEthnie(ide, "Strasbourgeois");
        int agilite = 40;
        int idg = Facade.creerRetiaire("Antonismael", agilite, 4);
        assertThat("Facade.agiliteRetiaire : Agilite ne vaut pas 40 ou agiliteMax des Retiaire",
                   Facade.agiliteRetiaire(idg), is(40));
        
        
    }

    
    @Test
    //2.a creerMirmillon
    public void testCreerMirmillon() {
        int ide = 4;
        gEthnie.creerEthnie(ide, "Strasbourgeois");
        int poids = 40;
        int idg = Facade.creerMirmillon("Antonismael", poids, 4);
        assertThat("Facade.poidsMirmillon : Poids ne vaut pas 40 ou poidsMax des Mirmillon",
                   Facade.poidsMirmillon(idg), either(is(40)).or(is(Facade.getPoidsMaxMirmillon())));

    }
    
    @Test
    //3.a listerGladiateursDEthnie
    public void testListerGladiateursDEthnie() {
        int ide = 4;
        gEthnie.creerEthnie(ide, "Strasbourgeois");
        int poids = 40;
        int idg = Facade.creerMirmillon("Antonismael", poids, 4);

        assertThat("Facade.listerGladiateursDEthnie(ethnie).contains(idg) : Le gladiateur ne fait pas partie de l'Ethnie",
                   Facade.listerGladiateursDEthnie(ide).contains(idg), is(true));

        Facade.supprimerGlad(idg);
        Facade.supprimerEthnie(ide);
    }
    
    @Test
    //4.a testIDG
    public void testIDG() {
        int ide = 4;
        gEthnie.creerEthnie(ide, "Strasbourgeois");
        int agilite = 40;

        int idg;
        for (int i = 1; i <= 10; i++) {
            idg = Facade.creerRetiaire("Antonismael", agilite, ide);
            assertThat("L'idg n'est pas " + i, idg, is(i));
        }

    }

    @Test
    //4.b testAjoutGlad
    public void testAjoutGlad() {
        int ide = 4;
        gEthnie.creerEthnie(ide, "Strasbourgeois");
        int agilite = 40;

        int idg;
        for (int i = 1; i <= 10; i++) {
            idg = Facade.creerRetiaire("Antonismael", agilite, ide);
            assertThat("L'idg n'est pas " + i, idg, is(i));
        }

    }
    
    @Test
    //5.a listerTousGalidateursInitial
    public void testListerTousGladiateursInitial() {
        assertThat("La liste des Gladiateurs n'est pas initialement vide", Facade.listerTousGladiateurs().isEmpty(), is(true));
    }
    
    
    @Test
    //5.b listerTousGalidateurs1
    public void testListerTousGladiateurs1() {
        gEthnie.creerEthnie(1, "Strasbourgeois");
        int idg = Facade.creerRetiaire("Antonismael", 30, 1);
        assertThat("La liste des Gladiateurs ne contient pas " + idg, Facade.listerTousGladiateurs().contains(idg), is(true));
    }
    
    @Test
    //5.c listerTousGalidateurs2
    public void testListerTousGladiateurs2() {
        gEthnie.creerEthnie(1, "Strasbourgeois");
        int idg = Facade.creerRetiaire("Antonismael", 30, 1);
        int idg2 = Facade.creerMirmillon("Antonismael", 30, 1);
        assertThat("La liste des Gladiateurs ne contient pas " + idg , Facade.listerTousGladiateurs().contains(idg), is(true));
        assertThat("La liste des Gladiateurs ne contient pas " + idg2, Facade.listerTousGladiateurs().contains(idg2), is(true));
    }
    
    //6.a
    @Test(expected = IllegalArgumentException.class)
    public void testCreerUneArme1() {
        int ida1 = Facade.creerUneArme(null, 0, 10);
        int ida2 = Facade.creerUneArme("", -10, 10);
    }


    //6.b
    @Test(expected = IllegalArgumentException.class)
    public void testCreerUneArme2() {
        int ida1 = Facade.creerUneArme("Kaaris", 10, 20);
        int ida2 = Facade.creerUneArme("Kaaris", 3, 67);
    }
    
    //7.a
    @Test
    public void testListerArmesDispoMirmillon1() {
        assertTrue("Facade.listerArmesDispoMirmillon : liste des armes diponbibles aux mirmillons non vide initialement",
                   Facade.listerArmesDispoMirmillon().isEmpty());
        // 7.b
        Integer ida = Facade.creerUneArme("KB9", 9, 10);
        Facade.autoriserArmeAuxMirmillons(ida);

        assertThat("Facade.listerArmesDispoMirmillon().size : n'est pas de taille 1",
                   Facade.listerArmesDispoMirmillon().size(), is(1));


        assertThat("Facade.listerArmesDispoMirmillon().contains(a) : la liste ne contient pas l'arme ",
                   Facade.listerArmesDispoMirmillon().contains(ida), is(true));

    }

    //7.b
    @Test
    public void testListerArmesDispoMirmillon2() {
        Integer ida = Facade.creerUneArme("M10", 20, 15);
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.autoriserArmeAuxRetiaires(ida);

        assertThat("Facade.listerArmesDispoMirmillon().size : n'est pas de taille 1",
                   Facade.listerArmesDispoMirmillon().size(), is(1));

        assertThat("Facade.listerArmesDispoRetiaire().size : n'est pas de taille 1",
                   Facade.listerArmesDispoRetiaire().size(), is(1));

        assertThat("Facade.listerArmesDispoMirmillon().contains(a) : la liste ne contient pas l'arme ",
                   Facade.listerArmesDispoMirmillon().contains(ida), is(true));

        assertThat("Facade.listerArmesDispoRetiaire().contains(a) : la liste ne contient pas l'arme ",
                   Facade.listerArmesDispoRetiaire().contains(ida), is(true));
    }
    
    //8.a
    @Test(expected = NoSuchElementException.class)
    public void testDonnerUneArme() {
        Facade.donnerUneArme(10, 10);

        // 8.b
        Ethnie ide = new Ethnie(1, "Neuhof");
        Integer idm = Facade.creerMirmillon("Lol", 30, 1);
        Integer ida = Facade.creerUneArme("Lance", 30, 1);
        Facade.donnerUneArme(idm, ida);

        assertTrue("gGladiateur.getGladiateur(idm).declarerArmes : liste des armes autorisées non vide",
                   gGladiateur.getGladiateur(idm).declarerArmes().isEmpty());
        
        //8.c
        
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.donnerUneArme(idm, ida);
        
        assertThat("gGladiateur.getGladiateur(idm).declarerArmes : n'est pas de taille 1",
                   gGladiateur.getGladiateur(idm).declarerArmes().size(), is(1));
    
        //8.d
        Facade.donnerUneArme(idm, ida);
        assertThat("gGladiateur.getGladiateur(idm).declarerArmes : n'est pas de taille 1",
                   gGladiateur.getGladiateur(idm).declarerArmes().size(), is(1));
        
        //8.e
        Integer ida2 = Facade.creerUneArme("Ok", 13, 13);
        Facade.autoriserArmeAuxMirmillons(ida2);
        Facade.donnerUneArme(idm, ida2);
        assertThat("gGladiateur.getGladiateur(idm).declarerArmes : n'est pas de taille 2",
                   gGladiateur.getGladiateur(idm).declarerArmes().size(), is(2));
    }
    
    @Test
    //10.a testFrapperA
    public void testFrapperA() {
        gEthnie.creerEthnie(1, "Strasbourgeois");
        int ida =  Facade.creerUneArme("GlaiveTest", 80, 0);
        int idg1 = Facade.creerMirmillon("Nathan", 50, 1);
        int idg2 = Facade.creerMirmillon("Ismo", 50, 1);
        
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.donnerUneArme(ida, idg1);
        
        int vieInit = Facade.vieGladiateur(idg2);
        
        Facade.frapper(idg1, idg2, ida); 


        assertThat("La vie du Mirmillon " + idg2 + " n'est pas diminuée de la somme entre la force du coup de l'agresseur et la puissance offensive de l'arme choisie", Facade.vieGladiateur(idg2), is(vieInit - (Facade.forceGladiateur(idg1) + gArme.getArme(ida).getValOff())));

    }
    
    
    @Test
    //10.b testFrapperB
    public void testFrapperB() {
        gEthnie.creerEthnie(1, "Strasbourgeois");
        int ida =  Facade.creerUneArme("GlaiveTest", 80, 0);
        int idg1 = Facade.creerMirmillon("Nathan", 50, 1);
        int idg2 = Facade.creerRetiaire("Ismo", 50, 1);
        
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.donnerUneArme(ida, idg1);
        
        int vieInit = Facade.vieGladiateur(idg2);
        
        Facade.frapper(idg1, idg2, ida); 
        
        int vie = (vieInit + ((Retiaire)gGladiateur.getGladiateur(idg2)).getAgilite()) - (Facade.forceGladiateur(idg1) + gArme.getArme(ida).getValOff());
        

        assertThat("La vie du Retiaire " + idg2 + " n'as pas pris en compte son agilité", Facade.vieGladiateur(idg2), is(vie));
    }
    
    @Test
    //10.c testFrapperC
    public void testFrapperC() {
        gEthnie.creerEthnie(1, "Strasbourgeois");
        int ida =  Facade.creerUneArme("GlaiveTest", 80, 50);
        int idg1 = Facade.creerMirmillon("Nathan", 50, 1);
        int idg2 = Facade.creerMirmillon("Ismo", 50, 1);
        
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.donnerUneArme(ida, idg1);
        Facade.donnerUneArme(ida, idg2);
        
        int vieInit = Facade.vieGladiateur(idg2);
        
        Facade.frapper(idg1, idg2, ida); 
        
        Gladiateur g = gGladiateur.getGladiateur(idg2);
        
        Integer val_deff = 0;
        for (Arme a : g.declarerArmes()){//accumule la valeur defensive des armes du gladiateur
            val_deff += a.getValDef();
        }
            
        int vie = (vieInit + val_deff) - (Facade.forceGladiateur(idg1) + gArme.getArme(ida).getValOff());
        


        assertThat("La vie du Mirmillon " + idg2 + " ne prends pas en compte la valeur defensive de ses armes", Facade.vieGladiateur(idg2), is(vie));

    }
    
    /**
     * @see Facade#frapper(Integer,Integer,Integer)
     */
    @Test
    //10.d testFrapperD
    public void testFrapperD() {
        gEthnie.creerEthnie(1, "Strasbourgeois");
        int ida =  Facade.creerUneArme("GlaiveTest", 80, 50);
        int idg1 = Facade.creerMirmillon("Nathan", 50, 1);
        int idg2 = Facade.creerRetiaire("Ismo", 50, 1);
        
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.autoriserArmeAuxRetiaires(ida);
        Facade.donnerUneArme(ida, idg1);
        Facade.donnerUneArme(ida, idg2);
        
        int vieInit = Facade.vieGladiateur(idg2);
        
        Facade.frapper(idg1, idg2, ida); 
        
        Gladiateur g = gGladiateur.getGladiateur(idg2);
        
        Integer val_deff = 0;
        for (Arme a : g.declarerArmes()){//accumule la valeur defensive des armes du gladiateur
            val_deff += a.getValDef();
        }
            
        int vie = (vieInit + val_deff + ((Retiaire)g).getAgilite()) - (Facade.forceGladiateur(idg1) + gArme.getArme(ida).getValOff());

        assertThat("La vie du Retiaire " + idg2 + " ne prends pas en compte la valeur defensive de ses armes et/ou son agilite", Facade.vieGladiateur(idg2), is(vie));

    }
    
    /**
     * @see Facade#frapper(Integer,Integer,Integer)
     */
    @Test
    //10.e testFrapperE
    public void testFrapperE() {
        gEthnie.creerEthnie(1, "Strasbourgeois");
        int ida =  Facade.creerUneArme("GlaiveTest", 10, 50);
        int idg1 = Facade.creerMirmillon("Nathan", 10, 1);
        int idg2 = Facade.creerRetiaire("Ismo", 50, 1);
        
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.donnerUneArme(ida, idg1);
        
        int vieInit = Facade.vieGladiateur(idg2);
        
        Facade.frapper(idg1, idg2, ida); 
        
        Gladiateur g = gGladiateur.getGladiateur(idg2);       
            
        int vie = (vieInit + ((Retiaire)g).getAgilite()) - (Facade.forceGladiateur(idg1) + gArme.getArme(ida).getValOff());


        //assertThat("La vie du Retiaire " + idg2 + " ne prends pas en compte la valeur defensive de ses armes et/ou son agilite", Facade.vieGladiateur(idg2), not(greaterThan(vie)));

    }
    
    
    
    @Test(expected = IllegalArgumentException.class)
    //11.a listerAgresseurs
    public void testListerAgresseurs() {
        gEthnie.creerEthnie(1, "Strasbourgeois");
        int idg = Facade.creerRetiaire("Antonismael", 30, 1);
        
        Facade.listerAgresseurs(idg);
    }
    
    
    @Test
    //11.b listerAgresseursMirmillon
    public void testListerAgresseursMirmillon() {
        gEthnie.creerEthnie(1, "Strasbourgeois");
        int ida =  Facade.creerUneArme("GlaiveTest", 80, 0);
        int idg1 = Facade.creerMirmillon("Nathan", 50, 1);
        int idg2 = Facade.creerMirmillon("Ismo", 50, 1);
        
        for (Arme a : gArme.getArmes()){
            System.out.println(a.getNom());
        }
        
        for (Gladiateur g : gGladiateur.listerGladiateurs()){
            System.out.println(g.getNom());
        }
        
        System.out.println(ida);
        
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.donnerUneArme(ida, idg1);
        
        Facade.frapper(idg1, idg2, ida); 
        
        
        for( int i : Facade.listerAgresseurs(idg2)) {
            System.out.println(i);
        }

        assertThat("La liste des agresseurs de Mirmillon " + idg2 + " ne contient pas " + idg1, Facade.listerAgresseurs(idg2).contains(idg1), is(true));

        assertThat("La liste des agresseurs de Mirmillon " + idg1 + " n'est pas vide", Facade.listerAgresseurs(idg1).isEmpty(), is(true));
    }
    
    /**
     * @see Facade#listerAgresseurs(Integer)
     */
    @Test
    //11.b listerAgresseursPlusieursCoups
    public void testListerAgresseursPlusieursCoups() {
        gEthnie.creerEthnie(1, "Strasbourgeois");
        int ida =  Facade.creerUneArme("GlaiveTest", 80, 0);
        int idg1 = Facade.creerMirmillon("Nathan", 50, 1);
        int idg2 = Facade.creerMirmillon("Ismo", 50, 1);
        
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.donnerUneArme(ida, idg1);
        
        Facade.frapper(idg1, idg2, ida); 
        Facade.frapper(idg1, idg2, ida); 
        
        int x =0;
        
        for(int i : Facade.listerAgresseurs(idg2)){
            if (i==idg1) {
                x++;
            }
        }

        assertThat("La liste des agresseurs de Mirmillon " + idg2 + " contient plusieurs fois le gladiateurs " + idg1, x, is(1));

    }
    
    /**
     * @see Facade#listerAgresseurs(Integer)
     */
    @Test
    //11.c listerAgresseursLuiMeme
    public void testListerAgresseursLuiMeme() {
        gEthnie.creerEthnie(1, "Strasbourgeois");
        int ida =  Facade.creerUneArme("GlaiveTest", 80, 0);
        int idg1 = Facade.creerMirmillon("Nathan", 50, 1);
        
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.donnerUneArme(ida, idg1);
        
        Facade.frapper(idg1, idg1, ida);

        assertThat("La liste des agresseurs de Mirmillon " + idg1 + " ne contient pas le gladiateur " + idg1, Facade.listerAgresseurs(idg1).contains(idg1), is(true));

    }
    
    @Test
    //12.a supprimerGlad
    public void testSupprimerGlad() {
        gEthnie.creerEthnie(1, "Strasbourgeois");
        int idg1 = Facade.creerRetiaire("Nathan", 50, 1);
        int idg2 = Facade.creerMirmillon("Ismo", 50, 1);
        
        Facade.supprimerGlad(idg1);
        
        assertThat("La liste contient plus d'un Gladiateur", Facade.listerTousGladiateurs().size(), is(1));
        assertThat("La liste ne contient pas le second Gladiateur", Facade.listerTousGladiateurs().contains(idg2), is(true));
        
        Facade.supprimerGlad(idg2);
        
        assertThat("La liste n'est pas vide", Facade.listerTousGladiateurs().isEmpty(), is(true));
        
    }
    
    /**
     * @see Facade#supprimerGlad(Integer)
     */
    @Test
    //12.b supprimerGladMirmillon
    public void testSupprimerGladMirmillon() {
        gEthnie.creerEthnie(1, "Strasbourgeois");
        int ida =  Facade.creerUneArme("Glaive", 80, 0);
        int idg1 = Facade.creerMirmillon("Nathan", 50, 1);
        int idg2 = Facade.creerMirmillon("Ismo", 50, 1);
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.donnerUneArme(ida, idg1);
        
        Facade.frapper(idg1, idg2, ida);      
        Facade.supprimerGlad(idg1);
        
        assertThat("La liste des agresseurs de Mirmillon " + idg2 + " n'est pas vide", Facade.listerAgresseurs(idg2).isEmpty(), is(true));
        
    }

    @Test
    public void testLancerJeu() {
        fail("Unimplemented");
    }

    /**
     * @see Facade#lancerJeuDEssai()
     */
    @Test
    public void testLancerJeuDEssai() {
        fail("Unimplemented");
    }

    /**
     * @see Facade#parametrage(Integer,Integer,Integer,Integer)
     */
    @Test
    public void testParametrage() {
        fail("Unimplemented");
    }
    
    /**
     * @see Facade#faireSaluerGladiateur(Integer)
     */
    @Test
    public void testFaireSaluerGladiateur() {
        fail("Unimplemented");
    }

    /**
     * @see Facade#faireRapport(Integer)
     */
    @Test
    public void testFaireRapport() {
        fail("Unimplemented");
    }

    /**
     * @see Facade#declarerArmes(Integer)
     */
    @Test
    public void testDeclarerArmes() {
        fail("Unimplemented");
    }

    /**
     * @see Facade#supprimerGlad(Integer)
     */
    


    

    /**
     * @see Facade#autoriserArmeAuxMirmillons(Integer)
     */
    @Test
    public void testAutoriserArmeAuxMirmillons() {
        fail("Unimplemented");
    }

    /**
     * @see Facade#autoriserArmeAuxRetiaires(Integer)
     */
    @Test
    public void testAutoriserArmeAuxRetiaires() {
        fail("Unimplemented");
    }

    

    


    @Test
    public void testListerArmesDispoRetiaire() {
        fail("Unimplemented");
    }

    /**
     * @see Facade#decrireArme(Integer)
     */
    @Test
    public void testDecrireArme() {
        fail("Unimplemented");
    }

    /**
     * @see Facade#nomDeLArme(Integer)
     */
    @Test
    public void testNomDeLArme() {
        fail("Unimplemented");
    }

    /**
     * @see Facade#listerEthnies()
     */
    @Test
    public void testListerEthnies() {
        fail("Unimplemented");
    }

    

    /**
     * @see Facade#decrireEthnie(Integer)
     */
    @Test
    public void testDecrireEthnie() {
        fail("Unimplemented");
    }

    /**
     * @see Facade#getScore(Integer)
     */
    @Test
    public void testGetScore() {
        fail("Unimplemented");
    }

    
    

    /**
     * @see Facade#desarmer(Integer,Integer)
     */
    @Test
    public void testDesarmer() {
        fail("Unimplemented");
    }

    /**
     * @see Facade#vainqueurs()
     */
    @Test
    public void testVainqueurs() {
        fail("Unimplemented");
    }

    /**
     * @see Facade#nomDuGladiateur(Integer)
     */
    @Test
    public void testNomDuGladiateur() {
        fail("Unimplemented");
    }

    /**
     * @see Facade#nomDeLEthnie(Integer)
     */
    @Test
    public void testNomDeLEthnie() {
        fail("Unimplemented");
    }
}
