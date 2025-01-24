import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import fr.draggas.project.chess.*;

public class ReineTest {

    @Test
    public void testDeplacementsPossibles() {
        Chess echiquier = new Chess(false);
        Reine dameBlanche = new Reine(true);
        Position positionDame = new Position(4, 4);
        echiquier.addPieces(positionDame, dameBlanche);
        echiquier.addPieces(new Position(4, 6), new Pion(true));
        echiquier.addPieces(new Position(4, 2), new Pion(false));
        echiquier.addPieces(new Position(6, 4), new Pion(false));
        echiquier.addPieces(new Position(6, 6), new Pion(false));
        echiquier.addPieces(new Position(2, 4), new Pion(true));

        List<Position> mouvements = dameBlanche.moovePossible(positionDame, echiquier);

        Assertions.assertTrue(mouvements.contains(new Position(5, 5))); // Diagonale droite-haut
        Assertions.assertTrue(mouvements.contains(new Position(3, 3))); // Diagonale gauche-bas
        Assertions.assertFalse(mouvements.contains(new Position(7, 7))); // Après obstacle noir en diagonale
        Assertions.assertTrue(mouvements.contains(new Position(5, 3))); // Diagonale droite-bas

        Assertions.assertTrue(mouvements.contains(new Position(4, 5))); // Case libre au-dessus
        Assertions.assertFalse(mouvements.contains(new Position(4, 7))); // Après obstacle blanc
        Assertions.assertTrue(mouvements.contains(new Position(4, 2))); // Obstacle noir (prenable)
        Assertions.assertTrue(mouvements.contains(new Position(5, 4))); // Droite
        Assertions.assertFalse(mouvements.contains(new Position(7, 4))); // Après obstacle noir
    }

    @Test
    public void testDeplacementSansObstacle() {
        Chess echiquier = new Chess(false);
        Reine dameBlanche = new Reine(true);
        Position positionDame = new Position(1, 1);
        echiquier.addPieces(positionDame, dameBlanche);

        List<Position> mouvements = dameBlanche.moovePossible(positionDame, echiquier);

        Assertions.assertEquals(21, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(8, 8))); // Diagonale
        Assertions.assertTrue(mouvements.contains(new Position(1, 8))); // Haut
        Assertions.assertTrue(mouvements.contains(new Position(8, 1))); // Droite
    }
}
