package fr.draggas.project.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Fou extends Pieces {

    public Fou(boolean couleurBlanche) {
        super('f', couleurBlanche);
    }
    
    @Override
    public List<Position> deplacementsPossible(Position positionDuFou, Chess echiquier) {
        List<Position> listeDeplacementsPossible = new ArrayList<>();
        
        verifierDirection(listeDeplacementsPossible, positionDuFou, echiquier, 1, 1);
        verifierDirection(listeDeplacementsPossible, positionDuFou, echiquier, -1, 1);
        verifierDirection(listeDeplacementsPossible, positionDuFou, echiquier, 1, -1);
        verifierDirection(listeDeplacementsPossible, positionDuFou, echiquier, -1, -1);
    
        return listeDeplacementsPossible;
    }
    
    private void verifierDirection(List<Position> listeDeplacementsPossible, Position positionDuFou, Chess echiquier, int deplacementX, int deplacementY) {
        int x = positionDuFou.getX();
        int y = positionDuFou.getY();
    
        while (true) {
            x += deplacementX;
            y += deplacementY;

            if (!Position.verifValeur(x, y)){
                break;
            }
            Position verifPosition = new Position(x, y);

            if (echiquier.positionEstVide(verifPosition)) {
                listeDeplacementsPossible.add(verifPosition);
            } else {
                if (echiquier.obtenirPieceALaPosition(verifPosition).getCouleur() != getCouleur()) {
                    listeDeplacementsPossible.add(verifPosition);
                }
                break;
            }
        }
    }
}
