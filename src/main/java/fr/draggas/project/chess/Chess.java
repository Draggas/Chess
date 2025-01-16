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
import java.util.Scanner;
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
    boolean tourBlanc = true;
    Position priseEnPassant = null;
    private final String nl = System.lineSeparator();
    Stack<String> historique = new Stack<>();
    String filename = "res/historique.txt";
    Position roiB = null;
    Position roiN = null;

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
            roiB = new Position("e1");
            roiN = new Position("e8");
        }
    }

    public void initialisationValidationDeCara(){
        CARA.put("colonne", Set.of('a','b','c','d','e','f','g','h'));
        CARA.put("ligne", Set.of('1', '2', '3', '4', '5', '6', '7', '8'));
    }

    public String affichage(){
        final String RED = "\u001B[31m";
        final String RESET = "\u001B[0m";
        String affichage = "";
        Position p;
        String affichageVide = "x";
        for(int ligne=8;ligne>=1;ligne--){
            for(int colonne=1;colonne<=8;colonne++){
                p = new Position(colonne, ligne);
                if(coupPossible.contains(p)){affichage += RED;}
                if(echiquier.containsKey(p)){
                    affichage += get(p).affichage();
                }
                else {
                    affichage += affichageVide;
                }
                if(coupPossible.contains(p)){affichage += RESET;}
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
        coupPossible = new ArrayList<>();
        char colonne = depart.charAt(0);
        char ligne = depart.charAt(1);
        if(Position.verifValeur(colonne, ligne)){ //b8
            Position v = new Position(colonne, ligne);
            if(!caseVide(v)){
                if(get(v).couleurBlanche() == tourBlanc){
                    coupPossible = get(v).moovePossible(v, this);
                    return true;
                }
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
                return moove(d,a);
            }
        }
        return false;
    }

    public boolean moove(Position d, Position a){
        if(tourBlanc != get(d).couleurBlanche()){
            coupPossible = new ArrayList<>();
            return false;
        }
        Position r = rechercheRoi(tourBlanc);
        if(get(d).getClass() == Roi.class || ((Roi)get(r)).enEchec(r, this)){
            coupPossible = new ArrayList<>();
            return false;
        }
        if(get(d).getClass() == Tour.class) ((Tour) get(d)).setRoque(false);
        if(caseVide(a)) echiquier.remove(a);
        echiquier.put(a, echiquier.remove(d));
        if(((Roi)get(r)).enEchec(r, this)){
            echiquier.put(d, echiquier.remove(a));
            coupPossible = new ArrayList<>();
            return false;
        }
        if(get(a).getClass() == Pion.class && (a.getY() == 8 || a.getY() == 1)){
            echiquier.put(a, choixPromotion(echiquier.remove(a).couleurBlanche()));
        }
        if(get(a).getClass() == Roi.class){
            ((Roi) get(a)).setRoque(false);
            ((Roi) get(a)).setGrandRoque(false);
            if(d.getX() == 5){
                if(a.getX() == 3){
                    echiquier.put(new Position(4, a.getY()), echiquier.remove(new Position(1,a.getY())));
                }
                if(a.getX() == 7){
                    echiquier.put(new Position(6, a.getY()), echiquier.remove(new Position(8,a.getY())));
                }
            }
        }
        coupPossible = new ArrayList<>();
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
                return new Reine(couleur);
        }
    }
    
    public List<Position> coupPossible(){
        return coupPossible;
    }

    public Position rechercheRoi(boolean couleurBlanche){
        for (Position p : echiquier.keySet()) {
            if(get(p).couleurBlanche() != couleurBlanche && get(p).getClass() == Roi.class){
                return p;
            }
        }
        return null;
    }

    public void gameplay(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Début de la partie");
        System.out.println("------------------");
        boolean fin = false;
        while(!fin){
            System.out.println(affichage());
            System.out.print("Départ ");
            if(tourBlanc) System.out.print(" Blanc : ");
            else System.out.print(" Noir : ");
            String sD = scan.nextLine();
            if(sD.equals("Quit")) break;
            if(verifCoup(sD)){
                System.out.println(affichage());
                System.out.print("Arrivé ");
                if(tourBlanc) System.out.print(" Blanc : ");
                else System.out.print(" Noir : ");
                String sA = scan.nextLine();
                if(sA.equals("Quit")) break;
                if(deplacement(sD, sA)){
                    tourBlanc = !tourBlanc;
                    System.out.println("Au tour de l'adversaire");
                    System.out.println("------------------");

                } else {
                    System.out.println(">Erreur dans le déplacement");
                }
            } else {
                System.out.println(">Erreur dans la pièce de départ");
            }
        }
        scan.close();
    }

    public static void main(String[] args) {
        Chess c = new Chess();
        c.gameplay();
    }

    public static void testPromotion(){
        Chess c = new Chess(false);
        c.addPieces(new Position("e7"), new Pion(true));
        System.out.println(c.affichage());
        c.verifCoup("e7");
        System.out.println(c.affichage());
        c.deplacement("e7", "e8");
        System.out.println(c.affichage());
    }
}