package echiquier;

import piece.Couleur;

import java.util.List;

/**
 * Modélise une pièce basique.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public interface IPiece {

    /**
     * Permet de savoir la couleur d'une pièce de l'echequier
     * @return Une couleur blanc ou noir
     */
    Couleur getCouleur();

    /**
     * Permet de connaître les coordonnées d'une pièce.
     * @return La coordonnée de la pièce
     */
    Coordonnee getCoordonnee();

    /**
     * Permet de fixer une nouvelle valeur à la coordonnée
     * @param coord la nouvelle coordonnée de la pièce
     */
    void setCoord(Coordonnee coord);

    /**
     * Permet de déplacer une pièce.
     * @param coordonnee la coordonnée de destination
     * @param echiquier l'échéquier sur lequel le déplacement de la pièce aura lieu
     */
    void deplacer(Coordonnee coordonnee, Echiquier echiquier);

    /**
     * Retourne une liste des différentes possibilités pour le déplacement d'une pièce
     * @param echiquier l'échequier sur lequel la pièce se trouvant
     * @return une liste des possibilités de déplacements d'une pièce
     */
    List<Coordonnee> listeDeplacement(IPiece[][] echiquier);

    /**
     * Permet de savoir si la pièce sur l'échequier est à nous
     * @param piece la pièce testée
     * @return True si la pièce est une pièce alliée, False dans le cas contraire.
     */
    boolean estAllie(IPiece piece);
}
