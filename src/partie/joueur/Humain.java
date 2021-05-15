package partie.joueur;

import pièces.Coordonnees;
import plateau.Plateau;

import java.util.Scanner;

public class Humain implements IJoueur {
    private Scanner scanner;

    public Humain ()
    {
        scanner = new Scanner(System.in);
    }

    public String nouveauDeplacement(Plateau plateau) {
        String  deplacement = scanner.nextLine();
        boolean depPossible = false;

        while (!depPossible) {
            Coordonnees origine = new Coordonnees(deplacement.substring(0, 2));
            Coordonnees destination = new Coordonnees(deplacement.substring(2));

            depPossible = plateau.isDeplacementPossible(origine, destination);

            if (!depPossible)
                deplacement = scanner.nextLine();
        }

        return deplacement;
    }
}
