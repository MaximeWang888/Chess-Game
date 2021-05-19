package pièces;

import plateau.Plateau;

public class Coordonnees {
    
    private int ligne;
    private int colonne;

    public Coordonnees(int ligne, int colonne)
    {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public Coordonnees(String coord)
    {
        this.ligne = convertLigne(coord.charAt(1));
        this.colonne = convertColonne(coord.charAt(0));
    }

    private int convertLigne(char numLigne)
    {
        return Plateau.getTailleLigne() - Character.getNumericValue(numLigne);
    }

    private int convertColonne(char lettreColonne)
    {
        return (int)lettreColonne - 97;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    @Override
    public boolean equals(Object obj) 
    {
        // test sur les références
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        // test sur les classes
        if (this.getClass() != obj.getClass ())
            return false;
            
        // test sur les données
        Coordonnees other = (Coordonnees) obj;
        return (this.ligne == other.ligne && this.colonne == other.colonne);
    }

    @Override
    public String toString() {
        return Character.toString((char)(colonne + 97)) + Integer.toString(Plateau.getTailleLigne() - ligne);
    }

}
