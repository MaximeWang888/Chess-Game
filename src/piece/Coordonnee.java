package piece;

/**
 * Modélise une coordonnée d'une pièce d'échecs.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class Coordonnee {

    /** Les coordonnées d'une pièce */
    private int ligne, colonne ;

    /**
     * Retourne la colonne de la pièce.
     * @return La colonne de la pièce.
     */
    public int getColonne() {
        return colonne;
    }

    /**
     * Retourne la ligne de la pièce.
     * @return La ligne de la pièce.
     */
    public int getLigne() {
        return ligne;
    }
}
