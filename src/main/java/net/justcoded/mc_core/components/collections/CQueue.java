package net.justcoded.mc_core.components.collections;

import net.justcoded.mc_core.components.interfaces.Queueable;

import java.io.Serializable;
import java.util.*;

public class CQueue<T> implements Queueable<T>, Serializable {

    private final Queue<T> queue = new LinkedList<>();

    @Override
    public boolean add(T t) {
        if (! this.queue.contains(t)) {
            queue.add(t);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeFirst() {
        if (queue.isEmpty()) {
            return false;
        }
        T peek = queue.peek();
        queue.remove(peek);
        return true;
    }

    @Override
    public boolean remove(T t) {
        if (! this.queue.contains(t)) {
            return false;
        }
        queue.remove(t);
        return true;
    }

    @Override
    public Optional<T> getFirst() {
        return Optional.ofNullable(this.queue.peek());
    }

    @Override
    public Queue<T> clone() {
        return new LinkedList<>(this.queue);
    }

    @Override
    public Queue<T> get() {
        return this.queue;
    }

    @Override
    public void clear() {
        this.queue.clear();
    }

    @Override
    public boolean contains(T t) {
        return queue.contains(t);
    }

    @Override
    public int size() {
        return this.queue.size();
    }
}
