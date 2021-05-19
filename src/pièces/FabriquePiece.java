package pièces;

public class FabriquePiece {
    
    public IPiece creationPiece(Couleur couleur, TypePiece type) {
        switch (type) {
            case TOUR     : return new Tour(couleur);
            case ROI      : return new Roi(couleur);
            case CAVALIER : return new Cavalier(couleur);
            case FOU      : return new Fou(couleur);
            case DAME     : return new Dame(couleur);
            default       : return null;
        }
    }
}
