package piece;

import echequier.Echequier;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

class PieceTest {

    @Test
    void setCoord() {
        // GIVEN
        Piece roi = new Roi(Couleur.NOIR,new Coordonnee(1,1));
        roi.setCoord(new Coordonnee(2,2));
        // THEN
        assertTrue(roi.getCoordonnee().getColonne() == 2 &&  roi.getCoordonnee().getLigne() ==2 );
    }

    @Test
    void deplacer() {
        // GIVEN
        Piece r1 = new Roi(Couleur.BLANC, new Coordonnee(1,1));
        Echequier echequier = new Echequier();
        Coordonnee nouvelleCoordonnee = new Coordonnee(1,2);
        // WHEN
        echequier.ajoutPiece(r1, r1.getCoordonnee());
        echequier.getPiece(new Coordonnee(1,1)).deplacer(nouvelleCoordonnee, echequier);
        // THEN
        assertEquals(r1.getCoordonnee(), nouvelleCoordonnee);
        assertTrue(echequier.getPiece(new Coordonnee(1,2)) == r1);
    }

    @Test
    void peutSeDeplacer() {
        // GIVEN
        Piece r1 = new Roi(Couleur.BLANC, new Coordonnee(1,1));
        Echequier echequier = new Echequier();
        Coordonnee nouvelleCoordonnee = new Coordonnee(1,2);
        // WHEN

        // THEN
        //assertEquals();
    }

    @Test
    void estAllie() {
        // GIVEN
        Piece roiMaxime = new Roi(Couleur.NOIR,new Coordonnee(1,1));
        Piece roiFabien = new Roi(Couleur.NOIR,new Coordonnee(1,1));
        // THEN
        assertTrue(roiMaxime.estAllie(roiFabien));
    }

    @Test
    void getCouleur() {
        // GIVEN
        Piece roi = new Roi(Couleur.NOIR,new Coordonnee(1,1));
        // THEN
        assertTrue(roi.getCouleur() == Couleur.NOIR );
    }

    @Test
    void getCoordonne() {
        // GIVEN
        Coordonnee c = new Coordonnee(1,1);
        Piece roi = new Roi(Couleur.NOIR, c );
        // THEN
        assertEquals(roi.getCoordonnee() , c );
    }
}