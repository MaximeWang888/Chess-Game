package pièces;

import java.util.ArrayList;

public class Fou extends Piece{

    @Override
    public char getNom() {
        if (getCouleur() == Couleur.BLANC)
            return 'F';
        else
            return 'f';
    }
    
    public Fou (Couleur couleur) {
        super(couleur);
    }

    private enum Direction {NORD_EST, NORD_OUEST, SUD_EST, SUD_OUEST}

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
            case NORD_EST   :
            case NORD_OUEST : return --varLigne;
            case SUD_EST    :
            case SUD_OUEST  : return ++varLigne;
            default         : return varLigne;
        }
    }

    private int varColonne(int varColonne, Direction direction) {
        switch (direction) {
            case NORD_EST   : 
            case SUD_EST    : return ++varColonne;
            case NORD_OUEST : 
            case SUD_OUEST  : return --varColonne;
            default         : return varColonne;
        }
    }


}
