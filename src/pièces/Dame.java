package pièces;

import java.util.ArrayList;

public class Dame extends Piece {
    @Override
    public char getNom() {
        if (getCouleur() == Couleur.BLANC)
            return 'D';
        else
            return 'd';
    }

    public Dame (Couleur couleur)
    {
        super(couleur);
    }

    private enum Direction {NORD, EST, SUD, OUEST, NORDEST, NORDOUEST, SUDEST, SUDOUEST}

    @Override
    public ArrayList<Coordonnees> listeDeplacement(Coordonnees position, IPiece[][] echiquier) {
        ArrayList<Coordonnees> listeDeplacement = new ArrayList<>();

        for (Dame.Direction direction : Dame.Direction.values())
            deplacementParDirection(position, echiquier, listeDeplacement, direction);

        return listeDeplacement;
    }

    private void deplacementParDirection(Coordonnees position, IPiece[][] echiquier, ArrayList<Coordonnees> listeDeplacement, Dame.Direction direction) {
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

    private int variationX(int variationX, Dame.Direction direction) {
        switch (direction) {
            case NORD :
            case NORDOUEST :
            case NORDEST : return --variationX;
            case SUD :
            case SUDOUEST :
            case SUDEST : return ++variationX;

            default    : return variationX;
        }
    }

    private int variationY(int variationY, Dame.Direction direction) {
        switch (direction) {
            case OUEST :
            case SUDOUEST :
            case NORDOUEST : return --variationY;
            case EST :
            case SUDEST :
            case NORDEST  : return ++variationY;
            default    : return variationY;
        }
    }
}


