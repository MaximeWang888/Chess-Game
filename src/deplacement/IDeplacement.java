package deplacement;

/**
 * Modélise un déplacement possible.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public interface IDeplacement {

    /**
     * Permet de savoir si un déplacement est autorisé ou non.
     * @return True si le déplacement est autorisé, False dans le cas contraire
     */
    boolean peutAllerEn();

}
