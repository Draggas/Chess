package fr.draggas.project.chess.controller;

import java.util.List;
import fr.draggas.project.chess.model.*;

public class ChessController {
    private Chess chess;

    public ChessController() {
        this.chess = new Chess();
    }

    public boolean deplacement(String depart, String arrivee) {
        return chess.deplacement(depart, arrivee);
    }

    public boolean verifCoup(String depart) {
        return chess.verifCoup(depart);
    }

    public boolean getTour() {
        return chess.getTour();
    }

    public boolean estFinPartie() {
        return chess.getFinPartie();
    }

    public void changementTour() {
        chess.changeTour();
    }

    public List<Position> getCoupPossible() {
        return chess.getCoupPossible();
    }

    public Pieces get(Position pose) {
        return chess.get(pose);
    }

    public boolean containsKey(Position pose) {
        return chess.containsKey(pose);
    }
}
