package pièces;

import plateau.Plateau;

public abstract class Piece implements IPiece {

    private Couleur couleur;

    protected Piece (Couleur couleur)
    {
        this.couleur = couleur;
    }

    @Override
    public Couleur getCouleur() {
        return couleur;
    }

    protected boolean isCoordonneesExistent(Coordonnees coord) {
        if ((coord.getLigne() >= 0 && coord.getLigne() < Plateau.tailleMaxX()) && (coord.getColonne() >= 0 && coord.getColonne() < Plateau.tailleMaxY()))
            return true;
        return false;
    }

}
