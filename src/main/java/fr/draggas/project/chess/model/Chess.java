package fr.draggas.project.chess.model;

import java.util.*;
import fr.draggas.project.chess.utils.Observable;

public class Chess extends Observable {
    Map<Position,Pieces> echiquier = new HashMap<>();
    List<Position> listeCoupsPossible = new ArrayList<>();
    boolean tourAuJoueurBlanc = true;
    boolean finDeLaPartie = false;
    Position priseEnPassant = null;

    public Chess(boolean nonVide){
        if(nonVide) initialisationPlateau();
    }

    public Chess(){
        this(true);
    }
    
    public void initialisationPlateau(){
        for(int ligne=0;ligne<2;ligne++){
            echiquier.put(new Position(1,ligne*7+1), new Tour((ligne==0)));
            echiquier.put(new Position(2,ligne*7+1), new Cavalier((ligne==0)));
            echiquier.put(new Position(3,ligne*7+1), new Fou((ligne==0)));
            echiquier.put(new Position(4,ligne*7+1), new Dame((ligne==0)));
            echiquier.put(new Position(5,ligne*7+1), new Roi((ligne==0)));
            echiquier.put(new Position(6,ligne*7+1), new Fou((ligne==0)));
            echiquier.put(new Position(7,ligne*7+1), new Cavalier((ligne==0)));
            echiquier.put(new Position(8,ligne*7+1), new Tour((ligne==0)));
            for(int colonne=1;colonne<9;colonne++){
                echiquier.put(new Position(colonne,ligne*5+2), new Pion((ligne==0)));
            }
        }
    }

    public Position getPriseEnPassant(){
        return this.priseEnPassant;
    }

    public void setPriseEnPassant(Position priseEnPassant) {
        this.priseEnPassant = priseEnPassant;
    }

    public void ajoutPieces(Position pose, Pieces p){
       echiquier.put(pose, p);
    }

    public List<Position> obtenirListeCoupsPossible(){
        return listeCoupsPossible;
    }

    public Pieces obtenirPieceALaPosition(Position pose){
        return echiquier.get(pose);
    }

    public boolean positionEstVide(Position pose){
        return !echiquier.containsKey(pose);
    }

    public boolean getTour(){
        return this.tourAuJoueurBlanc;
    }

    public void changementTour(){
        this.tourAuJoueurBlanc = !this.tourAuJoueurBlanc;
    }

    public boolean getFinPartie(){
        return this.finDeLaPartie;
    }

    public void viderCoupsPossible(){
        listeCoupsPossible = new ArrayList<>();
    }

    public boolean verificationDuPointDeDepart(String notationDepart){
        if(notationDepart.length() != 2) return false;
        viderCoupsPossible();
        if(Position.verifValeur(notationDepart)){
            Position positionDeDepart = new Position(notationDepart);
            if(!positionEstVide(positionDeDepart)){
                if(obtenirPieceALaPosition(positionDeDepart).getCouleur() == tourAuJoueurBlanc){
                    listeCoupsPossible = obtenirPieceALaPosition(positionDeDepart).deplacementsPossible(positionDeDepart, this);
                    notifyObservers();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verificationDuDeplacement(String notationDepart, String notationArrivee){
        if(notationArrivee.length() != 2) return false;
        if(listeCoupsPossible.isEmpty()){
            if(!verificationDuPointDeDepart(notationDepart)){
                viderCoupsPossible();
                notifyObservers();
                return false;
            }
        }
        if(Position.verifValeur(notationArrivee)){
            Position positionDepart = new Position(notationDepart);
            Position positionArrivee = new Position(notationArrivee);
            if(listeCoupsPossible.contains(positionArrivee)){
                return deplacement(positionDepart,positionArrivee);
            }
        }
        viderCoupsPossible();
        notifyObservers();
        return false;
    }

    public boolean deplacement(Position positionDepart, Position positionArrivee){
        if(tourAuJoueurBlanc != obtenirPieceALaPosition(positionDepart).getCouleur()){
            viderCoupsPossible();
            notifyObservers();
            return false;
        }
        if(obtenirPieceALaPosition(positionDepart).getClass() == Tour.class) ((Tour) obtenirPieceALaPosition(positionDepart)).setRoque(false);
        if(!positionEstVide(positionArrivee) && obtenirPieceALaPosition(positionArrivee).getClass() == Roi.class) finDeLaPartie = true;
        if(positionEstVide(positionArrivee)) echiquier.remove(positionArrivee);
            echiquier.put(positionArrivee, echiquier.remove(positionDepart));
        if(obtenirPieceALaPosition(positionArrivee).getClass() == Pion.class && (positionArrivee.getY() == 8 || positionArrivee.getY() == 1)){
            echiquier.put(positionArrivee, choixPromotion(echiquier.remove(positionArrivee).getCouleur()));
        }
        if(obtenirPieceALaPosition(positionArrivee).getClass() == Roi.class){
            ((Roi) obtenirPieceALaPosition(positionArrivee)).setRoque(false);
            ((Roi) obtenirPieceALaPosition(positionArrivee)).setGrandRoque(false);
            if(positionDepart.getX() == 5){
                if(positionArrivee.getX() == 3){
                    echiquier.put(new Position(4, positionArrivee.getY()), echiquier.remove(new Position(1,positionArrivee.getY())));
                }
                if(positionArrivee.getX() == 7){
                    echiquier.put(new Position(6, positionArrivee.getY()), echiquier.remove(new Position(8,positionArrivee.getY())));
                }
            }
        }
        notifyObservers();
        viderCoupsPossible();
        return true;
    }

    public Pieces choixPromotion(boolean couleur){
        Scanner s = new Scanner(System.in);
        System.out.println("Choisissez une pièce pour la promotion :");
        System.out.println("1 - Tour");
        System.out.println("2 - Cavalier");
        System.out.println("3 - Fou");
        System.out.println("Autres - Reine");
        System.out.print("Entrez le numéro correspondant à votre choix : ");
        int choix = s.nextInt();
        switch (choix) {
            case 1:
                s.close();
                return new Tour(couleur);
            case 2:
                s.close();
                return new Cavalier(couleur);
            case 3:
                s.close();
                return new Fou(couleur);
            default :
                s.close();
                return new Dame(couleur);
        }
    }
    
    public List<Position> listeCoupsPossible(){
        return listeCoupsPossible;
    }
}