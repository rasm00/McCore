package net.justcoded.mc_core.components.interfaces;

import java.util.Set;

public interface Settable<T> {

    boolean add(T t);
    boolean remove(T t);
    Set<T> clone();
    Set<T> get();
    void clear();
    boolean contains(T t);
    int size();
}
