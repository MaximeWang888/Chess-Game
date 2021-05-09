public class Main {
    public static void main (String [] args){
        Plateau plateau = new Plateau();
        plateau.initialiserPlateau();
        System.out.println(plateau);
        System.out.println(plateau.getListeDeplacement());
    }
}
