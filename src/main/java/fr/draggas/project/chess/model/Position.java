package fr.draggas.project.chess.model;

public class Position {
    int position_x;
    int position_y;

    public Position(int position_x, int position_y){
        this.position_x = position_x;
        this.position_y = position_y;
    }

    public Position(char position_x, int position_y){
        this((int)(position_x - 'a')+1, position_y);
    }

    public Position(char position_x, char position_y){
        this((int)(position_x - 'a')+1, Character.getNumericValue(position_y));
    }

    public Position(String s){
        this(s.charAt(0), Character.getNumericValue(s.charAt(1)));
    }

    public static boolean verifValeur(int x, int y){
        return (x >= 1 && x <= 8 && y >= 1 && y <= 8);
    }

    public static boolean verifValeur(char x, char y){
        return Position.verifValeur(new Position(x, y));
    }

    public static boolean verifValeur(Position p){
        return Position.verifValeur(p.getX(), p.getY());
    }

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
