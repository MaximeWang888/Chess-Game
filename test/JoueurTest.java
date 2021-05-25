import echiquier.*;
import fabrique.FabriqueJoueur;
import joueur.Humain;
import joueur.Joueur;
import joueur.Robot;
import joueur.TypeJoueur;
import org.junit.Test;
import piece.Roi;
import piece.Tour;

import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Tests unitaires sur la classe Echiquier dans le jeu de l'échiquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class JoueurTest {

    @Test
    public void testGetCouleur(){
        // GIVEN
        IJoueur j = new Robot(Couleur.BLANC);
        // THEN
        assertEquals(j.getCouleur(), Couleur.BLANC);
    }

    @Test
    public void testIsPieceDestSeSacrifiantForRoi(){
        Echiquier e = new Echiquier();
        FabriqueJoueur fj = new FabriqueJoueur();
        IJoueur j = fj.creationJoueur(TypeJoueur.HUMAIN, Couleur.BLANC);
        IJoueur j2 = fj.creationJoueur(TypeJoueur.ROBOT, Couleur.BLANC);
        Joueur joueur = new Humain(Couleur.BLANC, new Scanner(System.in));
        IPiece roi = new Roi(Couleur.BLANC, new Coordonnee(3,3));
        IPiece roi2 = new Roi(Couleur.NOIR, new Coordonnee(6,6));
        IPiece tour = new Tour(Couleur.NOIR, new Coordonnee(3,6));

        e.ajoutPiece(roi, roi.getCoordonnee());
        e.ajoutPiece(roi2, roi2.getCoordonnee());
        e.ajoutPiece(tour, tour.getCoordonnee());

        assertEquals(joueur.isPieceDestSeSacrifiantForRoi(roi.getCoordonnee(), tour.getCoordonnee(), roi2, e), true);
    }
}