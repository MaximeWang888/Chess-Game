package fabrique;

import echiquier.Coordonnee;
import echiquier.IPiece;

import piece.Couleur;
import piece.Roi;
import piece.Tour;
import piece.TypePiece;

/**
 * Permet de fabriquer une pièce dans le jeu de l'échéquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class FabriquePiece {

    public IPiece creationPiece(TypePiece type, Couleur couleur, Coordonnee coordonnee) {
        switch (type){
            case TOUR : return new Tour(type, couleur, coordonnee);
            case ROI : return new Roi(type, couleur, coordonnee);
            default   : return null;
        }
    }
}
