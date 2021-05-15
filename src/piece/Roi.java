package piece;

import echequier.Echequier;
import  echequier.IPiece;

import java.util.ArrayList;
import java.util.List;

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
     * Permet de déplacer le Roi sur l'échequier à la coordonnée passé en paramètre
     * @param coord la coordonnée à laquelle on veut déplacer le Roi
     * @param e L'échequier sur lequel le déplacement aura lieu
     */
    @Override
    public void deplacer(Coordonnee coord, Echequier e){
        if (peutSeDeplacer(coord, e)){
            e.enleverPieceDeLaCase(this.getCoordonnee());
            e.ajoutPiece(this,coord);
            super.setCoord(coord);
       }
    }

    /**
     * Permet de savoir si le Roi (la piece) peut se déplacer a une coordonnée
     * @param coord la coordonnée à laquelle on veut déplacer le Roi
     * @param e l'échéquier sur lequel on vérifie si le déplacement du Roi peut avoir lieu
     * @return TRUE si le roi peut se déplacer à cette positions et FALSE s'il peut pas se déplacer
     */
    @Override
    public boolean peutSeDeplacer(Coordonnee coord, Echequier e){
        if (Math.abs(super.getCoordonnee().getColonne() - coord.getColonne()) > 1
                || Math.abs(super.getCoordonnee().getLigne() - coord.getLigne()) > 1)
            return false;
        if (crainEchec())
                return false;
        return true;
    }

    /**
     * Permet de savoir si le Roi (la piece) est en danger d'echec causé par une pièce adverse
     * @return TRUE si le roi est en danger d'echec et FALSE s'il ne l'ai pas
     */
    public boolean crainEchec(){
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

        for (int varX = getCoordonnee().getColonne() - 1; varX <= getCoordonnee().getColonne() + 1; varX++) {
            for (int varY = getCoordonnee().getLigne() - 1; varY <= getCoordonnee().getLigne() + 1; varY++ ) {
                Coordonnee destination = new Coordonnee(varX, varY);

                if (isCoordonneesExistent(destination) && (echequier[varX][varY] == null ||
                        echequier[varX][varY].getCouleur() != this.getCouleur()))
                    listeDeplacement.add(destination);
            }
        }
        return listeDeplacement;
    }
}
