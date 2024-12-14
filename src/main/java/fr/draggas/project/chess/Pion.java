package fr.draggas.project.chess;

public class Pion extends Pieces {

    public Pion(boolean couleurBlanche) {
        super("p", couleurBlanche);
    }

    public String affichage() {
        return super.affichageCouleur(name);
    }

    public boolean verifMouvement(Position d, boolean prise, Position a, Chess e) {
        int direction = couleurBlanche ? 1 : -1;
        int ligneInitiale = couleurBlanche ? 2 : 7;
        int ligneDoubleAvancee = couleurBlanche ? 4 : 5;
        Position entreDoubleAvancee = new Position(a.getX(), (ligneInitiale + direction));
        if(prise && d.getY()+direction == a.getY() && (d.getX()-1 == a.getX() || d.getX()+1 == a.getX())) return true;
        if(!prise && (d.getX() == a.getX())){
            if(d.getY()+direction == a.getY()) return true;
            if(e.caseVide(entreDoubleAvancee) && ((d.getY() == ligneInitiale && a.getY() == ligneDoubleAvancee))) return true;
        }
        return false;
    }
    
    @Override
    public boolean verifMouvement(Position d, Position a, Chess e) {
        return verifMouvement(d, false, a, e); // par défaut
    }

}
