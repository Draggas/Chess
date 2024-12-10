package fr.draggas.project.chess;

public class Fou extends Pieces {

    public Fou(boolean couleurBlanche) {
        super("b", couleurBlanche);
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

