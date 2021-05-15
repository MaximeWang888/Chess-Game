package piece;


/**
 * Modélise une tour dans le jeu de l'échéquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class Tour {
    private Couleur couleur;

    /**
     * Permet de déplacer la Tour
     */
    public void deplacer(){

    }

    /**
     * Permet de savoir si la Tour (la piece) peut se déplacer a une coordonnée
     * @return TRUE si la Tour peut se déplacer à cette positions et FALSE s'il peut pas se déplacer
     */
    public boolean peutSeDeplacer(){

        return false;
    }

    /**
     * Permet de savoir la couleur de la Tour
     * @return Une couleur blanc ou noir
     */
    public Couleur getCouleur(){
        return couleur;
    }
}
