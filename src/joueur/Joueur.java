package joueur;

import echiquier.*;

/**
 * Modélise un joueur dans le jeu d'Echecs.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public abstract class Joueur implements IJoueur {

    /* */
    private Couleur couleur;

    public Joueur(Couleur couleur) {
        this.couleur = couleur;
    }

    @Override
    public Couleur getCouleur(){
        return couleur;
    }

    /**
     * Permet de savoir si la destination de la piece peut se sacrifier pour le roi
     * @param destination la destination de la piece
     * @param roi le roi protégé
     * @param echiquier l'echiquier sur lequel le deplacement de la piece vers la destination aura lieu
     * @return TRUE si la destination de la piece peut se sacrifier pour le roi, FALSE dans le cas contraire
     */
    public boolean isPieceDestSeSacrifiantForRoi(Coordonnee origine, Coordonnee destination, IPiece roi, Echiquier echiquier) {
        IPiece pieceAvant = echiquier.getPiece(destination);
        echiquier.getPiece(origine).deplacer(destination, echiquier);

        if (!roi.craintEchec(echiquier)){
            echiquier.getPiece(destination).deplacer(origine, echiquier);
            echiquier.ajoutPiece(pieceAvant, destination);
            return true;
        }
        echiquier.getPiece(destination).deplacer(origine, echiquier);
        echiquier.ajoutPiece(pieceAvant, destination);

        return false;
    }

}
