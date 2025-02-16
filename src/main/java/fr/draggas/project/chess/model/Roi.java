package fr.draggas.project.chess.model;

import java.util.List;

/**
 * Classe représentant un roi dans un jeu d'échecs.
 */
public class Roi extends Pieces {
    private boolean roque;
    private boolean grandRoque;

    /**
     * Constructeur de la classe Roi.
     * @param couleurBlanche Couleur du roi, true pour blanc, false pour noir.
     */
    public Roi(boolean couleurBlanche) {
        super('k', couleurBlanche);
        roque = true;
        grandRoque = true;
    }
    
    /**
     * Calcule les déplacements possibles pour le roi à partir de sa position actuelle sur l'échiquier.
     * @param positionDuRoi Position actuelle du roi.
     * @param echiquier Échiquier sur lequel le roi se déplace.
     * @return Liste des positions possibles.
     */
    @Override
    public List<Position> deplacementsPossible(Position positionDuRoi, Chess echiquier) {
        listeDeplacementsPossible.clear();
        ajouterDeplacementsClassiques(positionDuRoi, echiquier);
        ajouterDeplacementsRoque(positionDuRoi, echiquier);
        return listeDeplacementsPossible;
    }

    /**
     * Ajoute les déplacements classiques du roi (une case dans chaque direction).
     * @param positionDuRoi Position actuelle du roi.
     * @param echiquier Échiquier sur lequel le roi se déplace.
     */
    private void ajouterDeplacementsClassiques(Position positionDuRoi, Chess echiquier) {
        int[] deplacementsX = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] deplacementsY = {-1, 0, 1, -1, 1, -1, 0, 1};
        
        for (int i = 0; i < deplacementsX.length; i++) {
            int newX = positionDuRoi.getX() + deplacementsX[i];
            int newY = positionDuRoi.getY() + deplacementsY[i];
            if (Position.verifValeur(newX, newY)) {
                Position nouvellePosition = new Position(newX, newY);
                if (echiquier.positionEstVide(nouvellePosition) || echiquier.obtenirPieceALaPosition(nouvellePosition).getCouleur() != getCouleur()) {
                    listeDeplacementsPossible.add(nouvellePosition);
                }
            }
        }
    }

    /**
     * Ajoute les déplacements possibles pour le roque et le grand roque.
     * @param positionDuRoi Position actuelle du roi.
     * @param echiquier Échiquier sur lequel le roi se déplace.
     */
    private void ajouterDeplacementsRoque(Position positionDuRoi, Chess echiquier) {
        int positionYNecessaire = getCouleur() ? 1 : 8;
        Position poseRoque = new Position(8, positionYNecessaire);
        Position poseGrandRoque = new Position(1, positionYNecessaire);
        
        if (roque && verifierRoque(poseRoque, echiquier, positionYNecessaire)) {
            listeDeplacementsPossible.add(new Position(7, positionYNecessaire));
        }
        if (grandRoque && verifierGrandRoque(poseGrandRoque, echiquier, positionYNecessaire)) {
            listeDeplacementsPossible.add(new Position(3, positionYNecessaire));
        }
    }

    /**
     * Vérifie si le roque est possible.
     * @param poseRoque Position de la tour pour le roque.
     * @param echiquier Échiquier sur lequel le roi se déplace.
     * @param positionYNecessaire Ligne nécessaire pour le roque.
     * @return true si le roque est possible, false sinon.
     */
    public boolean verifierRoque(Position poseRoque, Chess echiquier, int positionYNecessaire) {
        return !echiquier.positionEstVide(poseRoque) &&
               echiquier.obtenirPieceALaPosition(poseRoque).getClass() == Tour.class &&
               echiquier.positionEstVide(new Position(7, positionYNecessaire)) &&
               echiquier.positionEstVide(new Position(6, positionYNecessaire)) &&
               ((Tour) echiquier.obtenirPieceALaPosition(poseRoque)).getRoque();
    }

    /**
     * Vérifie si le grand roque est possible.
     * @param poseGrandRoque Position de la tour pour le grand roque.
     * @param echiquier Échiquier sur lequel le roi se déplace.
     * @param positionYNecessaire Ligne nécessaire pour le grand roque.
     * @return true si le grand roque est possible, false sinon.
     */
    public boolean verifierGrandRoque(Position poseGrandRoque, Chess echiquier, int positionYNecessaire) {
        return !echiquier.positionEstVide(poseGrandRoque) &&
               echiquier.obtenirPieceALaPosition(poseGrandRoque).getClass() == Tour.class &&
               echiquier.positionEstVide(new Position(2, positionYNecessaire)) &&
               echiquier.positionEstVide(new Position(3, positionYNecessaire)) &&
               echiquier.positionEstVide(new Position(4, positionYNecessaire)) &&
               ((Tour) echiquier.obtenirPieceALaPosition(poseGrandRoque)).getRoque();
    }
    
    /**
     * Retourne le statut du roque du roi.
     * @return true si le roi peut encore roquer, false sinon.
     */
    public boolean getRoque(){
        return roque;
    }
    
    /**
     * Retourne le statut du grand roque du roi.
     * @return true si le roi peut encore faire le grand roque, false sinon.
     */
    public boolean getGrandRoque(){
        return grandRoque;
    }

    /**
     * Définit le statut du grand roque du roi.
     * @param grandRoque Nouveau statut du grand roque.
     */
    public void setGrandRoque(boolean grandRoque){
        this.grandRoque = grandRoque;
    }

    /**
     * Définit le statut du roque du roi.
     * @param roque Nouveau statut du roque.
     */
    public void setRoque(boolean roque){
        this.roque = roque;
    }
}
