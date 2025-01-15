package fr.draggas.project.chess;

import java.util.ArrayList;
import java.util.List;

public class Fou extends Pieces {

    public Fou(boolean couleurBlanche) {
        super("b", couleurBlanche);
    }

    public String affichage() {
        return super.affichageCouleur(name);
    }
    
    @Override
    public List<Position> moovePossible(Position p, Chess e) {
        List<Position> l = new ArrayList<>();
        
        checkDirection(l, p, e, 1, 1);
        checkDirection(l, p, e, -1, 1);
        checkDirection(l, p, e, 1, -1);
        checkDirection(l, p, e, -1, -1);
    
        return l;
    }
    
    private void checkDirection(List<Position> l, Position p, Chess e, int dx, int dy) {
        int x = p.getX();
        int y = p.getY();
    
        while (true) {
            x += dx;
            y += dy;

            if (!Position.verifValeur(x, y)){
                break;
            }
            Position test = new Position(x, y);

            if (e.caseVide(test)) {
                l.add(test);
            } else {
                if (e.get(test).couleurBlanche() != couleurBlanche()) {
                    l.add(test);
                }
                break;
            }
        }
    }
}
