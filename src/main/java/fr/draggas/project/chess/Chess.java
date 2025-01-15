package fr.draggas.project.chess;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Chess {
    Map<Position,Pieces> echiquier = new HashMap<>();
    List<Position> coupPossible = new ArrayList<>();
    /* y
     * 8 RNBQKBNR
     * 7 PPPPPPPP
     * 6 xxxxxxxx
     * 5 xxxxxxxx
     * 4 xxxxxxxx
     * 3 xxxxxxxx
     * 2 pppppppp
     * 1 rnbqkbnr
     * 
     *   abcdefgh x
     */

    private static final Map<String, Set<Character>> CARA = new HashMap<>();
    public Position priseEnPassantPossible = null;

    boolean petitRoqueB = false;
    boolean grandRoqueB = false;
    boolean petitRoqueN = false;
    boolean grandRoqueN = false;
    boolean tourBlanc = true;
    Position priseEnPassant = null;
    private final String nl = System.lineSeparator();
    Stack<String> historique = new Stack<>();
    String filename = "res/historique.txt";

    public Chess(boolean nonVide){
        if(nonVide) initialisationPlateau();
        initialisationValidationDeCara();
    }

    public Chess(){
        this(true);
    }
    
    public void initialisationPlateau(){
        for(int i=0;i<2;i++){
            echiquier.put(new Position(1,i*7+1), new Tour((i==0)));
            echiquier.put(new Position(2,i*7+1), new Cavalier((i==0)));
            echiquier.put(new Position(3,i*7+1), new Fou((i==0)));
            echiquier.put(new Position(4,i*7+1), new Reine((i==0)));
            echiquier.put(new Position(5,i*7+1), new Roi((i==0)));
            echiquier.put(new Position(6,i*7+1), new Fou((i==0)));
            echiquier.put(new Position(7,i*7+1), new Cavalier((i==0)));
            echiquier.put(new Position(8,i*7+1), new Tour((i==0)));
            for(int colonne=1;colonne<9;colonne++){
                echiquier.put(new Position(colonne,i*5+2), new Pion((i==0)));
            }
        }
        petitRoqueB = true;
        grandRoqueB = true;
        petitRoqueN = true;
        grandRoqueN = true;
    }

    public void initialisationValidationDeCara(){
        CARA.put("colonne", Set.of('a','b','c','d','e','f','g','h'));
        CARA.put("ligne", Set.of('1', '2', '3', '4', '5', '6', '7', '8'));
    }

    public String affichage(){
        String affichage = "";
        Position p;
        String affichageVide = "x";
        for(int ligne=8;ligne>=1;ligne--){
            for(int colonne=1;colonne<=8;colonne++){
                p = new Position(colonne, ligne);
                if(echiquier.containsKey(p)) affichage += get(p).affichage();
                else affichage += affichageVide;
            }
            if(ligne != 1) affichage += nl;
        }
        return affichage;
    }


    public void addPieces(Position pose, Pieces p){
       echiquier.put(pose, p);
    }
    public void removePieces(Position pose){
        echiquier.remove(pose);
    }

    public Pieces get(Position pose){
        return echiquier.get(pose);
    }

    public boolean caseVide(Position pose){
        return !echiquier.containsKey(pose);
    }

    public void changeTour(){
        this.tourBlanc = !this.tourBlanc;
    }

    public Position priseEnPassant(){
        return this.priseEnPassant;
    }

    public String lireSauvegarde(){
        return historique.toString();
    }

    public Stack<String> historique(){
        return historique;
    }
    public static void sauvegarderPartie(Stack<String> stack, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(stack);
        }
    }

    @SuppressWarnings("unchecked")
    public static Stack<String> recupererSauvegarde(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Stack<String>) ois.readObject();
        }
    }

    public boolean verifCoup(String depart){
        if(depart.length() != 2) return false;
        coupPossible = null;
        char colonne = depart.charAt(0);
        char ligne = depart.charAt(1);
        if(Position.verifValeur(colonne, ligne)){ //b8
            Position v = new Position(colonne, ligne);
            if(!caseVide(v)){
                coupPossible = get(v).moovePossible(v, this);
                return true;
            }
        }
        return false;
    }

    public boolean deplacement(String depart, String arrivee){
        if(coupPossible.isEmpty()){
            if(!verifCoup(depart)){
                return false;
            }
        }
        char colonne = arrivee.charAt(0);
        char ligne = arrivee.charAt(1);
        if(CARA.get("colonne").contains(colonne) && CARA.get("ligne").contains(ligne)){
            Position d = new Position(depart.charAt(0), depart.charAt(1));
            Position a = new Position(colonne, Character.getNumericValue(ligne));
            if(coupPossible.contains(a)){
                moove(d,a);
                return true;
            }
        }
        return false;
    }

    public void moove(Position d, Position a){
        if(caseVide(a)) echiquier.remove(a);
        echiquier.put(a, echiquier.remove(d));
        coupPossible = null;
    }

    public List<Position> coupPossible(){
        return coupPossible;
    }

    public static void main(String[] args) {
        Chess game = new Chess();
        game.affichage();
    }
}