import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import fr.draggas.project.chess.*;

public class FouTest {
    @Test
    public void testDeplacementsPossibles() {
        Chess echiquier = new Chess(false);
        Fou fouBlanc = new Fou(true);
        Position positionFou = new Position(4, 4);
        echiquier.addPieces(positionFou, fouBlanc);
    
        echiquier.addPieces(new Position(6, 6), new Pion(true));
        echiquier.addPieces(new Position(2, 2), new Pion(false));
        echiquier.addPieces(new Position(6, 2), new Pion(false));
        echiquier.addPieces(new Position(2, 6), new Pion(true));
    
        List<Position> mouvements = fouBlanc.moovePossible(positionFou, echiquier);
    
        Assertions.assertTrue(mouvements.contains(new Position(5, 5))); // Case vide en haut à droite
        Assertions.assertFalse(mouvements.contains(new Position(7, 7))); // Après obstacle blanc en haut à droite
        Assertions.assertTrue(mouvements.contains(new Position(3, 3))); // Case vide en bas à gauche
        Assertions.assertTrue(mouvements.contains(new Position(2, 2))); // Obstacle noir en bas à gauche (prenable)
        Assertions.assertTrue(mouvements.contains(new Position(5, 3))); // Case vide en haut à gauche
        Assertions.assertTrue(mouvements.contains(new Position(6, 2))); // Obstacle noir en haut à gauche (prenable)
        Assertions.assertFalse(mouvements.contains(new Position(7, 1))); // Après obstacle noir en haut à gauche
        Assertions.assertTrue(mouvements.contains(new Position(3, 5))); // Case vide en bas à droite
        Assertions.assertFalse(mouvements.contains(new Position(2, 6))); // Obstacle blanc en bas à droite
    }
    
    @Test
    public void testDeplacementSansObstacle() {
        Chess echiquier = new Chess(false);
        Fou fouBlanc = new Fou(true);
        Position positionFou = new Position(3, 3);
        echiquier.addPieces(positionFou, fouBlanc);
    
        List<Position> mouvements = fouBlanc.moovePossible(positionFou, echiquier);
    
        Assertions.assertEquals(11, mouvements.size());
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