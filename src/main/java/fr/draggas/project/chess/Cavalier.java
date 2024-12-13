package fr.draggas.project.chess;

public class Cavalier extends Pieces {

    public Cavalier(boolean couleurBlanche) {
        super("n", couleurBlanche);
    }

    public String affichage() {
        return super.affichageCouleur(name);
    }

    @Override
    public boolean verifMouvement(Position d, Position a) {
        for(int x=-2;x<=2;x++){
            if(x == 0) x = 1;
            for(int y=-2;y<=2;y++){
                if(y == 0) y = 1;
                if(x != y && x*-1 != y*-1 && d.getX()+x == a.getX() && d.getY()+y == a.getY()) return true;
            }
        }
        return false;
    }
}
