package net.justcoded.mc_core.components.interfaces;

import java.util.List;
import java.util.Optional;

public interface Listable<T> {

    boolean add(T t);
    boolean remove(T t);
    Optional<T> get(int index);
    List<T> clone();
    List<T> get();
    void clear();
    boolean contains(T t);
    int size();
}
