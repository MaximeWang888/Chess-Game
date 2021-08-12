package pièces;

import java.util.ArrayList;

public class Cavalier extends Piece {
    
    @Override
    public char getNom() {
        if (getCouleur() == Couleur.BLANC)
            return 'C';
        else
            return 'c';
    }

    public Cavalier (Couleur couleur) {
        super(couleur);
    }

    @Override
    public ArrayList<Coordonnees> listeDeplacement(Coordonnees position, IPiece[][] echiquier) {
        ArrayList<Coordonnees> listeDeplacement = new ArrayList<>();

        for (int varLigne = - 2; varLigne <= 2; varLigne++) {

            for (int varColonne = - 2; varColonne <= + 2; varColonne++ ) {

                if (Math.abs(varLigne) + Math.abs(varColonne) == 3) {

                    int ligneDestination = position.getLigne() + varLigne;
                    int colonneDestination = position.getColonne() + varColonne;
                    Coordonnees destination = new Coordonnees(ligneDestination, colonneDestination);

                    if (isCoordonneesExistent(destination) && (echiquier[ligneDestination][colonneDestination] == null
                            || echiquier[ligneDestination][colonneDestination].getCouleur() != this.getCouleur()))
                        listeDeplacement.add(destination);
                }
            }
        }

        return listeDeplacement;
    }
}
