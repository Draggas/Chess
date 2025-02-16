package fr.draggas.project.chess.utils;

import fr.draggas.project.chess.model.Position;

public interface Observer {
    void update();
    void promotion(Position arrivee, boolean couleur);
}
