package fr.draggas.project.chess.controller;

import fr.draggas.project.chess.model.*;

public class ChessController {
    private Chess chess;

    public ChessController(Chess chess) {
        this.chess = chess;
    }

    public boolean deplacement(String depart, String arrivee) {
        return chess.deplacement(depart, arrivee);
    }

    public boolean verifCoup(String depart) {
        return chess.verifCoup(depart);
    }

    public void changementTour() {
        chess.changeTour();
    }
}
