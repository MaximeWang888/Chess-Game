package piece;

import echequier.Echequier;

/**
 * Modélise une coordonnée d'une pièce d'échecs.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class Coordonnee {

    /** Les coordonnées d'une pièce */
    private int ligne, colonne ;

    /**
     * Constructeur à partir d'une ligne et d'une colonne
     * @param ligne la ligne d'une coordonnée
     * @param colonne la colonne d'une coordonnée
     */
    public Coordonnee(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    /**
     * Constructeur vide
     */
    public Coordonnee() {
        this(0,0);
    }


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

    /**
     * Permet de savoir si les coordonnée son des coordonnées valide sur le plateau
     * @return True si les coordonnées son valide sinon return False
     */
    public boolean coordValide(){
        return this.colonne>=0 & this.colonne< Echequier.LARGEUR & this.ligne>=0 & this.ligne< Echequier.HAUTEUR;
    }

    /**
     * Convertie une chaine de deuc caractère en coordonée
     * @param saisie La chaine de caractère a convertir
     */
    public void conversionEnCoord(String saisie){
            this.colonne = -1 ;
            this.ligne = -1 ;

        if(saisie.length()>= 2){
            this.ligne = Echequier.HAUTEUR - Character.getNumericValue(saisie.charAt(1));
            char c = saisie.charAt(0);
            switch (c){
                case 'a': colonne = 0;
                    break;
                case 'b': colonne = 1;
                    break;
                case 'c': colonne = 2;
                    break;
                case 'd': colonne = 3;
                    break;
                case 'e': colonne = 4;
                    break;
                case 'f': colonne = 5;
                    break;
                case 'g': colonne = 6;
                    break;
                case 'h': colonne = 7;
                    break;
            }
        }
    }

    /**
     * Affiche les coordonnées
     * @return les coordonnées
     */
    @Override
    public String toString() {
        return "Coordonnee{" +
                "ligne=" + ligne +
                ", colonne=" + colonne +
                '}';
    }

}
