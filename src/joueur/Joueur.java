package joueur;

import echiquier.Coordonnee;
import echiquier.Echiquier;
import echiquier.IJoueur;
import piece.Couleur;

/**
 * Modélise un joueur dans le jeu d'Echecs.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public abstract class Joueur implements IJoueur {

    private Couleur couleur;

    public Joueur(Couleur couleur) {
        this.couleur = couleur;
    }

    @Override
    public Couleur getCouleur(){
        return couleur;
    }

    @Override
    public void jouer(Echiquier echiquier, boolean attentionRoiPresqueEnEchec){
        String coup = this.coupJouer(echiquier, attentionRoiPresqueEnEchec);
        System.out.println("Le joueur " + this.getCouleur() + " a joué : " + coup);
    }

    /**
     * Permet d'abandonner la partie.
     */
    public void abandonner(){

    }

    /**
     * Permet de proposer nulle à la partie encours.
     */
    public void proposerNulle(){

    }
}
