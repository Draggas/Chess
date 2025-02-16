package fr.draggas.project.chess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite représentant une pièce d'échecs.
 */
public abstract class Pieces {
    final char caractereId; // Identifiant de la pièce
    final boolean couleurBlanche; // Couleur de la pièce, true pour blanc, false pour noir
    List<Position> listeDeplacementsPossible; // Liste des déplacements possibles

    /**
     * Constructeur de la classe Pieces.
     * @param caractereId Identifiant de la pièce.
     * @param couleurBlanche Couleur de la pièce, true pour blanc, false pour noir.
     */
    public Pieces(char caractereId, boolean couleurBlanche) {
        this.caractereId = caractereId;
        this.couleurBlanche = couleurBlanche;
        this.listeDeplacementsPossible = new ArrayList<>();
    }

    /**
     * Retourne l'identifiant de la pièce.
     * @return L'identifiant de la pièce, en majuscule si la pièce est noire.
     */
    public char getID(){
        return this.couleurBlanche ? caractereId : Character.toUpperCase(caractereId);
    }

    /**
     * Méthode abstraite pour obtenir les déplacements possibles d'une pièce à partir d'une position donnée sur un échiquier.
     * @param positionDeLaPiece Position actuelle de la pièce.
     * @param echiquier Échiquier sur lequel la pièce se déplace.
     * @return Liste des positions possibles.
     */
    public abstract List<Position> deplacementsPossible(Position positionDeLaPiece, Chess echiquier);

    /**
     * Retourne la couleur de la pièce.
     * @return true si la pièce est blanche, false si elle est noire.
     */
    public boolean getCouleur(){
        return couleurBlanche;
    }
}
