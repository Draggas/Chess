package fr.draggas.project.chess;

public class Roi extends Pieces {

    public Roi(boolean couleurBlanche) {
        super("k", couleurBlanche);
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