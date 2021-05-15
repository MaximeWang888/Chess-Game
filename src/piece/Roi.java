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
     * Permet de déplacer le Roi sur l'échequier à la coordonnée passé en paramètre
     * @param coord Les coordonnées du déplacement
     * @param e L'échequier sur lequel le déplacement aura lieu
     */
    public void deplacer(Coordonnee coord, Echequier e){
        if (peutSeDeplacer(coord, e)){
            e.enleverPieceDeLaCase(this.getCoordonnee());
            e.ajoutPiece(this,coord);
            super.setCoord(coord);
       }
    }

    /**
     * Permet de savoir si le Roi (la piece) peut se déplacer a une coordonnée
     * @return TRUE si le roi peut se déplacer à cette positions et FALSE s'il peut pas se déplacer
     */
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
     * Affiche le roi en caractère
     * @return R si la piece est une pice Blanche et return r si la piece est noir 
     */
    @Override
    public String toString() {
        if(getCouleur() == Couleur.BLANC)
            return "R";
        return "r";
    }


    /**
     * Retourne la liste des déplacements possible de la pièce Roi
     * @param echequier l'échequier sur lequel notre Roi se trouve
     * @return la liste des déplacements du Roi
     */
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
