package util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wesley Klop <wesley19097@gmail.com>
 */
public class Observable<T> {
    private Map<Integer, ObservableSubscriber<T>> subscribers = new HashMap<>();
    private T currentValue;

    public Observable() {
    }

    public Observable(T initialValue) {
        this.currentValue = initialValue;
    }

    private int getKey() {
        return subscribers.size() + 1;
    }

    public int subscribe(ObservableSubscriber<T> subscriber) {
        int key = getKey();
        subscribers.put(key, subscriber);
        return key;
    }

    public void unsubscribe(int subscriber) {
        subscribers.remove(subscriber);
    }

    public T getValue() {
        return currentValue;
    }

    public void setValue(T value) {
        currentValue = value;
        notifySubscribers();
    }

    public void notifySubscribers() {
        for (ObservableSubscriber<T> subscriber : subscribers.values()) {
            subscriber.onUpdate(currentValue);
        }
    }

    public interface ObservableSubscriber<T> {
        void onUpdate(T value);
    }
}
