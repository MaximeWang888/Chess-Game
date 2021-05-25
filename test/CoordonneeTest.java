import echiquier.Coordonnee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires sur la classe Coordonnee dans le jeu de l'échiquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class CoordonneeTest {

    @Test
    public void testGetY() {
        // GIVEN
        Coordonnee c = new Coordonnee(2,3);

        // THEN
        assertEquals(c.getY(),3);
        assertNotEquals(c.getY(),2);
    }

    @Test
    public void testGetX() {
        // GIVEN
        Coordonnee c = new Coordonnee(2,3);

        // THEN
        assertEquals(c.getX(),2);
        assertNotEquals(c.getX(),3);
    }

    @Test
    public void testSetX(){
        // GIVEN
        Coordonnee c = new Coordonnee();
        int changement = 5;
        // THEN
        assertEquals(c.getX(), 0);
        // WHEN
        c.setX(changement);
        // THEN
        assertEquals(c.getX(), changement);
    }

    @Test
    public void testSetY(){
        // GIVEN
        Coordonnee c = new Coordonnee();
        int changement = 5;
        // THEN
        assertEquals(c.getY(), 0);
        // WHEN
        c.setY(changement);
        // THEN
        assertEquals(c.getY(), changement);
    }


    @Test
    public void testIsCoordonneeExistante() {
        // GIVEN
        Coordonnee c = new Coordonnee(2,3);
        // THEN
        assertTrue(c.isCoordonneeExistante());
        AssertionError error = Assertions.assertThrows(AssertionError.class, () -> { new Coordonnee(-1,-1); });
        Assertions.assertEquals("La coordonnée dépasse la dimension de l'échiquier.", error.getMessage());
    }

    @Test
    public void testConversionEnCoord() {
        // GIVEN
        Coordonnee c = new Coordonnee();
        Coordonnee c1 = new Coordonnee();
        Coordonnee c2 = new Coordonnee();
        Coordonnee c3 = new Coordonnee();
        Coordonnee c4 = new Coordonnee();
        Coordonnee c5 = new Coordonnee();
        Coordonnee c6 = new Coordonnee();
        Coordonnee c7 = new Coordonnee();
        String mot = "a8" ;
        c = c.conversionEnCoord(mot);
        String mot1 = "b1" ;
        c1 = c1.conversionEnCoord(mot1);
        String mot2 = "c1" ;
        c2 = c2.conversionEnCoord(mot2);
        String mot3 = "d1" ;
        c3 = c3.conversionEnCoord(mot3);
        String mot4 = "e1" ;
        c4 = c4.conversionEnCoord(mot4);
        String mot5 = "f1" ;
        c5 = c5.conversionEnCoord(mot5);
        String mot6 = "g1" ;
        c6 = c6.conversionEnCoord(mot6);
        String mot7 = "h1" ;
        c7 = c7.conversionEnCoord(mot7);


        // THEN
        assertEquals(c.getY(),0);
        assertEquals(c.getX(),0);

        assertEquals(c1.getY(),7);
        assertEquals(c1.getX(),1);

        assertEquals(c2.getY(),7);
        assertEquals(c2.getX(),2);

        assertEquals(c3.getY(),7);
        assertEquals(c3.getX(),3);

        assertEquals(c4.getY(),7);
        assertEquals(c4.getX(),4);

        assertEquals(c5.getY(),7);
        assertEquals(c5.getX(),5);

        assertEquals(c6.getY(),7);
        assertEquals(c6.getX(),6);

        assertEquals(c7.getY(),7);
        assertEquals(c7.getX(),7);
    }

    @Test
    public void testConversionEnString(){
        // GIVEN
        Coordonnee c = new Coordonnee(0, 1);
        Coordonnee c1 = new Coordonnee(1,1);
        Coordonnee c2 = new Coordonnee(2,1);
        Coordonnee c3 = new Coordonnee(3,1);
        Coordonnee c4 = new Coordonnee(4,1);
        Coordonnee c5 = new Coordonnee(5,1);
        Coordonnee c6 = new Coordonnee(6,1);
        Coordonnee c7 = new Coordonnee(7,1);
        String cString = c.conversionEnString();
        String c1String = c1.conversionEnString();
        String c2String = c2.conversionEnString();
        String c3String = c3.conversionEnString();
        String c4String = c4.conversionEnString();
        String c5String = c5.conversionEnString();
        String c6String = c6.conversionEnString();
        String c7String = c7.conversionEnString();
        // THEN
        assertEquals(cString,"a7");

        assertEquals(c1String,"b7");

        assertEquals(c2String,"c7");

        assertEquals(c3String,"d7");

        assertEquals(c4String,"e7");

        assertEquals(c5String,"f7");

        assertEquals(c6String,"g7");

        assertEquals(c7String,"h7");
    }

    @Test
    public void testCoordonneeAlaLimiteDuPlateau(){
        // GIVEN
        Coordonnee coordALaLimite = new Coordonnee(0,0);
        Coordonnee coordALaLimite2 = new Coordonnee(7,5);
        Coordonnee coordPasALaLimite = new Coordonnee(5,5);
        // THEN
        assertEquals(coordALaLimite.coordonneeAlaLimiteDuPlateau(), coordALaLimite2.coordonneeAlaLimiteDuPlateau());
        assertNotEquals(coordALaLimite.coordonneeAlaLimiteDuPlateau(), coordPasALaLimite.coordonneeAlaLimiteDuPlateau());
    }

    @Test
    public void testToString(){
        // GIVEN
        Coordonnee c = new Coordonnee(1,1);
        String s = "Coordonnee{colonne=1, ligne=1}";

        // THEN
        assertEquals(c.toString(), s);
    }
}