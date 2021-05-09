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

    @Override
    public ArrayList<Coordonnees> listeDeplacement(Coordonnees position, IPiece[][] echiquier) {
        ArrayList<Coordonnees> listeDeplacement = new ArrayList<>();

        return listeDeplacement;
    }
}
