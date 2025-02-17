package fr.draggas.project.chess.view;

import fr.draggas.project.chess.controller.ChessController;
import fr.draggas.project.chess.model.Cavalier;
import fr.draggas.project.chess.model.Chess;
import fr.draggas.project.chess.model.Dame;
import fr.draggas.project.chess.model.Fou;
import fr.draggas.project.chess.model.Pieces;
import fr.draggas.project.chess.model.Position;
import fr.draggas.project.chess.model.Tour;
import fr.draggas.project.chess.utils.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe représentant une vue graphique d'un jeu d'échecs avec interface graphique.
 */
public class ChessBoard extends JFrame implements Observer {
    private final int TILE_SIZE = 60;
    private Chess chess;
    private ChessController controller;
    private Map<Character, Image> pieceImages = new HashMap<>();
    private Position selectedPosition = null;

    public ChessBoard(Chess chess, ChessController controller) {
        this.chess = chess;
        this.controller = controller;
        this.chess.addObserver(this);
        setTitle("Échiquier");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(10 * TILE_SIZE, 10 * TILE_SIZE);
        setResizable(false);

        loadPieceImages();

        add(new ChessBoardPanel());
    }

    private void loadPieceImages() {
        try {
            pieceImages.put('T', loadImage("ressources/black_rook.png"));
            pieceImages.put('C', loadImage("ressources/black_knight.png"));
            pieceImages.put('F', loadImage("ressources/black_bishop.png"));
            pieceImages.put('D', loadImage("ressources/black_queen.png"));
            pieceImages.put('R', loadImage("ressources/black_king.png"));
            pieceImages.put('P', loadImage("ressources/black_pawn.png"));

            pieceImages.put('t', loadImage("ressources/white_rook.png"));
            pieceImages.put('c', loadImage("ressources/white_knight.png"));
            pieceImages.put('f', loadImage("ressources/white_bishop.png"));
            pieceImages.put('d', loadImage("ressources/white_queen.png"));
            pieceImages.put('r', loadImage("ressources/white_king.png"));
            pieceImages.put('p', loadImage("ressources/white_pawn.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Image loadImage(String filePath) {
        return new ImageIcon(new File(filePath).getAbsolutePath()).getImage();
    }

    private class ChessBoardPanel extends JPanel {
        public ChessBoardPanel() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int col = (e.getX() - 40) / TILE_SIZE + 1;
                    int row = 8 - (e.getY() - 40) / TILE_SIZE;
                    Position clickedPosition = new Position(col, row);

                    if (selectedPosition == null) {
                        if (controller.verificationDuPointDeDepart(clickedPosition.toString())) {
                            selectedPosition = clickedPosition;
                            JOptionPane.showMessageDialog(null, "Pièce de départ : " + clickedPosition);
                        } else {
                            JOptionPane.showMessageDialog(null, "Sélection invalide. Veuillez sélectionner une pièce valide.");
                        }
                    } else {
                        Position depart = selectedPosition;
                        selectedPosition = null;
                        if (controller.verificationDuDeplacement(depart.toString(), clickedPosition.toString())) {
                            if(chess.getFinPartie()){
                                JOptionPane.showMessageDialog(null, "Victoire pour le joueur " + (chess.getTour() ? "Blanc" : "Noir"));
                                dispose();
                            } else {
                                controller.changementTour();
                                JOptionPane.showMessageDialog(null, "Déplacement valide de " + depart + " à " + clickedPosition + ". Tour au joueur " + (chess.getTour() ? "Blanc" : "Noir"));
                            }
                        } else {
                            if(!chess.positionEstVide(clickedPosition) && chess.obtenirPieceALaPosition(depart).getCouleur() == chess.obtenirPieceALaPosition(clickedPosition).getCouleur()){
                                JOptionPane.showMessageDialog(null, "Selection d'une autre pièce");
                                if (controller.verificationDuPointDeDepart(clickedPosition.toString())) {
                                    selectedPosition = clickedPosition;
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Déplacement invalide.");
                            }
                        }
                    }                    
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());

            int xOffset = 40;
            int yOffset = 40;

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    
                    Position position = new Position(col + 1, 8 - row);

                    if (position.equals(selectedPosition)) {
                        g.setColor(Color.YELLOW);
                    } else if (chess.obtenirListeCoupsPossible().contains(position)) {
                        g.setColor(Color.GREEN);
                    } else if ((row + col) % 2 == 0) {
                        g.setColor(Color.WHITE);
                    } else {
                        g.setColor(Color.DARK_GRAY);
                    }

                    g.fillRect(xOffset + col * TILE_SIZE, yOffset + row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    Pieces piece = chess.obtenirPieceALaPosition(position);
                    if (piece != null) {
                        g.drawImage(pieceImages.get(piece.getID()), xOffset + col * TILE_SIZE, yOffset + row * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                    }
                }
            }
        }
    }

    @Override
    public void update() {
        repaint();
    }

    /**
     * Propose une promotion pour un pion et retourne la nouvelle pièce choisie.
     * @param couleur La couleur du pion à promouvoir.
     */
    @Override
    public void promotion(Position arrivee, boolean couleur) {
        // Créer et afficher un dialogue pour la promotion
        JDialog promotionDialog = new JDialog(this, "Promotion du pion", true);
        promotionDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        promotionDialog.setSize(300, 200);
        promotionDialog.setLayout(new GridLayout(4, 1));
        promotionDialog.setUndecorated(true);

        String[] pieces = {"Tour", "Fou", "Cavalier", "Dame"};
        for (String pieceName : pieces) {
            JButton button = new JButton(pieceName);
            button.addActionListener(e -> {
                Pieces piece;
                switch (pieceName) {
                    case "Tour":
                        piece = new Tour(couleur);
                        break;
                    case "Fou":
                        piece = new Fou(couleur);
                        break;
                    case "Cavalier":
                        piece = new Cavalier(couleur);
                        break;
                    case "Dame":
                    default:
                        piece = new Dame(couleur);
                        break;
                }
                chess.ajoutPieces(arrivee, piece);
                promotionDialog.dispose();
                repaint();
            });
            promotionDialog.add(button);
        }

        promotionDialog.setLocationRelativeTo(this);
        promotionDialog.setVisible(true);
    }

    public static void main(String[] args) {
        Chess chess = new Chess();
        ChessController controller = new ChessController(chess);
        SwingUtilities.invokeLater(() -> {
            ChessBoard chessBoard = new ChessBoard(chess, controller);
            chessBoard.setVisible(true);
        });
    }
}