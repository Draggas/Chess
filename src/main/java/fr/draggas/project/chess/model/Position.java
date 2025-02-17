package fr.draggas.project.chess.model;

/**
 * Classe représentant une position sur un échiquier.
 */
public class Position {
    private static final int PRIME = 31; // Constante pour le calcul du hashCode
    private int x; // Coordonnée en x (colonne)
    private int y; // Coordonnée en y (ligne)

    /**
     * Constructeur qui initialise une position avec les coordonnées x et y.
     * @param x La colonne de la position (entre 1 et 8).
     * @param y La rangée de la position (entre 1 et 8).
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Constructeur qui initialise une position avec une notation en chaîne de caractères.
     * @param notation Notation de position (par exemple, "a1").
     */
    public Position(String notation){
        this(notation.charAt(0) - 'a' + 1, Character.getNumericValue(notation.charAt(1)));
    }

    /**
     * Vérifie si les coordonnées sont valides (entre 1 et 8).
     * @param x La coordonnée x à vérifier.
     * @param y La coordonnée y à vérifier.
     * @return true si les coordonnées sont valides, false sinon.
     */    
    public static boolean verifValeur(int x, int y){
        return (x >= 1 && x <= 8 && y >= 1 && y <= 8);
    }

    /**
     * Vérifie si la position est valide.
     * @param position La position à vérifier.
     * @return true si la position est valide, false sinon.
     */
    private static boolean verifValeur(Position position){
        return Position.verifValeur(position.getX(), position.getY());
    }

    /**
     * Vérifie si la notation de position est valide.
     * @param notation La notation de position à vérifier.
     * @return true si la notation est valide, false sinon.
     */
    public static boolean verifValeur(String notation){
        return Position.verifValeur(new Position(notation));
        }

        /**
         * Retourne une représentation sous forme de chaîne de caractères de la position.
         * Par exemple, pour x=5 et y=1, cela retourne "e1".
         * @return La représentation de la position en notation échiquier.
         */
        @Override
        public String toString() {
        char column = (char) ('a' + x - 1);
        return "" + column + y;
        }

        @Override
        public int hashCode() {
        int result = 1;
        result = PRIME * result + x;
        result = PRIME * result + y;
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;
        Position position = (Position) other;
        return this.x == position.getX() && this.y == position.getY();
    }

    /**
     * Obtient la coordonnée x de la position.
     * @return La coordonnée x.
     */
    public int getX(){
        return this.x;
    }

    /**
     * Obtient la coordonnée y de la position.
     * @return La coordonnée y.
     */
    public int getY(){
        return this.y;
    }

    /**
     * Définit la coordonnée x de la position.
     * @param x La nouvelle coordonnée x.
     * @throws IllegalArgumentException si la valeur x n'est pas entre 1 et 8.
     */
    public void setX(int x){
        if (!verifValeur(x, this.y))
            throw new IllegalArgumentException("Les valeurs doivent être entre 1 et 8");
        this.x = x;
    }

    /**
     * Définit la coordonnée y de la position.
     * @param y La nouvelle coordonnée y.
     * @throws IllegalArgumentException si la valeur y n'est pas entre 1 et 8.
     */
    public void setY(int y){
        if (!verifValeur(this.x, y))
            throw new IllegalArgumentException("Les valeurs doivent être entre 1 et 8");
        this.y = y;
    }
}