package io.github.xxyopen.cache.impl;

import io.github.xxyopen.cache.Cache;
import io.github.xxyopen.cache.util.Assert;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * LRU (最近最久未使用)缓存实现
 *
 * @author xxy
 * @version 1.0.0
 * @since 2021/12/7
 */
public class LruCache <K, V> implements Cache<K, V> {

    /**
     * 原始缓存
     * */
    private final Cache<K, V> cache;

    /**
     * 按照缓存key的访问顺序排序
     * */
    private Map<K, V> keyMap;

    /**
     * 最年长（最久未使用）的key
     * */
    private K eldestKey;

    public LruCache(Cache<K, V> cache, int maximumSize) {
        Assert.requireState(maximumSize != -1, "maximum size has not been set");
        Assert.requireArgument(maximumSize > 0,"maximum size must be greater than 0");
        this.cache = cache;
        setMaximumSize(maximumSize);
    }

    @Override
    public int getSize() {
        return cache.getSize();
    }

    public void setMaximumSize(final int maximumSize) {

        //链表key按照访问顺序排序，调用get方法后，会将这次访问的元素移至头部
        //实际初始容量应该比缓存容量大1
        //当实际缓存对象超出缓存容量（达到size+1）时，根据LRU缓存淘汰策略淘汰多余的元素
        keyMap = new LinkedHashMap<K, V>(maximumSize + 1, 1.0f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                boolean tooBig = size() > maximumSize;
                if (tooBig) {
                    eldestKey = eldest.getKey();
                }
                return tooBig;
            }

        };
    }

    @Override
    public void put(K key, V object) {
        checkEldestKey(key);
        cache.put(key, object);
    }

    @Override
    public V get(K key) {
        keyMap.get(key);
        return cache.get(key);
    }

    @Override
    public V get(K key, Callable<? extends V> call) throws Exception {
        return this.cache.get(key, call);
    }

    @Override
    public V remove(K key) {
        return cache.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
        keyMap.clear();
    }

    /**
     * 检查缓存对象是否超出容量
     * */
    private void checkEldestKey(K key) {
        keyMap.put(key, null);
        if (eldestKey != null) {
            cache.remove(eldestKey);
            eldestKey = null;
        }
    }
}
