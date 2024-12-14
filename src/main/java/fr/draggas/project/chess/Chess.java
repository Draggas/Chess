package fr.draggas.project.chess;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.jupiter.api.Assertions;

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
    boolean petitRoqueB = false;
    boolean grandRoqueB = false;
    boolean petitRoqueN = false;
    boolean grandRoqueN = false;
    boolean tourBlanc = true;
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
        petitRoqueB = true;
        grandRoqueB = true;
        petitRoqueN = true;
        grandRoqueN = true;
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
                if(echiquier.containsKey(p)) affichage += get(p).affichage();
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
        if(coup.equals("O-O") && verifPetitRoque() || coup.equals("O-O-O") && verifGrandRoque()){
            return deplacementRoque(coup);
        } else if(coup.equals("O-O") || coup.equals("O-O-O")){
            return false;
        }
        char type = 'P';
        int i = 0;
        if(coup.length() == 6) type = coup.charAt(i++);
        Position depart = new Position(coup.charAt(i++), Character.getNumericValue(coup.charAt(i++)));
        Boolean attrape = ('x' == coup.charAt(i++));
        Position arrivee = new Position(coup.charAt(i++), Character.getNumericValue(coup.charAt(i++)));
        if(!echiquier.containsKey(depart) || depart.equals(arrivee)) return false;
        if(attrape && echiquier.containsKey(arrivee) && (get(depart).couleurBlanche() == get(arrivee).couleurBlanche())) return false;
        Boolean test = switch (type) {
            case 'K' -> (get(depart).getClass() == Roi.class) && get(depart).verifMouvement(depart, arrivee, this);
            case 'Q' -> (get(depart).getClass() == Reine.class) && get(depart).verifMouvement(depart, arrivee, this);
            case 'R' -> (get(depart).getClass() == Tour.class) && get(depart).verifMouvement(depart, arrivee, this);
            case 'B' -> (get(depart).getClass() == Fou.class) && get(depart).verifMouvement(depart, arrivee, this);
            case 'N' -> (get(depart).getClass() == Cavalier.class) && get(depart).verifMouvement(depart, arrivee, this);
            case 'P' -> (get(depart).getClass() == Pion.class) && ((Pion)get(depart)).verifMouvement(depart, attrape, arrivee, this);
            default -> throw new NoSuchElementException("Type inconnu : " + type);
        };        
        return test && deplacement(depart, attrape, arrivee);
    }

    public boolean deplacement(Position d, boolean attrape, Position a){
        int n = tourBlanc ? 1 : 8;
        int finale = tourBlanc ? 7 : 2;
        if(get(d).getClass() == Roi.class && d.equals(new Position('e', n))){
            if(tourBlanc){
                petitRoqueB = false;
                grandRoqueB = false;
            } else {
                petitRoqueN = false;
                grandRoqueN = false;
            }
        }
        if(get(d).getClass() == Tour.class & d.equals(new Position('a',n))){
            if(tourBlanc){
                grandRoqueB = false;
            } else {
                grandRoqueN = false;
            }
        }
        if(get(d).getClass() == Tour.class & d.equals(new Position('h',n))){
            if(tourBlanc){
                petitRoqueB = false;
            } else {
                petitRoqueN = false;
            }
        }
        if(get(d).getClass() == Pion.class && d.getY() == finale){
            Pieces p = echiquier.remove(d);
            echiquier.put(a, new Reine(p.couleurBlanche()));
            return true;
        }
        if((attrape && echiquier.containsKey(a)) || (!attrape && !echiquier.containsKey(a))){
            echiquier.put(a, echiquier.remove(d));
            return true;
        } else {
            return false;
        }
    }

    public boolean deplacementRoque(String coup){
        int n = tourBlanc ? 1 : 8;
        if(coup.equals("O-O")){
            echiquier.put(new Position('f', n), echiquier.remove(new Position('h', n)));
            echiquier.put(new Position('g', n), echiquier.remove(new Position('e', n)));
        } else {
            echiquier.put(new Position('d', n), echiquier.remove(new Position('a', n)));
            echiquier.put(new Position('c', n), echiquier.remove(new Position('e', n)));
        }
        return true;
    }

    public boolean verifPetitRoque(){
        if((tourBlanc && petitRoqueB) || (!tourBlanc && petitRoqueN)){
            int n = tourBlanc ? 1 : 8;
            return  get(new Position('e', n)).getClass() == Roi.class && 
                    caseVide(new Position('f', n)) &&
                    caseVide(new Position('g', n)) &&
                    get(new Position('h', n)).getClass() == Tour.class;
        }
        return false;
    }

    public boolean verifGrandRoque(){
        if((tourBlanc && grandRoqueB) || (!tourBlanc && grandRoqueN)){
            int n = tourBlanc ? 1 : 8;
            return  get(new Position('e', n)).getClass() == Roi.class && 
                    caseVide(new Position('d', n)) &&
                    caseVide(new Position('c', n)) &&
                    caseVide(new Position('b', n)) &&
                    get(new Position('a', n)).getClass() == Tour.class;
        }
        return false;
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

    public static void main(String[] args) {
        Chess game = new Chess(false);
        game.addPieces(new Position('a',7), new Pion(true));
        game.estDeplacementValide("a7-a8");
        System.out.println(game.affichage());
    }
}