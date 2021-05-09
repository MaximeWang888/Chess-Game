public class FabriquePiece {
    public IPiece creationPiece(Couleur couleur, TypePiece type) {
        switch (type){
            case TOUR : return new Tour(couleur);
            case ROI : return new Roi(couleur);
            case FOU : return new Fou(couleur);
            default   : return null;
        }
    }
}
