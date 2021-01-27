package com.upuphub.trochilidae.cache.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Guava Cache Impl
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2021-01-27 19:25
 **/
public class GuavaCacheImpl <K,V> implements com.upuphub.trochilidae.cache.Cache<K,V> {

    Cache<String, String> cache = CacheBuilder.newBuilder()
            //cache的初始容量
            .initialCapacity(5)
            //cache最大缓存数
            .maximumSize(10)
            //设置写缓存后n秒钟过期
            .expireAfterWrite(17, TimeUnit.SECONDS)
            //设置读写缓存后n秒钟过期,实际很少用到,类似于expireAfterWrite
            //.expireAfterAccess(17, TimeUnit.SECONDS)
            .build();


    @Override
    public V getIfPresent(Object key) {
        return null;
    }

    @Override
    public V get(K key, Function<? super K, ? extends V> mappingFunction) {
        return null;
    }

    @Override
    public Map<K, V> getAllPresent(Iterable<?> keys) {
        return null;
    }

    @Override
    public Map<K, V> getAll(Iterable<? extends K> keys, Function<Iterable<? extends K>, Map<K, V>> mappingFunction) {
        return null;
    }

    @Override
    public void put(K key, V value) {

    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {

    }

    @Override
    public void invalidate(Object key) {

    }

    @Override
    public void invalidateAll(Iterable<?> keys) {

    }

    @Override
    public void invalidateAll() {

    }

    @Override
    public long estimatedSize() {
        return 0;
    }

    @Override
    public ConcurrentMap<K, V> asMap() {
        return null;
    }

    @Override
    public void cleanUp() {

    }
}
