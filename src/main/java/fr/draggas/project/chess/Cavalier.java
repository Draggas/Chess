package fr.draggas.project.chess;

import java.util.ArrayList;
import java.util.List;

public class Cavalier extends Pieces {

    public Cavalier(boolean couleurBlanche) {
        super("n", couleurBlanche);
    }

    public String affichage() {
        return super.affichageCouleur(name);
    }

    @Override
    public List<Position> moovePossible(Position p, Chess e) {
        List<Position> l = new ArrayList<>();
        
        int[][] moves = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };
    
        for (int[] move : moves) {
            int x = p.getX() + move[0];
            int y = p.getY() + move[1];
            if(Position.verifValeur(x, y)){
                Position test = new Position(x, y);
                if (e.caseVide(test) || e.get(test).couleurBlanche() != couleurBlanche()) {
                    l.add(test);
                }
            }

        }
    
        return l;
    }
    
}
