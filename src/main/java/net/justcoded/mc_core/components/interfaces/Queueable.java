package net.justcoded.mc_core.components.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.Queue;

public interface Queueable<T> {

    boolean add(T t);
    boolean removeFirst();

    boolean remove(T t);
    Optional<T> getFirst();
    Queue<T> clone();
    Queue<T> get();
    void clear();
    boolean contains(T t);
    int size();
}
