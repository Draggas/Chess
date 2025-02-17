package fr.draggas.project.chess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.draggas.project.chess.model.Position;

public class PositionTest {

    @Test
    public void test_construction_via_coordonnees() {
        Position position = new Position(3, 6);
        Assertions.assertEquals(3, position.getX());
        Assertions.assertEquals(6, position.getY());
    }

    @Test
    public void test_construction_via_notation() {
        Position position = new Position("c3");
        Assertions.assertEquals(3, position.getX());
        Assertions.assertEquals(3, position.getY());
    }

    @Test
    public void test_verification_de_coordonnees_valides() {
        Assertions.assertTrue(Position.verifValeur(1, 1));
        Assertions.assertTrue(Position.verifValeur(8, 8));
        Assertions.assertTrue(Position.verifValeur(5, 3));
    }

    @Test
    public void test_verification_de_coordonnees_invalides() {
        Assertions.assertFalse(Position.verifValeur(0, 5));
        Assertions.assertFalse(Position.verifValeur(9, 5));
        Assertions.assertFalse(Position.verifValeur(5, 0));
        Assertions.assertFalse(Position.verifValeur(5, 9));
    }

    @Test
    public void test_verification_via_notation() {
        Assertions.assertTrue(Position.verifValeur("d4"));
        Assertions.assertFalse(Position.verifValeur("z9"));
    }

    @Test
    public void test_equals_et_hashCode() {
        Position position1 = new Position(4, 6);
        Position position2 = new Position(4, 6);
        Position position3 = new Position(5, 6);
        Position position4 = position1;

        Assertions.assertTrue(position1.equals(position4));

        Assertions.assertFalse(position1.equals(null));

        Object nonPositionObject = new Object();
        Assertions.assertFalse(position1.equals(nonPositionObject));

        Assertions.assertTrue(position1.equals(position2));
        Assertions.assertFalse(position1.equals(position3));

        Assertions.assertEquals(position1.hashCode(), position2.hashCode());
        Assertions.assertNotEquals(position1.hashCode(), position3.hashCode());
    }

    @Test
    public void test_setX() {
        Position position = new Position(4, 6);
        position.setX(7);
        Assertions.assertEquals(7, position.getX());
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            position.setX(9);
        });
    }

    @Test
    public void test_setY() {
        Position position = new Position(4, 6);
        position.setY(7);
        Assertions.assertEquals(7, position.getY());
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            position.setY(9);
        });
    }

    @Test
    public void test_toString() {
        Position position = new Position(3, 6);
        Assertions.assertEquals("c6", position.toString());

        position = new Position("a1");
        Assertions.assertEquals("a1", position.toString());

        position = new Position(8, 8);
        Assertions.assertEquals("h8", position.toString());
    }
}
