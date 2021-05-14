package Echequier;

import piece.Coordonnee;
import piece.Couleur;
import piece.Piece;

import java.util.List;

/**
 * Modélise une pièce basique.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public interface IPiece {

    public abstract Couleur getCouleur();

    public abstract List<Coordonnee> listeDeplacement(Piece[][] echequier);
}
