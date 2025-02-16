package fr.draggas.project.chess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import fr.draggas.project.chess.model.Chess;
import fr.draggas.project.chess.model.Pion;
import fr.draggas.project.chess.model.Position;

public class PionTest {
    Chess echiquier;
    Pion pionBlanc;
    Pion pionNoir;
    Position positionDuPion;

    @BeforeEach
    public void setUp() {
        // Création d'un échiquier vide (false pour indiquer qu'on le initialise vide)
        echiquier = new Chess(false);

        // Création de pions blancs et noirs (pièces à tester)
        pionBlanc = new Pion(true);
        pionNoir = new Pion(false);
    }

    // Tests pour ajouterDeplacementVertical

    @Test
    public void test_deplacements_possible_avec_pion_blanc_double_deplacement() {
        // Placement du pion blanc sur l'échiquier à sa position initiale
        positionDuPion = new Position(2, 2);
        echiquier.ajoutPieces(positionDuPion, pionBlanc);

        // Récupération des mouvements possibles du pion blanc
        List<Position> mouvements = pionBlanc.deplacementsPossible(positionDuPion, echiquier);

        // Vérification des déplacements standards (1 ou 2 cases vers l'avant si libre)
        Assertions.assertEquals(2, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(2, 3))); // Avance d'une case
        Assertions.assertTrue(mouvements.contains(new Position(2, 4))); // Avance de deux cases (premier déplacement)
    }

    @Test
    public void test_deplacements_possible_avec_pion_noir_double_deplacement() {
        // Placement du pion noir sur l'échiquier à sa position initiale
        positionDuPion = new Position(2, 7);
        echiquier.ajoutPieces(positionDuPion, pionNoir);

        // Récupération des mouvements possibles du pion noir
        List<Position> mouvements = pionNoir.deplacementsPossible(positionDuPion, echiquier);

        // Vérification des déplacements standards (1 ou 2 cases vers l'avant si libre)
        Assertions.assertEquals(2, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(2, 6))); // Avance d'une case
        Assertions.assertTrue(mouvements.contains(new Position(2, 5))); // Avance de deux cases (premier déplacement)
    }

    @Test
    public void test_deplacements_impossible_avec_obstacle_devant_pion() {
        // Placement du pion blanc sur l'échiquier à sa position initiale
        positionDuPion = new Position(2, 2);
        echiquier.ajoutPieces(positionDuPion, pionBlanc);

        // Placement d'un obstacle directement devant le pion blanc
        Position obstaclePosition = new Position(2, 3);
        echiquier.ajoutPieces(obstaclePosition, new Pion(true));

        // Récupération des mouvements possibles
        List<Position> mouvements = pionBlanc.deplacementsPossible(positionDuPion, echiquier);

        // Vérification qu'aucun déplacement n'est possible à cause de l'obstacle
        Assertions.assertTrue(mouvements.isEmpty());
    }

    @Test
    public void test_deplacement_double_impossible_avec_obstacle_apres_premiere_case() {
        // Placement du pion blanc sur l'échiquier à sa position initiale
        positionDuPion = new Position(2, 2);
        echiquier.ajoutPieces(positionDuPion, pionBlanc);

        // Placement d'un obstacle à la deuxième case devant le pion blanc
        Position obstaclePosition = new Position(2, 4);
        echiquier.ajoutPieces(obstaclePosition, new Pion(true));

        // Récupération des mouvements possibles
        List<Position> mouvements = pionBlanc.deplacementsPossible(positionDuPion, echiquier);

        // Vérification qu'un seul déplacement est possible (double déplacement bloqué par l'obstacle)
        Assertions.assertEquals(1, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(2, 3))); // Avance d'une case
        Assertions.assertFalse(mouvements.contains(new Position(2, 4))); // Double déplacement bloqué
    }

    @Test
    public void test_deplacements_possible_pour_pion_blanc_sans_obstacle() {
        // Placement du pion blanc sur l'échiquier à sa position initiale
        positionDuPion = new Position(2, 3);
        echiquier.ajoutPieces(positionDuPion, pionBlanc);

        // Récupération des mouvements possibles du pion blanc
        List<Position> mouvements = pionBlanc.deplacementsPossible(positionDuPion, echiquier);

        // Vérification des déplacements standards
        Assertions.assertEquals(1, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(2, 4))); // Avance d'une case
        Assertions.assertFalse(mouvements.contains(new Position(2, 5))); // Double déplacement non permis
    }

    @Test
    public void test_deplacements_possible_avec_pion_noir_pas_a_position_initiale() {
        // Placement du pion noir sur l'échiquier pas à sa position initiale
        positionDuPion = new Position(2, 6);
        echiquier.ajoutPieces(positionDuPion, pionNoir);

        // Récupération des mouvements possibles du pion noir
        List<Position> mouvements = pionNoir.deplacementsPossible(positionDuPion, echiquier);

        // Vérification des déplacements standards (1 case vers l'avant si libre)
        Assertions.assertEquals(1, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(2, 5))); // Avance d'une case
        Assertions.assertFalse(mouvements.contains(new Position(2, 4))); // Double déplacement non permis
    }

    // Tests pour ajouterDeplacementsDiagonaux

    @Test
    public void test_deplacement_diagonal_capture() {
        // Placement du pion blanc sur l'échiquier
        positionDuPion = new Position(4, 4);
        echiquier.ajoutPieces(positionDuPion, pionBlanc);
        
        // Placement d'un pion noir en diagonale
        Position positionCible = new Position(5, 5);
        echiquier.ajoutPieces(positionCible, pionNoir);

        // Récupération des mouvements possibles du pion blanc
        List<Position> mouvements = pionBlanc.deplacementsPossible(positionDuPion, echiquier);

        // Vérification que la capture en diagonale est possible
        Assertions.assertTrue(mouvements.contains(positionCible));
    }

    @Test
    public void test_capture_diagonale_impossible() {
        // Placement du pion blanc sur l'échiquier
        positionDuPion = new Position(4, 4);
        echiquier.ajoutPieces(positionDuPion, pionBlanc);

        // Placement d'un pion blanc en diagonale
        Position positionCible = new Position(5, 5);
        echiquier.ajoutPieces(positionCible, new Pion(true));

        // Récupération des mouvements possibles du pion blanc
        List<Position> mouvements = pionBlanc.deplacementsPossible(positionDuPion, echiquier);

        // Vérification que la capture en diagonale n'est pas possible
        Assertions.assertFalse(mouvements.contains(positionCible));
    }

    @Test
    public void test_prise_en_passant() {
        // Placement du pion blanc sur l'échiquier
        Position positionPion = new Position(4, 4);
        echiquier.ajoutPieces(positionPion, pionBlanc);

        // Placement d'un pion noir à côté du pion blanc
        Pion pionNoir = new Pion(false);
        Position positionPionNoir = new Position(5, 4);
        echiquier.ajoutPieces(positionPionNoir, pionNoir);

        // Simulation d'une prise en passant (case cible après un coup adverse)
        echiquier.setPriseEnPassant(new Position(5, 5));

        // Récupération des mouvements possibles du pion blanc
        List<Position> mouvements = pionBlanc.deplacementsPossible(positionPion, echiquier);

        // Vérification de la prise en passant
        Assertions.assertTrue(mouvements.contains(new Position(5, 5)));
    }

    @Test
    public void test_prise_en_passant_impossible() {
        // Placement du pion blanc sur l'échiquier
        Position positionPion = new Position(4, 4);
        echiquier.ajoutPieces(positionPion, pionBlanc);

        // Placement d'un pion noir à côté du pion blanc
        Pion pionNoir = new Pion(false);
        Position positionPionNoir = new Position(5, 4);
        echiquier.ajoutPieces(positionPionNoir, pionNoir);

        // Récupération des mouvements possibles du pion blanc
        List<Position> mouvements = pionBlanc.deplacementsPossible(positionPion, echiquier);

        // Vérification que la prise en passant n'est pas possible
        Assertions.assertFalse(mouvements.contains(new Position(5, 5)));
        Assertions.assertTrue(mouvements.contains(new Position(4, 5)));
    }
}
