package packglad;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.either;



public class FacadeTest {
    public FacadeTest() {
    }
    
    @Before
    //Lance le jeu d'essais au début du test pour avoir déjà des ethnies créées
    public void setUp()
    {
        Facade.lancerJeu();
    }

    /**
     * @see Facade#lancerJeu()
     */
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
    
    @Test
    //4.a testIDG
    public void testIDG(){
        int ide = 4;
        gEthnie.creerEthnie(ide, "Strasbourgeois");
        int agilite = 200;
        
        int idg;
        for (int i = 1; i <= 10; i++)
        {
            idg= Facade.creerRetiaire("Antonismael", agilite, ide);
            assertThat("L'idg n'est pas " + i,
                               idg, is(i));
            Facade.supprimerGlad(idg);
        }
        
        Facade.supprimerEthnie(ide);
    }
    
    @Test
    //4.b testAjoutGlad
    public void testAjoutGlad(){
        int ide = 4;
        gEthnie.creerEthnie(ide, "Strasbourgeois");
        int agilite = 200;
        
        int idg;
        for (int i = 1; i <= 10; i++)
        {
            idg= Facade.creerRetiaire("Antonismael", agilite, ide);
            assertThat("L'idg n'est pas " + i,
                               idg, is(i));
            Facade.supprimerGlad(idg);
        }
        
        Facade.supprimerEthnie(ide);
    }
    
    

    /**
     * @see Facade#creerRetiaire(String,Integer,Integer)
     */
    @Test
    //1.a creerRetiaire
    public void testCreerRetiaire() {
        int ide = 4;
        gEthnie.creerEthnie(ide, "Strasbourgeois");
        int agilite = 200;
        int idg = Facade.creerRetiaire("Antonismael", agilite, 4);
        assertThat("Facade.agiliteRetiaire : Agilite ne vaut pas 200 ou agiliteMax des Retiaire",
                           Facade.agiliteRetiaire(idg), either(is(200)).or(is(Facade.getAgiliteMaxRetiaire())));
        
        Facade.supprimerGlad(idg);
        Facade.supprimerEthnie(ide);
    }

    /**
     * @see Facade#creerMirmillon(String,Integer,Integer)
     */
    @Test
    //2.a creerMirmillon
    public void testCreerMirmillon() {
        int ide = 4;
        gEthnie.creerEthnie(ide, "Strasbourgeois");
        int poids = 200;
        int idg = Facade.creerMirmillon("Antonismael", poids, 4);
        assertThat("Facade.poidsMirmillon : Poids ne vaut pas 200 ou poidsMax des Mirmillon",
                           Facade.poidsMirmillon(idg), either(is(200)).or(is(Facade.getPoidsMaxMirmillon())));
        
        Facade.supprimerGlad(idg);
        Facade.supprimerEthnie(ide);
    }

    /**
     * @see Facade#listerTousGladiateurs()
     */
    @Test
    public void testListerTousGladiateurs() {
        fail("Unimplemented");
    }

    /**
     * @see Facade#listerAgresseurs(Integer)
     */
    @Test
    public void testListerAgresseurs() {
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
    @Test
    public void testSupprimerGlad() {
        fail("Unimplemented");
    }

    /**
     * @see Facade#creerUneArme(String,Integer,Integer)
     */
    @Test
    public void testCreerUneArme() {
        fail("Unimplemented");
    }

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

    /**
     * @see Facade#donnerUneArme(Integer,Integer)
     */
    @Test
    public void testDonnerUneArme() {
        fail("Unimplemented");
    }

    /**
     * @see Facade#listerArmesDispoMirmillon()
     */
    @Test
    public void testListerArmesDispoMirmillon() {
        fail("Unimplemented");
    }

    /**
     * @see Facade#listerArmesDispoRetiaire()
     */
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
     * @see Facade#listerGladiateursDEthnie(Integer)
     */
    @Test
    //3.a listerGladiateursDEthnie
    public void testListerGladiateursDEthnie() {
        int ide = 4;
        gEthnie.creerEthnie(ide, "Strasbourgeois");
        int poids = 200;
        int idg = Facade.creerMirmillon("Antonismael", poids, 4);
        
        assertThat("Facade.listerGladiateursDEthnie(ethnie).contains(idg) : Le gladiateur ne fait pas partie de l'Ethnie",
        Facade.listerGladiateursDEthnie(ide).contains(idg), is(true));
        
        Facade.supprimerGlad(idg);
        Facade.supprimerEthnie(ide);
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
     * @see Facade#frapper(Integer,Integer,Integer)
     */
    @Test
    public void testFrapper() {
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
