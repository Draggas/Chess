package fr.draggas.project.chess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.draggas.project.chess.controller.*;
import fr.draggas.project.chess.model.Chess;

public class ChessControllerTest {
    @Test
    void test_controller(){
        ChessController controller = new ChessController(new Chess());
        Assertions.assertTrue(controller.verificationDuPointDeDepart("e2"));
        Assertions.assertTrue(controller.verificationDuDeplacement("e2","e4"));
        controller.changementTour();
    }   
}