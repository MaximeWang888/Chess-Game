
public abstract class Piece implements IPiece{

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
        if ((coord.getX() >= 0 && coord.getX() < Plateau.tailleMaxX()) && (coord.getY() >= 0 && coord.getY() < Plateau.tailleMaxY()))
            return true;
        return false;
    }

}
