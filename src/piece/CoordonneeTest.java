package piece;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoordonneeTest {

    @Test
    public void getColonne() {
        // GIVEN
        Coordonnee c = new Coordonnee(2,3);

        // THEN
        assertEquals(c.getColonne(),3);
        assertNotEquals(c.getColonne(),2);
    }

    @Test
    public void getLigne() {
        // GIVEN
        Coordonnee c = new Coordonnee(2,3);

        // THEN
        assertEquals(c.getLigne(),2);
        assertNotEquals(c.getLigne(),3);
    }

    @Test
    public void coordValide() {
        // GIVEN
        Coordonnee c = new Coordonnee(2,3);
        Coordonnee d = new Coordonnee(-1,-1);

        // THEN
        assertTrue(c.coordValide());
        assertFalse(d.coordValide());
    }

    @Test
    public void conversionEnCoord() {
        // GIVEN
        Coordonnee c = new Coordonnee(-1,-1);
        String mot = "a8" ;
        c.conversionEnCoord(mot);
        Coordonnee c1 = new Coordonnee(-1,-1);
        String mot1 = "h1" ;
        c1.conversionEnCoord(mot1);

        // THEN
        assertEquals(c.getColonne(),0);
        assertEquals(c.getLigne(),0);
        assertEquals(c1.getColonne(),7);
        assertEquals(c1.getLigne(),7);
    }
}