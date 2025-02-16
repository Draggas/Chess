package fr.draggas.project.chess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import fr.draggas.project.chess.model.Chess;
import fr.draggas.project.chess.model.Pion;
import fr.draggas.project.chess.model.Position;
import fr.draggas.project.chess.model.Roi;
import fr.draggas.project.chess.model.Tour;

public class RoiTest {
    Chess echiquier;
    Roi roiBlanc;
    Roi roiNoir;
    Position positionDuRoi;

    @BeforeEach
    void setUp() {
        // Création d'un échiquier vide
        echiquier = new Chess(false);
        
        // Création d'un roi blanc et d'un roi noir (pièces à tester)
        roiBlanc = new Roi(true);
        roiNoir = new Roi(false);
    }

    @Test
    public void test_des_deplacements_possibles_du_roi_sans_obstacles() {
        // Position initiale du roi blanc
        positionDuRoi = new Position(4, 4);
        echiquier.ajoutPieces(positionDuRoi, roiBlanc);

        // Calcul des déplacements possibles pour le roi blanc
        List<Position> mouvements = roiBlanc.deplacementsPossible(positionDuRoi, echiquier);

        // Vérification qu'il y a bien 8 déplacements possibles pour le roi sans obstacles
        Assertions.assertEquals(8, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(4, 5))); // Haut
        Assertions.assertTrue(mouvements.contains(new Position(4, 3))); // Bas
        Assertions.assertTrue(mouvements.contains(new Position(5, 4))); // Droite
        Assertions.assertTrue(mouvements.contains(new Position(3, 4))); // Gauche
        Assertions.assertTrue(mouvements.contains(new Position(5, 5))); // Haut Droite
        Assertions.assertTrue(mouvements.contains(new Position(3, 5))); // Haut Gauche
        Assertions.assertTrue(mouvements.contains(new Position(5, 3))); // Bas Droite
        Assertions.assertTrue(mouvements.contains(new Position(3, 3))); // Bas Gauche
    }

    @Test
    public void test_des_differents_roques_du_roi() {
        // Position initiale du roi blanc
        positionDuRoi = new Position(5, 1);
        echiquier.ajoutPieces(positionDuRoi, roiBlanc);

        // Ajout de Tours pour le roque
        Tour tourBlanc = new Tour(true);
        Tour tour2Blanc = new Tour(true);
        Position positionTour = new Position(1, 1);
        Position positionTour2 = new Position(8, 1);
        echiquier.ajoutPieces(positionTour, tourBlanc);
        echiquier.ajoutPieces(positionTour2, tour2Blanc);
        
        // Calcul des déplacements possibles pour le roi
        List<Position> mouvements = roiBlanc.deplacementsPossible(positionDuRoi, echiquier);

        // Vérification des déplacements possibles pour le roi avec roques
        Assertions.assertEquals(7, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(6, 1))); // Droite
        Assertions.assertTrue(mouvements.contains(new Position(4, 1))); // Gauche
        Assertions.assertTrue(mouvements.contains(new Position(5, 2))); // Haut
        Assertions.assertTrue(mouvements.contains(new Position(6, 2))); // Haut Droite
        Assertions.assertTrue(mouvements.contains(new Position(4, 2))); // Haut Gauche
        Assertions.assertTrue(mouvements.contains(new Position(7, 1))); // Petit Roque
        Assertions.assertTrue(mouvements.contains(new Position(3, 1))); // Grand Roque
    }

    @Test
    public void test_setters_et_getters_du_roi() {
        // Test des getters et setters de roque et grand roque
        roiBlanc.setRoque(false);
        roiBlanc.setGrandRoque(false);
        Assertions.assertFalse(roiBlanc.getRoque());
        Assertions.assertFalse(roiBlanc.getGrandRoque());

        roiBlanc.setRoque(true);
        roiBlanc.setGrandRoque(true);
        Assertions.assertTrue(roiBlanc.getRoque());
        Assertions.assertTrue(roiBlanc.getGrandRoque());
    }

    @Test
    public void test_deplacements_possibles_avec_obstacles() {
        // Position initiale du roi blanc
        positionDuRoi = new Position(4, 4);
        echiquier.ajoutPieces(positionDuRoi, roiBlanc);

        // Placement d'obstacles autour du roi
        echiquier.ajoutPieces(new Position(5, 5), new Pion(true)); // Pion blanc
        echiquier.ajoutPieces(new Position(3, 5), new Pion(false)); // Pion noir

        // Calcul des déplacements possibles pour le roi
        List<Position> mouvements = roiBlanc.deplacementsPossible(positionDuRoi, echiquier);

        // Vérification des déplacements possibles pour le roi avec obstacles
        Assertions.assertEquals(7, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(4, 5))); // Haut
        Assertions.assertTrue(mouvements.contains(new Position(4, 3))); // Bas
        Assertions.assertTrue(mouvements.contains(new Position(5, 4))); // Droite
        Assertions.assertTrue(mouvements.contains(new Position(3, 4))); // Gauche
        Assertions.assertFalse(mouvements.contains(new Position(5, 5))); // Haut Droite (obstacle)
        Assertions.assertTrue(mouvements.contains(new Position(3, 5))); // Haut Gauche (capturable)
        Assertions.assertTrue(mouvements.contains(new Position(5, 3))); // Bas Droite
        Assertions.assertTrue(mouvements.contains(new Position(3, 3))); // Bas Gauche
    }

    @Test
    public void test_verifier_roque() {
        // Position initiale du roi blanc
        positionDuRoi = new Position(5, 1);
        echiquier.ajoutPieces(positionDuRoi, roiBlanc);

        // Ajout de Tour pour le roque
        Tour tourBlanc = new Tour(true);
        Position positionTour = new Position(8, 1);
        echiquier.ajoutPieces(positionTour, tourBlanc);

        // Vérification que le roque est possible
        boolean roquePossible = roiBlanc.verifierRoque(positionTour, echiquier, 1);
        Assertions.assertTrue(roquePossible);
    }

    @Test
    public void test_verifier_grand_roque() {
        // Position initiale du roi blanc
        positionDuRoi = new Position(5, 1);
        echiquier.ajoutPieces(positionDuRoi, roiBlanc);

        // Ajout de Tour pour le grand roque
        Tour tourBlanc = new Tour(true);
        Position positionTour = new Position(1, 1);
        echiquier.ajoutPieces(positionTour, tourBlanc);

        // Vérification que le grand roque est possible
        boolean grandRoquePossible = roiBlanc.verifierGrandRoque(positionTour, echiquier, 1);
        Assertions.assertTrue(grandRoquePossible);
    }

    @Test
    public void test_verifier_roque_impossible() {
        // Position initiale du roi blanc
        positionDuRoi = new Position(5, 1);
        echiquier.ajoutPieces(positionDuRoi, roiBlanc);

        // Ajout de Tour pour le roque
        Tour tourBlanc = new Tour(true);
        Position positionTour = new Position(8, 1);
        echiquier.ajoutPieces(positionTour, tourBlanc);

        // Ajout d'un obstacle pour bloquer le roque
        echiquier.ajoutPieces(new Position(7, 1), new Pion(false));

        // Vérification que le roque n'est pas possible
        boolean roquePossible = roiBlanc.verifierRoque(positionTour, echiquier, 1);
        Assertions.assertFalse(roquePossible);
    }

    @Test
    public void test_verifier_grand_roque_impossible() {
        // Position initiale du roi blanc
        positionDuRoi = new Position(5, 1);
        echiquier.ajoutPieces(positionDuRoi, roiBlanc);

        // Ajout de Tour pour le grand roque
        Tour tourBlanc = new Tour(true);
        Position positionTour = new Position(1, 1);
        echiquier.ajoutPieces(positionTour, tourBlanc);

        // Ajout d'un obstacle pour bloquer le grand roque
        echiquier.ajoutPieces(new Position(3, 1), new Pion(false));

        // Vérification que le grand roque n'est pas possible
        boolean grandRoquePossible = roiBlanc.verifierGrandRoque(positionTour, echiquier, 1);
        Assertions.assertFalse(grandRoquePossible);
    }

    @Test
    public void test_deplacement_possible_pour_roi_noir() {
        // Position initiale du roi noir
        positionDuRoi = new Position(5, 8);
        echiquier.ajoutPieces(positionDuRoi, roiNoir);

        // Calcul des déplacements possibles pour le roi noir
        List<Position> mouvements = roiNoir.deplacementsPossible(positionDuRoi, echiquier);

        // Vérification des déplacements possibles pour le roi noir
        Assertions.assertEquals(5, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(5, 7))); // Bas
        Assertions.assertTrue(mouvements.contains(new Position(6, 8))); // Droite
        Assertions.assertTrue(mouvements.contains(new Position(4, 8))); // Gauche
        Assertions.assertTrue(mouvements.contains(new Position(6, 7))); // Bas Droite
        Assertions.assertTrue(mouvements.contains(new Position(4, 7))); // Bas Gauche
    }

    @Test
    public void test_deplacements_possibles_roi_en_bordure() {
        // Position initiale du roi blanc en bordure de l'échiquier
        positionDuRoi = new Position(1, 1);
        echiquier.ajoutPieces(positionDuRoi, roiBlanc);

        // Calcul des déplacements possibles pour le roi blanc
        List<Position> mouvements = roiBlanc.deplacementsPossible(positionDuRoi, echiquier);

        // Vérification des déplacements possibles pour le roi blanc en bordure
        Assertions.assertEquals(3, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(2, 1))); // Droite
        Assertions.assertTrue(mouvements.contains(new Position(1, 2))); // Haut
        Assertions.assertTrue(mouvements.contains(new Position(2, 2))); // Haut Droite
    }

    @Test
    public void test_deplacements_possibles_roi_en_coin() {
        // Position initiale du roi blanc dans un coin de l'échiquier
        positionDuRoi = new Position(8, 8);
        echiquier.ajoutPieces(positionDuRoi, roiBlanc);

        // Calcul des déplacements possibles pour le roi blanc
        List<Position> mouvements = roiBlanc.deplacementsPossible(positionDuRoi, echiquier);

        // Vérification des déplacements possibles pour le roi blanc dans un coin
        Assertions.assertEquals(3, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(7, 8))); // Gauche
        Assertions.assertTrue(mouvements.contains(new Position(8, 7))); // Bas
        Assertions.assertTrue(mouvements.contains(new Position(7, 7))); // Bas Gauche
    }

    @Test
    public void test_deplacements_possibles_roque_non_permis() {
        // Position initiale du roi blanc
        positionDuRoi = new Position(5, 1);
        echiquier.ajoutPieces(positionDuRoi, roiBlanc);

        // Ajout de Tours pour le roque
        Tour tourBlanc = new Tour(true);
        Position positionTour = new Position(8, 1);
        echiquier.ajoutPieces(positionTour, tourBlanc);

        // Désactiver le roque et le grand roque
        roiBlanc.setRoque(false);
        roiBlanc.setGrandRoque(false);

        // Calcul des déplacements possibles pour le roi
        List<Position> mouvements = roiBlanc.deplacementsPossible(positionDuRoi, echiquier);

        // Vérification des déplacements possibles pour le roi avec roques désactivés
        Assertions.assertEquals(5, mouvements.size());
        Assertions.assertTrue(mouvements.contains(new Position(6, 1))); // Droite
        Assertions.assertTrue(mouvements.contains(new Position(4, 1))); // Gauche
        Assertions.assertTrue(mouvements.contains(new Position(5, 2))); // Haut
        Assertions.assertTrue(mouvements.contains(new Position(6, 2))); // Haut Droite
        Assertions.assertTrue(mouvements.contains(new Position(4, 2))); // Haut Gauche
        Assertions.assertFalse(mouvements.contains(new Position(7, 1))); // Petit Roque non permis
        Assertions.assertFalse(mouvements.contains(new Position(3, 1))); // Grand Roque non permis
    }

    @Test
    public void test_verifier_roque_obstacle_sur_case_7() {
        // Position initiale du roi blanc
        positionDuRoi = new Position(5, 1);
        echiquier.ajoutPieces(positionDuRoi, roiBlanc);

        // Ajout de Tour pour le roque
        Tour tourBlanc = new Tour(true);
        Position positionTour = new Position(8, 1);
        echiquier.ajoutPieces(positionTour, tourBlanc);

        // Ajout d'un obstacle sur la case (7, 1)
        echiquier.ajoutPieces(new Position(7, 1), new Pion(false));

        // Vérification que le roque n'est pas possible
        boolean roquePossible = roiBlanc.verifierRoque(positionTour, echiquier, 1);
        Assertions.assertFalse(roquePossible);
    }

    @Test
    public void test_verifier_roque_tour_a_deja_bouge() {
        // Position initiale du roi blanc
        positionDuRoi = new Position(5, 1);
        echiquier.ajoutPieces(positionDuRoi, roiBlanc);

        // Ajout de Tour pour le roque
        Tour tourBlanc = new Tour(true);
        Position positionTour = new Position(8, 1);
        echiquier.ajoutPieces(positionTour, tourBlanc);

        // La tour a déjà bougé
        tourBlanc.setRoque(false);

        // Vérification que le roque n'est pas possible
        boolean roquePossible = roiBlanc.verifierRoque(positionTour, echiquier, 1);
        Assertions.assertFalse(roquePossible);
    }

    @Test
    public void test_verifier_grand_roque_obstacle_sur_case_3() {
        // Position initiale du roi blanc
        positionDuRoi = new Position(5, 1);
        echiquier.ajoutPieces(positionDuRoi, roiBlanc);

        // Ajout de Tour pour le grand roque
        Tour tourBlanc = new Tour(true);
        Position positionTour = new Position(1, 1);
        echiquier.ajoutPieces(positionTour, tourBlanc);

        // Ajout d'un obstacle sur la case (3, 1)
        echiquier.ajoutPieces(new Position(3, 1), new Pion(false));

        // Vérification que le grand roque n'est pas possible
        boolean grandRoquePossible = roiBlanc.verifierGrandRoque(positionTour, echiquier, 1);
        Assertions.assertFalse(grandRoquePossible);
    }

    @Test
    public void test_verifier_grand_roque_tour_a_deja_bouge() {
        // Position initiale du roi blanc
        positionDuRoi = new Position(5, 1);
        echiquier.ajoutPieces(positionDuRoi, roiBlanc);

        // Ajout de Tour pour le grand roque
        Tour tourBlanc = new Tour(true);
        Position positionTour = new Position(1, 1);
        echiquier.ajoutPieces(positionTour, tourBlanc);

        // La tour a déjà bougé
        tourBlanc.setRoque(false);

        // Vérification que le grand roque n'est pas possible
        boolean grandRoquePossible = roiBlanc.verifierGrandRoque(positionTour, echiquier, 1);
        Assertions.assertFalse(grandRoquePossible);
    }
}