//package com.myweb.content.service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//import javax.annotation.PostConstruct;
//
//import com.myweb.content.properties.RedisClientProperties;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.Cache;
//import org.springframework.cache.CacheManager;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.core.RedisTemplate;
//import java.nio.charset.Charset;
//
//public class RedisCacheService {
//
//    private static Charset CS_DEFAULT_CS= Charset.forName("UTF-8");
//
//    private CacheManager cacheManager;
//
//    private RedisTemplate redisTemplate;
//
//    private RedisClientProperties redisClientProperties;
//
//    @Autowired
//    public RedisCacheService(CacheManager cacheManager, RedisTemplate redisTemplate, RedisClientProperties redisClientProperties) {
//        this.cacheManager = cacheManager;
//        this.redisTemplate = redisTemplate;
//        this.redisClientProperties = redisClientProperties;
//    }
//
//    @PostConstruct
//    public void clearRedis() {
//        if (redisClientProperties.isFlushDbWhenStart()) {
//            flushDb();
//        } else {
//            List<RedisClientProperties.ContentToRemove> keyToRemove = redisClientProperties.getKeyToRemove();
//            if (keyToRemove != null&& keyToRemove.size()!=0) {
//                for (RedisClientProperties.ContentToRemove contentToRemove : keyToRemove) {
//                    String cacheName = contentToRemove.getCacheName();
//                    String pattern = contentToRemove.getKeyPattern();
//                    removeCache(cacheName, pattern);
//                }
//            }
//        }
//    }
//
//    public void flushDb() {
//        if (this.cacheManager instanceof RedisCacheManager) {
//            redisTemplate.getConnectionFactory().getConnection().flushDb();
//        }
//    }
//
//    public List<String> removeCache(String cacheName, String keyPattern) {
//        if (this.cacheManager instanceof RedisCacheManager) {
//            List<String> keyList = new ArrayList<>();
//            RedisCacheManager redisCacheManager = (RedisCacheManager) this.cacheManager;
//            Cache cache = redisCacheManager.getCache(cacheName);
//            String cachePrefix = cache.getName() + ":";
//            String rawKeyPattern = cacheName + ":" + keyPattern;
//            Set<byte[]> keys = redisTemplate.getConnectionFactory().getConnection().keys(rawKeyPattern.getBytes(
//                    CS_DEFAULT_CS));
//            for (byte[] keyBytes : keys) {
//                String pfxKey = new String(keyBytes, CS_DEFAULT_CS);
//                String newKey = pfxKey.replace(cachePrefix, "");
//                cache.evict(newKey);
//                keyList.add(newKey);
//            }
//            return keyList;
//        }
//        return null;
//    }
//
//    public void saveCache(String cacheName, String key, Object value) {
//        RedisCacheManager redisCacheManager = (RedisCacheManager) this.cacheManager;
//        Cache cache = redisCacheManager.getCache(cacheName);
//        cache.put(key, value);
//    }
//
//    public Object getCache(String cacheName, String key) {
//        RedisCacheManager redisCacheManager = (RedisCacheManager) this.cacheManager;
//        Cache.ValueWrapper wrapper = redisCacheManager.getCache(cacheName).get(key);
//        return wrapper != null ? wrapper.get() : null;
//    }
//}
