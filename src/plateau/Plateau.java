package plateau;
import java.util.ArrayList;

import pièces.Coordonnees;
import pièces.Couleur;
import pièces.FabriquePiece;
import pièces.IPiece;
import pièces.TypePiece;

public class Plateau {

    private static int LIGNE = 8;
    private static int COLONNE = 8;
    private Couleur couleurJoueur;
    
    private IPiece[][] echiquier = new IPiece[LIGNE][COLONNE];

    public Plateau () {
        initialiserPlateau();
        couleurJoueur = Couleur.BLANC;
    }

    private void initialiserPlateau() {
        FabriquePiece fp = new FabriquePiece();
        //------------------------ TOUR ----------------------------------
        echiquier[0][7] = fp.creationPiece(Couleur.NOIR, TypePiece.TOUR);
        echiquier[0][0] = fp.creationPiece(Couleur.NOIR, TypePiece.TOUR);

        echiquier[7][7] = fp.creationPiece(Couleur.BLANC, TypePiece.TOUR);
        echiquier[7][0] = fp.creationPiece(Couleur.BLANC, TypePiece.TOUR);

        //------------------------ FOU ----------------------------------
        echiquier[0][5] = fp.creationPiece(Couleur.NOIR, TypePiece.FOU);
        echiquier[0][2] = fp.creationPiece(Couleur.NOIR, TypePiece.FOU);

        echiquier[7][5] = fp.creationPiece(Couleur.BLANC, TypePiece.FOU);
        echiquier[7][2] = fp.creationPiece(Couleur.BLANC, TypePiece.FOU);

        //---------------------- CAVALIER -----------------------------------
        echiquier[0][1] = fp.creationPiece(Couleur.NOIR, TypePiece.CAVALIER);
        echiquier[0][6] = fp.creationPiece(Couleur.NOIR, TypePiece.CAVALIER);

        echiquier[7][6] = fp.creationPiece(Couleur.BLANC, TypePiece.CAVALIER);
        echiquier[7][1] = fp.creationPiece(Couleur.BLANC, TypePiece.CAVALIER);

        //------------------------ ROI ----------------------------------
        echiquier[0][4] = fp.creationPiece(Couleur.NOIR, TypePiece.ROI);
        echiquier[7][4] = fp.creationPiece(Couleur.BLANC, TypePiece.ROI);

        //------------------------- DAME ---------------------------------
        echiquier[0][3] = fp.creationPiece(Couleur.NOIR, TypePiece.DAME);
        echiquier[7][3] = fp.creationPiece(Couleur.BLANC, TypePiece.DAME);
    }

    public static int getTailleLigne() {
        return LIGNE;
    }

    public static int getTailleColonne() {
        return COLONNE;
    }

    public String getListeDeplacement() {

        StringBuilder str = new StringBuilder();
        for (int ligne = 0; ligne < LIGNE; ligne++) {

            for (int colonne = 0; colonne < COLONNE; colonne++) {

                if (echiquier[ligne][colonne] != null) {

                    str.append(echiquier[ligne][colonne].getNom() + " peut se déplacer en :\n");
                    for (Coordonnees destination : echiquier[ligne][colonne].listeDeplacement(new Coordonnees(ligne, colonne), echiquier)) {

                        if (isDeplacementPossible(new Coordonnees(ligne, colonne), destination))
                            str.append("[" + destination.getLigne() + "][" + destination.getColonne() + "]\n");
                    }
                    str.append("\n");
                }
            }
        }
        return str.toString();
    }

