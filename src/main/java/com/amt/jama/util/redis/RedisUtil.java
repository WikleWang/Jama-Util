package com.amt.jama.util.redis;

import com.amt.jama.util.string.SerializeUtil;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

public class RedisUtil {

    private static Logger log = Logger.getLogger(RedisUtil.class);

    private static Boolean active = false;

    static {
        try {
            RedisPool.getInstance(new RedisConfig());
            RedisPool.returnResource(RedisPool.getRedis());
            active = true;
        } catch (Exception e) {
            log.debug("redis has no been achieved!");
        }
    }

    public static boolean redisActive() {
        return active && RedisPool.isActive();
    }

    /**
     * 设置key的有效期，单位是秒
     *
     * @param key
     * @param exTime
     * @return
     */
    public static Long expire(String key, int exTime) {
        Jedis jedis = null;
        Long result = null;
        try {
            //从Redis连接池中获得Jedis对象
            jedis = RedisPool.getRedis();
            //设置成功则返回Jedis对象
            result = jedis.expire(key, exTime);
        } catch (Exception e) {
            log.error("expire key:" + key + " error", e);
            RedisPool.returnResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }


    public static Object get(String key, Class clz) {
        Object object = null;
        try {
            String data = get(key);
            if (data != null && data.length() > 0) {
                return object = SerializeUtil.unSerialize(data.getBytes());
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public static String setEx(String key, String value, int exTime) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getRedis();
            result = jedis.setex(key, exTime, value);
        } catch (Exception e) {
            log.error("setEx key:" + key + " value:" + value + " error", e);
            RedisPool.returnResource(jedis);
            return null;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static String set(String key, String value, Integer expire) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getRedis();
            if (expire != null) {
                result = jedis.setex(key, expire, value);
            } else {
                result = jedis.setex(key, 60, value);
            }
        } catch (Exception e) {
            log.error("set key:" + key + " value:" + value + " error", e);
            RedisPool.returnResource(jedis);
            return null;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static String get(String key) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getRedis();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("get key:" + key + " error", e);
            RedisPool.returnResource(jedis);
            return null;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

    public static Long del(String key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getRedis();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("del key:" + key + " error", e);
            RedisPool.returnResource(jedis);
            return result;
        }
        RedisPool.returnResource(jedis);
        return result;
    }

}
