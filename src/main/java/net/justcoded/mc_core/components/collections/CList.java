package net.justcoded.mc_core.components.collections;

import net.justcoded.mc_core.components.interfaces.Listable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CList<T> implements Listable<T>, Serializable {

    private final List<T> list = new ArrayList<>();

    @Override
    public boolean add(T t) {
        if (! this.list.contains(t)) {
            list.add(t);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(T t) {
        if (! this.list.contains(t)) {
            return false;
        }
        list.remove(t);
        return true;
    }

    @Override
    public Optional<T> get(int index) {
        if (this.list.size() > index) {
            return Optional.of(this.list.get(index));
        }
        return Optional.empty();
    }

    @Override
    public List<T> clone() {
        return new ArrayList<>(this.list);
    }

    @Override
    public List<T> get() {
        return this.list;
    }

    @Override
    public void clear() {
        this.list.clear();
    }

    @Override
    public boolean contains(T t) {
        return this.list.contains(t);
    }

    @Override
    public int size() {
        return this.list.size();
    }
}
