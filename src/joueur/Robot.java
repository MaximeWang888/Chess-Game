package joueur;

import echiquier.Coordonnee;
import echiquier.Echiquier;
import piece.Couleur;

import java.util.Collections;
import java.util.List;

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
    public String coupJouer(Echiquier echiquier, boolean attentionRoiPresqueEnEchec) {
        List<Coordonnee> listePiece = echiquier.getListePiece(this);
        Collections.shuffle(listePiece);
        Coordonnee origine = listePiece.get(0);

        List<Coordonnee> listeDestination = echiquier.getListeDestination(origine, this);
        Collections.shuffle(listeDestination);
        Coordonnee destination = listeDestination.get(0);

        String origineString = origine.conversionEnString();
        String destinationString = destination.conversionEnString();

        echiquier.deplacer(echiquier.getPiece(origine), destination);

        return origineString + destinationString;
    }
}
