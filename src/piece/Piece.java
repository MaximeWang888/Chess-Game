package piece;

import echequier.Echequier;
import echequier.IPiece;

/**
 * Modélise une pièce d'échequier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public abstract class Piece implements IPiece {

    /** Le type de la pièce */
    private TypePiece type;

    /** Couleur de la pièce */
    private Couleur couleur;

    /** Coordonnée de la pièce */
    private Coordonnee coord;

    /**
     * Constructeur à partir d'une couleur et d'une coordonnée
     * @param type le type de la pièce
     * @param couleur la couleur de la pièce
     * @param coord la coordonnée de la pièce
     */
    public Piece(TypePiece type, Couleur couleur, Coordonnee coord) {
        this.type = type;
        this.couleur = couleur;
        this.coord = coord;
    }

    /**
     * Permet de connaître les coordonnées d'une pièce.
     * @return La coordonnée de la pièce
     */
    @Override
    public Coordonnee getCoordonnee(){
        return coord;
    }

    /**
     * Permet de savoir la couleur d'une pièce de l'echequier
     * @return Une couleur blanc ou noir
     */
    @Override
    public Couleur getCouleur(){
        return couleur;
    }

    /**
     * Permet de fixer une nouvelle valeur à la coordonnée
     * @param coord la nouvelle coordonnée de la pièce
     */
    @Override
    public void setCoord(Coordonnee coord) {
        this.coord = coord;
    }

    /**
     * Permet de déplacer une pièce.
     * @param coord la coordonnée de destination
     * @param e l'échéquier sur lequel le déplacement de la pièce aura lieu
     */
    @Override
    public void deplacer(Coordonnee coord, Echequier e) {
        for (Coordonnee c : listeDeplacement(e.getPlateau())) {
            if ((c.getLigne() == coord.getLigne()) && (c.getColonne() == coord.getColonne())){
                e.enleverPieceDeLaCase(this.getCoordonnee());
                e.ajoutPiece(this,coord);
                setCoord(coord);
            }
        }
    }

    /**
     * Permet de savoir si la pièce sur l'échequier est à nous
     * @return True si la pièce est une pièce alliée, False dans le cas contraire.
     */
    public boolean estAllie(IPiece p){
        return this.couleur == p.getCouleur();
    }

    /**
     * Permet de connaître si une coordonnée existe sur le plateau
     * @param coord la coordonnée auquel nous vérifions si elle existe ou pas dans le plateau de jeu
     * @return TRUE si la coordonnée existe dans le plateau de jeu, FALSE dans le cas contraire
     */
    public boolean isCoordonneesExistent(Coordonnee coord) {
        if ((coord.getColonne() >= 0 && coord.getColonne() < Echequier.LARGEUR) &&
                (coord.getLigne() >= 0 && coord.getLigne() < Echequier.HAUTEUR))
            return true;
        return false;
    }

}
