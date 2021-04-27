package deplacement;

/**
 * Modélise un déplacement d'une case.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class Deplacement_Une_Case implements IDeplacement {

    /**
     * Permet de savoir si une pièce peut se déplacer d'une case.
     * @return True si le déplacement d'une case est autorisé, False dans le cas contraire
     */
    @Override
    public boolean peutAllerEn() {
        return true;
    }

}
