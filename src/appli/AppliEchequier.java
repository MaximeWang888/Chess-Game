package appli;

import echequier.*;
import piece.*;

/**
 * Modélise l'application lancant une
 * partie entre deux joueurs.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class AppliEchequier {

    public static void main(String[] args) {
        IPiece r1 = new Roi(TypePiece.ROI, Couleur.BLANC, new Coordonnee(1,1));
        Echequier echequier = new Echequier();
        echequier.ajoutPiece(r1, r1.getCoordonnee());
//        System.out.println(echequier);
//
//        System.out.println(echequier.getListeDeplacement());

//        echequier.getPiece(r1.getCoordonnee()).deplacer(new Coordonnee(1,2),echequier);
//        System.out.println(echequier);
//
//        System.out.println(echequier.getListeDeplacement());
//
//        echequier.getPiece(r1.getCoordonnee()).deplacer(new Coordonnee(2,2),echequier);
//        System.out.println(echequier);
//
//        System.out.println(echequier.getListeDeplacement());
//
//        echequier.getPiece(r1.getCoordonnee()).deplacer(new Coordonnee(1,2),echequier);
//        System.out.println(echequier);
//
//        System.out.println(echequier.getListeDeplacement());

        IPiece t1 = new Tour(TypePiece.TOUR, Couleur.NOIR, new Coordonnee(0,0));
        echequier.ajoutPiece(t1, t1.getCoordonnee());
        System.out.println(echequier);
        String d = echequier.getListeDeplacement();
        System.out.println(d);
        echequier.getPiece(t1.getCoordonnee()).deplacer(new Coordonnee(4,0), echequier);
        System.out.println(echequier);
        System.out.println(echequier.getListeDeplacement());
    }

}
