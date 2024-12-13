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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouvement'");
    }
}