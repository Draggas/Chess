package fr.draggas.project.chess.model;

import java.util.List;

/**
 * Classe représentant un cavalier dans un jeu d'échecs.
 */
public class Cavalier extends Pieces {

    /**
     * Constructeur de la classe Cavalier.
     * @param couleurBlanche Couleur du cavalier, true pour blanc, false pour noir.
     */
    public Cavalier(boolean couleurBlanche) {
        super('c', couleurBlanche);
    }

    /**
     * Calcule les déplacements possibles pour le cavalier à partir de sa position actuelle sur l'échiquier.
     * @param positionDuCavalier Position actuelle du cavalier.
     * @param echiquier Échiquier sur lequel le cavalier se déplace.
     * @return Liste des positions possibles.
     */
    @Override
    public List<Position> deplacementsPossible(Position positionDuCavalier, Chess echiquier) {
        listeDeplacementsPossible.clear();
        
        int[][] positionDeplacements = obtenirDeplacementsCavalier();
    
        for (int[] position : positionDeplacements) {
            verifierEtAjouterPosition(positionDuCavalier, echiquier, position);
        }
    
        return listeDeplacementsPossible;
    }

    /**
     * Obtient les déplacements possibles pour un cavalier.
     * @return Tableau des déplacements possibles.
     */
    private int[][] obtenirDeplacementsCavalier() {
        return new int[][]{
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };
    }

    /**
     * Vérifie si une position est valide et l'ajoute à la liste des déplacements possibles si elle l'est.
     * @param positionDuCavalier Position actuelle du cavalier.
     * @param echiquier Échiquier sur lequel le cavalier se déplace.
     * @param deplacement Mouvement à vérifier.
     */
    private void verifierEtAjouterPosition(Position positionDuCavalier, Chess echiquier, int[] deplacement) {
        int x = positionDuCavalier.getX() + deplacement[0];
        int y = positionDuCavalier.getY() + deplacement[1];
        if (Position.verifValeur(x, y)) {
            Position verifPosition = new Position(x, y);
            if (echiquier.positionEstVide(verifPosition) || echiquier.obtenirPieceALaPosition(verifPosition).getCouleur() != getCouleur()) {
                listeDeplacementsPossible.add(verifPosition);
            }
        }
    }
}
