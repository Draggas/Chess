import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import fr.draggas.project.chess.model.Cavalier;
import fr.draggas.project.chess.model.Chess;
import fr.draggas.project.chess.model.Pion;
import fr.draggas.project.chess.model.Position;

public class CavalierTest {
    Chess echiquier;
    Cavalier cavalierBlanc;
    Position positionDuCavalier;

    @BeforeEach
    public void setUp() {
        // Création d'un échiquier vide (false pour indiquer qu'on le initialise vide)
        echiquier = new Chess(false);
        
        // Création d'un cavalier blanc (pièce à tester)
        cavalierBlanc = new Cavalier(true);
        positionDuCavalier = new Position(4, 4);
        echiquier.ajoutPieces(positionDuCavalier, cavalierBlanc);
    }

    @Test
    public void test_des_deplacements_possibles_d_un_cavalier_avec_obstacles() {
        echiquier.ajoutPieces(new Position(5, 6), new Pion(true)); // Pion blanc (obstacle)
        echiquier.ajoutPieces(new Position(3, 2), new Pion(false)); // Pion noir (capturable)
        
        // Récupération des mouvements possibles du cavalier
        List<Position> mouvements = cavalierBlanc.deplacementsPossible(positionDuCavalier, echiquier);
        
        // Vérifications des mouvements possibles
        Assertions.assertTrue(mouvements.contains(new Position(3, 2))); // Capture d'un pion noir possible
        Assertions.assertFalse(mouvements.contains(new Position(5, 6))); // Mouvement bloqué par un pion blanc
    }

    @Test
    public void test_des_deplacements_possibles_d_un_cavalier_sans_obstacles() {
        // Récupération des mouvements possibles
        List<Position> mouvements = cavalierBlanc.deplacementsPossible(positionDuCavalier, echiquier);
        
        // Vérification que le cavalier peut bouger vers 8 positions différentes
        Assertions.assertEquals(8, mouvements.size());
        
        // Vérification de quelques mouvements attendus
        Assertions.assertTrue(mouvements.contains(new Position(6, 5))); // Mouvement en L vers la droite
        Assertions.assertTrue(mouvements.contains(new Position(2, 3))); // Mouvement en L vers la gauche
    }
}
