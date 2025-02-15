package fr.draggas.project.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Pion extends Pieces {

    public Pion(boolean couleurBlanche) {
        super('p', couleurBlanche);
    }

    @Override
    public List<Position> deplacementsPossible(Position positionDuPion, Chess echiquier) {
        List<Position> listeDeplacementsPossible = new ArrayList<>();
    
        int direction = getCouleur() ? 1 : -1;
    
        Position positionCible = new Position(positionDuPion.getX(), positionDuPion.getY() + direction);
        if (echiquier.positionEstVide(positionCible)) {
            listeDeplacementsPossible.add(positionCible);

            if ((getCouleur() && positionDuPion.getY() == 2) || (!getCouleur() && positionDuPion.getY() == 7)) {
                positionCible = new Position(positionDuPion.getX(), positionDuPion.getY() + 2 * direction);
                if (echiquier.positionEstVide(positionCible)) {
                    listeDeplacementsPossible.add(positionCible);
                }
            }
        }
        for(int i = -1; i*i==1; i=i+2){
            positionCible = new Position(positionDuPion.getX() + i, positionDuPion.getY() + direction);
            if (positionCible.getY() >= 1 && positionCible.getY() <= 8){
                if (!echiquier.positionEstVide(positionCible) && echiquier.obtenirPieceALaPosition(positionCible).getCouleur() != getCouleur()) {
                    listeDeplacementsPossible.add(positionCible);
                }
            }
            if(echiquier.getPriseEnPassant() != null && echiquier.getPriseEnPassant().equals(positionCible)){ 
                listeDeplacementsPossible.add(positionCible);
            }
        }
        return listeDeplacementsPossible;
    }
    
}
