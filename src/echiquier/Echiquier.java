package echiquier;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
                        if (plateau[x][y].isDeplacementPossible(this, destination, plateau[x][y].getCouleur(),
                                plateau[x][y].listeDeplacement(this)))
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

    /**
     * Permet de jouer un coup pour le joueur passé en paramètre
     * @param joueur le joueur qui joue
     * @param attentionRoiPresqueEnEchec le boolean nous permettant de savoir si notre roi
     *                                   est en position d'échec ou non
     */
    public String jouer(IJoueur joueur, boolean attentionRoiPresqueEnEchec){
        return joueur.jouer(this, attentionRoiPresqueEnEchec);
    }

    /**
     * La partie est terminée que si la liste des déplacements du roi est vide
     * @param joueur le joueur qui joue
     * @return TRUE ssi la liste des déplacements du roi est vide, FALSE dans le cas contraire
     */
    public boolean estPartieTerminee(IJoueur joueur, String coup) {
        // Affichage du jeu
        System.out.println(this);
        System.out.println("Au tour des " + joueur.getCouleur() + " de jouer");
        if (coup.equals("abandonner")){
            abandonnerAffichage(joueur);
            return true;
        }
        if (coup.equals("proposerNulle")) {
            System.out.println("Entrez 'oui' ou 'non' : ");
            System.out.print("> ");
            Scanner sc = new Scanner(System.in);
            String reponse = sc.nextLine();
            while (!(reponse.equals("oui") || reponse.equals("non"))){
                System.out.print("> ");
                reponse = sc.nextLine();
            }
            if (reponse.equals("oui")){
                System.out.println("La partie est déclarée nulle.");
                return true;
            }else if (reponse.equals("non")){
                System.out.println("La partie continue");
                return false;
            }
        }
        // Lorsque le robot ne trouve plus aucun déplacement alors il y a echec et mat
        if (coup.equals(""))
            return echecEtMat(joueur);

        IPiece roi = this.trouverRoi(joueur.getCouleur());
        assert roi != null;
        List<Coordonnee> listDRoi = roi.listeDeplacement(this);
        // Verifie que l'on peut toujours se deplacer avec le roi du joueur
        for (Coordonnee destination: listDRoi) {
            if (roi.isDeplacementPossible(this, destination, joueur.getCouleur(), listDRoi)) {
                return false;
            }
        }
        // Si le roi ne peut plus se déplacer du tout alors on vérifie si il est en échec et mat
        if (roi.craintEchec(this)) {
            return echecEtMat(joueur);
        }
        // Si le roi n'est pas en échec et mat, dans ce cas là, il est en Pat !
        System.out.println("Pat !");
        return true;
    }

    /**
     * Permet de connaître les pieces du joueur qui est en train de jouer
     * @param joueur le joueur qui joue
     * @return la liste des pieces du joueur qui est en train de jouer
     */
    public List<Coordonnee> getListePiece(IJoueur joueur) {
        List<Coordonnee> listePiece = new ArrayList<>();

        for (int x = 0; x < HAUTEUR; x++) {

            for (int y = 0; y < LARGEUR; y++) {

                Coordonnee dest = new Coordonnee(x, y);

                if (getPiece(dest) != null && getPiece(dest).getCouleur() == joueur.getCouleur())
                    listePiece.add(dest);
            }
        }
        return listePiece;
    }

    /**
     * Permet de connaître la liste des destinations possibles de la piece passé en paramètre
     * @param origine la coordonnee de la piece auquel nous allons testé ses déplacements
     * @param joueur le joueur qui joue
     * @return la liste des destinations possibles de la pièce passé en paramètre
     */
    public List<Coordonnee> getListeDestination(Coordonnee origine, IJoueur joueur) {
        List<Coordonnee> listeDestination = new ArrayList<>();
        for (Coordonnee destination : getPiece(origine).listeDeplacement(this)) {

            if (getPiece(origine).isDeplacementPossible(this, destination, joueur.getCouleur(),
                    getPiece(origine).listeDeplacement(this)))
                listeDestination.add(destination);
        }

        return listeDestination;
    }

    /**
     * Permet de retrouver le roi du joueur sur l'echiquier
     * @param couleurJoueur la couleur du joueur
     * @return le roi du joueur
     */
    public IPiece trouverRoi(Couleur couleurJoueur) {
        Coordonnee destination = new Coordonnee();
        for (int x = 0; x < HAUTEUR; x++) {
            for (int y = 0; y < LARGEUR; y++) {
                destination.setX(x);
                destination.setY(y);

                if (getPiece(destination) != null) {

                    if (getPiece(destination).getType().equals("ROI") &&
                            getPiece(destination).getCouleur() == couleurJoueur)
                        return getPiece(destination);
                }
            }
        }
        return null;
    }

    /**
     * Permet de representé sous la forme textuelle lors d'un abandon
     * @param joueur le joueur qui joue
     */
    private void abandonnerAffichage(IJoueur joueur) {
        if (joueur.getCouleur() == Couleur.BLANC)
            System.out.print("Le joueur noir a abandonné. " + "JOUEUR BLANC GAGNANT !!! \n");
        else
            System.out.print("Le joueur blanc a abandonné. " + "JOUEUR NOIR GAGNANT !!! \n");

    }

    /**
     * Permet de representé sous la forme textuelle lors d'un echec et mat
     * @param joueur le joueur qui joue
     * @return TRUE pour dire que la partie est terminée et il y a echec et mat
     */
    private boolean echecEtMat(IJoueur joueur) {
        System.out.print("Echec et mat ! les ");
        if (joueur.getCouleur() == Couleur.BLANC)
            System.out.print("blancs");
        else
            System.out.print("noirs");
        System.out.println(" ont gagné.");
        return true;
    }
}
