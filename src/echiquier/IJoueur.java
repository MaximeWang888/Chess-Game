package echiquier;

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
     * Permet de jouer un coup au joueur, c'est-à-dire une piece du joueur déplacé vers une destination
     * @param echiquier l'echiquier sur lequel on joue
     * @param attentionRoiPresqueEnEchec le boolean permettant de savoir si le roi du joueur est
     *                                   déjà en position d'échec
     * @return le coup jouer par le joueur sous forme de string
     */
    String jouer(Echiquier echiquier, boolean attentionRoiPresqueEnEchec);

}
