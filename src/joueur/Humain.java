package joueur;

import echiquier.Coordonnee;
import echiquier.Echiquier;
import echiquier.IPiece;
import piece.Couleur;
import piece.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Modélise un humain dans le jeu de l'échiquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class Humain extends Joueur {

    private Scanner scanner;

    public Humain (Couleur couleur, Scanner scanner)
    {
        super(couleur);
        this.scanner = scanner;
    }

    @Override
    public String coupJouer(Echiquier echiquier, boolean attentionRoiPresqueEnEchec){
        System.out.print("> ");
        String  deplacement = scanner.nextLine();
        Coordonnee origine = new Coordonnee();
        Coordonnee destination = new Coordonnee();
        boolean depPossible = false;
        IPiece roi = Piece.trouverRoi(echiquier, this.getCouleur());

        deplacement = saisieValide(deplacement);

        origine = origine.conversionEnCoord(deplacement.substring(0,2));
        destination = destination.conversionEnCoord(deplacement.substring(2,4));

        while (attentionRoiPresqueEnEchec){
            origine = origine.conversionEnCoord(deplacement.substring(0,2));
            destination = destination.conversionEnCoord(deplacement.substring(2,4));
            List<Coordonnee> l = echiquier.getPiece(origine).listeDeplacement(echiquier);
            boolean p = isDestSeSacrifiantForRoi(destination, roi, echiquier);
            boolean piècePeutSeDéplacer = echiquier.getPiece(origine).
                    isDeplacementPossible(echiquier, destination, this.getCouleur(), echiquier.getPiece(origine).
                            listeDeplacement(echiquier));
            if ((piècePeutSeDéplacer && isDestSeSacrifiantForRoi(destination, roi, echiquier))){
                System.out.println("ton roi il va se faire bouffer fait qqlch !!!");
                System.out.print("> ");
                deplacement = scanner.nextLine();
            }else if ((origine.getX() != roi.getCoordonnee().getX() && origine.getY() != roi.getCoordonnee().getY())){
                System.out.println("ton roi il va se faire bouffer fait qqlch !!!");
                System.out.print("> ");
                deplacement = scanner.nextLine();
            } else
                attentionRoiPresqueEnEchec = false;
        }

//        (origine.getX() != roi.getCoordonnee().getX() && origine.getY() != roi.getCoordonnee().getY()) ||
        while (!depPossible) {
            deplacement = saisieValide(deplacement);
            origine = origine.conversionEnCoord(deplacement.substring(0,2));
            destination = destination.conversionEnCoord(deplacement.substring(2,4));

            if (!echiquier.estVide(origine.getX(), origine.getY()) && echiquier.getPiece(origine).getCouleur() == this.getCouleur()) {
                List<Coordonnee> listttt = echiquier.getPiece(origine).listeDeplacement(echiquier);
                depPossible = echiquier.getPiece(origine).isDeplacementPossible(echiquier, destination, this.getCouleur(), listttt);
            }

            if (!depPossible) {
                System.out.println("Coup Illégal veuillez rejouez :) \n");
                System.out.print("> ");
                deplacement = scanner.nextLine();
            }else{
                echiquier.deplacer(echiquier.getPiece(origine), destination);
            }
        }

        return deplacement;
    }

    private boolean isDestSeSacrifiantForRoi(Coordonnee destination, IPiece roi, Echiquier echiquier) {
        List<Coordonnee> listCoordEnEchec = getListCoordonneeEchec(roi, echiquier);

        for (Coordonnee coordEnEchec: listCoordEnEchec) {
            if (coordEnEchec.getX() == destination.getX() && coordEnEchec.getY() == destination.getY())
                return true;
        }
        return false;
    }

    private List<Coordonnee> getListCoordonneeEchec(IPiece roi, Echiquier echiquier) {
        List<Coordonnee> listCoordonneeEchec = new ArrayList<>();
        List<Coordonnee> listDeplacementDuRoi = echiquier.getPiece(roi.getCoordonnee()).listeDeplacement(echiquier);
        for (Coordonnee deplacementDuRoi: listDeplacementDuRoi) {
            if (!echiquier.getPiece(roi.getCoordonnee()).isDeplacementPossible(echiquier, deplacementDuRoi, this.getCouleur(), listDeplacementDuRoi))
                listCoordonneeEchec.add(deplacementDuRoi);
        }
        return listCoordonneeEchec;
    }

    private String saisieValide(String deplacement) {
        while (!saisieCorrect(deplacement)){
            System.out.println("Coup Illégal veuillez rejouez :) \n");
            System.out.print("> ");
            deplacement = scanner.nextLine();
        }
        return deplacement;
    }

    private boolean saisieCorrect(String deplacement) {
        return deplacement.length() == 4 && Character.isDigit(deplacement.charAt(1)) && Character.isDigit(deplacement.charAt(3)) &&
                Character.isLetter(deplacement.charAt(0)) && Character.isLetter(deplacement.charAt(2));
    }
}
