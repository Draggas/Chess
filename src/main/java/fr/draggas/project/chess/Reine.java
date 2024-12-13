package fr.draggas.project.chess;

public class Reine extends Pieces {

    public Reine(boolean couleurBlanche) {
        super("q", couleurBlanche);
    }

    public String affichage() {
        return super.affichageCouleur(name);
    }

    @Override
    public boolean verifMouvement(Position d, Position a) {
        Tour t = new Tour(couleurBlanche);
        Fou f = new Fou(couleurBlanche);
        return f.verifMouvement(d, a) || t.verifMouvement(d, a);
    }
}