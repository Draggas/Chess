package fr.draggas.project.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Cavalier extends Pieces {

    public Cavalier(boolean couleurBlanche) {
        super('c', couleurBlanche);
    }

    @Override
    public List<Position> deplacementsPossible(Position positionDuCavalier, Chess echiquier) {
        List<Position> listeDeplacementsPossible = new ArrayList<>();
        
        int[][] positionDeplacements = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };
    
        for (int[] position : positionDeplacements) {
            int x = positionDuCavalier.getX() + position[0];
            int y = positionDuCavalier.getY() + position[1];
            if(Position.verifValeur(x, y)){
                Position verifPosition = new Position(x, y);
                if (echiquier.positionEstVide(verifPosition) || echiquier.obtenirPieceALaPosition(verifPosition).getCouleur() != getCouleur()) {
                    listeDeplacementsPossible.add(verifPosition);
                }
            }

        }
    
        return listeDeplacementsPossible;
    }
    
}
