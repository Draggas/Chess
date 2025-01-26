package fr.draggas.project.chess.model;

import java.util.List;

public abstract class Pieces {
    final String name;
    final boolean couleurBlanche;

    public Pieces(String name, boolean couleurBlanche) {
        this.name = name;
        this.couleurBlanche = couleurBlanche;
    }

    public String affichageCouleur(String a){
        if(this.couleurBlanche) return a;
        else return a.toUpperCase();
    }
    
    public abstract String affichage();

    public abstract List<Position> moovePossible(Position p, Chess e);

    public String getName(){
        return name;
    }
    public boolean couleurBlanche(){
        return couleurBlanche;
    }

}
