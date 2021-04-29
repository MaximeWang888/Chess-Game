package plateau;

import piece.*;

import java.util.ArrayList;

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
    //private ArrayList<Piece> tableauPiece;
    private Piece[][] tableauPiece;

    /**
     * Constructeur qui crée un plateau de jeu d'échecs
     */
    public Plateau(){
        tableauPiece= new Piece[LARGEUR][HAUTEUR];
        for(int x = 0 ; x < LARGEUR; x++ ) {
            for(int y = 0 ; y < HAUTEUR; y++ )
                this.tableauPiece[x][y] = null;
        }
        this.tableauPiece[3][3] = new Roi(Couleur.BLANC);
        this.tableauPiece[6][6] = new Roi(Couleur.NOIR);
    }

    /**
     * Permet de savoir si la case contient un pion
     * @param x coordonnee x soit la largeur
     * @param y coordonnee y soit la hauteur
     * @return True si la case est vide et FALSE dans le cas contraire
     */
    public boolean estVide(int x ,int y ){
        if(tableauPiece[x][y] == null)
            return true;
        return false ;
    }

    /**
     * Permets de renvoyer le pion qui est aux coordonnee x et y
     * @param x coordonnee x soit la largeur
     * @param y coordonnee y soit la hauteur
     * @return le pion aux coordonnee voulu
     */
    public Piece getPiece(int x ,int y){
        return this.tableauPiece[x][y];
    }

    /**
     * Afficher le plateau avec les pions s'il y en a
     * @return Une chaine de caractère avec le plateau et tous les pions
     */
    public String toString(){
        String chaineDeCaractere = "  ";
        char[] lettre = {'a','b','c','d','e','f','g','h'};
        for (char c : lettre) {
            chaineDeCaractere += "  " + c + " ";
        }
        chaineDeCaractere +="\n ";
        chaineDeCaractere +=" ";
        for (int i = 0; i < HAUTEUR; i++) {

            for (int k = 0; k < LARGEUR; k++) {
                chaineDeCaractere +=" ---";
            }
            chaineDeCaractere +="\n";
            chaineDeCaractere +=HAUTEUR - i;
            chaineDeCaractere +=" ";

            for (int j = 0; j < LARGEUR; j++) {
                chaineDeCaractere +="| ";
                if (estVide(j,i))
                    chaineDeCaractere +=" ";
                else
                    chaineDeCaractere += getPiece(j,i);
                chaineDeCaractere +=" ";
            }
            chaineDeCaractere +="| ";
            chaineDeCaractere +=HAUTEUR - i;
            chaineDeCaractere +="\n  ";
        }

        for (int k = 0; k < LARGEUR; k++) {
            chaineDeCaractere +=" ---";
        }
        chaineDeCaractere +="\n  ";
        for (char c : lettre) {
            chaineDeCaractere += "  " + c + " ";
        }

        return chaineDeCaractere;
    }

    public static void main(String[] args) {
        Plateau plateau = new Plateau();

        if(plateau.estVide(1,1))
            System.out.println("est vide");
        else
            System.out.println("est pas vide");

       System.out.println(plateau);
    }

}
