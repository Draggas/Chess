import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import fr.draggas.project.chess.*;

public class RoiTest {

    @Test
    public void testDeplacementsPossibles() {
        Chess echiquier = new Chess(false);
        Roi roiBlanc = new Roi(true);
        Position positionRoi = new Position(4, 4);
        echiquier.addPieces(positionRoi, roiBlanc);
        Reine reineNoire = new Reine(false);
        Position positionReine = new Position(1, 3);
        echiquier.addPieces(positionReine, reineNoire);

        List<Position> mouvements = roiBlanc.moovePossible(positionRoi, echiquier);

        Assertions.assertEquals(4, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(4, 5))); // Haut
        Assertions.assertFalse(mouvements.contains(new Position(3, 3))); // Bas Gauche
        Assertions.assertFalse(mouvements.contains(new Position(5, 3))); // Bas Droite
    }

    @Test
    public void testDeplacementsSansEchecs() {
        Chess echiquier = new Chess(false);
        Roi roiBlanc = new Roi(true);
        Position positionRoi = new Position(4, 4);
        echiquier.addPieces(positionRoi, roiBlanc);

        List<Position> mouvements = roiBlanc.moovePossible(positionRoi, echiquier);

        Assertions.assertEquals(8, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(4, 5))); // Haut
        Assertions.assertTrue(mouvements.contains(new Position(3, 3))); // Bas Gauche
        Assertions.assertTrue(mouvements.contains(new Position(5, 3))); // Bas Droite
    }

    @Test
    public void testRoque() {
        Chess echiquier = new Chess(false);
        Roi roiBlanc = new Roi(true);
        Position positionRoi = new Position(1,5);
        echiquier.addPieces(positionRoi, roiBlanc);
        Tour tourBlanc = new Tour(true);
        Position positionTour = new Position(1,8);
        echiquier.addPieces(positionTour, tourBlanc);
        Tour tour2Blanc = new Tour(true);
        Position positionTour2 = new Position(1,1);
        echiquier.addPieces(positionTour2, tour2Blanc);

        List<Position> mouvements = roiBlanc.moovePossible(positionRoi, echiquier);

        Assertions.assertEquals(7, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(1,7))); // Petit Roque
        Assertions.assertTrue(mouvements.contains(new Position(1,2))); // Grand Roque
    }
}
