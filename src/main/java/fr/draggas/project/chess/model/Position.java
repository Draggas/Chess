package fr.draggas.project.chess.model;

public class Position {
    int positionX;
    int positionY;

    public Position(int positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Position(char positionX, int positionY){
        this((int)(positionX - 'a')+1, positionY);
    }

    public Position(char positionX, char positionY){
        this((int)(positionX - 'a')+1, Character.getNumericValue(positionY));
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

    public static boolean verifValeur(Position position){
        return Position.verifValeur(position.getX(), position.getY());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + positionX;
        result = prime * result + positionY;
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;
        Position position = (Position) other;
        if (positionX != position.positionX) return false;
        if (positionY != position.positionY) return false;
        return true;
    }

    public int getX(){
        return this.positionX;
    }
    public int getY(){
        return this.positionY;
    }
    public void setX(int positionX){
        this.positionX = positionX;
    }
    public void setY(int positionY){
        this.positionY = positionY;
    }
}
