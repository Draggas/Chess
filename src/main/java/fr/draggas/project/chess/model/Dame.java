package fr.draggas.project.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Dame extends Pieces {

    public Dame(boolean couleurBlanche) {
        super('d', couleurBlanche);
    }

    @Override
    public List<Position> deplacementsPossible(Position positionDeLaDame, Chess echiquier){
        List<Position> listeDeplacementsPossible = new ArrayList<>();
        Tour tour = new Tour(couleurBlanche);
        Fou fou = new Fou(couleurBlanche);
        listeDeplacementsPossible.addAll(tour.deplacementsPossible(positionDeLaDame, echiquier));
        listeDeplacementsPossible.addAll(fou.deplacementsPossible(positionDeLaDame, echiquier));
        return listeDeplacementsPossible;
    }
}