package com.myweb.content.properties;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import java.util.List;
import java.util.Map;

public class RedisClientProperties extends RedisProperties {
    private boolean enableCache;

    private Pool pool;

    private Map<String, Long> expiration;

    private boolean flushDbWhenStart = false;

    private List<ContentToRemove> keyToRemove;

    public boolean isEnableCache() {
        return enableCache;
    }

    public void setEnableCache(boolean enableCache) {
        this.enableCache = enableCache;
    }

    public List<ContentToRemove> getKeyToRemove() {
        return keyToRemove;
    }

    public void setKeyToRemove(List<ContentToRemove> keyToRemove) {
        this.keyToRemove = keyToRemove;
    }

    @Override
    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    public Map<String, Long> getExpiration() {
        return expiration;
    }

    public void setExpiration(Map<String, Long> expiration) {
        this.expiration = expiration;
    }

    public boolean isFlushDbWhenStart() {
        return flushDbWhenStart;
    }

    public void setFlushDbWhenStart(boolean flushDbWhenStart) {
        this.flushDbWhenStart = flushDbWhenStart;
    }

    public static class Pool extends RedisProperties.Pool {
        private boolean testOnBorrow = false;
        private boolean testOnReturn = false;
        private boolean testWhileIdle = false;
        private int numTestsPerEvictionRun = 3;
        private long timeBetweenEvictionRunsMillis = -1L;

        public boolean isTestOnBorrow() {
            return testOnBorrow;
        }

        public void setTestOnBorrow(boolean testOnBorrow) {
            this.testOnBorrow = testOnBorrow;
        }

        public boolean isTestOnReturn() {
            return testOnReturn;
        }

        public void setTestOnReturn(boolean testOnReturn) {
            this.testOnReturn = testOnReturn;
        }

        public boolean isTestWhileIdle() {
            return testWhileIdle;
        }

        public void setTestWhileIdle(boolean testWhileIdle) {
            this.testWhileIdle = testWhileIdle;
        }

        public int getNumTestsPerEvictionRun() {
            return numTestsPerEvictionRun;
        }

        public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
            this.numTestsPerEvictionRun = numTestsPerEvictionRun;
        }

        public long getTimeBetweenEvictionRunsMillis() {
            return timeBetweenEvictionRunsMillis;
        }

        public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
            this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
        }
    }

    public static class ContentToRemove {
        private String cacheName;

        private String keyPattern;

        public String getCacheName() {
            return cacheName;
        }

        public void setCacheName(String cacheName) {
            this.cacheName = cacheName;
        }

        public String getKeyPattern() {
            return keyPattern;
        }

        public void setKeyPattern(String keyPattern) {
            this.keyPattern = keyPattern;
        }
    }
}
