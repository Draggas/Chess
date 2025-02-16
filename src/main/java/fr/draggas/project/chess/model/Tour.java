package fr.draggas.project.chess.model;

import java.util.List;

/**
 * Classe représentant une tour dans un jeu d'échecs.
 */
public class Tour extends Pieces {
    private boolean roque;

    /**
     * Constructeur de la classe Tour.
     * @param couleurBlanche Couleur de la tour, true pour blanc, false pour noir.
     */
    public Tour(boolean couleurBlanche) {
        super('t', couleurBlanche);
        roque = true;
    }

    /**
     * Calcule les déplacements possibles pour la tour à partir de sa position actuelle sur l'échiquier.
     * @param positionDeLaTour Position actuelle de la tour.
     * @param echiquier Échiquier sur lequel la tour se déplace.
     * @return Liste des positions possibles.
     */
    @Override
    public List<Position> deplacementsPossible(Position positionDeLaTour, Chess echiquier) {
        listeDeplacementsPossible.clear();
        verifierDirection(positionDeLaTour, echiquier, 1, 0);
        verifierDirection(positionDeLaTour, echiquier, -1, 0);
        verifierDirection(positionDeLaTour, echiquier, 0, 1);
        verifierDirection(positionDeLaTour, echiquier, 0, -1);
        return listeDeplacementsPossible;
    }

    /**
     * Vérifie les déplacements possibles dans une direction spécifique.
     * @param positionDeLaTour Position actuelle de la tour.
     * @param echiquier Échiquier sur lequel la tour se déplace.
     * @param deplacementX Déplacement en x dans la direction.
     * @param deplacementY Déplacement en y dans la direction.
     */
    private void verifierDirection(Position positionDeLaTour, Chess echiquier, int deplacementX, int deplacementY) {
        int x = positionDeLaTour.getX();
        int y = positionDeLaTour.getY();
        
        while (true) {
            x += deplacementX;
            y += deplacementY;
            if (!Position.verifValeur(x, y)){
                break;
            }
            Position positionCible = new Position(x, y);
            if (echiquier.positionEstVide(positionCible)) {
                listeDeplacementsPossible.add(positionCible);
            } else {
                if (echiquier.obtenirPieceALaPosition(positionCible).getCouleur() != getCouleur()) {
                    listeDeplacementsPossible.add(positionCible);
                }
                break;
            }
        }
    }

    /**
     * Retourne le statut du roque de la tour.
     * @return true si la tour peut encore roquer, false sinon.
     */
    public boolean getRoque(){
        return roque;
    }

    /**
     * Définit le statut du roque de la tour.
     * @param roque Nouveau statut du roque.
     */
    public void setRoque(boolean roque){
       this.roque = roque;
    }
}
