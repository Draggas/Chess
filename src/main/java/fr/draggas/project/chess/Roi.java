package fr.draggas.project.chess;

public class Roi extends Pieces {

    public Roi(boolean couleurBlanche) {
        super("k", couleurBlanche);
    }

    public String affichage() {
        return super.affichageCouleur(name);
    }

    @Override
    public boolean verifMouvement(Position d, Position a, Chess e) {
        return ((a.getX() - d.getX() <= 1) && (a.getY() - d.getY() <= 1));
    }
}