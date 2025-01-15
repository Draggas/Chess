package fr.draggas.project.chess;

import java.util.ArrayList;
import java.util.List;

public class Roi extends Pieces {

    public Roi(boolean couleurBlanche) {
        super("k", couleurBlanche);
    }

    public String affichage() {
        return super.affichageCouleur(name);
    }
    
    @Override
    public List<Position> moovePossible(Position p, Chess e) {
        List<Position> l = new ArrayList<>();
        
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        
        for (int i = 0; i < dx.length; i++) {
            if(Position.verifValeur(p.getX() + dx[i], p.getY() + dy[i])){
                Position n = new Position(p.getX() + dx[i], p.getY() + dy[i]);
                if (e.caseVide(n) || e.get(n).couleurBlanche() != couleurBlanche()) {
                    if (!enEchec(n, e)) {
                        l.add(n);
                    }
                }
            }
        }
        return l;
    }
    
    private boolean enEchec(Position n, Chess e) {
        for (Position p : e.echiquier.keySet()) {
            if(e.get(p).couleurBlanche() != this.couleurBlanche()){
                Pieces d = e.get(p);
                if (d.moovePossible(p, e).contains(n)) {
                    return true;
                }
            }
        }
        return false;
    }
    
}