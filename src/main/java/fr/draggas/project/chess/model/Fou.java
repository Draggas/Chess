package fr.draggas.project.chess.model;

import java.util.List;

/**
 * Classe représentant un fou dans un jeu d'échecs.
 */
public class Fou extends Pieces {

    /**
     * Constructeur de la classe Fou.
     * @param couleurBlanche Couleur du fou, true pour blanc, false pour noir.
     */
    public Fou(boolean couleurBlanche) {
        super('f', couleurBlanche);
    }
    
    /**
     * Calcule les déplacements possibles pour le fou à partir de sa position actuelle sur l'échiquier.
     * @param positionDuFou Position actuelle du fou.
     * @param echiquier Échiquier sur lequel le fou se déplace.
     * @return Liste des positions possibles.
     */
    @Override
    public List<Position> deplacementsPossible(Position positionDuFou, Chess echiquier) {
        listeDeplacementsPossible.clear();
        verifierDirection(positionDuFou, echiquier, 1, 1);
        verifierDirection(positionDuFou, echiquier, -1, 1);
        verifierDirection(positionDuFou, echiquier, 1, -1);
        verifierDirection(positionDuFou, echiquier, -1, -1);
        return listeDeplacementsPossible;
    }
    
    /**
     * Vérifie les déplacements possibles dans une direction spécifique.
     * @param positionDuFou Position actuelle du fou.
     * @param echiquier Échiquier sur lequel le fou se déplace.
     * @param deplacementX Déplacement en x dans la direction.
     * @param deplacementY Déplacement en y dans la direction.
     */
    private void verifierDirection(Position positionDuFou, Chess echiquier, int deplacementX, int deplacementY) {
        int x = positionDuFou.getX();
        int y = positionDuFou.getY();
    
        while (true) {
            x += deplacementX;
            y += deplacementY;

            if (!Position.verifValeur(x, y))
                break;
            Position verifPosition = new Position(x, y);

            if (echiquier.positionEstVide(verifPosition)) {
                listeDeplacementsPossible.add(verifPosition);
            } else {
                if (echiquier.obtenirPieceALaPosition(verifPosition).getCouleur() != getCouleur()) {
                    listeDeplacementsPossible.add(verifPosition);
                }
                break;
            }
        }
    }
}