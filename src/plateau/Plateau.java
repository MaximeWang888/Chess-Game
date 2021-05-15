package plateau;

import pièces.Coordonnees;
import pièces.Couleur;
import pièces.FabriquePiece;
import pièces.IPiece;
import pièces.TypePiece;

public class Plateau {

    private static int LIGNE = 8;
    private static int COLONNE = 8;

    private IPiece[][] echequier = new IPiece[LIGNE][COLONNE];
    private Couleur couleurJoueur;
    
    public Plateau () {
        initialiserPlateau();
        couleurJoueur = Couleur.BLANC;
    }

    private void initialiserPlateau() {
        FabriquePiece fp = new FabriquePiece();

        //------------------------ TOUR ----------------------------------
        echequier[0][7] = fp.creationPiece(Couleur.BLANC, TypePiece.TOUR);
        echequier[0][0] = fp.creationPiece(Couleur.BLANC, TypePiece.TOUR);

        echequier[7][7] = fp.creationPiece(Couleur.NOIR, TypePiece.TOUR);
        echequier[7][0] = fp.creationPiece(Couleur.NOIR, TypePiece.TOUR);

        //------------------------ FOU ----------------------------------
        echequier[0][5] = fp.creationPiece(Couleur.BLANC, TypePiece.FOU);
        echequier[0][2] = fp.creationPiece(Couleur.BLANC, TypePiece.FOU);

        echequier[7][5] = fp.creationPiece(Couleur.NOIR, TypePiece.FOU);
        echequier[7][2] = fp.creationPiece(Couleur.NOIR, TypePiece.FOU);

        //---------------------- CAVALIER -----------------------------------
        echequier[0][1] = fp.creationPiece(Couleur.BLANC, TypePiece.CAVALIER);
        echequier[0][6] = fp.creationPiece(Couleur.BLANC, TypePiece.CAVALIER);

        echequier[7][6] = fp.creationPiece(Couleur.NOIR, TypePiece.CAVALIER);
        echequier[7][1] = fp.creationPiece(Couleur.NOIR, TypePiece.CAVALIER);

        //------------------------ ROI ----------------------------------
        echequier[0][4] = fp.creationPiece(Couleur.BLANC, TypePiece.ROI);
        echequier[7][4] = fp.creationPiece(Couleur.NOIR, TypePiece.ROI);

        //------------------------- DAME ---------------------------------
        echequier[0][3] = fp.creationPiece(Couleur.BLANC, TypePiece.DAME);
        echequier[7][3] = fp.creationPiece(Couleur.NOIR, TypePiece.DAME);
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
                        str.append("[" + destination.getLigne() + "][" + destination.getColonne() + "]\n");
                    }
                    str.append("\n");
                }
            }
        }
        return str.toString();
    }

    public boolean isPartieTerminee() {
        return false;
    }

    public boolean isDeplacementPossible(Coordonnees origine, Coordonnees destination) {
        if ((origine.getLigne() < 0 || origine.getLigne() >= LIGNE) || (origine.getColonne() < 0 || origine.getColonne() >= COLONNE))
            return false;

        if (echequier[origine.getLigne()][origine.getColonne()] != null && echequier[origine.getLigne()][origine.getColonne()].getCouleur() == couleurJoueur)
        {
            for (Coordonnees destinationPossible : echequier[origine.getLigne()][origine.getColonne()].listeDeplacement(new Coordonnees(origine.getLigne(), origine.getColonne()), echequier))
            {
                if (destinationPossible.equals(destination))
                    return true;
            }
            return false;
        }

        return false;
    }

    public void appliquerDeplacement(String deplacement) {
        Coordonnees origine = new Coordonnees(deplacement.substring(0, 2));
        Coordonnees destination = new Coordonnees(deplacement.substring(2));

        echequier[destination.getLigne()][destination.getColonne()] = echequier[origine.getLigne()][origine.getColonne()];
        echequier[origine.getLigne()][origine.getColonne()] = null;

        changerTour();
    }

    private void changerTour() {
        if (couleurJoueur == Couleur.BLANC)
            couleurJoueur = Couleur.NOIR;
        else
            couleurJoueur = Couleur.BLANC;
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

