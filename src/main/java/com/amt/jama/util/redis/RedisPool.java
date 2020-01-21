package com.amt.jama.util.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {

    private static JedisPool pool = null;

    private static RedisConfig config = null;


    private RedisPool() {
    }

    private static class SingletonHolder {
        private static final RedisPool INSTANCE = new RedisPool();
    }

    public static boolean isActive() {
        return config != null && config.getActive();
    }

    public static RedisPool getInstance(RedisConfig config) throws Exception {
        RedisPool redisPool = SingletonHolder.INSTANCE;
        redisPool.init(config);
        return redisPool;
    }

    private void init(RedisConfig config) {
        RedisPool.config = config = config == null ? new RedisConfig() : config;
        if (config.getActive()) {
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            //控制一个pool可分配多少个Redis实例，通过pool.getResource()来获取；
            //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
            poolConfig.setMaxTotal(config.getMaxTotal());
            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            poolConfig.setMaxIdle(config.getMaxIdle());
            //表示当borrow(引入)一个Redis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；单位毫秒
            //小于零:阻塞不确定的时间,  默认-1
            poolConfig.setMaxWaitMillis(1000 * config.getMaxWaitMillis());
            //在borrow(引入)一个Redis实例时，是否提前进行validate操作；如果为true，则得到的Redis实例均是可用的；
            poolConfig.setTestOnBorrow(config.getTestOnBorrow());
            //return 一个Redis实例给pool时，是否检查连接可用性（ping()）
            poolConfig.setTestOnReturn(config.getTestOnReturn());
            //connectionTimeout 连接超时（默认2000ms）
            //soTimeout 响应超时（默认2000ms）
            String password = config.getPassword();
            if (password != null) {
                pool = new JedisPool(poolConfig, config.getUrl(), config.getPort(), config.getTimeout(), config.getPassword());
            } else {
                pool = new JedisPool(poolConfig, config.getUrl(), config.getPort(), config.getTimeout());
            }
        }
    }

    public static Jedis getRedis() {
        return pool.getResource();
    }

    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }


    public static void destroy() {
        if (pool != null) {
            pool.destroy();
        }
        if (config != null) {
            config = null;
        }
    }

}
