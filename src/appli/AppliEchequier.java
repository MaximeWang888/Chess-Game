package appli;

import echiquier.Echiquier;
import echiquier.IJoueur;
import fabrique.FabriqueJoueur;
import joueur.TypeJoueur;
import piece.Couleur;

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
        FabriqueJoueur fj = new FabriqueJoueur();

        IJoueur joueurPBlanc = fj.creationJoueur(TypeJoueur.HUMAIN, Couleur.BLANC);
        IJoueur joueurPNoir = fj.creationJoueur(TypeJoueur.HUMAIN, Couleur.NOIR);

        IJoueur joueur = changerDeJoueur(joueurPBlanc, joueurPNoir, estAuBlancDeJouer);

        while (!echiquier.estPartieTerminee(joueur)){
            System.out.println(echiquier);
            System.out.println("Au tour des " + joueur.getCouleur() + " de jouer");
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
}
