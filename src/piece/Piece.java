package piece;

import echiquier.Coordonnee;
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

    /** Le type de la pièce */
    private TypePiece type;

    /** Couleur de la pièce */
    private Couleur couleur;

    /** Coordonnée de la pièce */
    private Coordonnee coord;

    /**
     * Constructeur à partir d'une couleur et d'une coordonnée
     * @param type le type de la pièce
     * @param couleur la couleur de la pièce
     * @param coord la coordonnée de la pièce
     */
    protected Piece(TypePiece type, Couleur couleur, Coordonnee coord) {
        this.type = type;
        this.couleur = couleur;
        this.coord = coord;
    }

    @Override
    public TypePiece getType(){
        return type;
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

        List<Coordonnee> listeDeplacements;

//        if (echiquier.getPiece(destination) == null &&
//                echiquier.getPiece(destination).getCouleur() == couleurJoueur) {

        listeDeplacements = verifierListeDeplacement(listDeplacementDeMaPiece, echiquier, couleurJoueur);

        for (Coordonnee destinationSûrEtPossible : listeDeplacements) {

            if (destinationSûrEtPossible.getX() == destination.getX() && destinationSûrEtPossible.getY() == destination.getY())
                return true;
        }
        return false;
//        }

//        return false;
    }

    private List<Coordonnee> verifierListeDeplacement(List<Coordonnee> listDeplacementDeMaPiece,
                                                      Echiquier echiquier, Couleur couleurJoueur) {
        List<Coordonnee> deplacementSur = new ArrayList<>();
        Coordonnee coordDeMonAnciennePiece = this.getCoordonnee();
//        IPiece pièceTemporaire;
        for(Coordonnee destination : listDeplacementDeMaPiece) {
//            pièceTemporaire = echiquier.getPiece(destination);
//            echiquier.deplacer(this, destination);
//            this.deplacer(destination, echiquier);
            // si je suis une autre piece qu'un roi
            // je ne suis pas un roi ou si je suis un
            boolean isEchec = craintEchec(echiquier, couleurJoueur, destination);
            if (!this.getType().equals(TypePiece.ROI) || !isEchec)
            {
                deplacementSur.add(destination);
            }


//            echiquier.deplacer(echiquier.getPiece(destination), coordDeMonAnciennePiece);
//            echiquier.getPiece(destination).deplacer(this.getCoordonnee(), echiquier);
//            pièceTemporaire.deplacer(destination, echiquier);
        }
        return deplacementSur;
    }

    public static boolean craintEchec(Echiquier echiquier, Couleur couleurJoueur, Coordonnee destinationPPP) {

        IPiece roi = trouverRoi(echiquier, couleurJoueur);
        assert (roi != null);
        for (int x = 0; x < HAUTEUR; x++) {

            for (int y = 0; y < LARGEUR; y++) {
                Coordonnee destination = new Coordonnee(x, y);

                if (echiquier.getPiece(destination) != null &&
                        echiquier.getPiece(destination).getCouleur() != roi.getCouleur()) {
                    // Liste de déplacement des pièces ennemis
                    List<Coordonnee> l = echiquier.getPiece(destination).listeDeplacement(echiquier);
                    //Pour chaque piece adverse on verifie ses déplacements possibles
                    for (Coordonnee deplacement : l) {

                        if (deplacement.getX() == destinationPPP.getX() && deplacement.getY() == destinationPPP.getY())
                            return true;
                    }
                }
            }
        }
        return false;
    }

    public static IPiece trouverRoi(Echiquier echiquier, Couleur couleurJoueur) {
        Coordonnee destination = new Coordonnee();
        for (int x = 0; x < HAUTEUR; x++) {
            for (int y = 0; y < LARGEUR; y++) {
                destination.setX(x);
                destination.setY(y);

                if (echiquier.getPiece(destination) != null) {

                    if (echiquier.getPiece(destination).getType().equals(TypePiece.ROI) &&
                            echiquier.getPiece(destination).getCouleur() == couleurJoueur)
                        return echiquier.getPiece(destination);
                }
            }
        }
        return null;
    }


}
