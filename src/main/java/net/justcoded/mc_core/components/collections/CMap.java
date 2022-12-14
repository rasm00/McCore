package net.justcoded.mc_core.components.collections;

import net.justcoded.mc_core.interfaces.Mappable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class CMap<K, V> implements Mappable<K, V>, Serializable {

    private final Map<K, V> map = new HashMap<>();

    @Override
    public boolean add(K k, V v) {
        if (! this.map.containsKey(k)) {
            this.map.put(k, v);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(K k) {
        if (! this.map.containsKey(k)) {
            return false;
        }
        this.map.remove(k);
        return true;
    }

    @Override
    public boolean update(K k, V v) {
        if (! this.map.containsKey(k)) {
            return false;
        }
        this.map.replace(k, v);
        return true;
    }

    @Override
    public Optional<V> get(K k) {
        return Optional.ofNullable(this.map.get(k));
    }

    @Override
    public Map<K, V> clone() {
        return new HashMap<>(this.map);
    }

    @Override
    public Map<K, V> get() {
        return this.map ;
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @Override
    public boolean containsKey(K k) {
        return false;
    }

    @Override
    public boolean containsValue(V v) {
        return false;
    }

    @Override
    public Set<K> getKeys() {
        return null;
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public Map<K, V> getMap() {
        return map;
    }

    @Override
    public boolean remove(K k, V v) {
        return map.remove(k, v);
    }

    @Override
    public boolean set(K k, V v) {
        if (add(k, v)) {
            return true;
        }
        return update(k, v);
    }

    @Override
    public V getOrDefault(K k, V defaultV) {
        V get = getMap().get(k);
        return get == null ? defaultV : get;
    }
}
