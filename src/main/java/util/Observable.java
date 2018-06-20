package util;

import java.util.HashSet;
import java.util.Set;

/**
 * Observable is a utility class for Tracking changes of an Object
 *
 * @author Wesley Klop
 */
public class Observable<T> {
    //    private Set<Observer<T>> observers = Collections.newSetFromMap(new WeakHashMap<>());
    private Set<Observer<T>> observers = new HashSet<>();

    private T currentValue;

    public Observable() {
    }

    public Observable(T initialValue) {
        this.currentValue = initialValue;
    }


    /**
     * Subscribe to changes of object T
     *
     * @param observer the Object that implements the {@code Observable.Observer<T>} interface.
     */
    public void addObserver(Observer<T> observer) {
        observers.add(observer);
    }

    /**
     * Remove an observer.
     *
     * @param observer the observer to remove
     */
    public void removeObserver(Observer<T> observer) {
        observers.remove(observer);
    }

    /**
     * Return the value
     *
     * @return the current value contained in the observer
     */
    public T getValue() {
        return currentValue;
    }

    /**
     * Save the value, and notify all observers
     *
     * @param value the new value
     */
    public void setValue(T value) {
        currentValue = value;
        notifyObservers();
    }

    /**
     * Notify all observers
     */
    public void notifyObservers() {
        for (Observer<T> observer : observers) {
            if (observer != null)
                observer.onUpdate(currentValue);
        }
    }

}
