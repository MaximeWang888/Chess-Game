package partie.joueur;

public class FabriqueJoueur {
    public IJoueur creationJoueur(TypeJoueur type)
    {
        switch (type)
        {
            case HUMAIN : return new Humain();
            case ORDI   : return new Ordi();
            default     : return null;
        }
    }
}
