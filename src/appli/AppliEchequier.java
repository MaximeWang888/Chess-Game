package appli;

import echiquier.Coordonnee;
import echiquier.Echiquier;
import echiquier.IJoueur;
import echiquier.IPiece;
import fabrique.FabriqueJoueur;
import fabrique.FabriquePiece;
import joueur.TypeJoueur;
import piece.Couleur;
import piece.TypePiece;

/**
 * Modélise l'application lancant une
 * partie entre deux joueurs.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class AppliEchequier {

    private static boolean estAuBlancDeJouer = true;

    public static void main(String[] args) {

        Echiquier echiquier = new Echiquier();
        echiquier = initialiserPieces(echiquier);
        FabriqueJoueur fj = new FabriqueJoueur();

        IJoueur joueurPBlanc = fj.creationJoueur(TypeJoueur.HUMAIN, Couleur.BLANC);
        IJoueur joueurPNoir = fj.creationJoueur(TypeJoueur.HUMAIN, Couleur.NOIR);

        IJoueur joueur = changerDeJoueur(joueurPBlanc, joueurPNoir, estAuBlancDeJouer);

        while (!echiquier.estPartieTerminee(joueur)){
            echiquier.jouer(joueur);
            joueur = changerDeJoueur(joueurPBlanc, joueurPNoir, estAuBlancDeJouer);
        }
    }

    private static IJoueur changerDeJoueur(IJoueur joueurBlanc , IJoueur joueurNoir, Boolean estAuBlancDeJouer) {
        if (estAuBlancDeJouer) {
            AppliEchequier.estAuBlancDeJouer = false;
            return joueurBlanc;
        }else {
            AppliEchequier.estAuBlancDeJouer = true;
            return joueurNoir;
        }
    }

    private static Echiquier initialiserPieces(Echiquier echiquier){
        FabriquePiece fp = new FabriquePiece();
        IPiece roiB = fp.creationPiece(TypePiece.ROI, Couleur.BLANC, new Coordonnee(5,5));
        IPiece tourB = fp.creationPiece(TypePiece.TOUR, Couleur.BLANC, new Coordonnee(5,2));

        IPiece roiN = fp.creationPiece(TypePiece.ROI, Couleur.NOIR, new Coordonnee(5,7));
        IPiece tourN = fp.creationPiece(TypePiece.TOUR, Couleur.NOIR, new Coordonnee(0,3));
        IPiece tourN2 = fp.creationPiece(TypePiece.TOUR, Couleur.NOIR, new Coordonnee(1,6));
        IPiece tourN3 = fp.creationPiece(TypePiece.TOUR, Couleur.NOIR, new Coordonnee(7,1));

        echiquier.ajoutPiece(roiB, roiB.getCoordonnee());
        echiquier.ajoutPiece(tourB, tourB.getCoordonnee());
        echiquier.ajoutPiece(roiN, roiN.getCoordonnee());
        echiquier.ajoutPiece(tourN, tourN.getCoordonnee());
        echiquier.ajoutPiece(tourN2, tourN2.getCoordonnee());
        echiquier.ajoutPiece(tourN3, tourN3.getCoordonnee());


        return echiquier;
    }
}
