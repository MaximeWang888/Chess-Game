package piece;

import echiquier.Coordonnee;
import echiquier.IPiece;

import java.util.ArrayList;
import java.util.List;

/**
 * Modélise une tour dans le jeu de l'échéquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class Tour extends Piece{

    /**
     * Constructeur à partir d'une couleur et d'une coordonnée
     * @param couleur la couleur de la pièce
     * @param coord  la coordonnée de la pièce
     */
    public Tour(TypePiece type, Couleur couleur, Coordonnee coord) {
        super(type, couleur, coord);
    }

    /**
     * Permet d'avoir une représentation de la Tour sous la forme d'une chaîne de caractères.
     * @return 'T' si c'est une pièce blanche sinon 't' car c'est une piece noire.
     */
    @Override
    public String toString() {
        if (getCouleur() == Couleur.BLANC)
            return "T";
        return "t";
    }

    /**
     * Retourne une liste des possibilités pour le déplacement de la pièce Tour
     * @param echiquier l'échequier sur lequel la Tour se trouve
     * @return une liste des possibilités de déplacements de la Tour
     */
    @Override
    public List<Coordonnee> listeDeplacement(IPiece[][] echiquier) {
        List<Coordonnee> listeDeplacement = new ArrayList<>();

        for (Direction direction : Direction.values())
            deplacementParDirection(getCoordonnee(), echiquier, listeDeplacement, direction);

        return listeDeplacement;
    }

    /**
     * Permet de connaître toutes les possibilités pour le déplacement de la Tour dans une direction donnée
     * @param position la coordonnée actuelle de la pièce Tour
     * @param echequier l'échéquier sur laquelle la Tour se trouve
     * @param listeDeplacement la liste des possibilités pour le déplacement de la pièce Tour
     * @param direction la direction du déplacement de la Tour
     */
    private void deplacementParDirection(Coordonnee position, IPiece[][] echequier,
                                         List<Coordonnee> listeDeplacement, Direction direction) {
        int variationX = variationX(position.getX(), direction);
        int variationY = variationY(position.getY(), direction);

        Coordonnee destination = new Coordonnee(variationX, variationY);

        while (isCoordonneesExistent(destination) && echequier[variationX][variationY] == null) {
            listeDeplacement.add(destination);

            variationX = variationX(variationX, direction);
            variationY = variationY(variationY, direction);

            destination = new Coordonnee(variationX, variationY);
        }

        if (isCoordonneesExistent(destination) && echequier[variationX][variationY].getCouleur() != this.getCouleur())
            listeDeplacement.add(destination);
    }

    /**
     * Retourne les modifications des coordonnées (en X) de la pièce Tour pour une direction donnée
     * @param variationX la coordonnée en X de la pièce Tour
     * @param direction la direction à laquelle cela pouvant modifier les coordonnées (en X) de la Tour
     * @return la coordonnée en X de la pièce par défaut sinon
     * Moins un si la direction va vers l'OUEST ET Plus un si la direction va vers l'EST.
     */
    private int variationX(int variationX, Direction direction) {
        switch (direction) {
            case OUEST : return --variationX;
            case EST   : return ++variationX;
            default    : return variationX;
        }
    }

    /**
     * Retourne les modifications des coordonnées (en Y) de la pièce Tour pour une direction donnée
     * @param variationY la coordonnée en Y de la pièce Tour
     * @param direction la direction à laquelle cela pouvant modifier les coordonnées (en Y) de la Tour
     * @return la coordonnée en Y de la pièce par défaut sinon
     * Moins un si la direction va vers le NORD ET Plus un si la direction va vers le SUD.
     */
    private int variationY(int variationY, Direction direction) {
        switch (direction) {
            case NORD  : return --variationY;
            case SUD   : return ++variationY;
            default    : return variationY;
        }
    }
}
