package packglad;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class EthnieTest {
    private Ethnie e;
    //private Gladiateur g;

    @Before
    public void setUp()
    {
        e = new Ethnie(1, "Gaulois");
        //g = new Mirmillon(67, "Ismael", 62, e);
    }
    
    @Test 
    public void testCalculerScore() {
        assertThat("Ethnie.CalculerScore : Score de 0 car ethnie vide",
                   e.calculerScore(), is(0));
    }

    @Test
    public void testGetIde() {
        assertThat("Ethnie.getIde : Ide mal initialisé", e.getIde(), is(1));
    }

    @Test
    public void testGetNom() {
        assertThat("Ethnie.getNom : Nom mal initialisé", e.getNom(), is("Gaulois"));
    }

    
    
    @Test
    public void testListerGladiateurs() {
        assertTrue("Arme.listerGladiateurs : liste de armes autorisées non vide initialement", 
                  e.listerGladiateurs().isEmpty());
    }
    
  

    
    @Test(expected = IllegalArgumentException.class)
    //    "Ethnie.ajouterGlad : Gladiateur null ne lance pas l'exception IllegalArgumentException"
    public void testAjouterGlad() {
        e.ajouterGlad(null);
    }

    
    @Test
    public void testC_setPeutAjouter() {
        Ethnie.c_setPeutAjouter(false);
        assertThat("Ethnie.c_getPeutAjouter : Modifcation possible commune des ethnies mal modifiée", 
                   Ethnie.c_getPeutAjouter(), is(false));
       
        Ethnie.c_setPeutAjouter(true);   
        }

    
    @Test
    public void testC_getPeutAjouter() {
        assertThat("Ethnie.c_getPeutAjouter : Modifcation possible commune des ethnies mal initialisée", 
                   Ethnie.c_getPeutAjouter(), is(true)); 
        }
    
    
}
