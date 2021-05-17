package echequier;

import piece.Coordonnee;
import piece.Couleur;

import java.util.List;

/**
 * Modélise une pièce basique.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public interface IPiece {

    Couleur getCouleur();

    Coordonnee getCoordonnee();

    void setCoord(Coordonnee coord);

    void deplacer(Coordonnee coordonnee, Echequier echequier);

    List<Coordonnee> listeDeplacement(IPiece[][] echequier);
}
