package fr.draggas.project.chess;

public class Fou extends Pieces {

    public Fou(boolean couleurBlanche) {
        super("b", couleurBlanche);
    }

    public String affichage() {
        return super.affichageCouleur(name);
    }

    @Override
    public boolean verifMouvement(Position d, Position a, Chess e) {
        boolean[] nonBloque = {true, true, true, true};
        for(int i=1;i<=7;i++){
            if(nonBloque[0] && (d.getX()+i == a.getX()) && (d.getY()+i == a.getY())) return true;
            if(nonBloque[1] && (d.getX()-i == a.getX()) && (d.getY()+i == a.getY())) return true;
            if(nonBloque[2] && (d.getX()+i == a.getX()) && (d.getY()-i == a.getY())) return true;
            if(nonBloque[3] && (d.getX()-i == a.getX()) && (d.getY()-i == a.getY())) return true;
            if(nonBloque[0] && a.verifValeur(d.getX()+i, d.getY()+i) && !e.caseVide(new Position(d.getX()+i, d.getY()+i))) nonBloque[0] = false;
            if(nonBloque[1] && a.verifValeur(d.getX()-i, d.getY()+i) && !e.caseVide(new Position(d.getX()-i, d.getY()+i))) nonBloque[1] = false;
            if(nonBloque[2] && a.verifValeur(d.getX()+i, d.getY()-i) && !e.caseVide(new Position(d.getX()+i, d.getY()-i))) nonBloque[2] = false;
            if(nonBloque[3] && a.verifValeur(d.getX()-i, d.getY()-i) && !e.caseVide(new Position(d.getX()-i, d.getY()-i))) nonBloque[3] = false;
        }
        return false;
    }
}
