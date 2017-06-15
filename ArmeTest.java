package packglad;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ArmeTest {
    
    private Arme a;
    
    @Before
    public void setUp()
    {
        a = new Arme("gun", 10, 20, 1);           
    }
    
    @Test
    public void testGetIda()
    {
        assertThat("Arme.getIdg : Ida mal initialisé", a.getIda(), is(1));   
    }
    
    
    @Test
    public void testGetNom() {
        assertThat("Arme.getNom : Nom mal initialisé", a.getNom(), is("gun"));
    }

    
    @Test
    public void testGetValOff() {
        assertThat("Arme.getValOff : ValOff mal initialisée", a.getValOff(), is(10));
    }

    @Test
    public void testGetValDef() {
        assertThat("Arme.getValOff : ValDef mal initialisée", a.getValDef(), is(20));
    }


    @Test
    @Ignore
    public void testDecrire() {
        fail("Ce test est délicat à réaliser car le format exact n'a pas été fixé");
    }
}
