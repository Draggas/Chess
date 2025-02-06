package fr.draggas.project.chess.view;

import fr.draggas.project.chess.controller.ChessController;
import fr.draggas.project.chess.model.Position;
import java.util.Scanner;

public class ChessNoGraphicsView {
    private ChessController controller;
    private final String nl = System.lineSeparator();

    public ChessNoGraphicsView(ChessController controller) {
        this.controller = controller;
    }

    public void startGame(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Début de la partie\n------------------");

        while(!controller.estFinPartie()){
            System.out.println(affichage());
            System.out.print("[Quit pour quitter le jeu] Départ ");
            System.out.print(controller.getTour() ? " Blanc : " : "Noir : ");
            String depart = scan.nextLine();
            if (depart.equalsIgnoreCase("Quit")) break;
            if(controller.verifCoup(depart)){
                System.out.println(affichage());
                System.out.print("[Quit pour quitter le jeu] Arrivé ");
                System.out.print(controller.getTour() ? " Blanc : " : "Noir : ");
                String arrivee = scan.nextLine();
                if (arrivee.equalsIgnoreCase("Quit")) break;
                if(controller.deplacement(depart, arrivee)){
                    if(controller.estFinPartie()){
                        String victoire = controller.getTour() ? "blanches" : "noires";
                        System.out.println("Victoire pour le joueur qui a les pièces " + victoire);
                        break; 
                    }
                    controller.changementTour();
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

    
    public String affichage(){
        final String RED = "\u001B[31m";
        final String RESET = "\u001B[0m";
        String affichage = "";
        Position p;
        String affichageVide = "x";
        for(int ligne=8;ligne>=1;ligne--){
            affichage = affichage + ligne + " ";
            for(int colonne=1;colonne<=8;colonne++){
                p = new Position(colonne, ligne);
                if(controller.getCoupPossible().contains(p)){affichage += RED;}
                if(controller.containsKey(p)){
                    affichage += controller.get(p).affichage();
                }
                else {
                    affichage += affichageVide;
                }
                if(controller.getCoupPossible().contains(p)){affichage += RESET;}
            }
            if(ligne != 1) affichage += nl;
        }
        affichage += nl + "  ";
        for(int i=0;i<8;i++){
            affichage += (char)('a' + i);
        }
        return affichage;
    }
    
    public static void main(String[] args) {
        ChessController controller = new ChessController();
        ChessNoGraphicsView view = new ChessNoGraphicsView(controller);
        view.startGame();
    }
}
