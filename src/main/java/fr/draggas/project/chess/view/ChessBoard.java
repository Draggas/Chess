package fr.draggas.project.chess.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard extends JFrame {
    private final int TILE_SIZE = 60; // Taille d'une case
    private final String[] INITIAL_BOARD = {
        "rnbqkbnr", // rangée des pièces noires
        "pppppppp", // rangée des pions noirs
        "........", // rangées vides
        "........",
        "........",
        "........",
        "PPPPPPPP", // rangée des pions blancs
        "RNBQKBNR"  // rangée des pièces blanches
    };
    private Map<Character, Image> pieceImages = new HashMap<>(); // Images des pièces

    public ChessBoard() {
        setTitle("Échiquier");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(10 * TILE_SIZE, 10 * TILE_SIZE);
        setResizable(false);

        // Charger les images des pièces
        loadPieceImages();

        // Ajouter un panneau pour dessiner l'échiquier
        add(new ChessBoardPanel());
    }

    private void loadPieceImages() {
        try {
            // Charger les images depuis le dossier resources (chemin relatif)
            pieceImages.put('r', loadImage("ressources/black_rook.png"));
            pieceImages.put('n', loadImage("ressources/black_knight.png"));
            pieceImages.put('b', loadImage("ressources/black_bishop.png"));
            pieceImages.put('q', loadImage("ressources/black_queen.png"));
            pieceImages.put('k', loadImage("ressources/black_king.png"));
            pieceImages.put('p', loadImage("ressources/black_pawn.png"));

            pieceImages.put('R', loadImage("ressources/white_rook.png"));
            pieceImages.put('N', loadImage("ressources/white_knight.png"));
            pieceImages.put('B', loadImage("ressources/white_bishop.png"));
            pieceImages.put('Q', loadImage("ressources/white_queen.png"));
            pieceImages.put('K', loadImage("ressources/white_king.png"));
            pieceImages.put('P', loadImage("ressources/white_pawn.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Image loadImage(String filePath) {
        // Charger une image depuis un chemin relatif
        return new ImageIcon(new File(filePath).getAbsolutePath()).getImage();
    }

    private class ChessBoardPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Dessiner le fond gris autour de l'échiquier
            g.setColor(Color.GRAY); // Couleur de fond gris
            g.fillRect(0, 0, getWidth(), getHeight());

            // Définir l'offset pour centrer l'échiquier
            int xOffset = 40; // Marge à gauche et à droite
            int yOffset = 40; // Marge en haut et en bas

            // Dessiner l'échiquier
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    // Alterner les couleurs des cases
                    if ((row + col) % 2 == 0) {
                        g.setColor(Color.WHITE); // Cases claires
                    } else {
                        g.setColor(Color.DARK_GRAY); // Cases foncées
                    }
                    g.fillRect(xOffset + col * TILE_SIZE, yOffset + row * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                    // Dessiner les pièces si présentes
                    char piece = INITIAL_BOARD[row].charAt(col);
                    if (piece != '.') {
                        g.drawImage(pieceImages.get(piece), xOffset + col * TILE_SIZE, yOffset + row * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessBoard chessBoard = new ChessBoard();
            chessBoard.setVisible(true);
        });
    }
}
