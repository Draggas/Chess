package fr.draggas.project.chess;

public class Tour extends Pieces {

    public Tour(boolean couleurBlanche) {
        super("r", couleurBlanche);
    }

    public String affichage() {
        return super.affichageCouleur(name);
    }

    @Override
    public boolean verifMouvement(Position d, Position a, Chess e) {
        if(d.getX() == a.getX()){
            int incr = 0;
            if(d.getY() > a.getY()) incr--;
            if(d.getY() < a.getY()) incr++;
            for(int i = d.getY() + incr; i != a.getY(); i = i + incr){
                if(!e.caseVide(new Position(d.getX(), i))) return false;
            }
            return true;
        } else if(d.getY() == a.getY()){
            int incr = 0;
            if(d.getX() > a.getX()) incr--;
            if(d.getX() < a.getX()) incr++;
            for(int i = d.getX() + incr; i != a.getX(); i += incr){
                if(!e.caseVide(new Position(i, d.getY()))) return false;
            }
            return true;
        }
        return false;
    }
}