    private Boolean isExisteDeplacement() {

        for (int ligne = 0; ligne < LIGNE; ligne++) {

            for (int colonne = 0; colonne < COLONNE; colonne++) {

                if (echiquier[ligne][colonne] != null && echiquier[ligne][colonne].getCouleur() == couleurJoueur) {

                    for (Coordonnees destination : echiquier[ligne][colonne].listeDeplacement(new Coordonnees(ligne, colonne), echiquier)) {

                        if (isDeplacementPossible(new Coordonnees(ligne, colonne), destination))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isPartieTerminee() {

        //plus de déplacement possible + echec mat
        if (Boolean.TRUE.equals(!isExisteDeplacement())) {

            if (verifierEchec()) {

                System.out.print("Echec et mat ! les ");
                if (couleurJoueur == Couleur.BLANC)
                    System.out.print("noirs");
                else
                    System.out.print("blancs");
                System.out.println(" ont gagné.");
            }
                
            else 
                System.out.println("Pat !");

            return true;
        }
        //plus de déplacement possible
        return false;
    }

    public boolean isDeplacementPossible(Coordonnees origine, Coordonnees destination) {

        if ((origine.getLigne() < 0 || origine.getLigne() >= LIGNE) || (origine.getColonne() < 0 || origine.getColonne() >= COLONNE))
            return false;
        
        if (echiquier[origine.getLigne()][origine.getColonne()] != null && echiquier[origine.getLigne()][origine.getColonne()].getCouleur() == couleurJoueur) {

            ArrayList<Coordonnees> listeDeplacements = verifierListeDeplacement(echiquier[origine.getLigne()][origine.getColonne()].listeDeplacement(new Coordonnees(origine.getLigne(), origine.getColonne()), echiquier), origine);

            for (Coordonnees destinationPossible : listeDeplacements) {

                if (destinationPossible.equals(destination))
                    return true;
            }
            return false;
        }

        return false;
    }

    private ArrayList<Coordonnees> verifierListeDeplacement(ArrayList<Coordonnees> listeDeplacements, Coordonnees origine) {

        ArrayList<Coordonnees> deplacementSur = new ArrayList<>();
        for(Coordonnees destination : listeDeplacements) {

            IPiece temp =  echiquier[destination.getLigne()][destination.getColonne()];

            echiquier[destination.getLigne()][destination.getColonne()] = echiquier[origine.getLigne()][origine.getColonne()];
            echiquier[origine.getLigne()][origine.getColonne()] = null;

            if (!verifierEchec())
                deplacementSur.add(destination);

            echiquier[origine.getLigne()][origine.getColonne()] = echiquier[destination.getLigne()][destination.getColonne()];
            echiquier[destination.getLigne()][destination.getColonne()] = temp;
        }

        return deplacementSur;
    }

    public void appliquerDeplacement(String deplacement) {

        Coordonnees origine = new Coordonnees(deplacement.substring(0, 2));
        Coordonnees destination = new Coordonnees(deplacement.substring(2));

        echiquier[destination.getLigne()][destination.getColonne()] = echiquier[origine.getLigne()][origine.getColonne()];
        echiquier[origine.getLigne()][origine.getColonne()] = null;

        changerTour();
    }

    private void changerTour() {

        if (couleurJoueur == Couleur.BLANC)
            couleurJoueur = Couleur.NOIR;
        else
            couleurJoueur = Couleur.BLANC;
    }

    public Couleur getCouleurJoueur() {
        return couleurJoueur;
    }

    private boolean verifierEchec() {

        //Obtenir les coordonnées du roi adverse
        Coordonnees coordRoi = trouverRoi();

        //Double boucle for
        //retrouver les pièces adverses avec couleur
        for (int ligne = 0; ligne < LIGNE; ligne++) {

            for (int colonne = 0; colonne < COLONNE; colonne++) {

                if (echiquier[ligne][colonne] != null && echiquier[ligne][colonne].getCouleur() != couleurJoueur) {

                    //Pour chaque piece adverse on verifie ses déplacements possibles
                    for (Coordonnees dep : echiquier[ligne][colonne].listeDeplacement(new Coordonnees(ligne, colonne), echiquier)) {

                        //Verifier si une peut se déplacer aux coordonnée du roi adverse
                        if (dep.equals(coordRoi))
                            return true;
                    }
                }
            }
        }
        
        return false;
    }

    private Coordonnees trouverRoi() {

        for (int ligne = 0; ligne < LIGNE; ligne++) {

            for (int colonne = 0; colonne < COLONNE; colonne++) {

                if (echiquier[ligne][colonne] != null) {

                    if (Character.toUpperCase(echiquier[ligne][colonne].getNom()) == 'R' &&  echiquier[ligne][colonne].getCouleur() == couleurJoueur)
                        return new Coordonnees(ligne, colonne);
                }
            }
        }

        return null;
    }

    public ArrayList<Coordonnees> getListePiece () {

        ArrayList<Coordonnees> listePiece = new ArrayList<>();

        for (int ligne = 0; ligne < LIGNE; ligne++) {

            for (int colonne = 0; colonne < COLONNE; colonne++) {

                if (echiquier[ligne][colonne] != null && echiquier[ligne][colonne].getCouleur() == couleurJoueur)
                    listePiece.add(new Coordonnees(ligne, colonne));
            }
        }

        return listePiece;
    }

    public ArrayList<Coordonnees> getListeDestination(Coordonnees origine) {

        ArrayList<Coordonnees> listeDestination = new ArrayList<>();
        for (Coordonnees destination : echiquier[origine.getLigne()][origine.getColonne()].listeDeplacement(origine, echiquier)) {

            if (isDeplacementPossible(origine, destination))
                listeDestination.add(destination);
        }

        return listeDestination;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------

    public String toString() {

        StringBuilder str = new StringBuilder();

        str.append("\n"+ affichageBandeau());   //Bandeau supérieur
        
        str.append(affichageBordure());  //Bordure

        for (int nomLigne = LIGNE; nomLigne > 0; nomLigne--) {//Ligne du tableau

            str.append(nomLigne);    //Contenu de la ligne
            int ligne = LIGNE - nomLigne;
            for (int colonne = 0; colonne < COLONNE; colonne++) {

                str.append(" | ");
                if (echiquier[ligne][colonne] != null)
                    str.append(echiquier[ligne][colonne].getNom());

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
        for (int colonne = 0; colonne < COLONNE; colonne++)
            str.append("--- ");    
        str.append("   \n");

        return str.toString();
    }
    
}
