import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.draggas.project.chess.*;

public class ChessTest {
    Chess game;
    String ls = System.lineSeparator();
    String affichage =  "8 RNBQKBNR" + ls + "7 PPPPPPPP" + ls + "6 xxxxxxxx" + ls + "5 xxxxxxxx" + ls + "4 xxxxxxxx" + ls + "3 xxxxxxxx" + ls + "2 pppppppp" + ls + "1 rnbqkbnr" + ls + "  abcdefgh";
    String affichageVide =  "8 xxxxxxxx" + ls + "7 xxxxxxxx" + ls + "6 xxxxxxxx" + ls + "5 xxxxxxxx" + ls + "4 xxxxxxxx" + ls + "3 xxxxxxxx" + ls + "2 xxxxxxxx" + ls + "1 xxxxxxxx" + ls + "  abcdefgh";
    
    @BeforeEach
    public void Initialisation(){
        game = new Chess();
    }
    
    @Test
    public void TestPlateauPlein(){
        Assertions.assertEquals(affichage, game.affichage());
    }

    @Test
    public void TestPlateauVide(){
        game = new Chess(false);
        Assertions.assertEquals(affichageVide, game.affichage());
    }

    @Test
    public void TestNotationValide(){
        Assertions.assertTrue(game.verifCoup("e2"));
        game.changeTour();
        Assertions.assertTrue(game.verifCoup("d7"));
        Assertions.assertTrue(game.verifCoup("h8"));
        game.changeTour();
        Assertions.assertTrue(game.verifCoup("h1"));
        Assertions.assertFalse(game.verifCoup("g9"));
        Assertions.assertFalse(game.verifCoup("j1"));
        Assertions.assertFalse(game.verifCoup("d4"));
        Assertions.assertFalse(game.verifCoup("d5"));
        Assertions.assertFalse(game.verifCoup("d23"));
        Assertions.assertFalse(game.verifCoup("xx"));
    }
}