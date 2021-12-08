package io.github.xxyopen.cache.builder;

import io.github.xxyopen.cache.Cache;
import io.github.xxyopen.cache.impl.ExpireAfterWriteCache;
import io.github.xxyopen.cache.impl.LruCache;
import io.github.xxyopen.cache.impl.SimpleCache;
import io.github.xxyopen.cache.impl.SynchronizedCache;
import io.github.xxyopen.cache.util.Assert;

import java.time.Duration;

/**
 * 缓存构建器
 *
 * @author xxy
 * @version 1.0.0
 * @since 2021/12/7
 */
public class CacheBuilder<K, V> {

    /**
     * 缓存写入后的过期时间（纳秒），-1表示不过期
     * */
    private long expireAfterWriteNanos = -1L;

    /**
     * 缓存最大容量
     * */
    private int maximumSize = -1;

    /**
     * 缓存初始化容量
     * */
    private int initializeCapacity = -1;

    /**
     * 缓存是否线程安全
     * */
    private boolean sync = false;

    /**
     * 缓存是否采用LRU淘汰算法
     * */
    private boolean lru = false;

    public static <K, V> CacheBuilder<K, V> newBuilder() {
        return new CacheBuilder<>();
    }

    /**
     * 设置缓存写入后的过期时间
     */
    public CacheBuilder<K, V> expireAfterWrite(Duration duration) {
        long expireAfterWriteNanos = duration.toNanos();
        Assert.requireState(this.expireAfterWriteNanos == -1L, "expire after write was already set to %s ns", this.expireAfterWriteNanos);
        Assert.requireArgument(expireAfterWriteNanos > 0L, "expire after write must be greater than 0");
        this.expireAfterWriteNanos = expireAfterWriteNanos;
        return this;
    }

    /**
     * 设置缓存的最大容量
     * @param maximumSize 缓存的最大容量
     */
    public CacheBuilder<K, V> maximumSize(int maximumSize) {
        Assert.requireState(this.maximumSize == -1, "maximum size was already set to %s", this.maximumSize);
        Assert.requireArgument(maximumSize > 0, "maximum size must be greater than 0");
        this.maximumSize = maximumSize;
        return this;
    }

    /**
     * 设置缓存是否同步（线程安全）
     */
    public CacheBuilder<K, V> sync(boolean sync) {
        this.sync = sync;
        return this;
    }

    /**
     * 设置缓存是否使用LRU淘汰算法
     */
    public CacheBuilder<K, V> lru(boolean lru) {
        this.lru = lru;
        return this;
    }

    /**
     * 设置缓存的初始化容量
     */
    public CacheBuilder<K, V> initializeCapacity(int initializeCapacity) {
        Assert.requireState(this.initializeCapacity == -1, "initial capacity was already set to %s", this.maximumSize);
        Assert.requireArgument(initializeCapacity > 0, "initial capacity must be greater than 0");
        this.initializeCapacity = initializeCapacity;
        return this;
    }

    /**
     * 根据构建器属性构建一个缓存
     */
    public Cache<K, V> build() {
        Cache<K, V> cache = new SimpleCache<>(initializeCapacity);
        if (lru) {
            cache = new LruCache<>(cache, maximumSize);
        }
        if (expireAfterWriteNanos != -1) {
            cache = new ExpireAfterWriteCache<>(cache,expireAfterWriteNanos);
        }
        if (sync) {
            cache = new SynchronizedCache<>(cache);
        }
        return cache;
    }

}
