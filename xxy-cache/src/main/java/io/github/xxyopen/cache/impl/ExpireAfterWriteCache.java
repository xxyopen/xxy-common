package io.github.xxyopen.cache.impl;

import io.github.xxyopen.cache.Cache;
import io.github.xxyopen.cache.CacheItemProperties;
import io.github.xxyopen.cache.timer.ClearTimerExecutor;
import io.github.xxyopen.cache.util.Assert;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 定时缓存实现
 *
 * @author xxy
 * @version 1.0.0
 * @since 2021/12/7
 */
public class ExpireAfterWriteCache<K, V> implements Cache<K, V> {

    /**
     * 缓存写入后的过期时间（纳秒），-1表示不过期
     * */
    private final long expireAfterWriteNanos;

    private final Cache<K, V> cache;

    /**
     * 缓存条目属性（包括缓存时间等）的映射
     * */
    private final HashMap<K, CacheItemProperties> keyProp = new HashMap<>();

    public ExpireAfterWriteCache(Cache<K, V> cache, long expireAfterWriteNanos) {
        Assert.requireState(expireAfterWriteNanos != -1, "expire after write has not been set");
        Assert.requireArgument(expireAfterWriteNanos > 0,"expire after write must be greater than 0");
        this.expireAfterWriteNanos = expireAfterWriteNanos;
        this.cache = cache;
        //每10秒钟定期清理一次过期的缓存对象
        ClearTimerExecutor.INSTANCE.schedule(()->{
            Iterator<Map.Entry<K, CacheItemProperties>> iterator = keyProp.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<K, CacheItemProperties> entry = iterator.next();
                if (isExpire(entry.getValue())) {
                    iterator.remove();
                    cache.remove(entry.getKey());
                }
            }
        },10*1000);
    }

    @Override
    public void put(K key, V object) {
        keyProp.put(key, cacheItemProperties());
        this.cache.put(key, object);
    }

    @Override
    public V get(K key) {
        if (keyProp.get(key) != null && isExpire(keyProp.get(key))) {
            this.keyProp.remove(key);
            this.cache.remove(key);
            return null;
        }
        return this.cache.get(key);
    }

    @Override
    public V get(K key, Callable<? extends V> call) throws Exception {
        return this.cache.get(key, call);
    }

    @Override
    public V remove(K key) {
        keyProp.remove(key);
        return this.cache.remove(key);
    }

    @Override
    public void clear() {
        keyProp.clear();
        this.cache.clear();
    }

    @Override
    public int getSize() {
        return this.cache.getSize();
    }

    private boolean isExpire(CacheItemProperties itemProperties) {
        return System.nanoTime() - itemProperties.getWriteNanos() > expireAfterWriteNanos;
    }

    private CacheItemProperties cacheItemProperties() {
        CacheItemProperties cacheItemProperties = new CacheItemProperties();
        cacheItemProperties.setWriteNanos(System.nanoTime());
        return cacheItemProperties;
    }

}
