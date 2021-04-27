package plateau;

import joueur.IPiece;

/**
 * Modélise un plateau de jeu dans le jeu de l'échéquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class Plateau {
    /** Les constantes pour la taille du plateau */
    private final static int LARGEUR = 8;
    private final static int HAUTEUR = 8;

    /** Le plateau de jeu */
    private IPiece[][] tableauPiece;

    /**
     * Constructeur qui crée un plateau de jeu d'échecs
     */
    public Plateau(){
        IPiece[][] tableauPiece = new IPiece[LARGEUR][HAUTEUR];
        for(int x = 0 ; x < LARGEUR; x++ ) {
            for(int y = 0 ; y < HAUTEUR; y++ )
                tableauPiece[x][y] = null;
        }
    }

    /**
     * Permet de savoir si la case contient un pion
     * @param x coordonnee x soit la largeur
     * @param y coordonnee y soit la hauteur
     * @return True si la case est vide et FALSE dans le cas contraire
     */
    public boolean estVide(int x ,int y ){
        return tableauPiece[x][y] == null ;
    }

    /**
     * Permets de renvoyer le pion qui est aux coordonnee x et y
     * @param x coordonnee x soit la largeur
     * @param y coordonnee y soit la hauteur
     * @return le pion aux coordonnee voulu
     */
    public IPiece getPiece(int x ,int y){
        return tableauPiece[x][y];
    }

    /**
     * Afficher le plateau avec les pions s'il y en a
     * @return Une chaine de caractère avec le plateau et tous les pions
     */
    public String toString(){
        return "";
    }
}
