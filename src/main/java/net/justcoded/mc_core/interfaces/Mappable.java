package net.justcoded.mc_core.interfaces;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface Mappable<K, V> {

    boolean add(K t, V v);
    boolean remove(K t);
    boolean update(K k, V v);
    Optional<V> get(K k);
    Map<K,V> clone();
    Map<K,V> get();
    void clear();
    boolean containsKey(K k);
    boolean containsValue(V v);
    Set<K> getKeys();
    int size();
    Map<K, V> getMap();
    boolean set(K k, V v);
}
