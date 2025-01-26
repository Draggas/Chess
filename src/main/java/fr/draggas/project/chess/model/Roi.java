package fr.draggas.project.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Roi extends Pieces {
    private boolean roque;
    private boolean grandRoque;

    public Roi(boolean couleurBlanche) {
        super("k", couleurBlanche);
        roque = true;
        grandRoque = true;
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
                    l.add(n);
                }
            }
        }

        int y = couleurBlanche() ? 1 : 8;
        Position poseRoque = new Position(8,y);
        Position poseGrandRoque = new Position(1,y);
        if(roque && !e.caseVide(poseRoque) && e.get(poseRoque).getClass() == Tour.class && e.caseVide(new Position(7, y)) && e.caseVide(new Position(6, y)) && ((Tour) e.get(poseRoque)).roquePossible()){
            l.add(new Position(7,y));
        }
        if(grandRoque && !e.caseVide(poseGrandRoque) && e.get(poseGrandRoque).getClass() == Tour.class && e.caseVide(new Position(2, y)) && e.caseVide(new Position(4, y)) && e.caseVide(new Position(3, y)) && ((Tour) e.get(poseGrandRoque)).roquePossible()){
            l.add(new Position(2,y));
        }
        return l;
    }
    
    public boolean roquePossible(){
        return roque;
    }
    
    public boolean grandRoquePossible(){
        return grandRoque;
    }

    public void setGrandRoque(boolean grandRoque){
        this.grandRoque = grandRoque;
     }

    public void setRoque(boolean roque){
       this.roque = roque;
    }
}