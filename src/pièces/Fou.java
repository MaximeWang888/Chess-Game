package pièces;

import java.util.ArrayList;

public class Fou extends Piece {

    @Override
    public char getNom() {
        if (getCouleur() == Couleur.BLANC)
            return 'F';
        else
            return 'f';
    }
    public Fou (Couleur couleur) {
        super (couleur);
    }
    private enum Direction {NORDEST, NORDOUEST, SUDEST, SUDOUEST}

    @Override
    public ArrayList<Coordonnees> listeDeplacement(Coordonnees position, IPiece[][] echiquier) {
        ArrayList<Coordonnees> listeDeplacement = new ArrayList<>();

        for (Fou.Direction direction : Fou.Direction.values())
            deplacementParDirection(position, echiquier, listeDeplacement, direction);

        return listeDeplacement;
    }

    private void deplacementParDirection(Coordonnees position, IPiece[][] echiquier, ArrayList<Coordonnees> listeDeplacement, Fou.Direction direction) {
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

    private int variationX(int variationX, Fou.Direction direction) {
        switch (direction) {
            case NORDOUEST :
            case NORDEST : return --variationX;
            case SUDOUEST :
            case SUDEST : return ++variationX;
            default    : return variationX;
        }
    }

    private int variationY(int variationY, Fou.Direction direction) {
        switch (direction) {
            case SUDOUEST :
            case NORDOUEST : return --variationY;
            case SUDEST :
            case NORDEST  : return ++variationY;
            default    : return variationY;
        }
    }
}
