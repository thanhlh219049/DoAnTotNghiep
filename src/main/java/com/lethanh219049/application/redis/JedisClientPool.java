package com.lethanh219049.application.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;


/**
 * Redis Realization
 */
@Configuration
@Slf4j
public class JedisClientPool implements JedisClient {

    @Autowired
    JedisPool jedisPool;

    @Override
    public void set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        jedis.set(key, value);
        jedis.close();
    }

    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.get(key);
        jedis.close();
        return result;
    }

    @Override
    public Boolean exists(String key) {
        Jedis jedis = jedisPool.getResource();
        Boolean result = jedis.exists(key);
        jedis.close();
        return result;
    }

    @Override
    public void expire(String key, int seconds) {
        Jedis jedis = jedisPool.getResource();
        jedis.expire(key, seconds);
        jedis.close();
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }


    @Override
    public Long del(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.del(key);
        jedis.close();
        return result;
    }

    @Override
    public String set(String key, String value, String nxxx, String expx, long time) {
        Jedis jedis = jedisPool.getResource();
//        String result = jedis.set(key, value, nxxx, expx, time);
        String result = jedis.set(key, value);
        jedis.close();
        return result;
    }

    @Override
    public List<String> lrange(String key, long start, long stop) {
        Jedis jedis = jedisPool.getResource();
        List<String> result = jedis.lrange(key, start, stop);
        jedis.close();
        return result;
    }
    @Override
    public String ltrim(String key, long start, long stop) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.ltrim(key, start, stop);
        jedis.close();
        return result;
    }


    @Override
    public Long lpush(String key, String strings) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.lpush(key, strings);
        jedis.close();
        return result;
    }  @Override
    public Long rpush(String key, String strings) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.rpush(key, strings);
        jedis.close();
        return result;
    }


    @Override
    public Long llen(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.llen(key);
        jedis.close();
        return result;
    }

}
