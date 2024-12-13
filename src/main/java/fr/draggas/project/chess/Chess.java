package fr.draggas.project.chess;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class Chess {
    Map<Position,Pieces> echiquier = new HashMap<>();
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
    private final String nl = System.lineSeparator();

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
            for(int colonne=1;colonne<=8;colonne++){
                p = new Position(colonne, ligne);
                if(echiquier.containsKey(p)) affichage += echiquier.get(p).affichage();
                else affichage += affichageVide;
            }
            if(ligne != 1) affichage += nl;
        }
        return affichage;
    }

    public void deplacer(String mouvement){
        if(estNotationValide(mouvement) && estDeplacementValide(mouvement)){
            System.out.println("---" + nl + "Déplacement effectué : " + mouvement + nl + "---");
        } else {
            System.out.println("Erreur");
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
        char type = 'P';
        int i = 0;
        if(coup.length() == 6) type = coup.charAt(i++);
        Position depart = new Position(coup.charAt(i++), Character.getNumericValue(coup.charAt(i++)));
        Boolean attrape = ('x' == coup.charAt(i++));
        Position arrivee = new Position(coup.charAt(i++), Character.getNumericValue(coup.charAt(i++)));
        if(!echiquier.containsKey(depart) || depart.equals(arrivee)) return false;
        Boolean test = switch(type){
            case 'K' -> (echiquier.get(depart).getClass() == Roi.class) && echiquier.get(depart).verifMouvement(depart, arrivee);
            case 'Q' -> (echiquier.get(depart).getClass() == Reine.class) && echiquier.get(depart).verifMouvement(depart, arrivee);
            case 'R' -> (echiquier.get(depart).getClass() == Tour.class) && echiquier.get(depart).verifMouvement(depart, arrivee);
            case 'B' -> (echiquier.get(depart).getClass() == Fou.class) && echiquier.get(depart).verifMouvement(depart, arrivee);
            case 'N' -> (echiquier.get(depart).getClass() == Cavalier.class) && echiquier.get(depart).verifMouvement(depart, arrivee);
            case 'P' -> (echiquier.get(depart).getClass() == Pion.class) && ((Pion)echiquier.get(depart)).verifMouvement(depart, attrape, arrivee);
            default -> throw new NoSuchElementException();
        };
        return test && deplacement(depart, attrape, arrivee);
    }

    public boolean deplacement(Position d, boolean attrape, Position a){
        if((attrape && echiquier.containsKey(a)) || (!attrape && !echiquier.containsKey(a))){
            echiquier.put(a, echiquier.remove(d));
            return true;
        } else {
            return false;
        }
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
        System.out.println("---");
        chess.deplacer("e2-e4");
        chess.deplacer("e7-e5");
        chess.deplacer("Ng1-f3");
        chess.deplacer("Nb8-c6");
        System.out.println(chess.affichage());
        System.out.println("---");
        
        Chess chessVide = new Chess(false);
        chessVide.addPieces(new Position('a',1), new Fou(true));
        chessVide.deplacer("Ba1-g7");
        chessVide.deplacer("Bg7-f8");
        System.out.println(chessVide.affichage());
    }
}