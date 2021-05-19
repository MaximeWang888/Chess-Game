package partie;

import partie.joueur.FabriqueJoueur;
import partie.joueur.TypeJoueur;
import partie.joueur.IJoueur;
import plateau.Plateau;

public class Partie {
    private Plateau plateau;

    public Partie ()
    {
        plateau = new Plateau();
    }

    public void jouerPartie() {
        FabriqueJoueur fj = new FabriqueJoueur();
        //deux joueurs
        IJoueur J1 = fj.creationJoueur(TypeJoueur.HUMAIN);
        IJoueur J2 = fj.creationJoueur(TypeJoueur.ORDI);

        //tant que la partie n'est pas finie
        boolean partieTerminee = false;
        while (!partieTerminee) {

            partieTerminee = nouveauTour(J1);
            if (!partieTerminee)
                partieTerminee = nouveauTour(J2);
        }
        
    }

    private boolean nouveauTour(IJoueur joueur) {

        //affichage plateau
        //System.out.println(plateau.getListeDeplacement());
        System.out.println(plateau);
        System.out.println("Au tour des " + plateau.getCouleurJoueur() + " de jouer");

        //verification partie terminee
        boolean partieTerminee = plateau.isPartieTerminee();

        //deplacement
        if (!partieTerminee) {
            String coup = joueur.nouveauDeplacement(plateau);
            System.out.println("Le joueur " + plateau.getCouleurJoueur() + " a joué : " + coup);
            plateau.appliquerDeplacement(coup);

        }
        return partieTerminee;
    }
}