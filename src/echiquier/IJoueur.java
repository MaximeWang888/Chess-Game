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

    void jouer(Echiquier echiquier);

    String coupJouer(Echiquier echiquier);
}
