package piece;

import deplacement.*;

/**
 * Modélise une tour dans le jeu de l'échéquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */

public class Roi {
    private Couleur couleur;

    /**
     * Permet de déplacer le Roi
     */
    public void deplacer(){

    }

    /**
     * Permet de savoir si le Roi (la piece) peut se déplacer a une coordonnée
     * @return TRUE si le roi peut se déplacer à cette positions et FALSE s'il peut pas se déplacer
     */
    public boolean peutSeDeplacer(){

        return false;
    }

    /**
     * Permet de savoir si le Roi (la piece) est en danger d'echec causé par une pièce adverse
     * @return TRUE si le roi est en danger d'echec et FALSE s'il ne l'ai pas
     */
    public boolean crainEchec(){

        return false;
    }

    /**
     * Permet de savoir la couleur du Roi
     * @return Une couleur blanc ou noir
     */
    public Couleur getCouleur(){
        return couleur;
    }
}
