package deplacement;

/**
 * Modélise un déplacement en diagonal.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class Deplacement_Diagonal implements IDeplacement{

    /**
     * Permet de savoir si une pièce peut se déplacer en diagonal.
     * @return True si le déplacement en diagonal est autorisé, False dans le cas contraire
     */
    @Override
    public boolean peutAllerEn() {
        return true;
    }
}
