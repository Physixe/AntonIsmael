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
        Facade.creerEthnie(ide, "Strasbourgeois");
        int agilite = 40;
        int idg = Facade.creerRetiaire("Antonismael", agilite, 4);
        assertThat("Facade.agiliteRetiaire : Agilite ne vaut pas 40 ou agiliteMax des Retiaire",
                   Facade.agiliteRetiaire(idg), is(40));
        
        
    }

    
    @Test
    //2.a creerMirmillon
    public void testCreerMirmillon() {
        int ide = 4;
        Facade.creerEthnie(ide, "Strasbourgeois");
        int poids = 40;
        int idg = Facade.creerMirmillon("Antonismael", poids, 4);
        assertThat("Facade.poidsMirmillon : Poids ne vaut pas 40 ou poidsMax des Mirmillon",
                   Facade.poidsMirmillon(idg), either(is(40)).or(is(Facade.getPoidsMaxMirmillon())));

    }
    
    @Test
    //3.a listerGladiateursDEthnie
    public void testListerGladiateursDEthnie() {
        int ide = 4;
        Facade.creerEthnie(ide, "Strasbourgeois");
        int poids = 40;
        int idg = Facade.creerMirmillon("Antonismael", poids, 4);

        assertThat("Facade.listerGladiateursDEthnie(ethnie).contains(idg) : Le gladiateur ne fait pas partie de l'Ethnie",
                   Facade.listerGladiateursDEthnie(ide).contains(idg), is(true));

        Facade.supprimerGlad(idg);
        Facade.supprimerEthnie(ide);
    }
    
    @Test
    //4.a testIdA
    public void testIdA() {
        int ide = 4;
        Facade.creerEthnie(ide, "Strasbourgeois");
        int agilite = 40;

        int idg;
        for (int i = 1; i <= 10; i++) {
            idg = Facade.creerRetiaire("Antonismael", agilite, ide);
            assertThat("L'idg n'est pas " + i, idg, is(i));
        }

    }

    @Test
    //4.b testIdB
    public void testIdB() {
        int ide = 4;
        Facade.creerEthnie(ide, "Strasbourgeois");
        int agilite = 40;

        int idg;
        for (int i = 1; i <= 10; i++) {
            idg = Facade.creerRetiaire("Antonismael", agilite, ide);
            assertThat("L'idg n'est pas " + i, idg, is(i));
        }

    }
    
    @Test
    //5.a listerTousGalidateursA
    public void testListerTousGladiateursA() {
        assertThat("La liste des Gladiateurs n'est pas initialement vide", Facade.listerTousGladiateurs().isEmpty(), is(true));
    }
    
    
    @Test
    //5.b listerTousGalidateursB
    public void testListerTousGladiateursB() {
        Facade.creerEthnie(1, "Strasbourgeois");
        int idg = Facade.creerRetiaire("Antonismael", 30, 1);
        assertThat("La liste des Gladiateurs ne contient pas " + idg, Facade.listerTousGladiateurs().contains(idg), is(true));
    }
    
    @Test
    //5.c listerTousGalidateursC
    public void testListerTousGladiateursC() {
        Facade.creerEthnie(1, "Strasbourgeois");
        int idg = Facade.creerRetiaire("Antonismael", 30, 1);
        int idg2 = Facade.creerMirmillon("Antonismael", 30, 1);
        assertThat("La liste des Gladiateurs ne contient pas " + idg , Facade.listerTousGladiateurs().contains(idg), is(true));
        assertThat("La liste des Gladiateurs ne contient pas " + idg2, Facade.listerTousGladiateurs().contains(idg2), is(true));
    }
    
    //6.a testCreerUneArmeA
    @Test(expected = IllegalArgumentException.class)
    public void testCreerUneArmeA() {
        Facade.creerUneArme(null, 0, 10);
        Facade.creerUneArme("", -10, 10);
    }


    //6.b testCreerUneArmeB
    @Test(expected = IllegalArgumentException.class)
    public void testCreerUneArmeB() {
        Facade.creerUneArme("Kaaris", 10, 20);
        Facade.creerUneArme("Kaaris", 3, 67);
    }
    
    //7.a testListerArmesDispoMirmillonA
    @Test
    public void testListerArmesDispoMirmillonA() {
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

    //7.c testListerArmesDispoMirmillonC
    @Test
    public void testListerArmesDispoMirmillonC() {
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
    
    //8.a testDonnerUneArme
    @Test(expected = NoSuchElementException.class)
    public void testDonnerUneArme() {
        Facade.donnerUneArme(10, 10);

        // 8.b
        Facade.creerEthnie(1, "Neuhof");
        Integer idm = Facade.creerMirmillon("Lol", 30, 1);
        Integer ida = Facade.creerUneArme("Lance", 30, 1);
        Facade.donnerUneArme(idm, ida);

        assertTrue("Facade.declarerArmes : liste des armes autorisées non vide",
                   Facade.declarerArmes(idm).isEmpty());
        
        //8.c
        
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.donnerUneArme(idm, ida);
        
        assertThat("Facade.declarerArmes : n'est pas de taille 1",
                   Facade.declarerArmes(idm).size(), is(1));
    
        //8.d
        Facade.donnerUneArme(idm, ida);
        assertThat("Facade.declarerArmes : n'est pas de taille 1",
                   Facade.declarerArmes(idm).size(), is(1));
        
        //8.e
        Integer ida2 = Facade.creerUneArme("Ok", 13, 13);
        Facade.autoriserArmeAuxMirmillons(ida2);
        Facade.donnerUneArme(idm, ida2);
        assertThat("Facade.declarerArmes : n'est pas de taille 2",
                   Facade.declarerArmes(idm).size(), is(2));
    }
    
    /**
     * @see Facade#desarmer(Integer,Integer)
     */
    @Test(expected = NoSuchElementException.class)
    //9.a testDesarmerA
    public void testDesarmerA() {
        Facade.creerEthnie(1, "Strasbourgeois");
        int ida =  Facade.creerUneArme("GlaiveTest", 80, 0);
        int idg = Facade.creerMirmillon("Nathan", 50, 1);
        
        Facade.autoriserArmeAuxMirmillons(ida);
        
        Facade.desarmer(idg, ida);
    }
    
    /**
     * @see Facade#desarmer(Integer,Integer)
     */
    @Test(expected = IllegalArgumentException.class)
    //9.b testDesarmerB
    public void testDesarmerB() {
        Facade.creerEthnie(1, "Strasbourgeois");
        int ida =  Facade.creerUneArme("GlaiveTest", 80, 0);
        int idg = Facade.creerMirmillon("Nathan", 50, 1);
        
        Facade.donnerUneArme(ida, idg);
        
        Facade.desarmer(idg, ida);
    }
    
    /**
     * @see Facade#desarmer(Integer,Integer)
     */
    @Test
    //9.c testDesarmerC
    public void testDesarmerC() {
        Facade.creerEthnie(1, "Strasbourgeois");
        int ida =  Facade.creerUneArme("GlaiveTest", 80, 0);
        int idg = Facade.creerMirmillon("Nathan", 50, 1);
        
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.donnerUneArme(ida, idg);
        Facade.desarmer(idg, ida);  
        
        assertThat("La liste d'arme du Gladiateur " + idg + " n'est pas vide", Facade.declarerArmes(idg).isEmpty(), is(true));
    }
    
    @Test
    //10.a testFrapperA
    public void testFrapperA() {
        Facade.creerEthnie(1, "Strasbourgeois");
        int ida =  Facade.creerUneArme("GlaiveTest", 80, 0);
        int idg1 = Facade.creerMirmillon("Nathan", 50, 1);
        int idg2 = Facade.creerMirmillon("Ismo", 50, 1);
        
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.donnerUneArme(ida, idg1);
        
        int vieInit = Facade.vieGladiateur(idg2);
        
        Facade.frapper(idg1, idg2, ida); 


        assertThat("La vie du Mirmillon " + idg2 + " n'est pas diminuée de la somme entre la force du coup de l'agresseur et la puissance offensive de l'arme choisie", Facade.vieGladiateur(idg2), is(vieInit - (Facade.forceGladiateur(idg1) + Facade.getValOffArme(ida))));

    }
    
    
    @Test
    //10.b testFrapperB
    public void testFrapperB() {
        Facade.creerEthnie(1, "Strasbourgeois");
        int ida =  Facade.creerUneArme("GlaiveTest", 80, 0);
        int idg1 = Facade.creerMirmillon("Nathan", 50, 1);
        int idg2 = Facade.creerRetiaire("Ismo", 50, 1);
        
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.donnerUneArme(ida, idg1);
        
        int vieInit = Facade.vieGladiateur(idg2);
        
        Facade.frapper(idg1, idg2, ida); 
        
        int vie = (vieInit + Facade.agiliteRetiaire(idg2)) - (Facade.forceGladiateur(idg1) + Facade.getValOffArme(ida));
        

        assertThat("La vie du Retiaire " + idg2 + " n'as pas pris en compte son agilité", Facade.vieGladiateur(idg2), is(vie));
    }
    
    @Test
    //10.c testFrapperC
    public void testFrapperC() {
        Facade.creerEthnie(1, "Strasbourgeois");
        int ida =  Facade.creerUneArme("GlaiveTest", 80, 50);
        int idg1 = Facade.creerMirmillon("Nathan", 50, 1);
        int idg2 = Facade.creerMirmillon("Ismo", 50, 1);
        
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.donnerUneArme(ida, idg1);
        Facade.donnerUneArme(ida, idg2);
        
        int vieInit = Facade.vieGladiateur(idg2);
        
        Facade.frapper(idg1, idg2, ida); 
                
        Integer val_deff = 0;
        for (int a : Facade.declarerArmes(idg2)){//accumule la valeur defensive des armes du gladiateur
            val_deff += Facade.getValDeffArme(a);
        }
            
        int vie = (vieInit + val_deff) - (Facade.forceGladiateur(idg1) + Facade.getValOffArme(ida));
        


        assertThat("La vie du Mirmillon " + idg2 + " ne prends pas en compte la valeur defensive de ses armes", Facade.vieGladiateur(idg2), is(vie));

    }
    
    /**
     * @see Facade#frapper(Integer,Integer,Integer)
     */
    @Test
    //10.d testFrapperD
    public void testFrapperD() {
        Facade.creerEthnie(1, "Strasbourgeois");
        int ida =  Facade.creerUneArme("GlaiveTest", 80, 50);
        int idg1 = Facade.creerMirmillon("Nathan", 50, 1);
        int idg2 = Facade.creerRetiaire("Ismo", 50, 1);
        
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.autoriserArmeAuxRetiaires(ida);
        Facade.donnerUneArme(ida, idg1);
        Facade.donnerUneArme(ida, idg2);
        
        int vieInit = Facade.vieGladiateur(idg2);
        
        Facade.frapper(idg1, idg2, ida); 
                
        Integer val_deff = 0;
        for (int a : Facade.declarerArmes(idg2)){//accumule la valeur defensive des armes du gladiateur
            val_deff += Facade.getValDeffArme(a);
        }
            
        int vie = (vieInit + val_deff + Facade.agiliteRetiaire(idg2) - (Facade.forceGladiateur(idg1) + Facade.getValOffArme(ida)));

        assertThat("La vie du Retiaire " + idg2 + " ne prends pas en compte la valeur defensive de ses armes et/ou son agilite", Facade.vieGladiateur(idg2), is(vie));

    }
    
    /**
     * @see Facade#frapper(Integer,Integer,Integer)
     */
    @Test
    //10.e testFrapperE
    public void testFrapperE() {
        Facade.creerEthnie(1, "Strasbourgeois");
        int ida =  Facade.creerUneArme("GlaiveTest", 10, 50);
        int idg1 = Facade.creerMirmillon("Nathan", 10, 1);
        int idg2 = Facade.creerRetiaire("Ismo", 50, 1);
        
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.donnerUneArme(ida, idg1);
        
        int vieInit = Facade.vieGladiateur(idg2);
        
        Facade.frapper(idg1, idg2, ida); 
                    
        int vie = (vieInit + Facade.agiliteRetiaire(idg2)) - (Facade.forceGladiateur(idg1) + Facade.getValOffArme(ida));


        assertThat("La vie du Retiaire " + idg2 + " augmente lorsque son agilité est supérieure à la force du coup de l'agresseur", Facade.vieGladiateur(idg2) <= vie, is(true));
    }
    
    /**
     * @see Facade#frapper(Integer,Integer,Integer)
     */
    @Test
    //10.f testFrapperF
    public void testFrapperF() {
        Facade.creerEthnie(1, "Strasbourgeois");
        int ida =  Facade.creerUneArme("GlaiveTest", 10, 100);
        int idg1 = Facade.creerMirmillon("Nathan", 10, 1);
        int idg2 = Facade.creerRetiaire("Ismo", 10, 1);
        
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.donnerUneArme(ida, idg1);
        
        int vieInit = Facade.vieGladiateur(idg2);
        
        Facade.frapper(idg1, idg2, ida);         
        
        Integer val_deff = 0;
        for (int a : Facade.declarerArmes(idg2)){//accumule la valeur defensive des armes du gladiateur
            val_deff += Facade.getValDeffArme(a);
        }
            
        int vie = (vieInit + val_deff + Facade.agiliteRetiaire(idg2)) - (Facade.forceGladiateur(idg1) + Facade.getValOffArme(ida));
            

        assertThat("La vie du Retiaire " + idg2 + " augmente lorsque la puissance défensive de son arme - ou des armes - est supérieure à la force du coup de l'agresseur", Facade.vieGladiateur(idg2) <= vie, is(true));
    }
    
    /**
     * @see Facade#frapper(Integer,Integer,Integer)
     */
    @Test
    //10.g testFrapperG
    public void testFrapperG() {
        Facade.creerEthnie(1, "Strasbourgeois");
        int ida =  Facade.creerUneArme("GlaiveTest", 10, 0);
        int idg1 = Facade.creerMirmillon("Nathan", 10, 1);
        
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.donnerUneArme(ida, idg1);
        
        int vieInit = Facade.vieGladiateur(idg1);
        
        Facade.frapper(idg1, idg1, ida);         
            
        int vie = vieInit - (Facade.forceGladiateur(idg1) + Facade.getValOffArme(ida));
            

        assertThat("La vie du Mirmillon " + idg1 + " n'est pas diminuée correctement lorsqu'il se frappe", Facade.vieGladiateur(idg1), is(vieInit - 15));
    }
    
    /**
     * @see Facade#frapper(Integer,Integer,Integer)
     */
    @Test
    //10.h testFrapperH
    public void testFrapperH() {
        Facade.creerEthnie(1, "Strasbourgeois");
        int ida =  Facade.creerUneArme("GlaiveTest", 250, 0);
        int idg1 = Facade.creerMirmillon("Nathan", 10, 1);
        int idg2 = Facade.creerMirmillon("Ismo", 10, 1);
        
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.donnerUneArme(ida, idg1);
        
        int vieInit = Facade.vieGladiateur(idg2);
        
        Facade.frapper(idg1, idg1, ida); 
        Facade.frapper(idg1, idg2, ida); 
            

        assertThat("Le Mirmillon " + idg1 + " a pu frapper alors qu'il est mort" , Facade.vieGladiateur(idg2) <= vieInit, is(true));
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    //11.a listerAgresseurs
    public void testListerAgresseurs() {
        Facade.creerEthnie(1, "Strasbourgeois");
        int idg = Facade.creerRetiaire("Antonismael", 30, 1);
        
        Facade.listerAgresseurs(idg);
    }
    
    
    @Test
    //11.b listerAgresseursMirmillon
    public void testListerAgresseursMirmillon() {
        Facade.creerEthnie(1, "Strasbourgeois");
        int ida =  Facade.creerUneArme("GlaiveTest", 80, 0);
        int idg1 = Facade.creerMirmillon("Nathan", 50, 1);
        int idg2 = Facade.creerMirmillon("Ismo", 50, 1);
        
        
        Facade.autoriserArmeAuxMirmillons(ida);
        Facade.donnerUneArme(ida, idg1);
        
        Facade.frapper(idg1, idg2, ida); 

        assertThat("La liste des agresseurs de Mirmillon " + idg2 + " ne contient pas " + idg1, Facade.listerAgresseurs(idg2).contains(idg1), is(true));

        assertThat("La liste des agresseurs de Mirmillon " + idg1 + " n'est pas vide", Facade.listerAgresseurs(idg1).isEmpty(), is(true));
    }
    
    /**
     * @see Facade#listerAgresseurs(Integer)
     */
    @Test
    //11.b listerAgresseursPlusieursCoups
    public void testListerAgresseursPlusieursCoups() {
        Facade.creerEthnie(1, "Strasbourgeois");
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
        Facade.creerEthnie(1, "Strasbourgeois");
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
        Facade.creerEthnie(1, "Strasbourgeois");
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
        Facade.creerEthnie(1, "Strasbourgeois");
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
    //13.a
    public void testLancerJeuDEssai() {
        Facade.lancerJeuDEssai();
        assertThat("Le nombre d'ethnies ne vaut pas 3", Facade.listerEthnies().size(), is(3));
        assertThat("Le nomre d'armes ne vaut pas 6", Facade.listerArmes().size(), is(6));
        assertThat("Le nombre de gladiateurs ne vaut pas 6", Facade.listerTousGladiateurs().size(), is(6));
        
        assertThat("Nombre de glad d'ethnie 1 != 2", Facade.listerGladiateursDEthnie(1).size(), is(2));
        assertThat("Nombre de glad d'ethnie 2 != 2", Facade.listerGladiateursDEthnie(2).size(), is(2));
        assertThat("Nombre de glad d'ethnie 3 != 2", Facade.listerGladiateursDEthnie(3).size(), is(2));
       
        
        assertThat("Nombre d'armes dipo aux aux Mirmillons != 4", Facade.listerArmesDispoMirmillon().size(), is(4));
        assertThat("Nombre d'armes dipo aux aux Retiaires != 4", Facade.listerArmesDispoRetiaire().size(), is(4));
        
        assertThat("Armes de glad1 != 3", Facade.listerArmesDuGlad(1).size(), is(3));
        assertThat("Armes de glad2 != 4", Facade.listerArmesDuGlad(2).size(), is(4));
        assertThat("Armes de glad3 != 2", Facade.listerArmesDuGlad(3).size(), is(2));
        assertThat("Armes de glad4 != 2", Facade.listerArmesDuGlad(4).size(), is(2));
        assertThat("Armes de glad5 != 2", Facade.listerArmesDuGlad(5).size(), is(2));
        assertThat("Armes de glad6 != 2", Facade.listerArmesDuGlad(6).size(), is(2));

    }
}
