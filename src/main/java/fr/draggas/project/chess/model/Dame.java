package fr.draggas.project.chess.model;

import java.util.List;

/**
 * Classe représentant une dame dans un jeu d'échecs.
 */
public class Dame extends Pieces {

    /**
     * Constructeur de la classe Dame.
     * @param couleurBlanche Couleur de la dame, true pour blanc, false pour noir.
     */
    public Dame(boolean couleurBlanche) {
        super('d', couleurBlanche);
    }

    /**
     * Calcule les déplacements possibles pour la dame à partir de sa position actuelle sur l'échiquier.
     * La dame combine les déplacements de la tour et du fou.
     * 
     * @param positionDeLaDame Position actuelle de la dame.
     * @param echiquier Échiquier sur lequel la dame se déplace.
     * @return Liste des positions possibles.
     */
    @Override
    public List<Position> deplacementsPossible(Position positionDeLaDame, Chess echiquier){
        listeDeplacementsPossible.clear();
        
        Tour tour = new Tour(couleurBlanche);
        Fou fou = new Fou(couleurBlanche);
        
        ajouterDeplacementsPossibles(tour, positionDeLaDame, echiquier);
        ajouterDeplacementsPossibles(fou, positionDeLaDame, echiquier);
        
        return listeDeplacementsPossible;
    }

    /**
     * Ajoute les déplacements possibles d'une pièce à la liste des déplacements possibles de la dame.
     * 
     * @param piece Pièce dont on veut ajouter les déplacements possibles.
     * @param position Position actuelle de la dame.
     * @param echiquier Échiquier sur lequel la dame se déplace.
     */
    private void ajouterDeplacementsPossibles(Pieces piece, Position position, Chess echiquier) {
        listeDeplacementsPossible.addAll(piece.deplacementsPossible(position, echiquier));
    }
}
