package Echequier;

import piece.*;

/**
 * Modélise un plateau de jeu dans le jeu de l'échéquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class Echequier {
    /** Les constantes pour la taille du plateau */
    public final static int LARGEUR = 8;
    public final static int HAUTEUR = 8;

    /** Le plateau de jeu */
    //private ArrayList<Piece> tableauPiece;
    private Piece[][] plateau;

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

    /**
     * Permet de savoir si la case contient un pion
     * @param x coordonnee x soit la largeur
     * @param y coordonnee y soit la hauteur
     * @return True si la case est vide et FALSE dans le cas contraire
     */
    public boolean estVide(int x ,int y ){
        if(plateau[x][y] == null)
            return true;
        return false ;
    }

    /**
     * Permets de renvoyer le pion qui est aux coordonnee x et y
     * @param c La coordonnée à laquelle on prend la pièce
     * @return le pion aux coordonnee voulu
     */
    public Piece getPiece(Coordonnee c){
        return this.plateau[c.getColonne()][c.getLigne()];
    }

    /**
     * Afficher le plateau avec les pions s'il y en a
     * @return Une chaine de caractère avec le plateau et tous les pions
     */
    public String toString(){
        String chaineDeCaractere = "  ";
        char[] lettre = {'a','b','c','d','e','f','g','h'};
        for (char c : lettre)
            chaineDeCaractere += "  " + c + " ";

        chaineDeCaractere +="\n ";
        chaineDeCaractere +=" ";
        for (int i = 0; i < HAUTEUR; i++) {

            for (int k = 0; k < LARGEUR; k++)
                chaineDeCaractere += " ---";

            chaineDeCaractere += "\n";
            chaineDeCaractere += HAUTEUR - i;
            chaineDeCaractere += " ";

            for (int j = 0; j < LARGEUR; j++) {
                chaineDeCaractere +="| ";
                if (estVide(j,i))
                    chaineDeCaractere +=" ";
                else
                    chaineDeCaractere += getPiece(new Coordonnee(i,j));
                chaineDeCaractere +=" ";
            }
            chaineDeCaractere += "| ";
            chaineDeCaractere += HAUTEUR - i;
            chaineDeCaractere += "\n  ";
        }

        for (int k = 0; k < LARGEUR; k++)
            chaineDeCaractere += " ---";

        chaineDeCaractere += "\n  ";
        for (char c : lettre)
            chaineDeCaractere += "  " + c + " ";

        return chaineDeCaractere;
    }

    /**
     * Permet d'ajouter une piece au coordonnée indiqué
     * @param piece la pièce ajouter
     * @param coord les coordonnées où l'on ajoute la piece
     */
    public void ajoutPiece(Piece piece, Coordonnee coord) {
        this.plateau[coord.getColonne()][coord.getLigne()] = piece;
    }

    /**
     * Permet de faire pointer une case contenant un piece à null
     * @param coord coordonnée de la casse ou l'on doit enelver la piece
     */
    public void enleverPieceDeLaCase(Coordonnee coord) {
        this.plateau[coord.getColonne()][coord.getLigne()] = null;
    }
}
