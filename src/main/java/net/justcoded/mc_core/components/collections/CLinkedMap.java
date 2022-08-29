package net.justcoded.mc_core.components.collections;

import net.justcoded.mc_core.interfaces.Mappable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class CLinkedMap<K, V> implements Mappable<K, V> {
    protected LinkedHashMap<K, V> map = new LinkedHashMap<>();

    @Override
    public boolean add(K t, V v) {
        return map.put(t, v) != null;
    }

    @Override
    public boolean remove(K t) {
        return map.remove(t) != null;
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
        if (k == null || this.size() == 0) {
            return Optional.empty();
        }
        return Optional.ofNullable(map.get(k));
    }

    @Override
    public Map<K, V> clone() {
        return new LinkedHashMap<>(map);
    }

    @Override
    public Map<K, V> get() {
        return map;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean containsKey(K k) {
        return map.containsKey(k);
    }

    @Override
    public boolean containsValue(V v) {
        return map.containsValue(v);
    }

    @Override
    public Set<K> getKeys() {
        return map.keySet();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Map<K, V> getMap() {
        return null;
    }

    @Override
    public boolean remove(K k, V v) {
        return map.remove(k, v);
    }

    @Override
    public boolean set(K k, V v) {
        return false;
    }

    @Override
    public V getOrDefault(K k, V defaultV) {
        V get = getMap().get(k);
        return get == null ? defaultV : get;
    }
}
