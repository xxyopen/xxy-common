package io.github.xxyopen.cache;

import java.util.concurrent.Callable;

/**
 * 缓存接口
 *
 * @author xxy
 * @version 1.0.0
 * @since 2021/12/7
 */
public interface Cache<K, V> {

    /**
     * 将对象加入到缓存中，如果该缓存key已存在，则缓存对象将被覆盖
     * @param key 缓存key
     * @param object 缓存的对象
     */
    void put(K key, V object);

    /**
     * 根据缓存key从缓存中获取对应的缓存对象，如果缓存对象不存在或已过期，则返回null
     * @param key 缓存key
     * @return 缓存的对象
     */
    V get(K key);

    /**
     * 根据缓存key从缓存中获取对应的缓存对象，如果缓存对象不存在则调用函数，由函数处理后放入缓存中
     * @param key 缓存key
     * @param call  一个函数，函数的返回值会更新到缓存中
     * @return 缓存的对象
     * @throws Exception 可能的处理异常
     */
    V get(K key, Callable<? extends V> call) throws Exception;

    /**
     * 从缓存中移除对象
     * @param key 缓存key
     * @return 移除的缓存对象
     */
    V remove(K key);

    /**
     * 清空缓存
     */
    void clear();

    /**
     * 获取缓存的对象数量
     * @return  缓存的对象数量
     */
    int getSize();
}
