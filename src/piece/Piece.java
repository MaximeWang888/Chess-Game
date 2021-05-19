package piece;

import echiquier.Coordonnee;
import echiquier.Echiquier;
import echiquier.IPiece;

import static echiquier.Coordonnee.MINIMUM;

/**
 * Modélise une pièce d'échiquier.
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


    @Override
    public Coordonnee getCoordonnee(){
        return coord;
    }


    @Override
    public Couleur getCouleur(){
        return couleur;
    }


    @Override
    public void setCoord(Coordonnee coord) {
        this.coord = coord;
    }


    @Override
    public void deplacer(Coordonnee coord, Echiquier e) {
        for (Coordonnee c : listeDeplacement(e.getPlateau())) {
            if ((c.getX() == coord.getX()) && (c.getY() == coord.getY())){
                e.enleverPieceDuPlateau(this);
                e.ajoutPiece(this,coord);
                setCoord(coord);
            }
        }
    }

    @Override
    public boolean estAllie(IPiece p){
        return this.couleur == p.getCouleur();
    }

    /**
     * Permet de connaître si une coordonnée existe sur le plateau
     * @param coord la coordonnée auquel nous vérifions si elle existe ou pas dans le plateau de jeu
     * @return TRUE si la coordonnée existe dans le plateau de jeu, FALSE dans le cas contraire
     */
    public boolean isCoordonneeExistante(Coordonnee coord) {
        return ((coord.getY() >= MINIMUM && coord.getY() < Echiquier.LARGEUR)
                && (coord.getX() >= MINIMUM && coord.getX() < Echiquier.HAUTEUR));
    }

}
