package fr.draggas.project.chess;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Chess {
    Map<Position,Pieces> echiquier = new HashMap<>();
    /* x
     * 8 RNBQKBNR
     * 7 PPPPPPPP
     * 6 xxxxxxxx
     * 5 xxxxxxxx
     * 4 xxxxxxxx
     * 3 xxxxxxxx
     * 2 pppppppp
     * 1 rnbqkbnr
     * 
     *   abcdefgh y
     */

    private static final Map<String, Set<Character>> CARA = new HashMap<>();

    public Chess(boolean nonVide){
        if(nonVide) initialisationPlateau();
    }

    public Chess(){
        this(true);
    }
    
    public void initialisationPlateau(){
        
        for(int i=0;i<2;i++){
            echiquier.put(new Position(i*7+1,1), new Tour((i==0)));
            echiquier.put(new Position(i*7+1,2), new Cavalier((i==0)));
            echiquier.put(new Position(i*7+1,3), new Fou((i==0)));
            echiquier.put(new Position(i*7+1,4), new Roi((i==0)));
            echiquier.put(new Position(i*7+1,5), new Reine((i==0)));
            echiquier.put(new Position(i*7+1,6), new Fou((i==0)));
            echiquier.put(new Position(i*7+1,7), new Cavalier((i==0)));
            echiquier.put(new Position(i*7+1,8), new Tour((i==0)));
            for(int colonne=1;colonne<9;colonne++){
                echiquier.put(new Position(i*5+2,colonne), new Pion((i==0)));
            }
        }

    }

    public void initialisationValidationDeCara(){
        CARA.put("name", Set.of('R','N','B','Q','K'));
        CARA.put("colonne", Set.of('a','b','c','d','e','f','g','h'));
        CARA.put("ligne", Set.of('1', '2', '3', '4', '5', '6', '7', '8'));
        CARA.put("symbole", Set.of('-', 'x'));
    }

    public String affichage(){
        String affichage = "";
        Position p;
        String affichageVide = "x";
        for(int ligne=8;ligne>=1;ligne--){
            for(int colonne=8;colonne>=1;colonne--){
                p = new Position(ligne, colonne);
                if(echiquier.containsKey(p)) affichage += echiquier.get(p).affichage();
                else affichage += affichageVide;
            }
            if(ligne != 1) affichage += System.lineSeparator();
        }
        return affichage;
    }

    public void deplacer(String mouvement){
        if(estNotationValide(mouvement) && estDeplacementValide(mouvement)){

        }
    }

    public boolean estNotationValide(String coup) { // Exemple : Kb8xd4
        if(Set.of("O-O","O-O-O").contains(coup)) return true;
        if(coup.length() < 5 || coup.length() > 6) return false;
        int i = 0;
        if(coup.length() == 6 && CARA.get("name").contains(coup.charAt(0))){ // K
            i = 1;
        } else if(coup.length() != 5){
            return false;
        }
        return  CARA.get("colonne").contains(coup.charAt(i++)) && // b
                CARA.get("ligne").contains(coup.charAt(i++)) && // 8
                CARA.get("symbole").contains(coup.charAt(i++)) && // x
                CARA.get("colonne").contains(coup.charAt(i++)) && // d
                CARA.get("ligne").contains(coup.charAt(i)); // 4

    }

    public boolean estDeplacementValide(String coup) { // Exemple : e2-e4
        return false;
    }

    public void addPieces(Position pose, Pieces p){
       echiquier.put(pose, p);
    }
    public void removePieces(Position pose){
        echiquier.remove(pose);
     }

    public static void main(String[] args) {
        Chess chess = new Chess();
        System.out.println(chess.affichage());
    }
}