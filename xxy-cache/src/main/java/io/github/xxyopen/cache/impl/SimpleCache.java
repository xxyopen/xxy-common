package io.github.xxyopen.cache.impl;

import io.github.xxyopen.cache.Cache;
import io.github.xxyopen.cache.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 简单缓存实现
 *
 * @author xxy
 * @version 1.0.0
 * @since 2021/12/7
 */
public class SimpleCache<K, V> implements Cache<K, V> {

    private final Map<K, V> cache;

    public SimpleCache(int initializeCapacity) {
        Assert.requireState(initializeCapacity != -1, "initial capacity has not been set");
        cache = new HashMap<>(initializeCapacity);
    }

    @Override
    public void put(K key, V object) {
        cache.put(key, object);
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public V get(K key, Callable<? extends V> call) throws Exception {
        if (cache.containsKey(key)) {
            return cache.get(key);
        } else {
            V v2 = call.call();
            cache.put(key, v2);
            return v2;
        }
    }

    @Override
    public V remove(K key) {
        return cache.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public int getSize() {
        return cache.size();
    }
}
