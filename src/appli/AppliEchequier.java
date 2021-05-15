package appli;

import echequier.*;
import piece.Coordonnee;
import piece.Couleur;
import piece.Roi;

/**
 * Modélise l'application lancant une
 * partie entre deux joueurs.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class AppliEchequier {

    public static void main(String[] args) {
        IPiece r1 = new Roi(Couleur.BLANC, new Coordonnee(1,1));
        Echequier echequier = new Echequier();
        echequier.ajoutPiece(r1, r1.getCoordonnee());
        System.out.println(echequier);

        System.out.println(echequier.getListeDeplacement());

        echequier.getPiece(r1.getCoordonnee()).deplacer(new Coordonnee(1,2),echequier);
        System.out.println(echequier);

        System.out.println(echequier.getListeDeplacement());

        echequier.getPiece(r1.getCoordonnee()).deplacer(new Coordonnee(2,2),echequier);
        System.out.println(echequier);

        System.out.println(echequier.getListeDeplacement());

        echequier.getPiece(r1.getCoordonnee()).deplacer(new Coordonnee(1,2),echequier);
        System.out.println(echequier);

        System.out.println(echequier.getListeDeplacement());
    }

}
