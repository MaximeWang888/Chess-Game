package fabrique;

import echiquier.IJoueur;

import joueur.Humain;
import joueur.Robot;
import joueur.TypeJoueur;

/**
 * Permet de fabriquer un joueur dans le jeu de l'échéquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class FabriqueJoueur {

    public IJoueur creationJoueur(TypeJoueur type){
        switch (type) {
            case HUMAIN : return new Humain(type);
            case ROBOT: return new Robot(type);
            default     : return null;
        }
    }
}
