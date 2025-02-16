package fr.draggas.project.chess.controller;

import fr.draggas.project.chess.model.*;

/**
 * Contrôleur pour la gestion des interactions avec le modèle de jeu d'échecs.
 */
public class ChessController {
    private Chess chess;

    /**
     * Constructeur de la classe ChessController.
     * @param chess Instance du modèle de jeu d'échecs à contrôler.
     */
    public ChessController(Chess chess) {
        this.chess = chess;
    }

    /**
     * Vérifie si le déplacement d'une pièce est valide.
     * @param depart Notation de la position de départ.
     * @param arrivee Notation de la position d'arrivée.
     * @return true si le déplacement est valide, false sinon.
     */
    public boolean verificationDuDeplacement(String depart, String arrivee) {
        return chess.verificationDuDeplacement(depart, arrivee);
    }

    /**
     * Vérifie si le point de départ d'une pièce est valide.
     * @param depart Notation de la position de départ.
     * @return true si le point de départ est valide, false sinon.
     */
    public boolean verificationDuPointDeDepart(String depart) {
        return chess.verificationDuPointDeDepart(depart);
    }

    /**
     * Change le tour du joueur.
     */
    public void changementTour() {
        chess.changementTour();
    }
}