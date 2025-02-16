package fr.draggas.project.chess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import fr.draggas.project.chess.model.Cavalier;
import fr.draggas.project.chess.model.Chess;
import fr.draggas.project.chess.model.Position;
import fr.draggas.project.chess.model.Pion;

public class CavalierTest {
    Chess echiquier;
    Cavalier cavalierBlanc;
    Cavalier cavalierNoir;
    Position positionDuCavalier;

    @BeforeEach
    public void setUp() {
        // Création d'un échiquier vide (false pour indiquer qu'on le initialise vide)
        echiquier = new Chess(false);

        // Création de cavaliers blancs et noirs (pièces à tester)
        cavalierBlanc = new Cavalier(true);
        cavalierNoir = new Cavalier(false);
    }

    @Test
    public void test_deplacements_possible_pour_cavalier_blanc() {
        // Placement du cavalier blanc sur l'échiquier au centre
        positionDuCavalier = new Position(4, 4);
        echiquier.ajoutPieces(positionDuCavalier, cavalierBlanc);

        // Récupération des mouvements possibles du cavalier blanc
        List<Position> mouvements = cavalierBlanc.deplacementsPossible(positionDuCavalier, echiquier);

        // Vérification des déplacements possibles
        Assertions.assertEquals(8, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(6, 5)));
        Assertions.assertTrue(mouvements.contains(new Position(6, 3)));
        Assertions.assertTrue(mouvements.contains(new Position(2, 5)));
        Assertions.assertTrue(mouvements.contains(new Position(2, 3)));
        Assertions.assertTrue(mouvements.contains(new Position(5, 6)));
        Assertions.assertTrue(mouvements.contains(new Position(5, 2)));
        Assertions.assertTrue(mouvements.contains(new Position(3, 6)));
        Assertions.assertTrue(mouvements.contains(new Position(3, 2)));
    }

    @Test
    public void test_deplacements_possible_pour_cavalier_noir() {
        // Placement du cavalier noir sur l'échiquier au centre
        positionDuCavalier = new Position(4, 4);
        echiquier.ajoutPieces(positionDuCavalier, cavalierNoir);

        // Récupération des mouvements possibles du cavalier noir
        List<Position> mouvements = cavalierNoir.deplacementsPossible(positionDuCavalier, echiquier);

        // Vérification des déplacements possibles
        Assertions.assertEquals(8, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(6, 5)));
        Assertions.assertTrue(mouvements.contains(new Position(6, 3)));
        Assertions.assertTrue(mouvements.contains(new Position(2, 5)));
        Assertions.assertTrue(mouvements.contains(new Position(2, 3)));
        Assertions.assertTrue(mouvements.contains(new Position(5, 6)));
        Assertions.assertTrue(mouvements.contains(new Position(5, 2)));
        Assertions.assertTrue(mouvements.contains(new Position(3, 6)));
        Assertions.assertTrue(mouvements.contains(new Position(3, 2)));
    }

    @Test
    public void test_deplacements_hors_limites() {
        // Placement du cavalier blanc sur l'échiquier en bordure
        positionDuCavalier = new Position(1, 1);
        echiquier.ajoutPieces(positionDuCavalier, cavalierBlanc);

        // Récupération des mouvements possibles du cavalier blanc
        List<Position> mouvements = cavalierBlanc.deplacementsPossible(positionDuCavalier, echiquier);

        // Vérification des déplacements possibles
        Assertions.assertTrue(mouvements.contains(new Position(3, 2)));
        Assertions.assertTrue(mouvements.contains(new Position(2, 3)));
        Assertions.assertFalse(mouvements.contains(new Position(0, 0))); // Hors limites
        Assertions.assertFalse(mouvements.contains(new Position(0, 2))); // Hors limites
        Assertions.assertFalse(mouvements.contains(new Position(2, 0))); // Hors limites
    }

    @Test
    public void test_deplacements_possible_capture() {
        // Placement du cavalier blanc sur l'échiquier au centre
        positionDuCavalier = new Position(4, 4);
        echiquier.ajoutPieces(positionDuCavalier, cavalierBlanc);

        // Placement d'un pion noir sur une position capturable
        Position positionCible = new Position(6, 5);
        echiquier.ajoutPieces(positionCible, new Pion(false));

        // Récupération des mouvements possibles du cavalier blanc
        List<Position> mouvements = cavalierBlanc.deplacementsPossible(positionDuCavalier, echiquier);

        // Vérification que la capture est possible
        Assertions.assertTrue(mouvements.contains(positionCible));
    }

    @Test
    public void test_deplacements_impossible_capture_ami() {
        // Placement du cavalier blanc sur l'échiquier au centre
        positionDuCavalier = new Position(4, 4);
        echiquier.ajoutPieces(positionDuCavalier, cavalierBlanc);

        // Placement d'un pion blanc sur une position non capturable
        Position positionCible = new Position(6, 5);
        echiquier.ajoutPieces(positionCible, new Pion(true));

        // Récupération des mouvements possibles du cavalier blanc
        List<Position> mouvements = cavalierBlanc.deplacementsPossible(positionDuCavalier, echiquier);

        // Vérification que la capture n'est pas possible
        Assertions.assertFalse(mouvements.contains(positionCible));
    }
}
