package com.buzz.test.pattern.behavioral.observer.generic;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Observable<S extends Observable<S, O, A>, O extends Observer<S, O, A>, A> {
    protected List<O> observers;

    public Observable() {
        this.observers = new CopyOnWriteArrayList<>();
    }

    public void addObserver(O observer) {

        if (observer == null) {
            return;
        }

        observers.add(observer);
    }

    public void removeObserver(O observer) {
        if (observer == null) {
            return;
        }
        observers.remove(observer);
    }

    public void notifyObservers(A argument) {
        for (O observer : observers) {
            observer.update((S) this, argument);
        }
    }
}
