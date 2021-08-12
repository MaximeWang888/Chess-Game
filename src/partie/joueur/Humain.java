package partie.joueur;

import java.util.Scanner;

import pièces.Coordonnees;
import plateau.Plateau;

public class Humain implements IJoueur {
    private Scanner scanner;

    public Humain ()
    {
        scanner = new Scanner(System.in);
    }

    public String nouveauDeplacement(Plateau plateau) {

        System.out.print(">");
        String  deplacement = scanner.nextLine();
        boolean depPossible = false;
        
        while (!depPossible) {

            Coordonnees origine = new Coordonnees(deplacement.substring(0, 2));
            Coordonnees destination = new Coordonnees(deplacement.substring(2));

            depPossible = plateau.isDeplacementPossible(origine, destination);
            
            if (!depPossible) {
                System.out.print(">");
                deplacement = scanner.nextLine();
            }

        }

        return deplacement;
    }
}
