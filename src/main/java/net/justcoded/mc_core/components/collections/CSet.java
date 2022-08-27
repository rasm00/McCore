package net.justcoded.mc_core.components.collections;

import net.justcoded.mc_core.interfaces.Settable;

import java.io.Serializable;
import java.util.*;

public class CSet<T> implements Settable<T>, Serializable {

    private final Set<T> set = new HashSet<>();

    @Override
    public boolean add(T t) {
        if (! this.set.contains(t)) {
            set.add(t);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(T t) {
        if (! this.set.contains(t)) {
            return false;
        }
        set.remove(t);
        return true;
    }

    @Override
    public Set<T> clone() {
        return new HashSet<>(this.set);
    }

    @Override
    public Set<T> get() {
        return this.set;
    }

    @Override
    public void clear() {
        this.set.clear();
    }

    @Override
    public boolean contains(T t) {
        return this.set.contains(t);
    }

    @Override
    public int size() {
        return this.set.size();
    }
}
