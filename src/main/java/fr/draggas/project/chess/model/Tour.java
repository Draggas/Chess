package fr.draggas.project.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Tour extends Pieces {
    private boolean roque;

    public Tour(boolean couleurBlanche) {
        super('t', couleurBlanche);
        roque = true;
    }

    @Override
    public List<Position> deplacementsPossible(Position positionDeLaTour, Chess echiquier) {
        List<Position> listeDeplacementsPossible = new ArrayList<>();
        
        checkDirection(listeDeplacementsPossible, positionDeLaTour, echiquier, 1, 0);
        checkDirection(listeDeplacementsPossible, positionDeLaTour, echiquier, -1, 0);
        checkDirection(listeDeplacementsPossible, positionDeLaTour, echiquier, 0, 1);
        checkDirection(listeDeplacementsPossible, positionDeLaTour, echiquier, 0, -1);
        
        return listeDeplacementsPossible;
    }

    private void checkDirection(List<Position> listeDeplacementsPossible, Position positionDeLaTour, Chess echiquier, int deplacementX, int deplacementY) {
        int x = positionDeLaTour.getX();
        int y = positionDeLaTour.getY();
        
        while (true) {
            x += deplacementX;
            y += deplacementY;
            if (!Position.verifValeur(x, y)){
                break;
            }
            Position positionCible = new Position(x, y);
            if (echiquier.positionEstVide(positionCible)) {
                listeDeplacementsPossible.add(positionCible);
            } else {
                if (echiquier.obtenirPieceALaPosition(positionCible).getCouleur() != getCouleur()) {
                    listeDeplacementsPossible.add(positionCible);
                }
                break;
            }
        }
    }

    public boolean getRoque(){
        return roque;
    }

    public void setRoque(boolean roque){
       this.roque = roque;
    }
}