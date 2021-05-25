package joueur;

import echiquier.Coordonnee;
import echiquier.Echiquier;
import echiquier.IPiece;
import echiquier.Couleur;

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
    public String jouer(Echiquier echiquier, boolean attentionRoiPresqueEnEchec){
        Coordonnee origine = new Coordonnee();
        Coordonnee destination = new Coordonnee();
        boolean depPossible = false;
        IPiece roi = echiquier.trouverRoi(this.getCouleur());

        System.out.print("> ");
        String deplacement = scanner.nextLine();

        while (attentionRoiPresqueEnEchec){
            if (abandonnerOuProposerNulle(deplacement) != null)
                return deplacement;
            else {
                deplacement = saisieValide(deplacement);
                origine = origine.conversionEnCoord(deplacement.substring(0,2));
                destination = destination.conversionEnCoord(deplacement.substring(2,4));
                if (!echiquier.estVide(origine.getX(), origine.getY())){

                    boolean piecePeutSeDeplacer = echiquier.getPiece(origine).
                            isDeplacementPossible(echiquier, destination, this.getCouleur(), echiquier.getPiece(origine).
                                    listeDeplacement(echiquier));
                    if (piecePeutSeDeplacer){
                        if (isPieceDestSeSacrifiantForRoi(origine,destination, roi, echiquier))
                            attentionRoiPresqueEnEchec = false;
                        else
                            deplacement = getStringRoiEnDanger();
                    }else
                        deplacement = getStringCoupIllegal();
                }
            }
        }

        while (!depPossible) {
            if (abandonnerOuProposerNulle(deplacement) != null)
                return deplacement;
            else {
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
        }
        System.out.println("Le joueur " + this.getCouleur() + " a joué : " + deplacement);
        return deplacement;
    }

    private String abandonnerOuProposerNulle(String deplacement) {
        if (deplacement.equals("abandonner") || deplacement.equals("proposerNulle")){
            System.out.println("Vous avez choisi '" + deplacement + "' :(");
            return deplacement;
        }
        return null;
    }

    /**
     * Permet de prévenir sous un format textuel que le coup joué est un coup illegal
     * @return le nouveau deplacement de la piece du joueur
     */
    private String getStringCoupIllegal() {
        String deplacement;
        System.out.println("Coup Illégal veuillez rejouez :) \n");
        System.out.print("> ");
        deplacement = scanner.nextLine();
        return deplacement;
    }

    /**
     * Permet de prévenir sous un format textuel que le roi est en danger
     * @return le nouveau deplacement de la piece du joueur
     */
    private String getStringRoiEnDanger() {
        String deplacement;
        System.out.println("ton roi il va se faire bouffer fait qqlch !!!");
        System.out.print("> ");
        deplacement = scanner.nextLine();
        return deplacement;
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
