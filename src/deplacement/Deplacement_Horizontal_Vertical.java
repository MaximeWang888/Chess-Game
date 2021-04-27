package deplacement;

/**
 * Modélise un déplacement à l'horizontal et à la vertical.
 * @author  Fabien Rondan, Maxime Wang, Sebastien Ramirez
 * @version 1.0
 */
public class Deplacement_Horizontal_Vertical implements IDeplacement{

    /**
     * Permet de savoir si une pièce peut se déplacer en horizontal ou en vertical.
     * @return True si les déplacements horizontal/vertical sont autorisés, False dans le cas contraire
     */
    @Override
    public boolean peutAllerEn() {
        return true;
    }
}
