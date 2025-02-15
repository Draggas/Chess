import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import fr.draggas.project.chess.model.Chess;
import fr.draggas.project.chess.model.Position;
import fr.draggas.project.chess.model.Roi;
import fr.draggas.project.chess.model.Tour;

public class RoiTest {
    Chess echiquier;
    Roi roiBlanc;
    Position positionDuRoi;

    @BeforeEach
    void setUp() {
        // Création d'un échiquier vide
        echiquier = new Chess(false);
        
        // Création d'un roi blanc (pièce à tester)
        roiBlanc = new Roi(true);
    }

    @Test
    public void test_des_deplacements_possibles_du_roi_sans_obstacles() {
        // Position initiale du roi
        positionDuRoi = new Position(4, 4);
        echiquier.ajoutPieces(positionDuRoi, roiBlanc);

        // Calcul des déplacements possibles pour le roi
        List<Position> mouvements = roiBlanc.deplacementsPossible(positionDuRoi, echiquier);

        // Vérification qu'il y a bien 8 déplacements possibles pour le roi sans obstacles
        Assertions.assertEquals(8, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(4, 5))); // Haut
        Assertions.assertTrue(mouvements.contains(new Position(3, 3))); // Bas Gauche
        Assertions.assertTrue(mouvements.contains(new Position(5, 3))); // Bas Droite
    }

    @Test
    public void test_des_differents_roques_du_roi() {
        // Position initiale du roi
        positionDuRoi = new Position(5,1);
        echiquier.ajoutPieces(positionDuRoi, roiBlanc);

        // Ajouts Tours Pour Roque
        Tour tourBlanc = new Tour(true);
        Tour tour2Blanc = new Tour(true);
        Position positionTour = new Position(1,1);
        Position positionTour2 = new Position(8,1);
        echiquier.ajoutPieces(positionTour, tourBlanc);
        echiquier.ajoutPieces(positionTour2, tour2Blanc);
        
        // Calcul des déplacements possibles pour le roi
        List<Position> mouvements = roiBlanc.deplacementsPossible(positionDuRoi, echiquier);

        // Vérification qu'il y a bien 7 déplacements possibles pour le roi à la place de 5
        Assertions.assertEquals(7, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(7,1))); // Petit Roque
        Assertions.assertTrue(mouvements.contains(new Position(2,1))); // Grand Roque
    }
}
