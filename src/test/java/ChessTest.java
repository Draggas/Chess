
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.draggas.project.chess.Chess;

public class ChessTest {
    Chess game = new Chess();
    String ls = System.lineSeparator();
    String affichage =  "RNBQKBNR" + ls + 
                        "PPPPPPPP" + ls + 
                        "xxxxxxxx" + ls + 
                        "xxxxxxxx" + ls + 
                        "xxxxxxxx" + ls + 
                        "xxxxxxxx" + ls + 
                        "pppppppp" + ls + 
                        "rnbqkbnr";
    String affiPion =   "RNBQKBNR" + ls + 
                        "PPPxxPPP" + ls + 
                        "xxxPxxxx" + ls + 
                        "xxxxPxxx" + ls + 
                        "xxxxpxxx" + ls + 
                        "xxxpxxxx" + ls + 
                        "pppxxppp" + ls + 
                        "rnbqkbnr";
    
    @BeforeEach
    public void Initialisation(){
        game = new Chess();
    }
    
    @Test
    public void TestRepresentationPlateau(){
        Assertions.assertEquals(affichage, game.affichage());
    }

    public void TestDeplacementPion(){
        game.deplacer("e2-e4");
        game.deplacer("d2-d3");
        game.deplacer("e7-e5");
        game.deplacer("d7-d6");
        Assertions.assertEquals(affichage, affiPion);
    }
}
