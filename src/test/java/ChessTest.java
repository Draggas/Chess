
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.draggas.project.chess.Chess;

public class ChessTest {
    Chess game;
    String ls = System.lineSeparator();
    String affichage =  "RNBQKBNR" + ls + 
                        "PPPPPPPP" + ls + 
                        "xxxxxxxx" + ls + 
                        "xxxxxxxx" + ls + 
                        "xxxxxxxx" + ls + 
                        "xxxxxxxx" + ls + 
                        "pppppppp" + ls + 
                        "rnbqkbnr";
    String affichageVide =  "xxxxxxxx" + ls + 
                            "xxxxxxxx" + ls + 
                            "xxxxxxxx" + ls + 
                            "xxxxxxxx" + ls + 
                            "xxxxxxxx" + ls + 
                            "xxxxxxxx" + ls + 
                            "xxxxxxxx" + ls + 
                            "xxxxxxxx";
    
    @BeforeEach
    public void Initialisation(){
        game = new Chess();
    }
    
    @Test
    public void TestRepresentationPlateau(){
        Assertions.assertEquals(affichage, game.affichage());
    }

    @Test
    public void TestPlateauVide(){
        game = new Chess(false);
        Assertions.assertEquals(affichageVide, game.affichage());
    }

    @Test
    public void TestNotationValide(){
        game = new Chess(true);
        Assertions.assertTrue(game.estNotationValide("e2-e4"));
        Assertions.assertTrue(game.estNotationValide("d4-d3"));
        Assertions.assertTrue(game.estNotationValide("e8-e5"));
        Assertions.assertTrue(game.estNotationValide("Rd7-d6"));
        Assertions.assertTrue(game.estNotationValide("Na6-a7"));
        Assertions.assertTrue(game.estNotationValide("Bc5-c8"));
        Assertions.assertTrue(game.estNotationValide("Qf3-f1"));
        Assertions.assertTrue(game.estNotationValide("Ke1-e2"));
        Assertions.assertTrue(game.estNotationValide("O-O-O"));
        Assertions.assertTrue(game.estNotationValide("O-O"));
        Assertions.assertTrue(game.estNotationValide("Be2xh1"));
        Assertions.assertTrue(game.estNotationValide("Nf7xg6"));

        Assertions.assertFalse(game.estNotationValide("Ce2xh1"));
        Assertions.assertFalse(game.estNotationValide("Be2xBh1"));
        Assertions.assertFalse(game.estNotationValide("De2-Dh1"));
        Assertions.assertFalse(game.estNotationValide("Be2x-h1"));
        Assertions.assertFalse(game.estNotationValide("Bxh1"));
    }

    @Test
    public void TestDeplacementValidePion(){
        Assertions.assertTrue(game.estDeplacementValide("e2-e4"));
        Assertions.assertTrue(game.estDeplacementValide("d2-d3"));
        Assertions.assertTrue(game.estDeplacementValide("e7-e5"));
        Assertions.assertTrue(game.estDeplacementValide("d7-d6"));
        Assertions.assertFalse(game.estNotationValide("e2-"));
        Assertions.assertFalse(game.estNotationValide("Be2xBh1"));
    }
}
