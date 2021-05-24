package joueur;

import echiquier.Coordonnee;
import echiquier.Echiquier;
import echiquier.IPiece;
import piece.Couleur;

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
        Coordonnee origine = new Coordonnee();
        Coordonnee destination = new Coordonnee();
        boolean depPossible = false;
        IPiece roi = echiquier.trouverRoi(this.getCouleur());

        System.out.print("> ");
        String deplacement = scanner.nextLine();

        if (deplacement.equals("abandonner") || deplacement.equals("proposerNulle"))
            return deplacement;

        while (attentionRoiPresqueEnEchec){
            deplacement = saisieValide(deplacement);
            origine = origine.conversionEnCoord(deplacement.substring(0,2));
            destination = destination.conversionEnCoord(deplacement.substring(2,4));
            boolean piecePeutSeDéplacer = echiquier.getPiece(origine).
                    isDeplacementPossible(echiquier, destination, this.getCouleur(), echiquier.getPiece(origine).
                            listeDeplacement(echiquier));
            if (piecePeutSeDéplacer){
                if (isPieceDestSeSacrifiantForRoi(origine,destination, roi, echiquier))
                    attentionRoiPresqueEnEchec = false;
                else
                    deplacement = getStringRoiEnDanger();
            }else
                deplacement = getStringCoupIllegal();
        }

        while (!depPossible) {
            deplacement = saisieValide(deplacement);
            origine = origine.conversionEnCoord(deplacement.substring(0,2));
            destination = destination.conversionEnCoord(deplacement.substring(2,4));

            if (!echiquier.estVide(origine.getX(), origine.getY()) &&
                    echiquier.getPiece(origine).getCouleur() == this.getCouleur()) {
                depPossible = echiquier.getPiece(origine).isDeplacementPossible(echiquier, destination, this.getCouleur(),
                                echiquier.getPiece(origine).listeDeplacement(echiquier));
            }
            // Si le deplacement de la piece a la destination n'est pas possible alors re-saisir
            if (!depPossible) {
                deplacement = getStringCoupIllegal();
            }else{
                echiquier.deplacer(echiquier.getPiece(origine), destination);
            }
        }
        return deplacement;
    }

    private String getStringCoupIllegal() {
        String deplacement;
        System.out.println("Coup Illégal veuillez rejouez :) \n");
        System.out.print("> ");
        deplacement = scanner.nextLine();
        return deplacement;
    }

    /**
     * Permet de prévenir sous un format textuel que le roi est en danger
     * @return le deplacement
     */
    private String getStringRoiEnDanger() {
        String deplacement;
        System.out.println("ton roi il va se faire bouffer fait qqlch !!!");
        System.out.print("> ");
        deplacement = scanner.nextLine();
        return deplacement;
    }

    /**
     * Permet de savoir si la destination de la piece peut se sacrifier pour le roi
     * @param destination la destination de la piece
     * @param roi le roi protégé
     * @param echiquier l'echiquier sur lequel le deplacement de la piece vers la destination aura lieu
     * @return TRUE si la destination de la piece peut se sacrifier pour le roi, FALSE dans le cas contraire
     */
    private boolean isPieceDestSeSacrifiantForRoi(Coordonnee origine, Coordonnee destination, IPiece roi, Echiquier echiquier) {
        echiquier.getPiece(origine).deplacer(destination, echiquier);

        if (!echiquier.craintEchec(roi)){
            echiquier.getPiece(destination).deplacer(origine, echiquier);
            return true;
        }
        echiquier.getPiece(destination).deplacer(origine, echiquier);
        return false;
    }

    /**
     * Permet de savoir si la saisie du deplacement correspond au format attendu
     * @param deplacement la saisie du deplacement
     * @return TRUE si la saisie est valide, FALSE dans le cas contraire
     */
    private String saisieValide(String deplacement) {
        while (!(deplacement.length() == 4 && Character.isDigit(deplacement.charAt(1)) &&
                Character.isDigit(deplacement.charAt(3)) && Character.isLetter(deplacement.charAt(0)) &&
                Character.isLetter(deplacement.charAt(2)))){
            deplacement = getStringCoupIllegal();
        }
        return deplacement;
    }



}
