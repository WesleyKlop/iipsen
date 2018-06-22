package util;

/**
 * Observer interface, on every change this observers' onUpdate will be called with the new value
 *
 * @param <T> The Object type that is Observed
 */
public interface Observer<T> {
    /**
     * The method that gets called with the new value
     *
     * @param value the new value
     */
    void onUpdate(T value);
}
