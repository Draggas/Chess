package fr.draggas.project.chess;

public class Reine extends Pieces {

    public Reine(boolean couleurBlanche) {
        super("q", couleurBlanche);
    }

    public String affichage() {
        return super.affichageCouleur(name);
    }

    @Override
    public boolean verifMouvement(Position d, Position a, Chess e) {
        Tour t = new Tour(couleurBlanche);
        Fou f = new Fou(couleurBlanche);
        if(d.getX() == a.getX() || d.getY() == a.getY()){
            return t.verifMouvement(d, a, e);
        } else {
            return f.verifMouvement(d, a, e);
        }
    }
}