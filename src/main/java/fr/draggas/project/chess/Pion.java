package fr.draggas.project.chess;

public class Pion extends Pieces {

    public Pion(boolean couleurBlanche) {
        super("p", couleurBlanche);
    }

    public String affichage() {
        return super.affichageCouleur(name);
    }

    @Override
    public String mouvement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouvement'");
    }
    
}
