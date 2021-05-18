import static org.junit.Assert.*;
import org.junit.Test;

import echiquier.Coordonnee;
import echiquier.Echiquier;
import echiquier.IPiece;

import piece.Couleur;
import piece.Roi;
import piece.TypePiece;

/**
 * Tests unitaires sur la classe Echiquier dans le jeu de l'échiquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class EchiquierTest {

    @Test
    public void testEstVide() {
        // GIVEN
        Echiquier echiquier = new Echiquier();
        echiquier.ajoutPiece(new Roi(TypePiece.ROI, Couleur.BLANC, new Coordonnee(3,3)),
                new Coordonnee(3,3));

        // THEN
        assertTrue(echiquier.estVide(1,1));
        assertFalse(echiquier.estVide(3,3));
    }

    @Test
    public void testGetPiece() {
        // GIVEN
        Coordonnee c1 = new Coordonnee(1,2);
        Coordonnee c2 = new Coordonnee(2,2);
        IPiece r1 = new Roi(TypePiece.ROI, Couleur.BLANC, c1);
        Echiquier echiquier = new Echiquier();
        echiquier.ajoutPiece(r1, r1.getCoordonnee());
        // THEN
        assertEquals(echiquier.getPiece(c1), r1);
        assertNotEquals(echiquier.getPiece(c2), r1);
    }

    @Test
    public void testToString() {
        // GIVEN
        Echiquier echiquier = new Echiquier();
        echiquier.ajoutPiece(new Roi(TypePiece.ROI, Couleur.BLANC, new Coordonnee(3,0)),
                new Coordonnee(3,0));
        echiquier.ajoutPiece(new Roi(TypePiece.ROI, Couleur.NOIR, new Coordonnee(6,6)),
                new Coordonnee(6,6));

         StringBuilder affichage = new StringBuilder();
                 affichage.append("    a   b   c   d   e   f   g   h \n ")
                         .append("  --- --- --- --- --- --- --- ---\n")
                         .append("8 |   |   |   | R |   |   |   |   | 8\n")
                         .append("   --- --- --- --- --- --- --- ---\n")
                         .append("7 |   |   |   |   |   |   |   |   | 7\n")
                         .append("   --- --- --- --- --- --- --- ---\n")
                         .append("6 |   |   |   |   |   |   |   |   | 6\n")
                         .append("   --- --- --- --- --- --- --- ---\n")
                         .append("5 |   |   |   |   |   |   |   |   | 5\n")
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
        assertEquals(affichage.toString(), echiquier.toString());
    }
}