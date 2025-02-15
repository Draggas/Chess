package fr.draggas.project.chess.model;

import java.util.List;

public abstract class Pieces {
    final char caractereId;
    final boolean couleurBlanche;

    public Pieces(char caractereId, boolean couleurBlanche) {
        this.caractereId = caractereId;
        this.couleurBlanche = couleurBlanche;
    }

    public char getID(){
        if(this.couleurBlanche) return caractereId;
        else return Character.toUpperCase(caractereId);
    }

    public abstract List<Position> deplacementsPossible(Position p, Chess e);

    public boolean getCouleur(){
        return couleurBlanche;
    }

}
