package joueur;

import echiquier.Coordonnee;
import echiquier.Echiquier;
import echiquier.Couleur;

import java.util.*;

/**
 * Modélise un robot dans le jeu de l'échiquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class Robot extends Joueur {

    public Robot(Couleur couleur) {
        super(couleur);
    }

    @Override
    public String jouer(Echiquier echiquier, boolean attentionRoiPresqueEnEchec) {
        Random rand = new Random();
        List<Coordonnee> listePiece = echiquier.getListePiece(this);
        Coordonnee [][]pieceDeplacementSur = new Coordonnee[20][2];
        int i = 0;
        if (!attentionRoiPresqueEnEchec){
            Collections.shuffle(listePiece);
            Coordonnee origine = listePiece.get(0);
            List<Coordonnee> listeDestination = echiquier.getListeDestination(origine, this);
            Collections.shuffle(listeDestination);
            Coordonnee destination = listeDestination.get(0);
            String origineString = origine.conversionEnString();
            String destinationString = destination.conversionEnString();

            echiquier.deplacer(echiquier.getPiece(origine), destination);
            System.out.println("Le joueur " + this.getCouleur() + " a joué : " + origineString + destinationString);
            return origineString + destinationString;
        } else{
            for (Coordonnee coordPiece: listePiece) {
                List <Coordonnee> deplacementPiece = echiquier.getPiece(coordPiece).listeDeplacement(echiquier);
                for (Coordonnee desti : deplacementPiece) {
                    if (isPieceDestSeSacrifiantForRoi(coordPiece, desti, echiquier.trouverRoi(this.getCouleur()), echiquier)){
                        pieceDeplacementSur[i][0] = coordPiece;
                        pieceDeplacementSur[i][1] = desti;
                        i += 1;
                    }
                }
            }
        }
        String origineString = "";
        String destinationString = "";
        if (i > 0) {
            int rand_int1 = rand.nextInt(i);
            Coordonnee origine = pieceDeplacementSur[rand_int1][0];
            Coordonnee destination = pieceDeplacementSur[rand_int1][1];

            origineString = origine.conversionEnString();
            destinationString = destination.conversionEnString();

            echiquier.deplacer(echiquier.getPiece(origine), destination);
        }
        System.out.println("Le joueur " + this.getCouleur() + " a joué : " + origineString + destinationString);
        return origineString + destinationString;
    }

}
