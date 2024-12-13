package fr.draggas.project.chess;

public class Tour extends Pieces {

    public Tour(boolean couleurBlanche) {
        super("r", couleurBlanche);
    }

    public String affichage() {
        return super.affichageCouleur(name);
    }

    @Override
    public boolean verifMouvement(Position d, Position a) {
        return d.getX() == a.getX() || d.getY() == a.getY();
    }
}