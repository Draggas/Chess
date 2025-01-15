import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        Assertions.assertTrue(game.verifCoup("d7"));
        Assertions.assertTrue(game.verifCoup("h8"));
        Assertions.assertTrue(game.verifCoup("h1"));
        Assertions.assertFalse(game.verifCoup("g9"));
        Assertions.assertFalse(game.verifCoup("j1"));
        Assertions.assertFalse(game.verifCoup("d4"));
        Assertions.assertFalse(game.verifCoup("d5"));
        Assertions.assertFalse(game.verifCoup("d23"));
        Assertions.assertFalse(game.verifCoup("xx"));
    }

      
/*
 * 
    @Test
    public void TestPromotion(){
        game = new Chess(false);
        game.addPieces(new Position('a',7), new Pion(true));
        Assertions.assertTrue(game.estDeplacementValide("a7-a8"));
        Assertions.assertEquals(Reine.class, game.get(new Position('a', 8)).getClass());
        game.changeTour();
        game.addPieces(new Position('a',2), new Pion(false));
        Assertions.assertTrue(game.estDeplacementValide("a2-a1"));        
        Assertions.assertEquals(Reine.class, game.get(new Position('a', 1)).getClass());
        game.addPieces(new Position('b',2), new Pion(false));
        game.addPieces(new Position('c',1), new Fou(true));
        Assertions.assertTrue(game.estDeplacementValide("b2xc1"));
        Assertions.assertEquals(Reine.class, game.get(new Position('c', 1)).getClass());
        game.addPieces(new Position('d',2), new Pion(true));
        Assertions.assertFalse(game.estDeplacementValide("d2-d1"));
    }
 */

    @Test
    public void TestSauvegarde() throws ClassNotFoundException, IOException{
        Assertions.assertTrue(game.estNotationValide("e2-e4"));
        Assertions.assertTrue(game.estNotationValide("d4-d3"));
        Assertions.assertTrue(game.estNotationValide("e8-e5"));
        Assertions.assertTrue(game.estNotationValide("Rd7-d6"));
        Chess.sauvegarderPartie(game.historique(), "test.txt");
        Chess g = new Chess(true);
        Chess.recupererSauvegarde("test.txt");
        Assertions.assertEquals(game.lireSauvegarde(), g.lireSauvegarde());
    }
}