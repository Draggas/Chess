package fr.draggas.project.chess.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard extends JFrame {
    private final int TILE_SIZE = 60;
    private final String[] INITIAL_BOARD = {
        "rnbqkbnr",
        "pppppppp",
        "........",
        "........",
        "........",
        "........",
        "PPPPPPPP",
        "RNBQKBNR" 
    };
    private Map<Character, Image> pieceImages = new HashMap<>();

    public ChessBoard() {
        setTitle("Échiquier");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(10 * TILE_SIZE, 10 * TILE_SIZE);
        setResizable(false);

        loadPieceImages();

        add(new ChessBoardPanel());
    }

    private void loadPieceImages() {
        try {
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
        return new ImageIcon(new File(filePath).getAbsolutePath()).getImage();
    }

    private class ChessBoardPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());

            int xOffset = 40;
            int yOffset = 40;

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if ((row + col) % 2 == 0) {
                        g.setColor(Color.WHITE);
                    } else {
                        g.setColor(Color.DARK_GRAY);
                    }
                    g.fillRect(xOffset + col * TILE_SIZE, yOffset + row * TILE_SIZE, TILE_SIZE, TILE_SIZE);

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
