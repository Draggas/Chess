import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.draggas.project.chess.model.Position;

public class PositionTest {
    @Test
    public void test_verification_d_une_position_de_l_echiquier() {
        // Vérification de la conversion des coordonnées de colonne et ligne en objets Position
        Assertions.assertEquals(new Position(1, 5), new Position('a', 5)); // 'a' -> 1, '5' -> 5
        Assertions.assertEquals(new Position(2, 5), new Position('b', '5')); // 'b' -> 2, '5' -> 5
        Assertions.assertEquals(new Position(4, 5), new Position('d', 5)); // 'd' -> 4, '5' -> 5
        Assertions.assertEquals(new Position(8, 5), new Position('h', '5')); // 'h' -> 8, '5' -> 5
    
        // Vérification de la conversion d'une notation classique (ex: "e5") en Position
        Assertions.assertEquals(new Position(5, 5), new Position("e5")); // 'e' -> 5, '5' -> 5
    
        // Vérification des méthodes de validation des positions (coordonnées valides ou non)
        Assertions.assertTrue(Position.verifValeur(2, 4)); // Validité de la position (2, 4)
        Assertions.assertFalse(Position.verifValeur(8, 0)); // Invalidité de la position (8, 0) -> ligne 8 valide mais colonne 0 invalide
        Assertions.assertTrue(Position.verifValeur(new Position("e4"))); // Validation de la position "e4"
        Assertions.assertFalse(Position.verifValeur(new Position("e9"))); // Invalidité de la position "e9" -> ligne 9 invalide
    }    
}