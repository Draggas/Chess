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

    /*ici */
    public boolean containsKey(Position pose){
        return echiquier.containsKey(pose);
    }

    public boolean caseVide(Position pose){
        return !echiquier.containsKey(pose);
    }

    public boolean getTour(){
        return this.tourAuJoueurBlanc;
    }

    public boolean getFinPartie(){
        return this.finDeLaPartie;
    }

    public void changeTour(){
        this.tourAuJoueurBlanc = !this.tourAuJoueurBlanc;
    }

    public boolean verifCoup(String depart){
        if(depart.length() != 2) return false;
        listeCoupsPossible = new ArrayList<>();
        char colonne = depart.charAt(0);
        char ligne = depart.charAt(1);
        if(Position.verifValeur(colonne, ligne)){
            Position v = new Position(colonne, ligne);
            if(!caseVide(v)){
                if(obtenirPieceALaPosition(v).getCouleur() == tourAuJoueurBlanc){
                    listeCoupsPossible = obtenirPieceALaPosition(v).deplacementsPossible(v, this);
                    notifyObservers();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean deplacement(String depart, String arrivee){
        if(arrivee.length() != 2) return false;
        if(listeCoupsPossible.isEmpty()){
            if(!verifCoup(depart)){
                listeCoupsPossible = new ArrayList<>();
                notifyObservers();
                return false;
            }
        }
        char colonne = arrivee.charAt(0);
        char ligne = arrivee.charAt(1);
        if(Position.verifValeur(colonne, ligne)){
            Position d = new Position(depart.charAt(0), depart.charAt(1));
            Position a = new Position(colonne, Character.getNumericValue(ligne));
            if(listeCoupsPossible.contains(a)){
                return moove(d,a);
            }
        }
        listeCoupsPossible = new ArrayList<>();
        notifyObservers();
        return false;
    }

    public boolean moove(Position d, Position a){
        if(tourAuJoueurBlanc != obtenirPieceALaPosition(d).getCouleur()){
            listeCoupsPossible = new ArrayList<>();
            notifyObservers();
            return false;
        }
        if(obtenirPieceALaPosition(d).getClass() == Tour.class) ((Tour) obtenirPieceALaPosition(d)).setRoque(false);
        if(!caseVide(a) && obtenirPieceALaPosition(a).getClass() == Roi.class) finDeLaPartie = true;
        if(caseVide(a)) echiquier.remove(a);
        echiquier.put(a, echiquier.remove(d));
        if(obtenirPieceALaPosition(a).getClass() == Pion.class && (a.getY() == 8 || a.getY() == 1)){
            echiquier.put(a, choixPromotion(echiquier.remove(a).getCouleur()));
        }
        if(obtenirPieceALaPosition(a).getClass() == Roi.class){
            ((Roi) obtenirPieceALaPosition(a)).setRoque(false);
            ((Roi) obtenirPieceALaPosition(a)).setGrandRoque(false);
            if(d.getX() == 5){
                if(a.getX() == 3){
                    echiquier.put(new Position(4, a.getY()), echiquier.remove(new Position(1,a.getY())));
                }
                if(a.getX() == 7){
                    echiquier.put(new Position(6, a.getY()), echiquier.remove(new Position(8,a.getY())));
                }
            }
        }
        notifyObservers();
        listeCoupsPossible = new ArrayList<>();
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