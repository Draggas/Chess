import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.draggas.project.chess.model.Position;

public class PositionTest {

    @Test
    public void TestVerifPosition(){
        Assertions.assertEquals(new Position(1,5), new Position('a', 5));
        Assertions.assertEquals(new Position(2,5), new Position('b', '5'));
        Assertions.assertEquals(new Position(4,5), new Position('d', 5));
        Assertions.assertEquals(new Position(8,5), new Position('h', '5'));
        Assertions.assertEquals(new Position(5,5), new Position("e5"));
        Assertions.assertTrue(Position.verifValeur(2, 4));
        Assertions.assertFalse(Position.verifValeur(8, 0));
        Assertions.assertTrue(Position.verifValeur(new Position("e4")));
        Assertions.assertFalse(Position.verifValeur(new Position("e9")));
    }
}