import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import fr.draggas.project.chess.model.Chess;
import fr.draggas.project.chess.model.Position;
import fr.draggas.project.chess.model.Roi;
import fr.draggas.project.chess.model.Tour;

public class RoiTest {

    @Test
    public void testDeplacements() {
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
        Position positionRoi = new Position(5,1);
        echiquier.addPieces(positionRoi, roiBlanc);
        Tour tourBlanc = new Tour(true);
        Position positionTour = new Position(8,1);
        echiquier.addPieces(positionTour, tourBlanc);
        Tour tour2Blanc = new Tour(true);
        Position positionTour2 = new Position(1,1);
        echiquier.addPieces(positionTour2, tour2Blanc);

        List<Position> mouvements = roiBlanc.moovePossible(positionRoi, echiquier);

        Assertions.assertEquals(7, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(7,1))); // Petit Roque
        Assertions.assertTrue(mouvements.contains(new Position(2,1))); // Grand Roque

        echiquier.deplacement("e1", "g1");
        Assertions.assertEquals(Tour.class, echiquier.get(new Position("f1")).getClass());
        Assertions.assertTrue(echiquier.caseVide(new Position("h1")));
    }
}
