package fr.draggas.project.chess.model;

import java.util.*;
import fr.draggas.project.chess.utils.Observable;

/**
 * Classe représentant un jeu d'échecs.
 */
public class Chess extends Observable {
    private Map<Position, Pieces> echiquier = new HashMap<>();
    private List<Position> listeCoupsPossible = new ArrayList<>();
    private boolean tourAuJoueurBlanc = true;
    private boolean finDeLaPartie = false;
    private Position priseEnPassant = null;

    /**
     * Constructeur de la classe Chess.
     * @param nonVide Indique si l'échiquier doit être initialisé avec les pièces.
     */
    public Chess(boolean nonVide) {
        if (nonVide) initialisationPlateau();
    }

    /**
     * Constructeur par défaut de la classe Chess.
     * L'échiquier est initialisé avec les pièces.
     */
    public Chess() {
        this(true);
    }
    
    /**
     * Initialise le plateau d'échecs avec les pièces.
     */
    private void initialisationPlateau() {
        for (int ligne = 0; ligne < 2; ligne++) {
            initialiserLigne(ligne);
            initialiserPions(ligne);
        }
        notifyObservers();
    }

    /**
     * Initialise une ligne d'échecs avec les pièces spécifiques.
     * @param ligne Indique la ligne à initialiser (0 pour blanc, 1 pour noir).
     */
    private void initialiserLigne(int ligne) {
        int y = ligne * 7 + 1;
        boolean couleurBlanche = (ligne == 0);
        
        echiquier.put(new Position(1, y), new Tour(couleurBlanche));
        echiquier.put(new Position(2, y), new Cavalier(couleurBlanche));
        echiquier.put(new Position(3, y), new Fou(couleurBlanche));
        echiquier.put(new Position(4, y), new Dame(couleurBlanche));
        echiquier.put(new Position(5, y), new Roi(couleurBlanche));
        echiquier.put(new Position(6, y), new Fou(couleurBlanche));
        echiquier.put(new Position(7, y), new Cavalier(couleurBlanche));
        echiquier.put(new Position(8, y), new Tour(couleurBlanche));
    }

    /**
     * Initialise les pions sur l'échiquier.
     * @param ligne Indique la ligne de départ des pions (0 pour blanc, 1 pour noir).
     */
    private void initialiserPions(int ligne) {
        int y = ligne * 5 + 2;
        boolean couleurBlanche = (ligne == 0);

        for (int colonne = 1; colonne <= 8; colonne++) {
            echiquier.put(new Position(colonne, y), new Pion(couleurBlanche));
        }
    }

    /**
     * Obtient la position de prise en passant.
     * @return La position de prise en passant.
     */
    public Position getPriseEnPassant() {
        return this.priseEnPassant;
    }

    /**
     * Définit la position de prise en passant.
     * @param priseEnPassant La nouvelle position de prise en passant.
     */
    public void setPriseEnPassant(Position priseEnPassant) {
        this.priseEnPassant = priseEnPassant;
    }

    /**
     * Ajoute une pièce à l'échiquier.
     * @param pose La position de la pièce.
     * @param p La pièce à ajouter.
     */
    public void ajoutPieces(Position pose, Pieces p) {
        echiquier.put(pose, p);
    }

    /**
     * Obtient la liste des coups possibles.
     * @return La liste des coups possibles.
     */
    public List<Position> obtenirListeCoupsPossible() {
        return listeCoupsPossible;
    }

    /**
     * Obtient la pièce à une position donnée sur l'échiquier.
     * @param pose La position de la pièce.
     * @return La pièce à la position donnée.
     */
    public Pieces obtenirPieceALaPosition(Position pose) {
        return echiquier.get(pose);
    }

    /**
     * Vérifie si une position est vide sur l'échiquier.
     * @param pose La position à vérifier.
     * @return true si la position est vide, false sinon.
     */
    public boolean positionEstVide(Position pose) {
        return !echiquier.containsKey(pose);
    }

    /**
     * Obtient le tour du joueur.
     * @return true si c'est le tour du joueur blanc, false sinon.
     */
    public boolean getTour() {
        return this.tourAuJoueurBlanc;
    }

    /**
     * Change le tour du joueur.
     */
    public void changementTour() {
        this.tourAuJoueurBlanc = !this.tourAuJoueurBlanc;
    }

