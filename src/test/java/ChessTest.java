import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.draggas.project.chess.*;

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
        Assertions.assertTrue(game.estDeplacementValide("Nd4-f3"));
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

    @Test
    public void TestDeplacementValideFou(){
        game = new Chess(false);
        game.addPieces(new Position('a',1), new Fou(true));
        game.addPieces(new Position('a',2), new Fou(true));
        Assertions.assertTrue(game.estDeplacementValide("Ba1-g7"));
        Assertions.assertTrue(game.estDeplacementValide("Bg7-f8"));
        Assertions.assertTrue(game.estDeplacementValide("Bf8-h6"));
        game.addPieces(new Position('d',2), new Cavalier(false));
        Assertions.assertTrue(game.estDeplacementValide("Bh6xd2"));
        game.addPieces(new Position('d',5), new Pion(false));
        Assertions.assertTrue(game.estDeplacementValide("Ba2xd5"));
        Assertions.assertTrue(game.estDeplacementValide("Bd5-h1"));

        Assertions.assertFalse(game.estDeplacementValide("Bd2-g1"));
        Assertions.assertFalse(game.estDeplacementValide("Bd2-d3"));
        Assertions.assertFalse(game.estDeplacementValide("Bd2xe3"));
        game.addPieces(new Position('d',5), new Pion(false));
        Assertions.assertFalse(game.estDeplacementValide("Bh1-d5"));
        Assertions.assertFalse(game.estDeplacementValide("Bh1-b2"));
    }

    @Test
    public void TestDeplacementValideTour(){
        game = new Chess(false);
        game.addPieces(new Position('a',1), new Tour(true));
        Assertions.assertTrue(game.estDeplacementValide("Ra1-a8"));
        Assertions.assertTrue(game.estDeplacementValide("Ra8-f8"));
        Assertions.assertTrue(game.estDeplacementValide("Rf8-f3"));
        Assertions.assertTrue(game.estDeplacementValide("Rf3-b3"));
        
        Assertions.assertFalse(game.estDeplacementValide("Rf3-f3"));
    }

    @Test
    public void TestDeplacementValideReine(){
        game = new Chess(false);
        game.addPieces(new Position('a',1), new Reine(true));
        Assertions.assertTrue(game.estDeplacementValide("Qa1-g7"));
        Assertions.assertTrue(game.estDeplacementValide("Qg7-f8"));
        Assertions.assertTrue(game.estDeplacementValide("Qf8-h6"));
        game.addPieces(new Position('d',2), new Cavalier(false));
        Assertions.assertTrue(game.estDeplacementValide("Qh6xd2"));
        game.addPieces(new Position('d',5), new Pion(false));
        Assertions.assertTrue(game.estDeplacementValide("Qd2xd5"));
        Assertions.assertTrue(game.estDeplacementValide("Qd5-h1"));
        Assertions.assertTrue(game.estDeplacementValide("Qh1-a1"));
        Assertions.assertTrue(game.estDeplacementValide("Qa1-a8"));
        Assertions.assertTrue(game.estDeplacementValide("Qa8-f8"));
        Assertions.assertTrue(game.estDeplacementValide("Qf8-f3"));
        Assertions.assertTrue(game.estDeplacementValide("Qf3-b3"));
    }

    @Test
    public void TestDeplacementValideRoi(){
        game = new Chess(false);
        game.addPieces(new Position('a',1), new Roi(true));
        Assertions.assertTrue(game.estDeplacementValide("Ka1-b2"));
        Assertions.assertTrue(game.estDeplacementValide("Kb2-b3"));
        Assertions.assertTrue(game.estDeplacementValide("Kb3-b4"));
        game.addPieces(new Position('d',2), new Cavalier(false));
        Assertions.assertTrue(game.estDeplacementValide("Kb4-b5"));
        game.addPieces(new Position('d',5), new Pion(false));
        Assertions.assertTrue(game.estDeplacementValide("Kb5-a4"));
    }

}

/* Restants :
 * - Déplacement : Ne passe pas à travers les pièces
 * - Roi : O-O et O-O-O
 * - Pion : Promotion
 * - Pion : Prise en passant
 * - Système de tour
 * - Roi : Case d'Echecs et Echec et Mat
 * - Roi : Obligation de ne plus être en échec
 * - Notation avec "+" et "#"
 * - Notation du format "Rxb2" avec vérifications si 2 pièces peut y aller
 * - Système de création de terrain
 * - Système de cadence
 */
