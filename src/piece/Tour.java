package piece;

import echiquier.Coordonnee;
import echiquier.Echiquier;
import echiquier.IPiece;

import java.util.ArrayList;
import java.util.List;

import static echiquier.Coordonnee.MINIMUM;
import static echiquier.Echiquier.HAUTEUR;
import static echiquier.Echiquier.LARGEUR;

/**
 * Modélise une tour dans le jeu de l'échiquier.
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
        assert (type == TypePiece.TOUR);
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
    public List<Coordonnee> listeDeplacement(Echiquier echiquier) {
        List<Coordonnee> listeDeplacement = new ArrayList<>();

        for (Direction direction : Direction.values())
            deplacementParDirection(getCoordonnee(), echiquier, listeDeplacement, direction);

        return listeDeplacement;
    }

//    /**
//     * Permet de connaître toutes les possibilités pour le déplacement de la Tour dans une direction donnée
//     * @param position la coordonnée actuelle de la pièce Tour
//     * @param echequier l'échéquier sur laquelle la Tour se trouve
//     * @param listeDeplacement la liste des possibilités pour le déplacement de la pièce Tour
//     * @param direction la direction du déplacement de la Tour
//     */
//    private void deplacementParDirection(Coordonnee position, Echiquier echequier,
//                                         List<Coordonnee> listeDeplacement, Direction direction) {
//        boolean indexOutOfBounds = false;
//        int variationX = variationX(position.getX(), direction);
//        int variationY = variationY(position.getY(), direction);
//
//        Coordonnee destination = new Coordonnee(variationX, variationY);
//
//        while (!indexOutOfBounds){
//            indexOutOfBounds = aide(destination, echequier, listeDeplacement, direction, variationX, variationY);
//        }
//
//    }
//
//    private boolean aide(Coordonnee destination, Echiquier echequier, List<Coordonnee> listeDeplacement,
//                         Direction direction, int variationX, int variationY){
//        while (destination.isCoordonneeExistante()){
//            if (variationX == this.getCoordonnee().getX() && variationY == this.getCoordonnee().getY())
//                return true;
//            while (echequier.getPiece(new Coordonnee(variationX, variationY)) == null){
//                listeDeplacement.add(destination);
//
//                variationX = variationX(variationX, direction);
//                variationY = variationY(variationY, direction);
//                if (listeDeplacement.get(listeDeplacement.size() - 1).getX() == variationX &&
//                        listeDeplacement.get(listeDeplacement.size() - 1).getY() == variationY)
//                    return true;
//
//                if (variationX >= MINIMUM && variationX < LARGEUR && variationY >= MINIMUM && variationY < HAUTEUR)
//                    destination = new Coordonnee(variationX, variationY);
//                else
//                    return true;
//            }
//            return true;
//        }
//        return false;
//    }

    private void deplacementParDirection(Coordonnee position, Echiquier echiquier,
                                         List<Coordonnee> listeDeplacement, Direction direction) {
        int varX = variationX(position.getX(), direction);
        int varY = variationY(position.getY(), direction);

        Coordonnee destination = new Coordonnee(varX, varY);
        boolean aDoublon = false;

        while (destination.isCoordonneeExistante() && echiquier.getPiece(destination) == null && !aDoublon) {
            listeDeplacement.add(destination);

            varX = variationX(varX, direction);
            varY = variationY(varY, direction);
            if (listeDeplacement.get(listeDeplacement.size() - 1 ).getX() == varX &&
                    listeDeplacement.get(listeDeplacement.size() - 1).getY() == varY)
                aDoublon = true;

            destination = new Coordonnee(varX, varY);
        }

        if (destination.isCoordonneeExistante()){
            if (!echiquier.estVide(destination.getX(), destination.getY()))
                if (echiquier.getPiece(destination).getCouleur() != this.getCouleur())
                    listeDeplacement.add(destination);
        }
    }
    /**
     * Retourne les modifications des coordonnées (en X) de la pièce Tour pour une direction donnée
     * @param variationX la coordonnée en X de la pièce Tour
     * @param direction la direction à laquelle cela pouvant modifier les coordonnées (en X) de la Tour
     * @return la coordonnée en X de la pièce par défaut sinon
     * Moins un si la direction va vers l'OUEST ET Plus un si la direction va vers l'EST.
     */
    private int variationX(int variationX, Direction direction) {
        if ((variationX == MINIMUM && direction != Direction.EST) || (variationX == HAUTEUR -1 && direction != Direction.OUEST))
            return variationX;
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
        if ((variationY == MINIMUM && direction != Direction.SUD) || (variationY == HAUTEUR - 1 && direction != Direction.NORD))
            return variationY;
        switch (direction) {
            case NORD  : return --variationY;
            case SUD   : return ++variationY;
            default    : return variationY;
        }
    }
}
