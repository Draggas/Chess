
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

    @Test
    public void TestNotationValide(){
        Assertions.assertTrue(Chess.estNotationValide("e2-e4"));
        Assertions.assertTrue(Chess.estNotationValide("d4-d3"));
        Assertions.assertTrue(Chess.estNotationValide("e8-e5"));
        Assertions.assertTrue(Chess.estNotationValide("Rd7-d6"));
        Assertions.assertTrue(Chess.estNotationValide("Na6-a7"));
        Assertions.assertTrue(Chess.estNotationValide("Bc5-c8"));
        Assertions.assertTrue(Chess.estNotationValide("Qf3-f1"));
        Assertions.assertTrue(Chess.estNotationValide("Ke1-e2"));
        Assertions.assertTrue(Chess.estNotationValide("O-O-O"));
        Assertions.assertTrue(Chess.estNotationValide("O-O"));
        Assertions.assertTrue(Chess.estNotationValide("Be2xh1"));
        Assertions.assertTrue(Chess.estNotationValide("Nf7xg6"));

        Assertions.assertFalse(Chess.estNotationValide("Ce2xh1"));
        Assertions.assertFalse(Chess.estNotationValide("Be2xBh1"));
        Assertions.assertFalse(Chess.estNotationValide("De2-Dh1"));
        Assertions.assertFalse(Chess.estNotationValide("Be2x-h1"));
        Assertions.assertFalse(Chess.estNotationValide("Bxh1"));
    }

    public void TestDeplacementPion(){
        game.deplacer("e2-e4");
        game.deplacer("d2-d3");
        game.deplacer("e7-e5");
        game.deplacer("d7-d6");
        Assertions.assertEquals(affichage, affiPion);
    }
}
