import fabrique.FabriquePiece;
import org.junit.Test;
import static org.junit.Assert.*;

import echiquier.Coordonnee;
import echiquier.Echiquier;
import echiquier.IPiece;

import echiquier.Couleur;
import piece.Roi;
import piece.TypePiece;

/**
 * Tests unitaires sur la classe Piece dans le jeu de l'échiquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class PieceTest {

    @Test
    public void testGetType(){
        // GIVEN
        IPiece roi = new Roi(Couleur.BLANC, new Coordonnee(1,1));
        IPiece roi2 = new Roi(Couleur.NOIR, new Coordonnee(5,5));
        // THEN
        assertEquals(roi.getType(), roi2.getType());
    }

    @Test
    public void testSetCoord() {
        // GIVEN
        IPiece roi = new Roi(Couleur.NOIR,new Coordonnee(1,1));
        roi.setCoord(new Coordonnee(2,2));
        // THEN
        assertTrue(roi.getCoordonnee().getY() == 2 &&  roi.getCoordonnee().getX() ==2 );
    }

    @Test
    public void testDeplacer() {
        // GIVEN
        IPiece r1 = new Roi(Couleur.BLANC, new Coordonnee(1,1));
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
    public void testEstAllie() {
        // GIVEN
        IPiece roiMaxime = new Roi(Couleur.NOIR,new Coordonnee(1,1));
        IPiece roiFabien = new Roi(Couleur.NOIR,new Coordonnee(1,1));

        // THEN
        assertTrue(roiMaxime.estAllie(roiFabien));
    }

    @Test
    public void testGetCouleur() {
        // GIVEN
        IPiece roi = new Roi(Couleur.NOIR,new Coordonnee(1,1));
        // THEN
        assertTrue(roi.getCouleur() == Couleur.NOIR );
    }

    @Test
    public void testGetCoordonnee() {
        // GIVEN
        Coordonnee c = new Coordonnee(1,1);
        IPiece roi = new Roi(Couleur.NOIR, c);
        // THEN
        assertEquals(roi.getCoordonnee() , c );
    }

    @Test
    public void testIsDeplacementPossible() {
        // GIVEN
        Coordonnee c = new Coordonnee(5,5);
        Echiquier e = new Echiquier();
        IPiece roi = new Roi(Couleur.NOIR, c);
        // THEN
        assertEquals(roi.isDeplacementPossible(e, roi.getCoordonnee(), roi.getCouleur(), roi.listeDeplacement(e)) , true);
    }

    @Test
    public void testCraintEchec(){
        // GIVEN
        Echiquier echiquier = new Echiquier();
        FabriquePiece fp = new FabriquePiece();

        IPiece roiB = fp.creationPiece(TypePiece.ROI, Couleur.BLANC, new Coordonnee(5,5));
        IPiece tourB = fp.creationPiece(TypePiece.TOUR, Couleur.BLANC, new Coordonnee(2,6));
        IPiece tourB2 = fp.creationPiece(TypePiece.TOUR, Couleur.BLANC, new Coordonnee(1,6));
        IPiece tourB3 = fp.creationPiece(TypePiece.TOUR, Couleur.BLANC, new Coordonnee(3,6));

        echiquier.ajoutPiece(roiB, roiB.getCoordonnee());
        echiquier.ajoutPiece(tourB, tourB.getCoordonnee());
        echiquier.ajoutPiece(tourB2, tourB2.getCoordonnee());
        echiquier.ajoutPiece(tourB3, tourB3.getCoordonnee());

        IPiece roiN = fp.creationPiece(TypePiece.ROI, Couleur.NOIR, new Coordonnee(2,3));

        echiquier.ajoutPiece(roiN, roiN.getCoordonnee());

        // THEN
        assertEquals(roiN.craintEchec(echiquier), true);
    }
}