    /**
     * Vérifie si la partie est terminée.
     * @return true si la partie est terminée, false sinon.
     */
    public boolean getFinPartie() {
        return this.finDeLaPartie;
    }

    /**
     * Vide la liste des coups possibles.
     */
    public void viderCoupsPossible() {
        listeCoupsPossible = new ArrayList<>();
    }

    /**
     * Vérifie le point de départ du déplacement d'une pièce.
     * @param notationDepart La notation de départ.
     * @return true si le point de départ est valide, false sinon.
     */
    public boolean verificationDuPointDeDepart(String notationDepart) {
        if (notationDepart.length() != 2) {
            return false;
        }
        viderCoupsPossible();
        if (Position.verifValeur(notationDepart)) {
            Position positionDeDepart = new Position(notationDepart);
            if (!positionEstVide(positionDeDepart) && obtenirPieceALaPosition(positionDeDepart).getCouleur() == tourAuJoueurBlanc) {
                listeCoupsPossible = obtenirPieceALaPosition(positionDeDepart).deplacementsPossible(positionDeDepart, this);
                notifyObservers();
                return true;
            }
        }
        return false;
    }

    /**
     * Vérifie le déplacement d'une pièce.
     * @param notationDepart La notation de départ.
     * @param notationArrivee La notation d'arrivée.
     * @return true si le déplacement est valide, false sinon.
     */
    public boolean verificationDuDeplacement(String notationDepart, String notationArrivee) {
        if (notationArrivee.length() != 2) {
            return false;
        }
        if (listeCoupsPossible.isEmpty() && !verificationDuPointDeDepart(notationDepart)) {
            viderCoupsPossible();
            return false;
        }
        if (Position.verifValeur(notationArrivee)) {
            Position positionDepart = new Position(notationDepart);
            Position positionArrivee = new Position(notationArrivee);
            if (listeCoupsPossible.contains(positionArrivee)) {
                return effectuerDeplacement(positionDepart, positionArrivee);
            }
        }
        viderCoupsPossible();
        notifyObservers();
        return false;
    }

    /**
     * Effectue le déplacement d'une pièce.
     * @param positionDepart La position de départ.
     * @param positionArrivee La position d'arrivée.
     * @return true si le déplacement est effectué avec succès, false sinon.
     */
    private boolean effectuerDeplacement(Position positionDepart, Position positionArrivee) {
        if (tourAuJoueurBlanc != obtenirPieceALaPosition(positionDepart).getCouleur()) {
            viderCoupsPossible();
            notifyObservers();
            return false;
        }

        Pieces piece = obtenirPieceALaPosition(positionDepart);

        if (piece.getClass() == Tour.class) {
            ((Tour) piece).setRoque(false);
        }

        if (!positionEstVide(positionArrivee) && obtenirPieceALaPosition(positionArrivee).getClass() == Roi.class) {
            finDeLaPartie = true;
        }

        echiquier.remove(positionArrivee);
        echiquier.put(positionArrivee, echiquier.remove(positionDepart));

        if (piece.getClass() == Pion.class && (positionArrivee.getY() == 8 || positionArrivee.getY() == 1)) {
            notifyObservers(positionArrivee, piece.getCouleur());
        }

        if (piece.getClass() == Roi.class) {
            ((Roi) piece).setRoque(false);
            ((Roi) piece).setGrandRoque(false);
            gererRoque(positionDepart, positionArrivee);
        }

        viderCoupsPossible();
        notifyObservers();
        return true;
    }

    /**
     * Gère le roque lors du déplacement d'un roi.
     * @param positionDepart La position de départ du roi.
     * @param positionArrivee La position d'arrivée du roi.
     */
    private void gererRoque(Position positionDepart, Position positionArrivee) {
        if (positionDepart.getX() == 5) {
            if (positionArrivee.getX() == 3) {
                echiquier.put(new Position(4, positionArrivee.getY()), echiquier.remove(new Position(1, positionArrivee.getY())));
            } else if (positionArrivee.getX() == 7) {
                echiquier.put(new Position(6, positionArrivee.getY()), echiquier.remove(new Position(8, positionArrivee.getY())));
            }
        }
    }
}