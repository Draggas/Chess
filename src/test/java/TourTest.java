import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import fr.draggas.project.chess.*;

public class TourTest {

    @Test
    public void testDeplacementsPossibles() {
        Chess echiquier = new Chess(false);
        Tour tourBlanche = new Tour(true);
        Position positionTour = new Position(4, 4);
        echiquier.addPieces(positionTour, tourBlanche);
        echiquier.addPieces(new Position(4, 6), new Pion(true));
        echiquier.addPieces(new Position(4, 2), new Pion(false));
        echiquier.addPieces(new Position(6, 4), new Pion(false));
        echiquier.addPieces(new Position(2, 4), new Pion(true));

        List<Position> mouvements = tourBlanche.moovePossible(positionTour, echiquier);

        Assertions.assertTrue(mouvements.contains(new Position(4, 5))); // Case vide au-dessus
        Assertions.assertFalse(mouvements.contains(new Position(4, 7))); // Obstacle blanc au-dessus
        Assertions.assertTrue(mouvements.contains(new Position(4, 3))); // Case vide en dessous
        Assertions.assertTrue(mouvements.contains(new Position(4, 2))); // Obstacle noir en dessous (prenable)
        Assertions.assertTrue(mouvements.contains(new Position(5, 4))); // Case vide à droite
        Assertions.assertTrue(mouvements.contains(new Position(6, 4))); // Obstacle noir à droite (prenable)
        Assertions.assertFalse(mouvements.contains(new Position(7, 4))); // Après obstacle noir à droite
        Assertions.assertTrue(mouvements.contains(new Position(3, 4))); // Case vide à gauche
        Assertions.assertFalse(mouvements.contains(new Position(2, 4))); // Obstacle blanc à gauche
    }

    @Test
    public void testDeplacementSansObstacle() {
        Chess echiquier = new Chess(false);
        Tour tourBlanche = new Tour(true);
        Position positionTour = new Position(1, 1);
        echiquier.addPieces(positionTour, tourBlanche);

        List<Position> mouvements = tourBlanche.moovePossible(positionTour, echiquier);

        Assertions.assertEquals(14, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(2, 1))); // Droite
        Assertions.assertTrue(mouvements.contains(new Position(1, 2))); // Haut
        Assertions.assertTrue(mouvements.contains(new Position(8, 1))); // Extrémité droite
        Assertions.assertTrue(mouvements.contains(new Position(1, 8))); // Extrémité haut
    }
}
