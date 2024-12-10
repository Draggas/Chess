package fr.draggas.project.chess;

public abstract class Pieces {
    final String name; // promotion --> changement de pièces donc pas besoin de changer de nom
    final boolean couleurBlanche; // pas de changement de couleur

    public Pieces(String name, boolean couleurBlanche) {
        this.name = name;
        this.couleurBlanche = couleurBlanche;
    }

    public String affichageCouleur(String a){
        if(this.couleurBlanche) return a;
        else return a.toUpperCase();
    }
    
    public abstract String affichage();

    public abstract String mouvement();

    public String getName(){
        return name;
    }
    public boolean couleurBlanche(){
        return couleurBlanche;
    }

}
