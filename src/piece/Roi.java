package piece;

import  echequier.IPiece;

import java.util.ArrayList;
import java.util.List;

import static echequier.Echequier.HAUTEUR;
import static echequier.Echequier.LARGEUR;

/**
 * Modélise une tour dans le jeu de l'échéquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */

public class Roi extends Piece {

    /**
     * Constructeur à partir d'une couleur et d'une coordonnée
     * @param couleur la couleur du Roi
     * @param coord la coordonnée du Roi
     */
    public Roi(Couleur couleur, Coordonnee coord) {
        super(couleur, coord);
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
            if (destPieceEnnemi.getColonne() == destinationRoi.getColonne() &&
                    destPieceEnnemi.getLigne() == destinationRoi.getLigne())
                return true;
        }

        return false;
    }

    /**
     * Retourne la liste des possibilités pour le déplacement de la pièce Roi
     * @param echequier l'échequier sur lequel le Roi se trouve
     * @return une liste des possibilités de déplacements du Roi
     */
    @Override
    public List<Coordonnee> listeDeplacement(IPiece[][] echequier) {
        List<Coordonnee> listeDeplacement = new ArrayList<>();
        List<Coordonnee> listeDeplacementPieceEnnemis = listeDeplacementPieceEnnemis(echequier);


        for (int varX = getCoordonnee().getColonne() - 1; varX <= getCoordonnee().getColonne() + 1; varX++) {
            for (int varY = getCoordonnee().getLigne() - 1; varY <= getCoordonnee().getLigne() + 1; varY++ ) {
                Coordonnee destination = new Coordonnee(varX, varY);

                if (isCoordonneesExistent(destination) && (echequier[varX][varY] == null ||
                        echequier[varX][varY].getCouleur() != this.getCouleur()) && !crainEchec(destination, listeDeplacementPieceEnnemis))
                    listeDeplacement.add(destination);
            }
        }
        return listeDeplacement;
    }

    private List<Coordonnee> listeDeplacementPieceEnnemis(IPiece[][] echequier) {
        List<Coordonnee> listeDeplacement = new ArrayList<>();

        for (int idxLigne = 0; idxLigne < LARGEUR; idxLigne++) {
            for (int idxColonne = 0; idxColonne < HAUTEUR; idxColonne++) {
                IPiece piece = echequier[idxLigne][idxColonne];
                // est une pièce ennemie
                if ((piece != null) && (this != piece) && (!this.estAllie(piece))){
                    listeDeplacement = piece.listeDeplacement(echequier);
                }
            }
        }

        return listeDeplacement;
    }
}
