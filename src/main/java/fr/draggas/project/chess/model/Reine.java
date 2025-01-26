package fr.draggas.project.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Reine extends Pieces {

    public Reine(boolean couleurBlanche) {
        super("q", couleurBlanche);
    }

    public String affichage() {
        return super.affichageCouleur(name);
    }
    
    @Override
    public List<Position> moovePossible(Position p, Chess e){
        List<Position> l = new ArrayList<>();
        Tour t = new Tour(couleurBlanche);
        Fou f = new Fou(couleurBlanche);
        l.addAll(t.moovePossible(p, e));
        l.addAll(f.moovePossible(p, e));
        return l;
    }
}