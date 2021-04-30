package piece;

/**
 * Modélise une pièce d'échequier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public abstract class Piece  {

    /** Couleur de la pièce */
    private Couleur couleur;

    /** Coordonnée de la pièce */
    private Coordonnee coord;

    /**
     * Constructeur à partir d'une couleur et d'une coordonnée
     * @param couleur la couleur de la pièce
     * @param coord la coordonnée de la pièce
     */
    public Piece(Couleur couleur, Coordonnee coord) {
        this.couleur = couleur;
        this.coord = coord;
    }

    /**
     * Permet de déplacer une pièce.
     */
    public void deplacer(){

    }

    /**
     * Permet de savoir si une pièce peut se déplacer ou non.
     * @return True si le déplacement est autorisé, False dans le cas contraire.
     */
    public boolean peutSeDeplacer(){
        return false;
    }

    /**
     * Permet d'avoir une représentation de la pièce sous la forme d'une chaîne de caractères.
     * @return la représentation textuelle de la pièce
     */
    public String toString(){
        return "";
    }

    /**
     * Permet de savoir si la pièce sur l'échequier est à nous
     * @return True si la pièce est une pièce alliée, False dans le cas contraire.
     */
    public boolean estAllie(){
    return true;
    }

    /**
     * Permet de savoir la couleur d'une pièce de l'echequier
     * @return Une couleur blanc ou noir
     */
    public Couleur getCouleur(){
        return couleur;
    }

    /**
     * Permet de connaître les coordonnées d'une pièce.
     * @return La coordonnée de la pièce
     */
    public Coordonnee getcoordonne(){
        return coord;
    }
}
