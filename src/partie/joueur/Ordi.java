package partie.joueur;

import java.util.ArrayList;
import java.util.Collections;

import pièces.Coordonnees;
import plateau.Plateau;

public class Ordi implements IJoueur {

    public String nouveauDeplacement(Plateau plateau) {

        ArrayList<Coordonnees> listePiece = plateau.getListePiece();
        Collections.shuffle(listePiece);
        Coordonnees origine = listePiece.get(0);

        ArrayList<Coordonnees> listeDestination = plateau.getListeDestination(origine);
        Collections.shuffle(listeDestination);
        Coordonnees destination = listeDestination.get(0);

        return origine.toString() + destination.toString();
    }
    
}
