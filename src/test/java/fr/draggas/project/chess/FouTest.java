package fr.draggas.project.chess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import fr.draggas.project.chess.model.Chess;
import fr.draggas.project.chess.model.Fou;
import fr.draggas.project.chess.model.Pion;
import fr.draggas.project.chess.model.Position;

public class FouTest {
    Chess echiquier;
    Fou fouBlanc;
    Position positionDuFou;

    @BeforeEach
    public void setUp() {
        // Création d'un échiquier vide (false pour indiquer qu'on le initialise vide)
        echiquier = new Chess(false);
        
        // Création du fou blanc (pièce à tester)
        fouBlanc = new Fou(true);
    }

    @Test
    public void test_des_deplacements_possibles_d_un_fou_avec_obstacles() {
        // Placement du fou blanc
        positionDuFou = new Position(4, 4);
        echiquier.ajoutPieces(positionDuFou, fouBlanc);
    
        // Ajout d'obstacles sur les diagonales
        echiquier.ajoutPieces(new Position(6, 6), new Pion(true)); // Pion blanc (obstacle)
        echiquier.ajoutPieces(new Position(2, 6), new Pion(true)); // Pion blanc (obstacle)
        echiquier.ajoutPieces(new Position(2, 2), new Pion(false)); // Pion noir (capturable)
        echiquier.ajoutPieces(new Position(6, 2), new Pion(false)); // Pion noir (capturable)
    
        List<Position> mouvements = fouBlanc.deplacementsPossible(positionDuFou, echiquier);
    
        // Vérifications des mouvements possibles en fonction des obstacles
        Assertions.assertFalse(mouvements.contains(new Position(7, 7))); // Bloqué par pion blanc
        Assertions.assertFalse(mouvements.contains(new Position(2, 6))); // Bloqué par pion blanc
        Assertions.assertFalse(mouvements.contains(new Position(7, 1))); // Bloqué après pion noir
        Assertions.assertTrue(mouvements.contains(new Position(2, 2))); // Capture du pion noir en bas à gauche
        Assertions.assertTrue(mouvements.contains(new Position(6, 2))); // Capture du pion noir en haut à gauche
        Assertions.assertTrue(mouvements.contains(new Position(5, 5))); // Case libre en haut à droite
        Assertions.assertTrue(mouvements.contains(new Position(3, 3))); // Case libre en bas à gauche
        Assertions.assertTrue(mouvements.contains(new Position(5, 3))); // Case libre en haut à gauche
        Assertions.assertTrue(mouvements.contains(new Position(3, 5))); // Case libre en bas à droite
    }
    
    @Test
    public void test_des_deplacements_possibles_d_un_fou_sans_obstacles() {
        // Placement du fou blanc
        positionDuFou = new Position(3, 3);
        echiquier.ajoutPieces(positionDuFou, fouBlanc);
    
        List<Position> mouvements = fouBlanc.deplacementsPossible(positionDuFou, echiquier);
    
        // Vérifie que le fou a bien 11 mouvements possibles (sans obstacles)
        Assertions.assertEquals(11, mouvements.size());

        // Vérifications des déplacements en diagonale
        Assertions.assertTrue(mouvements.contains(new Position(4, 4))); // Haut-droite
        Assertions.assertTrue(mouvements.contains(new Position(5, 5))); // Haut-droite
        Assertions.assertTrue(mouvements.contains(new Position(6, 6))); // Haut-droite
        Assertions.assertTrue(mouvements.contains(new Position(2, 2))); // Bas-gauche
        Assertions.assertTrue(mouvements.contains(new Position(1, 1))); // Bas-gauche
        Assertions.assertTrue(mouvements.contains(new Position(4, 2))); // Haut-gauche
        Assertions.assertTrue(mouvements.contains(new Position(5, 1))); // Haut-gauche
        Assertions.assertTrue(mouvements.contains(new Position(2, 4))); // Bas-droite
        Assertions.assertTrue(mouvements.contains(new Position(1, 5))); // Bas-droite
    }
}