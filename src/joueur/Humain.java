package joueur;

import java.util.Scanner;

/**
 * Modélise un humain dans le jeu de l'échiquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class Humain extends Joueur {

    /** Le scanner qui permet à l'humain de saisir son déplacement */
    private Scanner sc;

    public Humain (TypeJoueur type)
    {
        super(type);
        this.sc = new Scanner(System.in);
    }

}
