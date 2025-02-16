package fr.draggas.project.chess.view;

import fr.draggas.project.chess.controller.ChessController;
import fr.draggas.project.chess.model.Cavalier;
import fr.draggas.project.chess.model.Chess;
import fr.draggas.project.chess.model.Dame;
import fr.draggas.project.chess.model.Fou;
import fr.draggas.project.chess.model.Pieces;
import fr.draggas.project.chess.model.Position;
import fr.draggas.project.chess.model.Tour;

import java.util.Scanner;
import fr.draggas.project.chess.utils.*;

/**
 * Classe représentant une vue textuelle d'un jeu d'échecs sans interface graphique.
 */
public class ChessNoGraphicsView implements Observer {
    private Chess chess;
    private ChessController controller;
    private final String nl = System.lineSeparator();

    /**
     * Constructeur de la classe ChessNoGraphicsView.
     * @param chess Instance du modèle de jeu d'échecs.
     * @param controller Instance du contrôleur de jeu d'échecs.
     */
    public ChessNoGraphicsView(Chess chess, ChessController controller) {
        this.chess = chess;
        this.controller = controller;
        chess.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println(affichageEchiquier());
    }

    /**
     * Génère une chaîne de caractères représentant l'affichage de l'échiquier.
     * @return Chaîne de caractères représentant l'affichage de l'échiquier.
     */
    public String affichageEchiquier() {
        final String RED = "\u001B[31m";
        final String RESET = "\u001B[0m";
        StringBuilder affichage = new StringBuilder();
        Position p;
        String affichageVide = "x";

        for (int ligne = 8; ligne >= 1; ligne--) {
            affichage.append(ligne).append(" ");
            for (int colonne = 1; colonne <= 8; colonne++) {
                p = new Position(colonne, ligne);
                if (chess.obtenirListeCoupsPossible().contains(p)) affichage.append(RED);
                if (!chess.positionEstVide(p)) {
                    affichage.append(chess.obtenirPieceALaPosition(p).getID());
                } else {
                    affichage.append(affichageVide);
                }
                if (chess.obtenirListeCoupsPossible().contains(p)) affichage.append(RESET);
            }
            if (ligne != 1) affichage.append(nl);
        }
        affichage.append(nl).append("  ");
        for (int i = 0; i < 8; i++) {
            affichage.append((char) ('a' + i));
        }
        return affichage.toString();
    }

    /**
     * Lance le jeu d'échecs sans interface graphique.
     */
    public void lancerLeJeu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Début de la partie\n------------------");

        while (!chess.getFinPartie()) {
            System.out.println(affichageEchiquier());
            if (!traiterTour(scan)) {
                break;
            }
        }
        scan.close();
    }

    /**
     * Traite un tour de jeu.
     * @param scan Scanner pour lire l'entrée utilisateur.
     * @return true si le jeu continue, false si le jeu est terminé.
     */
    private boolean traiterTour(Scanner scan) {
        String depart = demanderDeplacement(scan, "Départ");
        if (depart.equalsIgnoreCase("Quit")) return false;

        if (controller.verificationDuPointDeDepart(depart)) {
            String arrivee = demanderDeplacement(scan, "Arrivée");
            if (arrivee.equalsIgnoreCase("Quit")) return false;

            if (controller.verificationDuDeplacement(depart, arrivee)) {
                if (chess.getFinPartie()) {
                    annoncerVictoire();
                    return false;
                }
                changerTour();
            } else {
                afficherErreurDeplacement();
            }
        } else {
            afficherErreurPieceDepart();
        }
        return true;
    }

    /**
     * Demande un déplacement à l'utilisateur.
     * @param scan Scanner pour lire l'entrée utilisateur.
     * @param etape Étape du déplacement (Départ ou Arrivée).
     * @return Chaîne de caractères représentant la notation de la position.
     */
    private String demanderDeplacement(Scanner scan, String etape) {
        System.out.print("[Quit pour quitter le jeu] " + etape + " ");
        System.out.print(chess.getTour() ? "Blanc : " : "Noir : ");
        return scan.nextLine();
    }

    /**
     * Annonce la victoire du joueur.
     */
    private void annoncerVictoire() {
        String victoire = chess.getTour() ? "blanches" : "noires";
        System.out.println("Victoire pour le joueur qui a les pièces " + victoire);
    }

    /**
     * Change le tour du joueur.
     */
    private void changerTour() {
        System.out.println("Au tour de l'adversaire");
        System.out.println("------------------");
        controller.changementTour();
    }

    /**
     * Affiche un message d'erreur pour un déplacement invalide.
     */
    private void afficherErreurDeplacement() {
        System.out.println(">Erreur dans le déplacement");
    }

    /**
     * Affiche un message d'erreur pour une pièce de départ invalide.
     */
    private void afficherErreurPieceDepart() {
        System.out.println(">Erreur dans la pièce de départ");
    }

        /**
     * Propose une promotion pour un pion et retourne la nouvelle pièce choisie.
     * @param couleur La couleur du pion à promouvoir.
     * @return La nouvelle pièce choisie pour la promotion.
     */
    @Override
    public void promotion(Position arrivee, boolean couleur) {
        Scanner s = new Scanner(System.in);
        System.out.println("Choisissez une pièce pour la promotion :");
        System.out.println("1 - Tour");
        System.out.println("2 - Cavalier");
        System.out.println("3 - Fou");
        System.out.println("Autres - Reine");
        System.out.print("Entrez le numéro correspondant à votre choix : ");
        int choix = s.nextInt();
        Pieces piece;

        switch (choix) {
            case 1:
                piece = new Tour(couleur);
                break;
            case 2:
                piece = new Cavalier(couleur);
                break;
            case 3:
                piece = new Fou(couleur);
                break;
            default:
                piece = new Dame(couleur);
                break;
        }

        s.close();
        chess.ajoutPieces(arrivee, piece);
    }

    /**
     * Point d'entrée principal pour lancer le jeu.
     * @param args Arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        Chess chess = new Chess();
        ChessController controller = new ChessController(chess);
        ChessNoGraphicsView view = new ChessNoGraphicsView(chess, controller);

        chess.addObserver(view);
        
        view.lancerLeJeu();
    }
}