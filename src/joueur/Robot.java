package joueur;

import echiquier.Echiquier;
import piece.Couleur;

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
        return null;
    }
}
