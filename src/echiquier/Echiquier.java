package echiquier;

import joueur.Joueur;
import piece.Couleur;
import piece.Piece;

import java.util.List;

import static piece.Piece.trouverRoi;

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
     * @return la pièce aux coordonnees voulu
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
        assert (getPiece(piece.getCoordonnee()).equals(piece) && coordonnee.isCoordonneeExistante());
        piece.deplacer(coordonnee, this);
    }

    /**
     * Permet d'ajouter une piece au coordonnée indiqué
     * @param piece la pièce ajouter
     * @param coord les coordonnées où l'on ajoute la piece
     */
    public void ajoutPiece(IPiece piece, Coordonnee coord) {
        assert (piece != null && coord.isCoordonneeExistante());
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
    public String getListeDeplacement(IJoueur joueur) {
        StringBuilder str = new StringBuilder();
        for (int x = 0; x < LARGEUR; x++) {
            for (int y = 0; y < HAUTEUR; y++) {
                if (plateau[x][y] != null && joueur.getCouleur() == plateau[x][y].getCouleur()) {
                    str.append(plateau[x][y].toString())
                            .append(" peut se déplacer en : (format:[COL][LIGNE])\n");
                    List<Coordonnee> l = plateau[x][y].listeDeplacement(this);
                    for (Coordonnee destination : l) {
                        if (plateau[x][y].isDeplacementPossible(this, destination, plateau[x][y].getCouleur(), plateau[x][y].listeDeplacement(this)))
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

    public void jouer(IJoueur joueur, boolean attentionRoiPresqueEnEchec){
        joueur.jouer(this, attentionRoiPresqueEnEchec);
    }

    /**
     * La partie est terminée que si la liste des déplacements du roi est vide
     * @param joueur le joueur qui joue
     * @return TRUE ssi la liste des déplacements du roi est vide, FALSE dans le cas contraire
     */
    public boolean estPartieTerminee(IJoueur joueur) {
//        System.out.println(this.getListeDeplacement(joueur));
        System.out.println(this);
        System.out.println("Au tour des " + joueur.getCouleur() + " de jouer");
        IPiece roi = trouverRoi(this, joueur.getCouleur());
        assert roi != null;
        List<Coordonnee> listDRoi = roi.listeDeplacement(this);
        if (this.craintEchec(roi))
            System.out.println("attention ton roi en danger \n");
        for (Coordonnee destination: listDRoi) {
            if (roi.isDeplacementPossible(this, destination, joueur.getCouleur(), listDRoi)) {
                return false;
            }
        }
        if (this.craintEchec(roi)) {
            System.out.print("Echec et mat ! les ");
            if (joueur.getCouleur() == Couleur.BLANC)
                System.out.print("noirs");
            else
                System.out.print("blancs");
            System.out.println(" ont gagné.");
            return true;
        }
        System.out.println("Pat !");
        return true;
        // plus de déplacement possible + echec mat
//        if (!existeEncoreDesDeplacements(this, joueur)) {

//        if (craintEchec(this, joueur)) {
//
//            System.out.print("Echec et mat ! les ");
//            if (joueur.getCouleur() == Couleur.BLANC)
//                System.out.print("noirs");
//            else
//                System.out.print("blancs");
//            System.out.println(" ont gagné.");
//            return true;
//        } else {
//            System.out.println("Pat !");
//            return true;
//        }
        //        }
        //plus de déplacement possible
//        return false;
    }

    public boolean craintEchec(IPiece roi) {
        for (int x = 0; x < HAUTEUR; x++) {

            for (int y = 0; y < LARGEUR; y++) {
                Coordonnee destination = new Coordonnee(x, y);

                if (getPiece(destination) != null &&
                        getPiece(destination).getCouleur() != roi.getCouleur()) {
                    // Liste de déplacement des pièces ennemis
                    List<Coordonnee> l = getPiece(destination).listeDeplacement(this);
                    //Pour chaque piece adverse on verifie ses déplacements possibles
                    for (Coordonnee deplacement : l) {

                        if (deplacement.getX() == roi.getCoordonnee().getX() && deplacement.getY() == roi.getCoordonnee().getY())
                            return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean existeEncoreDesDeplacements(Echiquier echiquier, IJoueur joueur) {
        Coordonnee destination = new Coordonnee();
        for (int x = 0; x < HAUTEUR; x++) {

            for (int y = 0; y < LARGEUR; y++) {

                destination.setX(x);
                destination.setY(y);

                // C'est une pièce du joueur
                if (!echiquier.estVide(destination.getX(), destination.getY()) &&
                        echiquier.getPiece(destination).getCouleur() == joueur.getCouleur()) {
                    // Regarde les deplacements possibles de cette pièce
                    IPiece piece = echiquier.getPiece(destination);
                    List<Coordonnee> listDeplacement = piece.listeDeplacement(echiquier);
                    // Pour chaque déplacement de cette pièce
                    for (Coordonnee deplacement : listDeplacement) {

                        if (piece.isDeplacementPossible(echiquier, deplacement, joueur.getCouleur(), listDeplacement))
                            return true;
                    }
                }
            }
        }
        return false;
    }


}
