package fr.draggas.project.chess;

import java.util.HashMap;
import java.util.Map;

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
     *   12345678 y
     */

    public Chess(){
        initialisationPlateau();
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
            affichage += System.lineSeparator();
        }
        return affichage;
    }

    public static void main(String[] args) {
        Chess chess = new Chess();
        System.out.println(chess.affichage());
    }
}