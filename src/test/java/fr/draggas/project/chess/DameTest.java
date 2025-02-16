package fr.draggas.project.chess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import fr.draggas.project.chess.model.Chess;
import fr.draggas.project.chess.model.Pion;
import fr.draggas.project.chess.model.Position;
import fr.draggas.project.chess.model.Dame;

public class DameTest {
    Chess echiquier;
    Dame dameBlanche;
    Position positionDeLaDame;

    @BeforeEach
    public void setUp() {
        // Création d'un échiquier vide (false pour indiquer qu'on le initialise vide)
        echiquier = new Chess(false);
        
        // Création d'une dame blanche (pièce à tester)
        dameBlanche = new Dame(true);
    }

    @Test
    public void test_des_deplacements_possibles_d_une_dame_avec_obstacles() {
        // Placement de la dame blanche
        positionDeLaDame = new Position(4, 4);
        echiquier.ajoutPieces(positionDeLaDame, dameBlanche);

        // Placement d'obstacles sur l'échiquier
        echiquier.ajoutPieces(new Position(4, 6), new Pion(true)); // Pion blanc (obstacle)
        echiquier.ajoutPieces(new Position(2, 4), new Pion(true)); // Pion blanc (obstacle)
        echiquier.ajoutPieces(new Position(4, 2), new Pion(false)); // Pion noir (capturable)
        echiquier.ajoutPieces(new Position(6, 4), new Pion(false)); // Pion noir (capturable)
        echiquier.ajoutPieces(new Position(6, 6), new Pion(false)); // Pion noir (capturable)

        // Récupération des déplacements possibles de la dame
        List<Position> mouvements = dameBlanche.deplacementsPossible(positionDeLaDame, echiquier);

        // Ajout d'obstacles sur les diagonales et les lignes/colonnes
        Assertions.assertFalse(mouvements.contains(new Position(4, 7))); // Après obstacle blanc
        Assertions.assertFalse(mouvements.contains(new Position(7, 4))); // Après obstacle noir
        Assertions.assertFalse(mouvements.contains(new Position(7, 7))); // Après obstacle noir en diagonale
        Assertions.assertTrue(mouvements.contains(new Position(4, 2))); // Obstacle noir (prenable)
        Assertions.assertTrue(mouvements.contains(new Position(4, 5))); // Case libre au-dessus
        Assertions.assertTrue(mouvements.contains(new Position(5, 4))); // Case libre à droite
        Assertions.assertTrue(mouvements.contains(new Position(5, 5))); // Diagonale droite-haut
        Assertions.assertTrue(mouvements.contains(new Position(3, 3))); // Diagonale gauche-bas
        Assertions.assertTrue(mouvements.contains(new Position(5, 3))); // Diagonale droite-bas

    }

    @Test
    public void test_des_deplacements_possibles_d_une_dame_sans_obstacles() {
        // Placement de la dame blanche
        positionDeLaDame = new Position(1, 1);
        echiquier.ajoutPieces(positionDeLaDame, dameBlanche);

        // Récupération des déplacements possibles sans obstacles
        List<Position> mouvements = dameBlanche.deplacementsPossible(positionDeLaDame, echiquier);

        // Vérification du nombre de déplacements possibles
        Assertions.assertEquals(21, mouvements.size()); // 22 mouvements possibles
        Assertions.assertTrue(mouvements.contains(new Position(8, 8))); // Diagonale
        Assertions.assertTrue(mouvements.contains(new Position(1, 8))); // Haut
        Assertions.assertTrue(mouvements.contains(new Position(8, 1))); // Droite
    }
}
