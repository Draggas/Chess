import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.draggas.project.chess.Cavalier;
import fr.draggas.project.chess.Chess;
import fr.draggas.project.chess.Position;

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
    public void TestVerifPositionCaractere(){
        Assertions.assertTrue(new Position(1,5).equals(new Position('a', 5)));
        Assertions.assertEquals(new Position(2,5), new Position('b', 5));
        Assertions.assertEquals(new Position(5,5), new Position('e', 5));
        Assertions.assertEquals(new Position(4,5), new Position('d', 5));
        Assertions.assertEquals(new Position(8,5), new Position('h', 5));
    }

    @Test
    public void TestDeplacementValidePion(){
        Assertions.assertTrue(game.estDeplacementValide("e2-e4"));
        Assertions.assertTrue(game.estDeplacementValide("d2-d3"));
        Assertions.assertTrue(game.estDeplacementValide("e7-e5"));
        Assertions.assertTrue(game.estDeplacementValide("d7-d5"));
        Assertions.assertTrue(game.estDeplacementValide("e4xd5"));
        Assertions.assertTrue(game.estDeplacementValide("c7-c6"));
        Assertions.assertTrue(game.estDeplacementValide("d5xc6"));
        Assertions.assertTrue(game.estDeplacementValide("b7xc6"));

        Assertions.assertFalse(game.estDeplacementValide("a2-a5"));
        Assertions.assertFalse(game.estDeplacementValide("a6-a4"));
        Assertions.assertFalse(game.estDeplacementValide("a3-a8"));
        Assertions.assertFalse(game.estDeplacementValide("b3-a4"));
    }

    @Test
    public void TestDeplacementValideCavalier(){
        game = new Chess(false);
        game.addPieces(new Position('a',1), new Cavalier(true));
        Assertions.assertTrue(game.estDeplacementValide("Na1-c2"));
        Assertions.assertTrue(game.estDeplacementValide("Nc2-a3"));
        Assertions.assertTrue(game.estDeplacementValide("Na3-b5"));
        Assertions.assertTrue(game.estDeplacementValide("Nb5-c3"));
        Assertions.assertTrue(game.estDeplacementValide("Nc3-e2"));
        Assertions.assertTrue(game.estDeplacementValide("Ne2-d4"));
        Assertions.assertTrue(game.estDeplacementValide("Nd4-Nf3"));
        game.addPieces(new Position('e',5), new Cavalier(false));
        Assertions.assertTrue(game.estDeplacementValide("Nf3xe5"));
        game.addPieces(new Position('c',4), new Cavalier(false));
        Assertions.assertTrue(game.estDeplacementValide("Ne5xc4"));
        game.addPieces(new Position('d',2), new Cavalier(false));
        Assertions.assertTrue(game.estDeplacementValide("Nc4xd2"));

        Assertions.assertFalse(game.estDeplacementValide("Nd2-d7"));
        Assertions.assertFalse(game.estDeplacementValide("Nd2-e3"));
        Assertions.assertFalse(game.estDeplacementValide("Nd2xc4"));
        Assertions.assertFalse(game.estDeplacementValide("Nd2-Nd9"));
        Assertions.assertFalse(game.estDeplacementValide("Nd1-Nd4"));
    }
}
