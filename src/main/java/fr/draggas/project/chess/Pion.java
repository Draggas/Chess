package fr.draggas.project.chess;

import java.util.ArrayList;
import java.util.List;

public class Pion extends Pieces {

    public Pion(boolean couleurBlanche) {
        super("p", couleurBlanche);
    }

    public String affichage() {
        return super.affichageCouleur(name);
    }

    @Override
    public List<Position> moovePossible(Position p, Chess e) {
        List<Position> l = new ArrayList<>();
    
        int direction = couleurBlanche() ? 1 : -1;
    
        Position a = new Position(p.getX(), p.getY() + direction);
        if (e.caseVide(a)) {
            l.add(a);

            if ((couleurBlanche() && p.getY() == 2) || (!couleurBlanche() && p.getY() == 7)) {
                Position a2 = new Position(p.getX(), p.getY() + 2 * direction);
                if (e.caseVide(a2)) {
                    l.add(a2);
                }
            }
        }
        for(int i = -1; i*i==1; i=i+2){
            Position d = new Position(p.getX() + i, p.getY() + direction);
            if (d.getY() >= 1 && d.getY() <= 8){
                if (!e.caseVide(d) && e.get(d).couleurBlanche() != couleurBlanche()) {
                    l.add(d);
                }
            }
            if(e.priseEnPassantPossible != null && e.priseEnPassantPossible.equals(d)){ 
                l.add(d);
            }
        }
        return l;
    }
    
}
