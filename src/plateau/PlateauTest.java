package plateau;

import static org.junit.Assert.*;
import org.junit.Test;

import piece.Coordonnee;
import piece.Couleur;
import piece.Piece;
import piece.Roi;

public class PlateauTest {

    @Test
    public void estVide() {
        // GIVEN
        Plateau plateau = new Plateau();
        plateau.ajoutPiece(new Roi(Couleur.BLANC, new Coordonnee(3,3)), new Coordonnee(3,3));

        // THEN
        assertTrue(plateau.estVide(1,1));
        assertFalse(plateau.estVide(3,3));
    }

    @Test
    public void getPiece() {
        // GIVEN
        Piece r1 = new Roi(Couleur.BLANC, new Coordonnee(1,1));
        Plateau plateau = new Plateau();
        plateau.ajoutPiece(r1, r1.getcoordonne());

        // THEN
        assertEquals(plateau.getPiece(1,1), r1);
    }

    @Test
    public void testToString() {
        // GIVEN
        Plateau plateau = new Plateau();
        plateau.ajoutPiece(new Roi(Couleur.BLANC, new Coordonnee(3,3)), new Coordonnee(3,3));
        plateau.ajoutPiece(new Roi(Couleur.NOIR, new Coordonnee(6,6)), new Coordonnee(6,6));

        String affichage =
                        "    a   b   c   d   e   f   g   h \n" +
                        "   --- --- --- --- --- --- --- ---\n" +
                        "8 |   |   |   |   |   |   |   |   | 8\n" +
                        "   --- --- --- --- --- --- --- ---\n" +
                        "7 |   |   |   |   |   |   |   |   | 7\n" +
                        "   --- --- --- --- --- --- --- ---\n" +
                        "6 |   |   |   |   |   |   |   |   | 6\n" +
                        "   --- --- --- --- --- --- --- ---\n" +
                        "5 |   |   |   | R |   |   |   |   | 5\n" +
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
        assertEquals(affichage,plateau.toString());
    }
}