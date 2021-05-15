package pièces;

import java.util.ArrayList;

public class Tour extends Piece {

    @Override
    public char getNom() {
        if (getCouleur() == Couleur.BLANC)
            return 'T';
        else
            return 't';
    }

    public Tour (Couleur couleur)
    {
        super(couleur);
    }

    private enum Direction {NORD, EST, SUD, OUEST}

    @Override
    public ArrayList<Coordonnees> listeDeplacement(Coordonnees position, IPiece[][] echiquier) {
        ArrayList<Coordonnees> listeDeplacement = new ArrayList<>();

        for (Direction direction : Direction.values())
            deplacementParDirection(position, echiquier, listeDeplacement, direction);

        return listeDeplacement;
    }

    private void deplacementParDirection(Coordonnees position, IPiece[][] echiquier, ArrayList<Coordonnees> listeDeplacement, Direction direction) {
        int variationX = variationX(position.getLigne(), direction);
        int variationY = variationY(position.getColonne(), direction);

        Coordonnees destination = new Coordonnees(variationX, variationY);

        while (isCoordonneesExistent(destination) && echiquier[variationX][variationY] == null) {
            listeDeplacement.add(destination);

            variationX = variationX(variationX, direction);
            variationY = variationY(variationY, direction);

            destination = new Coordonnees(variationX, variationY);
        }

        if (isCoordonneesExistent(destination) && echiquier[variationX][variationY].getCouleur() != this.getCouleur())
            listeDeplacement.add(destination);
    }

    private int variationX(int variationX, Direction direction) {
        switch (direction) {
            case OUEST : return --variationX;
            case EST   : return ++variationX;
            default    : return variationX;
        }
    }

    private int variationY(int variationY, Direction direction) {
        switch (direction) {
            case NORD  : return --variationY;
            case SUD   : return ++variationY;
            default    : return variationY;
        }
    }


}
