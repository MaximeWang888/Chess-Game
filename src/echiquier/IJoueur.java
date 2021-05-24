package echiquier;

import piece.Couleur;

/**
 * Modélise un joueur du jeu d'échec
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public interface IJoueur {

    /**
     * Permet de connaître la couleur du joueur
     * @return la couleur du joueur
     */
    Couleur getCouleur();

    /**
     * Permet de jouer un coup
     * @param echiquier
     * @param attentionRoiPresqueEnEchec
     * @return
     */
    String jouer(Echiquier echiquier, boolean attentionRoiPresqueEnEchec);

    /**
     * Le coup jouait par le joueur
     * @param echiquier
     * @param attentionRoiPresqueEnEchec
     * @return
     */
    String coupJouer(Echiquier echiquier, boolean attentionRoiPresqueEnEchec);


}
