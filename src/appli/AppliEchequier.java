package appli;

import Echequier.*;
import piece.Coordonnee;
import piece.Couleur;
import piece.*;
import piece.Roi;

/**
 * Modélise l'application lancant une
 * partie entre deux joueurs.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class AppliEchequier {

    public static void main(String[] args) {
        Piece r1 = new Roi(Couleur.BLANC, new Coordonnee(1,1));
        Echequier echequier = new Echequier();
        echequier.ajoutPiece(r1, r1.getCoordonnee());
        System.out.println(echequier);

        echequier.getPiece(new Coordonnee(1,1)).deplacer(new Coordonnee(1,2),echequier);
        System.out.println(echequier);
    }

}
