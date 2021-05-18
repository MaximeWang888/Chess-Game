package echiquier;

/**
 * Modélise un plateau de jeu dans le jeu de l'échiquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class Echiquier {

    /** Les constantes pour la taille du plateau */
    public static final int LARGEUR = 8;
    public static final int HAUTEUR = 8;

    /** Le plateau de jeu */
    private IPiece[][] plateau;

    /**
     * Constructeur qui crée un plateau de jeu d'échecs
     */
    public Echiquier(){
        plateau = new IPiece[LARGEUR][HAUTEUR];
        for(int x = 0 ; x < LARGEUR; x++ ) {
            for(int y = 0 ; y < HAUTEUR; y++ )
                this.plateau[x][y] = null;
        }
    }

    /**
     * Renvoie le plateau de jeu d'échec
     * @return le plateau de jeu d'échec
     */
    public IPiece[][] getPlateau(){
        return plateau;
    }

    /**
     * Permet de renvoyer la pièce qui est à la coordonnée x et y
     * @param c La coordonnée à laquelle on prend la pièce
     * @return le pion aux coordonnee voulu
     */
    public IPiece getPiece(Coordonnee c){
        return this.plateau[c.getX()][c.getY()];
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
     * Afficher le plateau avec les pièces s'il y en a
     * @return Une chaine de caractère avec le plateau et tous les pions
     */
    public String toString(){
        StringBuilder chaineDeCaractere = new StringBuilder();
        char[] lettre = {'a','b','c','d','e','f','g','h'};
        chaineDeCaractere.append("  ");
        for (char c : lettre)
            chaineDeCaractere.append("  ").append(c).append(" ");

        chaineDeCaractere.append("\n ").append(" ");
        for (int ligne = 0; ligne < HAUTEUR; ligne++) {

            for (int col = 0; col < LARGEUR; col++)
                chaineDeCaractere.append(" ---");

            chaineDeCaractere.append("\n").append(HAUTEUR - ligne).append(" ");

            for (int colonne = 0; colonne < LARGEUR; colonne++) {
                chaineDeCaractere.append("| ");
                if (estVide(colonne,ligne))
                    chaineDeCaractere.append(" ");
                else
                    chaineDeCaractere.append(getPiece(new Coordonnee(colonne, ligne)).toString());
                chaineDeCaractere.append(" ");
            }
            chaineDeCaractere.append("| ");
            chaineDeCaractere.append(HAUTEUR - ligne);
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
     * Permet de déplacer une pièce de l'échiquer
     * @param piece la pièce déplacée
     * @param coordonnee la destination du déplacement
     */
    public void deplacer(IPiece piece, Coordonnee coordonnee){
        assert (getPiece(piece.getCoordonnee()).equals(piece) && coordonnee.coordValide());
        piece.deplacer(coordonnee, this);
    }

    /**
     * Permet d'ajouter une piece au coordonnée indiqué
     * @param piece la pièce ajouter
     * @param coord les coordonnées où l'on ajoute la piece
     */
    public void ajoutPiece(IPiece piece, Coordonnee coord) {
        assert (piece != null && coord.coordValide());
        this.plateau[coord.getX()][coord.getY()] = piece;
    }

    /**
     * Permet d'enlever la pièce du plateau de jeu d'échec
     * @param piece la pièce enlevée du plateau de jeu d'échec
     */
    public void enleverPieceDuPlateau(IPiece piece) {
        assert (piece != null);
        this.plateau[piece.getCoordonnee().getX()][piece.getCoordonnee().getY()] = null;
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
                            .append(" peut se déplacer en : (format:[COL][LIGNE])\n");
                    for (Coordonnee destination : plateau[idxLigne][idxColonne].listeDeplacement(plateau)) {
                        str.append("[")
                                .append(destination.getX())
                                .append("][")
                                .append(destination.getY())
                                .append("]\n");
                    }
                    str.append("\n");
                }
            }
        }
        return str.toString();
    }


}
