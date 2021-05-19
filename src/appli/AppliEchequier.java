package appli;

import echiquier.Coordonnee;
import echiquier.Echiquier;
import echiquier.IPiece;

import fabrique.FabriquePiece;
import piece.Couleur;
import piece.TypePiece;

import java.util.Scanner;

/**
 * Modélise l'application lancant une
 * partie entre deux joueurs.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class AppliEchequier {
    /** Déclare un objet et initialise avec un objet d'entrée standard prédéfini */
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        FabriquePiece fp = new FabriquePiece();

        IPiece r1 = fp.creationPiece(TypePiece.ROI, Couleur.BLANC, new Coordonnee(1,1));
        IPiece t1 = fp.creationPiece(TypePiece.TOUR, Couleur.NOIR, new Coordonnee(0,0));
        IPiece t2 = fp.creationPiece(TypePiece.TOUR, Couleur.NOIR, new Coordonnee(3,1));
        Echiquier echiquier = new Echiquier();

        System.out.print("> ");
        String coordDeplacement = sc.nextLine();
        Coordonnee coord = new Coordonnee();
        coord = coord.conversionEnCoord(coordDeplacement);

        echiquier.ajoutPiece(r1, r1.getCoordonnee());
        System.out.println(echiquier);
        echiquier.deplacer(r1, new Coordonnee(2,1));
        System.out.println(echiquier);
        System.out.println(echiquier.getListeDeplacement());
        System.out.println("1111111111111111111111111111111111111111111111111111111111");

        echiquier.ajoutPiece(t1, t1.getCoordonnee());
        System.out.println(echiquier);
        String s = echiquier.getListeDeplacement();
        System.out.println(s);
        System.out.println("2222222222222222222222222222222222222222222222222222222222222222");

        System.out.println(coord);
        echiquier.deplacer(t1, coord);
        // echiquier.getPiece(t1.getCoordonnee()).deplacer(new Coordonnee(5,0), echiquier);
        System.out.println(echiquier);
        System.out.println(echiquier.getListeDeplacement());
        System.out.println("333333333333333333333333333333333333333333333333333333333333333");

        echiquier.ajoutPiece(t2, t2.getCoordonnee());

        echiquier.deplacer(t1, new Coordonnee(5,1));
//        echiquier.getPiece(t1.getCoordonnee()).deplacer(new Coordonnee(5,1), echiquier);
        System.out.println(echiquier);
        String s1 = echiquier.getListeDeplacement();
        System.out.println(s1);
        System.out.println("4444444444444444444444444444444444444444444444444444444444444444");
        echiquier.enleverPieceDuPlateau(t1);
        echiquier.ajoutPiece(t1, new Coordonnee(0,1));

        echiquier.deplacer(r1, new Coordonnee(2,1));
        System.out.println(echiquier);
        System.out.println(echiquier.getListeDeplacement());
    }


}
