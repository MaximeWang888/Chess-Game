import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import echiquier.Coordonnee;
import echiquier.Echiquier;
import echiquier.IPiece;

import piece.Couleur;
import piece.Roi;
import piece.TypePiece;

class PieceTest {

    @Test
    void testSetCoord() {
        // GIVEN
        IPiece roi = new Roi(TypePiece.ROI, Couleur.NOIR,new Coordonnee(1,1));
        roi.setCoord(new Coordonnee(2,2));
        // THEN
        assertTrue(roi.getCoordonnee().getY() == 2 &&  roi.getCoordonnee().getX() ==2 );
    }

    @Test
    void testDeplacer() {
        // GIVEN
        IPiece r1 = new Roi(TypePiece.ROI, Couleur.BLANC, new Coordonnee(1,1));
        Echiquier echiquier = new Echiquier();
        Coordonnee nouvelleCoordonnee = new Coordonnee(1,2);
        // WHEN
        echiquier.ajoutPiece(r1, r1.getCoordonnee());
        echiquier.getPiece(new Coordonnee(1,1)).deplacer(nouvelleCoordonnee, echiquier);
        // THEN
        assertEquals(r1.getCoordonnee(), nouvelleCoordonnee);
        assertTrue(echiquier.getPiece(new Coordonnee(1,2)) == r1);
    }

    @Test
    void testEstAllie() {
        // GIVEN
        IPiece roiMaxime = new Roi(TypePiece.ROI, Couleur.NOIR,new Coordonnee(1,1));
        IPiece roiFabien = new Roi(TypePiece.ROI, Couleur.NOIR,new Coordonnee(1,1));

        // THEN
        assertTrue(roiMaxime.estAllie(roiFabien));
    }

    @Test
    void testGetCouleur() {
        // GIVEN
        IPiece roi = new Roi(TypePiece.ROI, Couleur.NOIR,new Coordonnee(1,1));
        // THEN
        assertTrue(roi.getCouleur() == Couleur.NOIR );
    }

    @Test
    void testGetCoordonnee() {
        // GIVEN
        Coordonnee c = new Coordonnee(1,1);
        IPiece roi = new Roi(TypePiece.ROI, Couleur.NOIR, c);
        // THEN
        assertEquals(roi.getCoordonnee() , c );
    }
}