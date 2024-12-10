package fr.draggas.project.chess;

public class Tour extends Pieces {

    public Tour(boolean couleurBlanche) {
        super("r", couleurBlanche);
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