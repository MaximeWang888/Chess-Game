package partie;
import partie.joueur.FabriqueJoueur;
import partie.joueur.IJoueur;
import partie.joueur.TypeJoueur;
import plateau.Plateau;

public class Partie {
    private Plateau plateau ;

    public Partie (){
        plateau = new Plateau();
    }

    public void jouerPartie()
    {
        FabriqueJoueur fj = new FabriqueJoueur();
        //deux joueurs
        IJoueur J1 = fj.creationJoueur(TypeJoueur.HUMAIN);
        IJoueur J2 = fj.creationJoueur(TypeJoueur.HUMAIN);

        //tant que la partie n'est pas finie
        boolean partieTerminee = false;
        while (!partieTerminee)
        {
            partieTerminee = nouveauTour(J1);
            if (!partieTerminee)
                partieTerminee = nouveauTour(J2);
        }

    }

    private boolean nouveauTour(IJoueur joueur)
    {
        //affichage plateau
        System.out.println(plateau);

        //verification partie terminee
        boolean partieTerminee = plateau.isPartieTerminee();

        //deplacement
        if (!partieTerminee)
            plateau.appliquerDeplacement(joueur.nouveauDeplacement(plateau));

        return partieTerminee;
    }
}

