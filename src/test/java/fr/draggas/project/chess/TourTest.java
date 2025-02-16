package fr.draggas.project.chess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import fr.draggas.project.chess.model.Chess;
import fr.draggas.project.chess.model.Pion;
import fr.draggas.project.chess.model.Position;
import fr.draggas.project.chess.model.Tour;

public class TourTest {
    Chess echiquier;
    Tour tourBlanche;
    Position positionDeLaTour;

    @BeforeEach
    void setUp() {
        // Création d'un échiquier vide
        echiquier = new Chess(false);
        
        // Création d'une tour blanche (pièce à tester)
        tourBlanche = new Tour(true);
    }

    @Test
    public void test_des_deplacements_possibles_d_une_tour_avec_obstacles() {
        // Position initiale de la tour
        positionDeLaTour = new Position(4, 4);
        echiquier.ajoutPieces(positionDeLaTour, tourBlanche);

        // Placement d'obstacles sur l'échiquier
        echiquier.ajoutPieces(new Position(4, 6), new Pion(true)); // Pion blanc (obstacle)
        echiquier.ajoutPieces(new Position(4, 2), new Pion(false)); // Pion noir (prenable)
        echiquier.ajoutPieces(new Position(6, 4), new Pion(false)); // Pion noir (prenable)
        echiquier.ajoutPieces(new Position(2, 4), new Pion(true)); // Pion blanc (obstacle)

        // Calcul des déplacements possibles pour la tour
        List<Position> mouvements = tourBlanche.deplacementsPossible(positionDeLaTour, echiquier);

        // Vérifications des mouvements possibles en fonction des obstacles
        Assertions.assertTrue(mouvements.contains(new Position(4, 5))); // Case vide au-dessus
        Assertions.assertFalse(mouvements.contains(new Position(4, 7))); // Bloqué par pion blanc
        Assertions.assertTrue(mouvements.contains(new Position(4, 3))); // Case vide en dessous
        Assertions.assertTrue(mouvements.contains(new Position(4, 2))); // Pion noir en dessous (prenable)
        Assertions.assertTrue(mouvements.contains(new Position(5, 4))); // Case vide à droite
        Assertions.assertTrue(mouvements.contains(new Position(6, 4))); // Pion noir à droite (prenable)
        Assertions.assertFalse(mouvements.contains(new Position(7, 4))); // Après obstacle noir à droite
        Assertions.assertTrue(mouvements.contains(new Position(3, 4))); // Case vide à gauche
        Assertions.assertFalse(mouvements.contains(new Position(2, 4))); // Bloqué par pion blanc à gauche
    }

    @Test
    public void test_des_deplacements_possibles_d_une_tour_sans_obstacles() {
        // Position initiale de la tour
        positionDeLaTour = new Position(1, 1);
        echiquier.ajoutPieces(positionDeLaTour, tourBlanche);

        // Calcul des déplacements possibles pour la tour sans obstacles
        List<Position> mouvements = tourBlanche.deplacementsPossible(positionDeLaTour, echiquier);

        // Vérification qu'il y a bien 14 déplacements possibles pour la tour sans obstacles
        Assertions.assertEquals(14, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(2, 1))); // Déplacement vers la droite
        Assertions.assertTrue(mouvements.contains(new Position(1, 2))); // Déplacement vers le haut
        Assertions.assertTrue(mouvements.contains(new Position(8, 1))); // Extrémité droite
        Assertions.assertTrue(mouvements.contains(new Position(1, 8))); // Extrémité haut
    }

    @Test
    public void test_setRoque() {
        // Test des setters et getters de roque
        tourBlanche.setRoque(false);
        Assertions.assertFalse(tourBlanche.getRoque());

        tourBlanche.setRoque(true);
        Assertions.assertTrue(tourBlanche.getRoque());
    }
}
