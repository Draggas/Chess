package fr.draggas.project.chess;

public class Fou extends Pieces {

    public Fou(boolean couleurBlanche) {
        super("b", couleurBlanche);
    }

    public String affichage() {
        return super.affichageCouleur(name);
    }

    @Override
    public boolean verifMouvement(Position d, Position a) {
        for(int i=1;i<=7;i++){
            if((d.getX()+i == a.getX()) && (d.getY()+i == a.getY())) return true;
            if((d.getX()-i == a.getX()) && (d.getY()+i == a.getY())) return true;
            if((d.getX()+i == a.getX()) && (d.getY()-i == a.getY())) return true;
            if((d.getX()-i == a.getX()) && (d.getY()-i == a.getY())) return true;
        }
        return false;
    }
}

