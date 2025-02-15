import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.draggas.project.chess.model.Chess;

public class ChessTest {
    Chess echiquier;

    @BeforeEach
    public void setUp(){
        echiquier = new Chess(); // Initialisation de l'échiquier avec les pièces à leur position initiale
    }

    @Test
    public void test_ecriture_des_notations_valides(){
        
        // Test de pièces valides
        Assertions.assertTrue(echiquier.verificationDuPointDeDepart("e2")); // Pion blanc bien initié
        echiquier.changementTour(); // Changement de tour (Joueur Noir)
        Assertions.assertTrue(echiquier.verificationDuPointDeDepart("d7")); // Pion noir bien initié
        Assertions.assertTrue(echiquier.verificationDuPointDeDepart("h8")); // Pion noir bien initié
        echiquier.changementTour(); // Changement de tour (Joueur Blanc)
        Assertions.assertTrue(echiquier.verificationDuPointDeDepart("h1")); // Pion blanc bien initié

        // Test de pièces invalides
        Assertions.assertFalse(echiquier.verificationDuPointDeDepart("g9")); // Hors de l'échiquier (9 n'existe pas)
        Assertions.assertFalse(echiquier.verificationDuPointDeDepart("j1")); // Hors de l'échiquier (colonne "j" inexistante)
        Assertions.assertFalse(echiquier.verificationDuPointDeDepart("d4")); // Case sans pièce au début de partie
        Assertions.assertFalse(echiquier.verificationDuPointDeDepart("d5")); // Case sans pièce au début de partie
        Assertions.assertFalse(echiquier.verificationDuPointDeDepart("d23")); // Format incorrect (23 n'existe pas)
        Assertions.assertFalse(echiquier.verificationDuPointDeDepart("xx")); // Format incorrect (pas une notation échiquéenne)
    }
}