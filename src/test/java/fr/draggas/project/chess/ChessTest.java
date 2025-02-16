package fr.draggas.project.chess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.draggas.project.chess.model.*;

import java.util.List;

public class ChessTest {
    Chess echiquier;

    @BeforeEach
    public void setUp() {
        echiquier = new Chess(); // Initialisation de l'échiquier avec les pièces à leur position initiale
    }

    @Test
    public void test_ecriture_des_notations_valides() {
        // Test de pièces valides
        Assertions.assertTrue(echiquier.verificationDuPointDeDepart("e2")); // Pion blanc bien initié
        echiquier.changementTour(); // Changement de tour (Joueur Noir)
        Assertions.assertTrue(echiquier.verificationDuPointDeDepart("d7")); // Pion noir bien initié
        Assertions.assertTrue(echiquier.verificationDuPointDeDepart("h8")); // Pion noir bien initié
        echiquier.changementTour(); // Changement de tour (Joueur Blanc)
        Assertions.assertTrue(echiquier.verificationDuPointDeDepart("h1")); // Tour blanche bien initiée

        // Test de pièces invalides
        Assertions.assertFalse(echiquier.verificationDuPointDeDepart("g9")); // Hors de l'échiquier (9 n'existe pas)
        Assertions.assertFalse(echiquier.verificationDuPointDeDepart("j1")); // Hors de l'échiquier (colonne "j" inexistante)
        Assertions.assertFalse(echiquier.verificationDuPointDeDepart("xx")); // Format incorrect (pas une notation échiquéenne)
        Assertions.assertFalse(echiquier.verificationDuPointDeDepart("d4")); // Case sans pièce au début de partie
        Assertions.assertFalse(echiquier.verificationDuPointDeDepart("d5")); // Case sans pièce au début de partie
        Assertions.assertFalse(echiquier.verificationDuPointDeDepart("d23")); // Format incorrect (23 n'existe pas)
    }

    @Test
    public void test_effectuerDeplacement_valide() {
        // Effectuer un déplacement valide d'un pion blanc
        Assertions.assertTrue(echiquier.verificationDuDeplacement("e2", "e4"));
    }

    @Test
    public void test_effectuerDeplacement_invalide() {
        // Effectuer un déplacement invalide (mauvaise couleur de joueur)
        echiquier.changementTour(); // Tour du joueur noir
        Assertions.assertFalse(echiquier.verificationDuDeplacement("e2", "e4")); // Mauvaise Couleur
        Assertions.assertFalse(echiquier.verificationDuDeplacement("e7","zx")); // Format incorrect // Mauvaise Couleur
        echiquier.verificationDuPointDeDepart("e7");
        Assertions.assertFalse(echiquier.verificationDuDeplacement("e2","e5")); // Mauvaise Couleur
        Assertions.assertFalse(echiquier.verificationDuDeplacement("e7","d23")); // Format incorrect (23 n'existe pas)
    }

    @Test
    public void test_effectuerDeplacement_fin_de_partie() {
        // Effectuer un déplacement pour mettre fin à la partie
        Assertions.assertTrue(echiquier.getTour());
        echiquier.ajoutPieces(new Position(5, 5), new Pion(true)); // Roi blanc au centre de l'échiquier
        echiquier.ajoutPieces(new Position(6, 6), new Roi(false)); // Pion noir prêt à capturer
        Assertions.assertTrue(echiquier.verificationDuDeplacement("e5", "f6")); // Déplacement qui capture le roi et met fin à la partie
        Assertions.assertTrue(echiquier.getFinPartie()); // Vérifier que la partie est terminée
    }

    @Test
    public void test_gererRoque_petit_roque() {
        // Effectuer un petit roque
        echiquier = new Chess(false);
        echiquier.ajoutPieces(new Position(5, 1), new Roi(true));
        echiquier.ajoutPieces(new Position(8, 1), new Tour(true));
        Assertions.assertTrue(echiquier.verificationDuDeplacement("e1", "g1")); // Petit roque
        Assertions.assertTrue(echiquier.verificationDuDeplacement("f1", "f2"));
    }

    @Test
    public void test_gererRoque_grand_roque() {
        // Effectuer un grand roque
        echiquier = new Chess(false);
        echiquier.ajoutPieces(new Position(5, 1), new Roi(true));
        echiquier.ajoutPieces(new Position(1, 1), new Tour(true));
        Assertions.assertTrue(echiquier.verificationDuDeplacement("e1", "c1")); // Grand roque
    }

    @Test
    public void test_effectuerDeplacement_tour_et_roque() {
        // Effectuer un déplacement et vérifier le roque
        echiquier = new Chess(false);
        echiquier.ajoutPieces(new Position(5, 1), new Roi(true));
        echiquier.ajoutPieces(new Position(8, 1), new Tour(true));
        echiquier.ajoutPieces(new Position(1, 1), new Tour(true));

        // Petit Roque
        Assertions.assertTrue(echiquier.verificationDuDeplacement("e1", "g1"));
        Pieces pieceTour1 = echiquier.obtenirPieceALaPosition(new Position(6, 1));
        Assertions.assertEquals('t', pieceTour1.getID());

        // Grand Roque
        echiquier.ajoutPieces(new Position(5, 1), new Roi(true)); // Repositionner le Roi pour un autre test
        Assertions.assertTrue(echiquier.verificationDuDeplacement("e1", "c1"));
        Pieces pieceTour2 = echiquier.obtenirPieceALaPosition(new Position(4, 1));
        Assertions.assertEquals('t', pieceTour2.getID());
    }

    @Test
    public void test_listeCoupsPossible() {
        // Test de la méthode listeCoupsPossible
        List<Position> listeCoups = echiquier.obtenirListeCoupsPossible();
        Assertions.assertTrue(listeCoups.isEmpty());
    }

    @Test
    public void test_promotion() {
        // Effectuer un déplacement et vérifier le roque
        echiquier = new Chess(false);
        echiquier.ajoutPieces(new Position(5, 7), new Pion(true));
        Assertions.assertTrue(echiquier.verificationDuDeplacement("e7", "e8"));
    }

    @Test
    public void test_pieces_getID() {
        // Test de la méthode Pieces.getID()
        Pieces roiBlanc = new Roi(true);
        Pieces roiNoir = new Roi(false);
        Assertions.assertEquals('k', roiBlanc.getID());
        Assertions.assertEquals('K', roiNoir.getID());
    }
}
