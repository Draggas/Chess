package fr.draggas.project.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Roi extends Pieces {
    private boolean roque;
    private boolean grandRoque;

    public Roi(boolean couleurBlanche) {
        super('k', couleurBlanche);
        roque = true;
        grandRoque = true;
    }
    
    @Override
    public List<Position> deplacementsPossible(Position positionDuRoi, Chess echiquier) {
        List<Position> listeDeplacementsPossible = new ArrayList<>();
        
        int[] deplacementsX = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] deplacementsY = {-1, 0, 1, -1, 1, -1, 0, 1};
        
        for (int i = 0; i < deplacementsX.length; i++) {
            if(Position.verifValeur(positionDuRoi.getX() + deplacementsX[i], positionDuRoi.getY() + deplacementsY[i])){
                Position n = new Position(positionDuRoi.getX() + deplacementsX[i], positionDuRoi.getY() + deplacementsY[i]);
                if (echiquier.positionEstVide(n) || echiquier.obtenirPieceALaPosition(n).getCouleur() != getCouleur()) {
                    listeDeplacementsPossible.add(n);
                }
            }
        }

        int positionYNecessaire = getCouleur() ? 1 : 8;
        Position poseRoque = new Position(8,positionYNecessaire);
        Position poseGrandRoque = new Position(1,positionYNecessaire);
        if(roque && !echiquier.positionEstVide(poseRoque) && echiquier.obtenirPieceALaPosition(poseRoque).getClass() == Tour.class && echiquier.positionEstVide(new Position(7, positionYNecessaire)) && echiquier.positionEstVide(new Position(6, positionYNecessaire)) && ((Tour) echiquier.obtenirPieceALaPosition(poseRoque)).getRoque()){
            listeDeplacementsPossible.add(new Position(7,positionYNecessaire));
        }
        if(grandRoque && !echiquier.positionEstVide(poseGrandRoque) && echiquier.obtenirPieceALaPosition(poseGrandRoque).getClass() == Tour.class && echiquier.positionEstVide(new Position(2, positionYNecessaire)) && echiquier.positionEstVide(new Position(4, positionYNecessaire)) && echiquier.positionEstVide(new Position(3, positionYNecessaire)) && ((Tour) echiquier.obtenirPieceALaPosition(poseGrandRoque)).getRoque()){
            listeDeplacementsPossible.add(new Position(2,positionYNecessaire));
        }
        return listeDeplacementsPossible;
    }
    
    public boolean getRoque(){
        return roque;
    }
    
    public boolean getGrandRoque(){
        return grandRoque;
    }

    public void setGrandRoque(boolean grandRoque){
        this.grandRoque = grandRoque;
     }

    public void setRoque(boolean roque){
       this.roque = roque;
    }
}