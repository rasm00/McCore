package net.justcoded.mc_core.interfaces;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface Mappable<K, V> {

    boolean add(K k, V v);
    boolean remove(K k);
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
    boolean remove(K k, V v);
    boolean set(K k, V v);
    V getOrDefault(K k, V defaultV);
}
