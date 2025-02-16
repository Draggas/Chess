package fr.draggas.project.chess.utils;

import java.util.ArrayList;
import java.util.List;

import fr.draggas.project.chess.model.Position;

public class Observable {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    protected void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    protected void notifyObservers(Position arrivee, Boolean couleur) {
        for (Observer observer : observers) {
            observer.promotion(arrivee, couleur);
        }
    }
}
