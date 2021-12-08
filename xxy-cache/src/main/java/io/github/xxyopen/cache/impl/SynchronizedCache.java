package io.github.xxyopen.cache.impl;

import io.github.xxyopen.cache.Cache;

import java.util.concurrent.Callable;

/**
 * 线程安全缓存实现
 *
 * @author xxy
 * @version 1.0.0
 * @since 2021/12/7
 */
public class SynchronizedCache<K, V> implements Cache<K, V> {

    private final Cache<K, V> cache;

    public SynchronizedCache(Cache<K, V> cache) {
        this.cache = cache;
    }

    @Override
    public synchronized void put(K key, V object) {
        this.cache.put(key, object);
    }

    @Override
    public synchronized V get(K key) {
        return this.cache.get(key);
    }

    @Override
    public V get(K key, Callable<? extends V> call) throws Exception {
        return this.cache.get(key, call);
    }

    @Override
    public synchronized V remove(K key) {
        return this.cache.remove(key);
    }

    @Override
    public synchronized void clear() {
        this.cache.clear();
    }

    @Override
    public synchronized int getSize() {
        return this.cache.getSize();
    }
}
