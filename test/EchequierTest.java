import static org.junit.Assert.*;

import echequier.Echequier;
import org.junit.Test;

import piece.*;

public class EchequierTest {

    @Test
    public void testEstVide() {
        // GIVEN
        Echequier echequier = new Echequier();
        echequier.ajoutPiece(new Roi(TypePiece.ROI, Couleur.BLANC, new Coordonnee(3,3)),
                new Coordonnee(3,3));

        // THEN
        assertTrue(echequier.estVide(1,1));
        assertFalse(echequier.estVide(3,3));
    }

    @Test
    public void testGetPiece() {
        // GIVEN
        Coordonnee c1 = new Coordonnee(1,2);
        Coordonnee c2 = new Coordonnee(2,2);
        Piece r1 = new Roi(TypePiece.ROI, Couleur.BLANC, c1);
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
        echequier.ajoutPiece(new Roi(TypePiece.ROI, Couleur.BLANC, new Coordonnee(3,0)),
                new Coordonnee(3,0));
        echequier.ajoutPiece(new Roi(TypePiece.ROI, Couleur.NOIR, new Coordonnee(6,6)),
                new Coordonnee(6,6));

         StringBuilder affichage = new StringBuilder();
                 affichage.append("   a   b   c   d   e   f   g   h \n")
                         .append("   --- --- --- --- --- --- --- ---\n")
                         .append("8 |   |   |   |   |   |   |   |   | 8\n")
                         .append("   --- --- --- --- --- --- --- ---\n")
                         .append("7 |   |   |   |   |   |   |   |   | 7\n")
                         .append("   --- --- --- --- --- --- --- ---\n")
                         .append("6 |   |   |   |   |   |   |   |   | 6\n")
                         .append("   --- --- --- --- --- --- --- ---\n")
                         .append("5 | R |   |   |   |   |   |   |   | 5\n")
                         .append("   --- --- --- --- --- --- --- ---\n")
                         .append("4 |   |   |   |   |   |   |   |   | 4\n")
                         .append("   --- --- --- --- --- --- --- ---\n")
                         .append("3 |   |   |   |   |   |   |   |   | 3\n")
                         .append("   --- --- --- --- --- --- --- ---\n")
                         .append("2 |   |   |   |   |   |   | r |   | 2\n")
                         .append("   --- --- --- --- --- --- --- ---\n")
                         .append("1 |   |   |   |   |   |   |   |   | 1\n")
                         .append("   --- --- --- --- --- --- --- ---\n")
                         .append("    a   b   c   d   e   f   g   h ");

        // THEN
        assertEquals(affichage, echequier.toString());
    }
}