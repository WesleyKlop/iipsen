package util;

import java.util.*;
import java.util.function.Consumer;

/**
 * WeakSet is an of Set that is backed by a WeakHashMap (Same way as HashSet)
 * The nice thing about a WeakSet is that keys that are out of scope get garbage collected
 * and removed from the set so that we can't iterate on it again. This makes it so our disposed views
 * don't get updated after removal
 *
 * @author Wesley Klop
 */
public class WeakSet<T> extends AbstractSet<T> implements Set<T> {

    private static final Object fakeValue = new Object();
    private Map<T, Object> backingMap = new WeakHashMap<>();

    @Override
    public Iterator<T> iterator() {
        return backingMap.keySet().iterator();
    }

    @Override
    public boolean add(T o) {
        return backingMap.put(o, fakeValue) == null;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return backingMap.remove(o) == null;
    }

    @Override
    public boolean isEmpty() {
        return backingMap.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return backingMap.keySet().contains(o);
    }

    @Override
    public void clear() {
        backingMap.clear();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        backingMap.keySet().forEach(action);
    }

    @Override
    public int size() {
        return backingMap.size();
    }
}
