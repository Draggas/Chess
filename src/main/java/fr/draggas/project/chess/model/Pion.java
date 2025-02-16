package fr.draggas.project.chess.model;

import java.util.List;

/**
 * Classe représentant un pion dans un jeu d'échecs.
 */
public class Pion extends Pieces {

    /**
     * Constructeur de la classe Pion.
     * @param couleurBlanche Couleur du pion, true pour blanc, false pour noir.
     */
    public Pion(boolean couleurBlanche) {
        super('p', couleurBlanche);
    }

    /**
     * Calcule les déplacements possibles pour le pion à partir de sa position actuelle sur l'échiquier.
     * @param positionDuPion Position actuelle du pion.
     * @param echiquier Échiquier sur lequel le pion se déplace.
     * @return Liste des positions possibles.
     */
    @Override
    public List<Position> deplacementsPossible(Position positionDuPion, Chess echiquier) {
        listeDeplacementsPossible.clear();
        int direction = getCouleur() ? 1 : -1;
        
        ajouterDeplacementVertical(positionDuPion, echiquier, direction);
        ajouterDeplacementsDiagonaux(positionDuPion, echiquier, direction);
        
        return listeDeplacementsPossible;
    }

    /**
     * Ajoute les déplacements verticaux possibles pour le pion.
     * @param positionDuPion Position actuelle du pion.
     * @param echiquier Échiquier sur lequel le pion se déplace.
     * @param direction Direction du déplacement (1 pour blanc, -1 pour noir).
     */
    private void ajouterDeplacementVertical(Position positionDuPion, Chess echiquier, int direction) {
        Position positionCible = new Position(positionDuPion.getX(), positionDuPion.getY() + direction);
        if (echiquier.positionEstVide(positionCible)) {
            listeDeplacementsPossible.add(positionCible);

            // Double déplacement au premier coup
            if ((getCouleur() && positionDuPion.getY() == 2) || (!getCouleur() && positionDuPion.getY() == 7)) {
                positionCible = new Position(positionDuPion.getX(), positionDuPion.getY() + 2 * direction);
                if (echiquier.positionEstVide(positionCible)) {
                    listeDeplacementsPossible.add(positionCible);
                }
            }
        }
    }

    /**
     * Ajoute les déplacements diagonaux possibles pour le pion (captures et prise en passant).
     * @param positionDuPion Position actuelle du pion.
     * @param echiquier Échiquier sur lequel le pion se déplace.
     * @param direction Direction du déplacement (1 pour blanc, -1 pour noir).
     */
    private void ajouterDeplacementsDiagonaux(Position positionDuPion, Chess echiquier, int direction) {
        for (int i = -1; i <= 1; i += 2) {
            Position positionCible = new Position(positionDuPion.getX() + i, positionDuPion.getY() + direction);
            // Capture
            if (!echiquier.positionEstVide(positionCible) && echiquier.obtenirPieceALaPosition(positionCible).getCouleur() != getCouleur()) {
                listeDeplacementsPossible.add(positionCible);
            }
            // Prise en passant
            if (echiquier.getPriseEnPassant() != null && echiquier.getPriseEnPassant().equals(positionCible)) {
                listeDeplacementsPossible.add(positionCible);
            }
        }
    }
}
