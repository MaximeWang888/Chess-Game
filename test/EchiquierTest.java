import static org.junit.Assert.*;

import echiquier.*;
import fabrique.FabriqueJoueur;
import fabrique.FabriquePiece;
import joueur.Robot;
import joueur.TypeJoueur;
import org.junit.Test;

import piece.Roi;
import piece.TypePiece;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests unitaires sur la classe Echiquier dans le jeu de l'échiquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class EchiquierTest {

    @Test
    public void testGetPlateau(){
        // GIVEN
        Echiquier e = new Echiquier();
        Echiquier e1 = new Echiquier();

        // THEN
        assertEquals(e.getPlateau(), e1.getPlateau());
    }

    @Test
    public void testEstVide() {
        // GIVEN
        Echiquier echiquier = new Echiquier();
        Coordonnee c1 = new Coordonnee(3,3);
        IPiece r1 = new Roi(Couleur.BLANC, c1);
        echiquier.ajoutPiece(r1, r1.getCoordonnee());

        // THEN
        assertTrue(echiquier.estVide(1,1));
        assertFalse(echiquier.estVide(3,3));
    }

    @Test
    public void testGetPiece() {
        // GIVEN
        Coordonnee c1 = new Coordonnee(1,2);
        Coordonnee c2 = new Coordonnee(2,2);
        IPiece r1 = new Roi(Couleur.BLANC, c1);
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
        echiquier.ajoutPiece(new Roi(Couleur.BLANC, new Coordonnee(3,0)),
                new Coordonnee(3,0));
        echiquier.ajoutPiece(new Roi(Couleur.NOIR, new Coordonnee(6,6)),
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

    @Test
    public void testDeplacer(){
        // GIVEN
        Echiquier e = new Echiquier();
        FabriquePiece fp = new FabriquePiece();
        IPiece p = fp.creationPiece(TypePiece.TOUR, Couleur.BLANC, new Coordonnee(1, 6));
        e.ajoutPiece(p, p.getCoordonnee());
        // WHEN
        Coordonnee destination = new Coordonnee(5,6);
        e.deplacer(p, destination);
        // THEN
        assertEquals(e.getPiece(destination),p);
    }

    @Test
    public void testGetListeDeplacement(){
        // GIVEN
        Echiquier e = new Echiquier();
        FabriquePiece fp = new FabriquePiece();
        IPiece p = fp.creationPiece(TypePiece.ROI, Couleur.BLANC, new Coordonnee(1, 6));
        // WHEN
        e.ajoutPiece(p, p.getCoordonnee());
        Echiquier e1 = new Echiquier();
        // THEN
        assertNotEquals(e.getListeDeplacement(new Robot(Couleur.BLANC)), e1.getListeDeplacement(new Robot(Couleur.NOIR)));
    }

    @Test
    public void testJouer(){
        // GIVEN
        Echiquier echiquier = new Echiquier();
        FabriquePiece fp = new FabriquePiece();
        FabriqueJoueur fj = new FabriqueJoueur();

        IJoueur joueur = fj.creationJoueur(TypeJoueur.HUMAIN, Couleur.BLANC);

        IPiece roiB = fp.creationPiece(TypePiece.ROI, Couleur.BLANC, new Coordonnee(5,5));
        IPiece tourB = fp.creationPiece(TypePiece.TOUR, Couleur.BLANC, new Coordonnee(1,6));

        echiquier.ajoutPiece(roiB, roiB.getCoordonnee());
        echiquier.ajoutPiece(tourB, tourB.getCoordonnee());

        IPiece roiN = fp.creationPiece(TypePiece.ROI, Couleur.NOIR, new Coordonnee(5,7));

        echiquier.ajoutPiece(roiN, roiN.getCoordonnee());
        // THEN
//        assertEquals(echiquier.jouer(joueur, false), "");
    }

    @Test
    public void testEstPartieTerminee(){
        // GIVEN
        Echiquier echiquier = new Echiquier();
        FabriquePiece fp = new FabriquePiece();
        FabriqueJoueur fj = new FabriqueJoueur();

        IJoueur joueur = fj.creationJoueur(TypeJoueur.HUMAIN, Couleur.BLANC);

        IPiece roiB = fp.creationPiece(TypePiece.ROI, Couleur.BLANC, new Coordonnee(2,5));
        IPiece tourB = fp.creationPiece(TypePiece.TOUR, Couleur.BLANC, new Coordonnee(2,4));

        echiquier.ajoutPiece(roiB, roiB.getCoordonnee());
        echiquier.ajoutPiece(tourB, tourB.getCoordonnee());

        IPiece roiN = fp.creationPiece(TypePiece.ROI, Couleur.NOIR, new Coordonnee(3,4));

        echiquier.ajoutPiece(roiN, roiN.getCoordonnee());
        // THEN
        assertEquals(echiquier.estPartieTerminee(joueur, "beuzfbzeu"), false);
        assertEquals(echiquier.estPartieTerminee(joueur, "abandonner"), true);
    }

    @Test
    public void testGetListePiece(){
        // GIVEN
        Echiquier echiquier = new Echiquier();
        FabriquePiece fp = new FabriquePiece();
        FabriqueJoueur fj = new FabriqueJoueur();
        IJoueur joueur = fj.creationJoueur(TypeJoueur.HUMAIN, Couleur.BLANC);
        IPiece roiB = fp.creationPiece(TypePiece.ROI, Couleur.BLANC, new Coordonnee(5,5));
        echiquier.ajoutPiece(roiB, roiB.getCoordonnee());
        List<Coordonnee> listPiece = new ArrayList<>();
        listPiece.add(new Coordonnee(5,5));
        // THEN
        assertEquals(echiquier.getListePiece(joueur).get(0).getX(), listPiece.get(0).getX());
        assertEquals(echiquier.getListePiece(joueur).get(0).getY(), listPiece.get(0).getY());

    }

    @Test
    public void testGetListeDestination(){
        // GIVEN
        Echiquier echiquier = new Echiquier();
        FabriquePiece fp = new FabriquePiece();
        FabriqueJoueur fj = new FabriqueJoueur();
        Coordonnee origine = new Coordonnee(5,5);
        IJoueur joueur = fj.creationJoueur(TypeJoueur.HUMAIN, Couleur.BLANC);
        IPiece roiB = fp.creationPiece(TypePiece.ROI, Couleur.BLANC, origine);
        echiquier.ajoutPiece(roiB, roiB.getCoordonnee());
        List<Coordonnee> listeDestination = new ArrayList<>();
        listeDestination.add(new Coordonnee(4,4));
        // THEN
        assertEquals(echiquier.getListeDestination(origine, joueur).get(0).getX(), listeDestination.get(0).getX());
        assertEquals(echiquier.getListeDestination(origine, joueur).get(0).getY(), listeDestination.get(0).getY());
    }

    @Test
    public void testTrouverRoi(){
        // GIVEN
        Echiquier echiquier = new Echiquier();
        Echiquier echiquier2 = new Echiquier();
        FabriquePiece fp = new FabriquePiece();
        FabriqueJoueur fj = new FabriqueJoueur();
        IJoueur joueur = fj.creationJoueur(TypeJoueur.HUMAIN, Couleur.BLANC);
        IPiece roiB = fp.creationPiece(TypePiece.ROI, Couleur.BLANC, new Coordonnee(5,5));
        echiquier.ajoutPiece(roiB, roiB.getCoordonnee());
        // THEN
        assertEquals(echiquier.trouverRoi(joueur.getCouleur()), roiB);
        assertEquals(echiquier2.trouverRoi(joueur.getCouleur()), null);
    }
}