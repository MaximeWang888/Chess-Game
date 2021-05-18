package joueur;

import echiquier.IJoueur;

/**
 * Modélise un joueur dans le jeu d'Echecs.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public abstract class Joueur implements IJoueur {

    /** Le type du joueur */
    private TypeJoueur type;

    public Joueur(TypeJoueur type) {
        this.type = type;
    }

    /**
     * Permet d'abandonner la partie.
     */
    public void abandonner(){

    }

    /**
     * Permet de proposer nulle à la partie encours.
     */
    public void proposerNulle(){

    }
}
