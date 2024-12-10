
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.draggas.project.chess.Chess;

public class ChessTest {
    String ls = System.lineSeparator();
    @Test
    public void TestRepresentationPlateau(){
        Chess game = new Chess();
        Assertions.assertEquals(game.toString(),"RNBQKBNR" + ls + "PPPPPPPP" + ls + "xxxxxxxx" + ls + "xxxxxxxx" + ls + "xxxxxxxx" + ls + "xxxxxxxx" + ls + "pppppppp" + "rnbqkbnr");
    }
}
