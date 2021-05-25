package piece;

import echiquier.Coordonnee;
import echiquier.Couleur;
import echiquier.Echiquier;
import echiquier.IPiece;

import java.util.ArrayList;
import java.util.List;

import static echiquier.Echiquier.HAUTEUR;
import static echiquier.Echiquier.LARGEUR;

/**
 * Modélise une pièce d'échiquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public abstract class Piece implements IPiece {

    /** Couleur de la pièce */
    private Couleur couleur;

    /** Coordonnée de la pièce */
    private Coordonnee coord;

    /**
     * Constructeur à partir d'une couleur et d'une coordonnée
     * @param couleur la couleur de la pièce
     * @param coord la coordonnée de la pièce
     */
    protected Piece(Couleur couleur, Coordonnee coord) {
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
        for (Coordonnee c : listeDeplacement(e)) {
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

    @Override
    public boolean isDeplacementPossible(Echiquier echiquier, Coordonnee destination, Couleur couleurJoueur, List<Coordonnee> listDeplacementDeMaPiece) {
        // On est bien dans le plateau et pas en dehors des limites du plateau
        if (!this.getCoordonnee().isCoordonneeExistante() && this.getCouleur() == couleurJoueur)
            return false;
        List<Coordonnee> listeDeplacements = verifierListeDeplacement(listDeplacementDeMaPiece, echiquier, couleurJoueur);

        for (Coordonnee destinationSûrEtPossible : listeDeplacements) {

            if (destinationSûrEtPossible.getX() == destination.getX() && destinationSûrEtPossible.getY() == destination.getY())
                return true;
        }
        return false;
    }

    @Override
    public boolean craintEchec(Echiquier echiquier) {
        for (int x = 0; x < HAUTEUR; x++) {

            for (int y = 0; y < LARGEUR; y++) {
                Coordonnee destination = new Coordonnee(x, y);

                if (echiquier.getPiece(destination) != null &&
                        echiquier.getPiece(destination).getCouleur() != this.getCouleur()) {

                    List<Coordonnee> listDeplacementPieceEnnemis = echiquier.getPiece(destination).listeDeplacement(echiquier);

                    for (Coordonnee deplacement : listDeplacementPieceEnnemis) {
                        if (deplacement.getX() == this.getCoordonnee().getX() && deplacement.getY() == this.getCoordonnee().getY())
                            return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Permet d'avoir une liste de déplacement sûr de la liste de déplacement de la pièce passé en paramètre
     * @param listDeplacementDeMaPiece la liste de déplacement possible de ma pièce
     * @param echiquier l'echiquier sur lequel on joue
     * @param couleurJoueur la couleur du joueur
     * @return une liste de déplacement sûr de la liste de déplacement de la pièce passé en paramètre
     */
    private List<Coordonnee> verifierListeDeplacement(List<Coordonnee> listDeplacementDeMaPiece,
                                                      Echiquier echiquier, Couleur couleurJoueur) {
        List<Coordonnee> deplacementSur = new ArrayList<>();
        for(Coordonnee destination : listDeplacementDeMaPiece) {
            boolean isDeplacementSur = isDeplacementSur(echiquier, couleurJoueur, destination);
            if (!this.getType().equals("ROI") || isDeplacementSur)
            {
                deplacementSur.add(destination);
            }
        }
        return deplacementSur;
    }

    /**
     * Permet de savoir si la destination est un déplacement sûr
     * @param echiquier l'echiquier sur lequel on joue
     * @param couleurJoueur la couleur du joueur
     * @param destinationTester la destination testé pour savoir si c'est un déplacement sûr
     * @return TRUE si le déplacement passé en paramètre est un déplacement sûr, FALSE dans le cas contraire
     */
    private boolean isDeplacementSur(Echiquier echiquier, Couleur couleurJoueur, Coordonnee destinationTester) {

        for (int x = 0; x < HAUTEUR; x++) {

            for (int y = 0; y < LARGEUR; y++) {
                Coordonnee destination = new Coordonnee(x, y);

                if (echiquier.getPiece(destination) != null &&
                        echiquier.getPiece(destination).getCouleur() != couleurJoueur) {

                    for (Coordonnee deplacementEnnemis : echiquier.getPiece(destination).listeDeplacement(echiquier)) {

                        if (deplacementEnnemis.getX() == destinationTester.getX() && deplacementEnnemis.getY() == destinationTester.getY())
                            return false;
                    }
                }
            }
        }
        return true;
    }

}
