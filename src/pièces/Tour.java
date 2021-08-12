package pièces;

import java.util.ArrayList;

public class Tour extends Piece{

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
        int varLigne = varLigne(position.getLigne(), direction);
        int varColonne = varColonne(position.getColonne(), direction);

        Coordonnees destination = new Coordonnees(varLigne, varColonne);

        while (isCoordonneesExistent(destination) && echiquier[varLigne][varColonne] == null) {
            listeDeplacement.add(destination);
 
            varLigne = varLigne(varLigne, direction);
            varColonne = varColonne(varColonne, direction);
    
            destination = new Coordonnees(varLigne, varColonne);
        }

        if (isCoordonneesExistent(destination) && echiquier[varLigne][varColonne].getCouleur() != this.getCouleur())
            listeDeplacement.add(destination);
    }

    private int varLigne(int varLigne, Direction direction) {
        switch (direction) {
            case OUEST : return --varLigne;
            case EST   : return ++varLigne;
            default    : return varLigne;
        }
    }

    private int varColonne(int varColonne, Direction direction) {
        switch (direction) {
            case NORD  : return --varColonne;
            case SUD   : return ++varColonne;
            default    : return varColonne;
        }
    }


}
