package fr.draggas.project.chess;

public class Pion extends Pieces {

    public Pion(boolean couleurBlanche) {
        super("p", couleurBlanche);
    }

    public String affichage() {
        return super.affichageCouleur(name);
    }

    @Override
    public boolean verifMouvement(Position d, boolean prise, Position a) {
        if(prise){

        } else {
            if(d.getX()+1 == a.getX()){
                return false;
            }
        }
        return false;
    }
}
