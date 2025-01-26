import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import fr.draggas.project.chess.model.Cavalier;
import fr.draggas.project.chess.model.Chess;
import fr.draggas.project.chess.model.Pion;
import fr.draggas.project.chess.model.Position;

public class CavalierTest {

    @Test
    public void testDeplacementsPossibles() {
        Chess echiquier = new Chess(false);
        Cavalier cavalierBlanc = new Cavalier(true);
        Position positionCavalier = new Position(4, 4);
        echiquier.addPieces(positionCavalier, cavalierBlanc);
        echiquier.addPieces(new Position(5, 6), new Pion(true));
        echiquier.addPieces(new Position(3, 2), new Pion(false));

        List<Position> mouvements = cavalierBlanc.moovePossible(positionCavalier, echiquier);

        Assertions.assertTrue(mouvements.contains(new Position(6, 5))); // Mouvement possible
        Assertions.assertTrue(mouvements.contains(new Position(2, 3))); // Mouvement possible
        Assertions.assertTrue(mouvements.contains(new Position(3, 2))); // Mouvement avec capture (noir)
        Assertions.assertFalse(mouvements.contains(new Position(5, 6))); // Mouvement bloqué (blanc)
    }

    @Test
    public void testDeplacementSansObstacle() {
        Chess echiquier = new Chess(false);
        Cavalier cavalierBlanc = new Cavalier(true);
        Position positionCavalier = new Position(4, 4);
        echiquier.addPieces(positionCavalier, cavalierBlanc);

        List<Position> mouvements = cavalierBlanc.moovePossible(positionCavalier, echiquier);

        Assertions.assertEquals(8, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(6, 5)));
        Assertions.assertTrue(mouvements.contains(new Position(2, 3)));
    }
}
