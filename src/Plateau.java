public class Plateau {

    private static int LIGNE = 8;
    private static int COLONNE = 8;

    private IPiece [][] echequier = new IPiece [LIGNE][COLONNE];

    public void initialiserPlateau() {
        FabriquePiece fp = new FabriquePiece();

        //------------------------ TOUR ----------------------------------
        echequier[0][7] = fp.creationPiece(Couleur.BLANC, TypePiece.TOUR);
        echequier[0][0] = fp.creationPiece(Couleur.BLANC, TypePiece.TOUR);

        echequier[7][7] = fp.creationPiece(Couleur.NOIR, TypePiece.TOUR);
        echequier[7][0] = fp.creationPiece(Couleur.NOIR, TypePiece.TOUR);

        //---------------------- FOU ------------------------------------
        echequier[0][5] = fp.creationPiece(Couleur.BLANC, TypePiece.FOU);
        echequier[0][2] = fp.creationPiece(Couleur.BLANC, TypePiece.FOU);

        echequier[7][5] = fp.creationPiece(Couleur.NOIR, TypePiece.FOU);
        echequier[7][2] = fp.creationPiece(Couleur.NOIR, TypePiece.FOU);

        //------------------------ ROI ---------------------------------
        echequier[7][3] = fp.creationPiece(Couleur.NOIR, TypePiece.ROI);
        echequier[0][3] = fp.creationPiece(Couleur.BLANC, TypePiece.ROI);
    }
    public static int tailleMaxX() {
        return LIGNE;
    }

    public static int tailleMaxY() {
        return COLONNE;
    }

    public String getListeDeplacement() {
        StringBuilder str = new StringBuilder();
        for (int idxLigne = 0; idxLigne < LIGNE; idxLigne++) {
            for (int idxColonne = 0; idxColonne < COLONNE; idxColonne++) {
                if (echequier[idxLigne][idxColonne] != null) {
                    str.append(echequier[idxLigne][idxColonne].getNom() + " peut se déplacer en :\n");
                    for (Coordonnees destination : echequier[idxLigne][idxColonne].listeDeplacement(new Coordonnees(idxLigne, idxColonne), echequier)) {
                        str.append("[" + destination.getX() + "][" + destination.getY() + "]\n");
                    }
                    str.append("\n");
                }
            }
        }
        return str.toString();
    }
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("\n"+ affichageBandeau());   //Bandeau supérieur

        str.append(affichageBordure());  //Bordure

        for (int nomLigne = LIGNE; nomLigne > 0; nomLigne--) { //Ligne du tableau
            str.append(nomLigne);    //Contenu de la ligne
            int idxLigne = LIGNE - nomLigne;
            for (int idxColonne = 0; idxColonne < COLONNE; idxColonne++) {
                str.append(" | ");
                if (echequier[idxLigne][idxColonne] != null)
                    str.append(echequier[idxLigne][idxColonne].getNom());

                else
                    str.append(" ");
            }
            str.append(" | " + nomLigne + "\n");

            str.append(affichageBordure());   //Bordure
        }
        str.append(affichageBandeau());   //Bandeau inférieur

        return str.toString();
    }

    private String affichageBandeau() {
        StringBuilder str = new StringBuilder();

        str.append("   ");
        for (char c = 'a'; c <= (char)(COLONNE + 96); c++)
            str.append(" " + c + "  ");
        str.append("   \n");

        return str.toString();
    }

    private String affichageBordure() {
        StringBuilder str = new StringBuilder();

        str.append("   ");
        for (int idxColonne = 0; idxColonne < COLONNE; idxColonne++)
            str.append("--- ");
        str.append("   \n");

        return str.toString();
    }

}

