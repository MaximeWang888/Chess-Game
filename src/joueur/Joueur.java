package joueur;

/**
 * Modélise un joueur dans le jeu d'Echecs.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public abstract class Joueur {

    private TypeJoueur type;

    private String nom;

    public Joueur(TypeJoueur type, String nom) {
        this.type = type;
        this.nom = nom;
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
