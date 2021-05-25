package piece;

import echiquier.Coordonnee;
import echiquier.Couleur;
import echiquier.Echiquier;

import java.util.ArrayList;
import java.util.List;

/**
 * Modélise une tour dans le jeu de l'échiquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */

public class Roi extends Piece {

    /**
     * Constructeur à partir d'une couleur et d'une coordonnée
     * @param couleur la couleur du Roi
     * @param coord la coordonnée du Roi
     */
    public Roi(Couleur couleur, Coordonnee coord) {
        super(couleur, coord);
    }

    /**
     * Permet d'avoir une représentation du Roi sous la forme d'une chaîne de caractères.
     * @return 'R' si la piece est une pièce blanche sinon 'r' car c'est une piece noire.
     */
    @Override
    public String toString() {
        if(getCouleur() == Couleur.BLANC)
            return "R";
        return "r";
    }

    @Override
    public String getType() {
        return "ROI";
    }

    @Override
    public List<Coordonnee> listeDeplacement(Echiquier echiquier) {
        List<Coordonnee> listeDeplacement = new ArrayList<>();

        for (int varX = getCoordonnee().getX() - 1; varX <= getCoordonnee().getX() + 1; varX++) {
            for (int varY = getCoordonnee().getY() - 1; varY <= getCoordonnee().getY() + 1; varY++ ) {
                Coordonnee destination = new Coordonnee(varX, varY);

                if (destination.isCoordonneeExistante() && (echiquier.getPiece(destination) == null ||
                        echiquier.getPiece(destination).getCouleur() != this.getCouleur()))
                    listeDeplacement.add(destination);
            }
        }
        return listeDeplacement;
    }


}
