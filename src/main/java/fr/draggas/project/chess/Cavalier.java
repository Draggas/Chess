package fr.draggas.project.chess;

public class Cavalier extends Pieces {

    public Cavalier(boolean couleurBlanche) {
        super("n", couleurBlanche);
    }

    public String affichage() {
        return super.affichageCouleur(name);
    }

    @Override
    public boolean verifMouvement(Position d, boolean prise, Position a) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouvement'");
    }
    
}
