import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.draggas.project.chess.*;

public class ChessTest {
    Chess game;
    String ls = System.lineSeparator();
    String affichage =  "RNBQKBNR" + ls + "PPPPPPPP" + ls + "xxxxxxxx" + ls + "xxxxxxxx" + ls + "xxxxxxxx" + ls + "xxxxxxxx" + ls + "pppppppp" + ls + "rnbqkbnr";
    String affichageVide =  "xxxxxxxx" + ls + "xxxxxxxx" + ls + "xxxxxxxx" + ls + "xxxxxxxx" + ls + "xxxxxxxx" + ls + "xxxxxxxx" + ls + "xxxxxxxx" + ls + "xxxxxxxx";
    
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