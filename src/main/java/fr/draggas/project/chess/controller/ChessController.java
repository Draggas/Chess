package fr.draggas.project.chess.controller;

import fr.draggas.project.chess.model.*;

public class ChessController {
    private Chess chess;

    public ChessController(Chess chess) {
        this.chess = chess;
    }

    public boolean verificationDuDeplacement(String depart, String arrivee) {
        return chess.verificationDuDeplacement(depart, arrivee);
    }

    public boolean verificationDuPointDeDepart(String depart) {
        return chess.verificationDuPointDeDepart(depart);
    }

    public void changementTour() {
        chess.changementTour();
    }
}
