package pièces;

import java.util.ArrayList;

public class Roi extends Piece {

    @Override
    public char getNom() {
        if (getCouleur() == Couleur.BLANC)
            return 'R';
        else
            return 'r';
    }

    public Roi (Couleur couleur)
    {
        super(couleur);
    }

    @Override
    public ArrayList<Coordonnees> listeDeplacement(Coordonnees position, IPiece[][] echiquier) {
        ArrayList<Coordonnees> listeDeplacement = new ArrayList<>();

        for (int varX = position.getLigne() - 1; varX <= position.getLigne() + 1; varX++) {
            for (int varY = position.getColonne() - 1; varY <= position.getColonne() + 1; varY++ ) {
                Coordonnees destination = new Coordonnees(varX, varY);

                if (isCoordonneesExistent(destination) && (echiquier[varX][varY] == null || echiquier[varX][varY].getCouleur() != this.getCouleur()))
                    listeDeplacement.add(destination);
            }
        }

        return listeDeplacement;
    }
}