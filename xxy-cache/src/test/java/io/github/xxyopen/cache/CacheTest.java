package io.github.xxyopen.cache;

import io.github.xxyopen.cache.builder.CacheBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * 缓存单元测试
 *
 * @author xxy
 * @version 1.0.0
 * @since 2021/12/8
 */
public class CacheTest {

    @Test
    public void lruCacheTest() {
        CacheBuilder<String, String> cacheBuilder = CacheBuilder.newBuilder();
        Cache<String, String> cache = cacheBuilder.lru(true).initializeCapacity(5).maximumSize(5).build();

        cache.put("key1","object1");
        cache.put("key2","object2");
        cache.put("key3","object3");
        cache.put("key4","object4");

        cache.get("key2");

        cache.put("key5","object5");
        cache.put("key6","object6");
        cache.put("key7","object7");

        Assert.assertEquals(cache.getSize(),5);
        Assert.assertNull(cache.get("key1"));
        Assert.assertNull(cache.get("key3"));

        Assert.assertNotNull(cache.get("key2"));
        Assert.assertNotNull(cache.get("key4"));
        Assert.assertNotNull(cache.get("key5"));
        Assert.assertNotNull(cache.get("key6"));
        Assert.assertNotNull(cache.get("key7"));



    }

    @Test
    public void expireAfterWriteCacheTest() throws Exception{
        CacheBuilder<String, String> cacheBuilder = CacheBuilder.newBuilder();
        Cache<String, String> cache = cacheBuilder.expireAfterWrite(Duration.ofSeconds(5)).initializeCapacity(7).build();

        cache.put("key1","object1");
        cache.put("key2","object2");
        cache.put("key3","object3");
        cache.put("key4","object4");

        TimeUnit.SECONDS.sleep(12);

        cache.put("key5","object5");
        cache.put("key6","object6");
        cache.put("key7","object7");

        Assert.assertEquals(cache.getSize(),3);


        Assert.assertNull(cache.get("key1"));
        Assert.assertNull(cache.get("key2"));
        Assert.assertNull(cache.get("key3"));
        Assert.assertNull(cache.get("key4"));

        Assert.assertNotNull(cache.get("key5"));
        Assert.assertNotNull(cache.get("key6"));
        Assert.assertNotNull(cache.get("key7"));

    }

}
