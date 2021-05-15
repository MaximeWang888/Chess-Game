package echequier;

import static org.junit.Assert.*;
import org.junit.Test;

import piece.Coordonnee;
import piece.Couleur;
import piece.Piece;
import piece.Roi;

public class EchequierTest {

    @Test
    public void estVide() {
        // GIVEN
        Echequier echequier = new Echequier();
        echequier.ajoutPiece(new Roi(Couleur.BLANC, new Coordonnee(3,3)), new Coordonnee(3,3));

        // THEN
        assertTrue(echequier.estVide(1,1));
        assertFalse(echequier.estVide(3,3));
    }

    @Test
    public void getPiece() {
        // GIVEN
        Coordonnee c1 = new Coordonnee(1,2);
        Coordonnee c2 = new Coordonnee(2,2);
        Piece r1 = new Roi(Couleur.BLANC, c1);
        Echequier echequier = new Echequier();
        echequier.ajoutPiece(r1, r1.getCoordonnee());
        // THEN
        assertEquals(echequier.getPiece(c1), r1);
        assertNotEquals(echequier.getPiece(c2), r1);
    }

    @Test
    public void testToString() {
        // GIVEN
        Echequier echequier = new Echequier();
        echequier.ajoutPiece(new Roi(Couleur.BLANC, new Coordonnee(3,0)), new Coordonnee(3,0));
        echequier.ajoutPiece(new Roi(Couleur.NOIR, new Coordonnee(6,6)), new Coordonnee(6,6));

         String affichage =
                        "    a   b   c   d   e   f   g   h \n" +
                        "   --- --- --- --- --- --- --- ---\n" +
                        "8 |   |   |   |   |   |   |   |   | 8\n" +
                        "   --- --- --- --- --- --- --- ---\n" +
                        "7 |   |   |   |   |   |   |   |   | 7\n" +
                        "   --- --- --- --- --- --- --- ---\n" +
                        "6 |   |   |   |   |   |   |   |   | 6\n" +
                        "   --- --- --- --- --- --- --- ---\n" +
                        "5 | R |   |   |   |   |   |   |   | 5\n" +
                        "   --- --- --- --- --- --- --- ---\n" +
                        "4 |   |   |   |   |   |   |   |   | 4\n" +
                        "   --- --- --- --- --- --- --- ---\n" +
                        "3 |   |   |   |   |   |   |   |   | 3\n" +
                        "   --- --- --- --- --- --- --- ---\n" +
                        "2 |   |   |   |   |   |   | r |   | 2\n" +
                        "   --- --- --- --- --- --- --- ---\n" +
                        "1 |   |   |   |   |   |   |   |   | 1\n" +
                        "   --- --- --- --- --- --- --- ---\n" +
                        "    a   b   c   d   e   f   g   h " ;

        // THEN
        assertEquals(affichage, echequier.toString());
    }
}