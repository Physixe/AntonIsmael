package packglad;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class MirmillonTest {
    
    private Mirmillon m;
    private Ethnie e;
    
    @Before
    public void setUp()
    {
        e = new Ethnie(2, "Gaulois");
        m = new Mirmillon(2, "Younix", 50, e);        
    }
    
    @Test
    public void testGetIdg() 
    {
        assertThat("Gladiateug.getIdg : Idg mal initialisé", m.getIdg(), is(2));
    }
    
    
    @Test
    public void testGetNom()
    {
        assertThat("Gladiateur.getNom : Nom mal initialisé", m.getNom(), is("Younix"));
    }
    
    @Test
    public void testGetForce() 
    {
        assertThat("Gladiateur.getForce : force mal initialisée", m.getForce(), is(m.getPoids()/2));
    }
        
    @Test
    public void testGetEthnie()
    {
        assertThat("Gladiateur.getEthnie : ethnie mal initialisée", 
                   m.getEthnie(), is(e));
    }  
       
    @Test
    public void testGetVie()
    {
        assertThat("Gladiateur.getVie : Vie mal initialisée", 
                   m.getVie(), is(Gladiateur.c_getVieInitiale()));
    }   
        
    @Test
    public void testGetTypeGlad()
    {
        assertThat("Retiaire.getTypeGlad : Type du retiaire mal initialisé", 
                   m.getType(), is(Mirmillon.c_getType()));    
    }   
        
    @Test
    public void testGetTypeGladMir()
    {
        assertThat("Mirmillon.getTypeGlad : Type du mirmillon mal initialisé", 
                   m.getType(), is(Mirmillon.c_getType()));    
    }    
        
    @Test
    public void testC_getPoidsMaxMir()
    {
        assertThat("Mirmillon.c_getForceRet : Force commune des mirmillons mal initialisée", 
                   Mirmillon.c_getPoidsMax(), is(100));    
    }
    
    
    @Test
    public void testSetC_poidsMax() {
        Mirmillon.c_setPoidsMax(60);
        assertThat("Mirmillon.c_getPoidsMax() : Poids max des mirmillons mal modifié",
            Mirmillon.c_getPoidsMax(), is(60));
        
        Retiaire.c_setForce(100);
    }

    @Test
    public void testC_getTypeGladMir()
    {
        assertThat("Mirmillon.c_getTypeGladMir() : Type des Mirmillons mal initialisé", 
                   Mirmillon.c_getType().toLowerCase(), is("mirmillon"));
    }


    @Test
    public void testC_setTypeGladMir()
    {
        String s = "NouveauNOM";
        String t = Mirmillon.c_getType();
        Mirmillon.c_setType(s);
        assertThat("Retiaire.c_getTypeGladMir() : Type des retiaires mal modifié", 
                   Mirmillon.c_getType(), is(s));
        Mirmillon.c_setType(t);
    }

    @Test
    @Ignore
    public void testRapport()
    {
        fail("Ce test est délicat à réaliser car le format exact n'a pas été fixé");
    }
    

    @Test(expected = IllegalArgumentException.class)
    //    "Mirmillon.armeAutorisee : Arme null ne lance pas l'exception IllegalArgumentException"
    public void testArmeAutorisee()
    {
        m.armeEstAutorisee(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    //    "Mimillon.autoriserArme : Arme null ne lance pas l'exception IllegalArgumentException"
    public void testAutoriserArme()
    {
        Mirmillon.c_autoriserArmeMirmillon(null);
    }
    
    @Test
    public void testListerArmesAutorisees()
    {
        assertTrue("Mirmillon.listerArmesAutorisees : liste des armes autorisées non vide initialement", 
                   Mirmillon.c_getArmesDispoMir().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    //    "Retiaire.autoriserArme : Arme null ne lance pas l'exception IllegalArgumentException"
    
    public void testAutoriserArmeMir()
    {
        Mirmillon.c_autoriserArmeMirmillon(null);
    }

    @Test
    public void testListerArmesAutoriseesMir()
    {
        assertTrue("Retiaire.listerArmesAutoriseesMir : liste des armes autorisées non vide initialement", 
                   Mirmillon.c_getArmesDispoMir().isEmpty());
    }

    @Test
    public void testEstBienPortant()
    {
        assertTrue("Gladiateur.estBienPortant : younix devrait être bien portant", 
                   m.getEtat()=="vivant");
    }
    
    @Test
    public void testEstBlesse()
    {
        assertFalse("Gladiateur.estBlesse : younix ne devrait pas être blessé", 
                  m.getEtat()=="blessé");
    }
    
    @Test
    public void testEstMortOuMoribond()
    {
        assertFalse("Gladiateur.estMortOuMoribond : younix ne devrait pas être mort", 
                   m.getEtat()=="moribond" || m.getEtat()=="mort");
    }
    
    @Test
    public void testGetMesArmes()
    {
        assertTrue("Gladiateur.getMesArmes : younix ne devrait pas avoir d'arme", 
                   m.declarerArmes().isEmpty());    }
    
    @Test(expected = IllegalArgumentException.class)
    //    "Gladiateur.frapper : Gladiateur ou Arme null ne lance pas l'exception IllegalArgumentException"
    public void testFrapper()
    {
        m.frapper(null, null);
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    //    "Gladiateur.recevoirCoup : Gladiateur null ne lance pas l'exception IllegalArgumentException"
    //    "Gladiateur.recevoirCoup : force négative ne lance pas l'exception IllegalArgumentException"
    public void testRecevoirCoup()
    {
        m.recoitCoups(null, gArme.getArme(-1));
    }
    
    @Test
    @Ignore
    public void testSaluer()
    {
        fail("Ce test est délicat à réaliser car le format exact n'a pas été fixé");
    }
    
    @Test(expected = IllegalArgumentException.class)
    //    "Gladiateur.recevoirArme : Arme null ne lance pas l'exception IllegalArgumentException"
    public void testRecevoirArme()
    {
        m.addArme(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    //    "Gladiateur.perdreArme : Arme null ne lance pas l'exception IllegalArgumentException"
    public void testPerdreArme()
    {
        m.perdreArme(null);
    }  
    
    @Test(expected = IllegalArgumentException.class)
    //    "Mirmmilon.init : Identificateur négatif ne lance pas l'exception IllegalArgumentException"
    public void testMirmillon1()
    {
        new Mirmillon(-1, "Negativix", 30, e);
    }
    
    @Test(expected = IllegalArgumentException.class)
    //    "Mirmillon.init : Nom null ne lance pas l'exception IllegalArgumentException"
    public void testMirmillon2()
    {
        new Mirmillon(1, null, 30, e);
    }

    @Test(expected = IllegalArgumentException.class)
    //    "Mirmillon.init : Nom vide ne lance pas l'exception IllegalArgumentException"
    public void testMirmillon3()
    {
        new Retiaire(1, "", 30, e);
    }

    @Test(expected = IllegalArgumentException.class)
    //    "Mirmillon.init : poids négatif ne lance pas l'exception IllegalArgumentException"
    public void testMirmillon4()
    {
        new Mirmillon(1, "Agilix", -100, e);
    }
    
    @Test(expected = IllegalArgumentException.class)
    //    "Mirmillon.init : poids > max ne lance pas l'exception IllegalArgumentException"
    public void testMirmillon5()
    {
        new Mirmillon(1, "Agilix", Mirmillon.c_getPoidsMax() + 10, e);
    }
    
}
