import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import fr.draggas.project.chess.model.Chess;
import fr.draggas.project.chess.model.Pion;
import fr.draggas.project.chess.model.Position;

public class PionTest {

    @Test
    public void testDeplacementClassique() {
        Chess echiquier = new Chess(false);
        Pion pionBlanc = new Pion(true);
        Position positionPion = new Position(2, 2);
        echiquier.addPieces(positionPion, pionBlanc);

        List<Position> mouvements = pionBlanc.moovePossible(positionPion, echiquier);

        Assertions.assertEquals(2, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(2, 3))); // 1
        Assertions.assertTrue(mouvements.contains(new Position(2, 4))); // 2
    }

    @Test
    public void testBlocage() {
        Chess echiquier = new Chess(false);
        Pion pionBlanc = new Pion(true);
        Position positionPion = new Position(2, 2);
        echiquier.addPieces(positionPion, pionBlanc);
        echiquier.addPieces(new Position(2, 3), new Pion(true));

        List<Position> mouvements = pionBlanc.moovePossible(positionPion, echiquier);
        Assertions.assertFalse(mouvements.contains(new Position(2, 3)));
        Assertions.assertFalse(mouvements.contains(new Position(2,4)));
        Assertions.assertTrue(mouvements.isEmpty());
    }

    @Test
    public void testPriseDiagonale() {
        Chess echiquier = new Chess(false);
        Pion pionBlanc = new Pion(true);
        Position positionPion = new Position(2, 2);
        echiquier.addPieces(positionPion, pionBlanc);

        echiquier.addPieces(new Position(3, 3), new Pion(false));
        echiquier.addPieces(new Position(1, 3), new Pion(false));

        List<Position> mouvements = pionBlanc.moovePossible(positionPion, echiquier);

        Assertions.assertTrue(mouvements.contains(new Position(3, 3))); // prise
        Assertions.assertTrue(mouvements.contains(new Position(1, 3))); // prise
        Assertions.assertTrue(mouvements.contains(new Position(2, 3))); // non prise
        Assertions.assertTrue(mouvements.contains(new Position(2, 4))); // non prise
        Assertions.assertEquals(4, mouvements.size());
    }

    @Test
    public void testPriseEnPassant() {
        Chess echiquier = new Chess(false);
        Pion pionBlanc = new Pion(true);
        Position positionPion = new Position(4, 4);
        echiquier.addPieces(positionPion, pionBlanc);

        Pion pionNoir = new Pion(false);
        Position positionPionNoir = new Position(5, 4);
        echiquier.addPieces(positionPionNoir, pionNoir);

        echiquier.priseEnPassantPossible = new Position(5, 5);

        List<Position> mouvements = pionBlanc.moovePossible(positionPion, echiquier);

        Assertions.assertEquals(2, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(5, 5)));
    }

    @Test
    public void testPasDePriseEnPassant() {
        Chess echiquier = new Chess(false);
        Pion pionBlanc = new Pion(true);
        Position positionPion = new Position(4, 4);
        echiquier.addPieces(positionPion, pionBlanc);

        Pion pionNoir = new Pion(false);
        Position positionPionNoir = new Position(5, 4);
        echiquier.addPieces(positionPionNoir, pionNoir);

        List<Position> mouvements = pionBlanc.moovePossible(positionPion, echiquier);

        Assertions.assertFalse(mouvements.contains(new Position(5, 5)));
        Assertions.assertTrue(mouvements.contains(new Position(4, 5)));
        Assertions.assertEquals(1, mouvements.size());
    }
}
