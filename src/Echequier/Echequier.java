package echequier;

import piece.Coordonnee;
import piece.*;

/**
 * Modélise un plateau de jeu dans le jeu de l'échéquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class Echequier {
    /** Les constantes pour la taille du plateau */
    public static final int LARGEUR = 8;
    public static final int HAUTEUR = 8;

    /** Le plateau de jeu */
    private IPiece[][] plateau;

    /**
     * Constructeur qui crée un plateau de jeu d'échecs
     */
    public Echequier(){
        plateau = new Piece[LARGEUR][HAUTEUR];
        for(int x = 0 ; x < LARGEUR; x++ ) {
            for(int y = 0 ; y < HAUTEUR; y++ )
                this.plateau[x][y] = null;
        }
    }

    public IPiece[][] getPlateau(){
        return plateau;
    }
    /**
     * Permet de savoir si la case contient un pion
     * @param x coordonnee x soit la largeur
     * @param y coordonnee y soit la hauteur
     * @return True si la case est vide et FALSE dans le cas contraire
     */
    public boolean estVide(int x ,int y ){
        return plateau[x][y] == null;
    }

    /**
     * Permets de renvoyer le pion qui est aux coordonnee x et y
     * @param c La coordonnée à laquelle on prend la pièce
     * @return le pion aux coordonnee voulu
     */
    public IPiece getPiece(Coordonnee c){
        return this.plateau[c.getColonne()][c.getLigne()];
    }

    /**
     * Afficher le plateau avec les pièces s'il y en a
     * @return Une chaine de caractère avec le plateau et tous les pions
     */
    public String toString(){
        StringBuilder chaineDeCaractere = new StringBuilder();
        char[] lettre = {'a','b','c','d','e','f','g','h'};
        for (char c : lettre)
            chaineDeCaractere.append("  ").append(c).append(" ");

        chaineDeCaractere.append("\n ").append(" ");
        for (int i = 0; i < HAUTEUR; i++) {

            for (int k = 0; k < LARGEUR; k++)
                chaineDeCaractere.append(" ---");

            chaineDeCaractere.append("\n").append(HAUTEUR - i).append(" ");

            for (int j = 0; j < LARGEUR; j++) {
                chaineDeCaractere.append("| ");
                if (estVide(j,i))
                    chaineDeCaractere.append(" ");
                else
                    chaineDeCaractere.append(getPiece(new Coordonnee(i, j)));
                chaineDeCaractere.append(" ");
            }
            chaineDeCaractere.append("| ");
            chaineDeCaractere.append(HAUTEUR - i);
            chaineDeCaractere.append("\n  ");
        }

        for (int k = 0; k < LARGEUR; k++)
            chaineDeCaractere.append(" ---");

        chaineDeCaractere.append("\n  ");
        for (char c : lettre)
            chaineDeCaractere.append("  ").append(c).append(" ");

        return chaineDeCaractere.toString();
    }

    /**
     * Permet d'ajouter une piece au coordonnée indiqué
     * @param piece la pièce ajouter
     * @param coord les coordonnées où l'on ajoute la piece
     */
    public void ajoutPiece(IPiece piece, Coordonnee coord) {
        this.plateau[coord.getColonne()][coord.getLigne()] = piece;
    }

    /**
     * Permet de faire pointer une case contenant un piece à null
     * @param coord coordonnée de la casse ou l'on doit enelver la piece
     */
    public void enleverPieceDeLaCase(Coordonnee coord) {
        this.plateau[coord.getColonne()][coord.getLigne()] = null;
    }

    /**
     * Permet de connaître sous une réprésentation textuelle les déplacements
     * autorisés de toutes les pièces sur l'échequier.
     * @return une liste de déplacement autorisé des pièces sur l'échequier
     */
    public String getListeDeplacement() {
        StringBuilder str = new StringBuilder();
        for (int idxLigne = 0; idxLigne < HAUTEUR; idxLigne++) {
            for (int idxColonne = 0; idxColonne < LARGEUR; idxColonne++) {
                if (plateau[idxLigne][idxColonne] != null) {
                    str.append(plateau[idxLigne][idxColonne].toString())
                            .append(" peut se déplacer en : (format:[col][ligne])\n");
                    for (Coordonnee destination : plateau[idxLigne][idxColonne].listeDeplacement(plateau)) {
                        str.append("[")
                                .append(destination.getLigne())
                                .append("][")
                                .append(destination.getColonne())
                                .append("]\n");
                    }
                    str.append("\n");
                }
            }
        }
        return str.toString();
    }
}
