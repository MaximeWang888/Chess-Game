package fabrique;

import echiquier.IJoueur;

import joueur.Humain;
import joueur.Robot;
import joueur.TypeJoueur;
import piece.Couleur;

import java.util.Scanner;

/**
 * Permet de fabriquer un joueur dans le jeu de l'échéquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class FabriqueJoueur {

    public IJoueur creationJoueur(TypeJoueur type, Couleur couleur){
        switch (type) {
            case HUMAIN : return new Humain(couleur, new Scanner(System.in));
            case ROBOT: return new Robot(couleur);
            default     : return null;
        }
    }
}
