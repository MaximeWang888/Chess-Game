package joueur;

import echiquier.Coordonnee;
import echiquier.Echiquier;
import piece.Couleur;

import java.util.List;
import java.util.Scanner;

/**
 * Modélise un humain dans le jeu de l'échiquier.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class Humain extends Joueur {

    private Scanner scanner;

    public Humain (Couleur couleur, Scanner scanner)
    {
        super(couleur);
        this.scanner = scanner;
    }

    @Override
    public String coupJouer(Echiquier echiquier){
        System.out.print(">");
        String  deplacement = scanner.nextLine();
        Coordonnee origine = new Coordonnee();
        Coordonnee destination = new Coordonnee();
        boolean depPossible = false;

        while (!depPossible) {
            origine = origine.conversionEnCoord(deplacement.substring(0,2));
            destination = destination.conversionEnCoord(deplacement.substring(2,4));
            if (!echiquier.estVide(origine.getX(), origine.getY()) && echiquier.getPiece(origine).getCouleur() == this.getCouleur()) {
                List<Coordonnee> listttt = echiquier.getPiece(origine).listeDeplacement(echiquier);
                depPossible = echiquier.getPiece(origine).isDeplacementPossible(echiquier, destination, this.getCouleur(), listttt);
            }

            if (!depPossible) {
                System.out.print(">");
                deplacement = scanner.nextLine();
            }else{
                echiquier.deplacer(echiquier.getPiece(origine), destination);
            }
        }

        return deplacement;
    }
}
