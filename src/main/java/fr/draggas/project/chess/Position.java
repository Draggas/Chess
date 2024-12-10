package fr.draggas.project.chess;

public class Position {
    int position_x;
    int position_y;

    public Position(int position_x, int position_y){
        if(position_x < 1 || position_x > 8 || position_y < 1 || position_y > 8){
            System.err.println("Valeur de la position incorrecte. Doit comprendre une valeur entre 1 et 8 pour le x et le y, valeur actuelle x/y : " + position_x + "/" + position_y);
        }
        this.position_x = position_x;
        this.position_y = position_y;
    }

    // Redéfinition de Hashcode et de Equals pour valider le ContainsKey
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + position_x;
        result = prime * result + position_y;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Position p = (Position) o;
        if (position_x != p.position_x) return false;
        if (position_y != p.position_y) return false;
        return true;
    }

    public int getX(){
        return position_x;
    }
    public int getY(){
        return position_y;
    }
    public void setX(int x){
        position_x = x;
    }
    public void setY(int y){
        position_y = y;
    }
}
