package piece;

import echiquier.Coordonnee;
import echiquier.IPiece;

import java.util.ArrayList;
import java.util.List;

import static echiquier.Echiquier.HAUTEUR;
import static echiquier.Echiquier.LARGEUR;

/**
 * Modélise une tour dans le jeu de l'échiquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */

public class Roi extends Piece {

    /**
     * Constructeur à partir d'une couleur et d'une coordonnée
     * @param couleur la couleur du Roi
     * @param coord la coordonnée du Roi
     */
    public Roi(TypePiece type, Couleur couleur, Coordonnee coord) {
        super(type, couleur, coord);
        assert (type == TypePiece.ROI);
    }

    /**
     * Permet d'avoir une représentation du Roi sous la forme d'une chaîne de caractères.
     * @return 'R' si la piece est une pièce blanche sinon 'r' car c'est une piece noire.
     */
    @Override
    public String toString() {
        if(getCouleur() == Couleur.BLANC)
            return "R";
        return "r";
    }

    /**
     * Permet de savoir si le Roi (la piece) est en danger d'echec causé par une pièce adverse
     * @return TRUE si le roi est en danger d'echec et FALSE s'il ne l'ai pas
     */
    public boolean crainEchec(Coordonnee destinationRoi, List<Coordonnee> listeDeplacement){

        for (Coordonnee destPieceEnnemi : listeDeplacement) {
            if (destPieceEnnemi.getY() == destinationRoi.getY() &&
                    destPieceEnnemi.getX() == destinationRoi.getX())
                return true;
        }

        return false;
    }

    @Override
    public List<Coordonnee> listeDeplacement(IPiece[][] echiquier) {
        List<Coordonnee> listeDeplacement = new ArrayList<>();
        List<Coordonnee> listeDeplacementPieceEnnemis = listeDeplacementPieceEnnemis(echiquier);

        for (int varX = getCoordonnee().getX() - 1; varX <= getCoordonnee().getX() + 1; varX++) {
            for (int varY = getCoordonnee().getY() - 1; varY <= getCoordonnee().getY() + 1; varY++ ) {
                Coordonnee destination = new Coordonnee(varX, varY);

                if (isCoordonneeExistante(destination) && (echiquier[varX][varY] == null ||
                        echiquier[varX][varY].getCouleur() != this.getCouleur()) &&
                        !crainEchec(destination, listeDeplacementPieceEnnemis))
                    listeDeplacement.add(destination);
            }
        }
        return listeDeplacement;
    }

    /**
     * Retourne une liste de déplacement des pièces ennemis
     * @param echiquier l'échiquier testé
     * @return une liste de déplacement des pièces ennemis
     */
    private List<Coordonnee> listeDeplacementPieceEnnemis(IPiece[][] echiquier) {
        List<Coordonnee> listeDeplacement = new ArrayList<>();

        for (int x = 0; x < LARGEUR; x++) {
            for (int y = 0; y < HAUTEUR; y++) {
                IPiece piece = echiquier[x][y];
                // est une pièce ennemie
                if ((piece != null) && (this != piece) && (!this.estAllie(piece))){
                    listeDeplacement = piece.listeDeplacement(echiquier);
                }
            }
        }
        return listeDeplacement;
    }
}
