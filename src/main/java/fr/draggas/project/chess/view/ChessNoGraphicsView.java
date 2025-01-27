package fr.draggas.project.chess.view;

import fr.draggas.project.chess.model.Chess;

import java.util.Scanner;

public class ChessNoGraphicsView {

    private final Chess chess;

    public ChessNoGraphicsView() {
        this.chess = new Chess();
    }

    public void startGame(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Début de la partie");
        System.out.println("------------------");
        boolean fin = false;
        while(!fin){
            System.out.println(chess.affichage());
            System.out.print("Départ ");
            if(chess.getTour()) System.out.print(" Blanc : ");
            else System.out.print(" Noir : ");
            String sD = scan.nextLine();
            if(sD.equals("Quit")) break;
            if(chess.verifCoup(sD)){
                System.out.println(chess.affichage());
                System.out.print("Arrivé ");
                if(chess.getTour()) System.out.print(" Blanc : ");
                else System.out.print(" Noir : ");
                String sA = scan.nextLine();
                if(sA.equals("Quit")) break;
                if(chess.deplacement(sD, sA)){
                    if(chess.getFinPartie()){
                        String victoire = chess.getTour() ? "blanches" : "noires";
                        System.out.println("Victoire pour le joueur qui a les pièces " + victoire);
                        break; 
                    }
                    chess.changeTour();
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
        ChessNoGraphicsView view = new ChessNoGraphicsView();
        view.startGame();
    }
}
