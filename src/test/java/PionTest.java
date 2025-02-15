import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import fr.draggas.project.chess.model.Chess;
import fr.draggas.project.chess.model.Pion;
import fr.draggas.project.chess.model.Position;

public class PionTest {
    Chess echiquier;
    Pion pionBlanc;
    Position positionDuPion;

    @BeforeEach
    public void setUp() {
        // Création d'un échiquier vide (false pour indiquer qu'on le initialise vide)
        echiquier = new Chess(false);

        // Création d'un pion blanc (pièce à tester)
        pionBlanc = new Pion(true);
        positionDuPion = new Position(2, 2);
    }

    @Test
    public void test_des_deplacements_classiques_d_un_pion() {
        // Placement du pion blanc sur l'échiquier
        echiquier.ajoutPieces(positionDuPion, pionBlanc);

        // Récupération des mouvements possibles du pion
        List<Position> mouvements = pionBlanc.deplacementsPossible(positionDuPion, echiquier);

        // Vérification des déplacements standards (1 ou 2 cases vers l'avant si libre)
        Assertions.assertEquals(2, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(2, 3))); // Avance d'une case
        Assertions.assertTrue(mouvements.contains(new Position(2, 4))); // Avance de deux cases (premier déplacement)
    }

    @Test
    public void test_des_deplacements_impossibles_lorsqu_un_pion_rencontre_une_autre_piece() {
        // Placement du pion blanc sur l'échiquier
        echiquier.ajoutPieces(positionDuPion, pionBlanc);
        
        // Placement d'un autre pion blanc devant lui (bloque le mouvement)
        echiquier.ajoutPieces(new Position(2, 3), new Pion(true));

        // Récupération des mouvements possibles
        List<Position> mouvements = pionBlanc.deplacementsPossible(positionDuPion, echiquier);

        // Vérification que le pion ne peut pas avancer
        Assertions.assertTrue(mouvements.isEmpty()); // Aucun mouvement possible
        Assertions.assertFalse(mouvements.contains(new Position(2, 3))); // Bloqué
        Assertions.assertFalse(mouvements.contains(new Position(2, 4))); // Bloqué
    }

    @Test
    public void test_des_prises_en_diagonales_du_pion() {
        // Placement du pion blanc sur l'échiquier
        echiquier.ajoutPieces(positionDuPion, pionBlanc);

        // Placement de pions noirs en diagonale (capturables)
        echiquier.ajoutPieces(new Position(3, 3), new Pion(false)); // Pion noir (capturable)
        echiquier.ajoutPieces(new Position(1, 3), new Pion(false)); // Pion noir (capturable)

        // Récupération des mouvements possibles
        List<Position> mouvements = pionBlanc.deplacementsPossible(positionDuPion, echiquier);

        // Vérification des captures en diagonale et des déplacements classiques
        Assertions.assertEquals(4, mouvements.size()); // 4 mouvements possibles
        Assertions.assertTrue(mouvements.contains(new Position(3, 3))); // Prise en diagonale droite
        Assertions.assertTrue(mouvements.contains(new Position(1, 3))); // Prise en diagonale gauche
        Assertions.assertTrue(mouvements.contains(new Position(2, 3))); // Avance d'une case
        Assertions.assertTrue(mouvements.contains(new Position(2, 4))); // Avance de deux cases
    }

    @Test
    public void test_prise_en_passant() {
        // Placement du pion blanc sur l'échiquier
        Position positionPion = new Position(4, 4);
        echiquier.ajoutPieces(positionPion, pionBlanc);

        // Placement d'un pion noir à côté du pion blanc
        Pion pionNoir = new Pion(false);
        Position positionPionNoir = new Position(5, 4);
        echiquier.ajoutPieces(positionPionNoir, pionNoir);

        // Simulation d'une prise en passant (case cible après un coup adverse)
        echiquier.setPriseEnPassant(new Position(5, 5));

        // Récupération des mouvements possibles du pion blanc
        List<Position> mouvements = pionBlanc.deplacementsPossible(positionPion, echiquier);

        // Vérification de la prise en passant
        Assertions.assertEquals(2, mouvements.size()); // prise en passant et mouvement en avant classique
        Assertions.assertTrue(mouvements.contains(new Position(5, 5))); // Capture en passant
    }

    @Test
    public void test_prise_en_passant_impossible() {
        // Placement du pion blanc sur l'échiquier
        Position positionPion = new Position(4, 4);
        echiquier.ajoutPieces(positionPion, pionBlanc);

        // Placement d'un pion noir à côté du pion blanc
        Pion pionNoir = new Pion(false);
        Position positionPionNoir = new Position(5, 4);
        echiquier.ajoutPieces(positionPionNoir, pionNoir);

        // Aucun coup spécial permettant la prise en passant
        List<Position> mouvements = pionBlanc.deplacementsPossible(positionPion, echiquier);

        // Vérification que la prise en passant n'est pas possible
        Assertions.assertEquals(1, mouvements.size()); // Un seul déplacement possible
        Assertions.assertFalse(mouvements.contains(new Position(5, 5))); // Pas de prise en passant
        Assertions.assertTrue(mouvements.contains(new Position(4, 5))); // Déplacement classique
    }
}
