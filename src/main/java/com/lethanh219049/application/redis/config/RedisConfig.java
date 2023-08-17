package com.lethanh219049.application.redis.config;

import com.lethanh219049.application.redis.model.RedisPoolConfigProperties;
import com.lethanh219049.application.redis.model.RedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
@Slf4j
@Component
public class RedisConfig {
    private final RedisProperties redis;

    public RedisConfig(RedisProperties redis) {
        this.redis = redis;
    }


    @Bean
    public JedisPool jedisPool() {
        RedisPoolConfigProperties poolConfig = redis.getPoolConfig();
        final JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(poolConfig.getMaxTotal());
        jedisPoolConfig.setMaxIdle(poolConfig.getMaxIdle());
        jedisPoolConfig.setMinIdle(poolConfig.getMinIdle());
//        jedisPoolConfig.setMaxWaitMillis(poolConfig.getMaxWaitMillis());
//        jedisPoolConfig.setMinEvictableIdleTimeMillis(poolConfig.getMinEvictableIdleTimeMillis());
//        jedisPoolConfig.setNumTestsPerEvictionRun(poolConfig.getNumTestsPerEvictionRun());
//        jedisPoolConfig.setTestOnBorrow(poolConfig.getTestOnBorrow());
//        jedisPoolConfig.setTestOnReturn(poolConfig.getTestOnReturn());
//        jedisPoolConfig.setTestWhileIdle(poolConfig.getTestWhileIdle());
//        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(poolConfig.getTimeBetweenEvictionRunsMillis());
        log.info("JedisPoolConfig Initialize ........");
        log.info("JedisPoolConfig Info ........ {}", poolConfig);
        String host = redis.getHost();
        int port = redis.getPort();
//        String password = redis.getPassword();
//        int timeout = redis.getTimeout();
//        final JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        final JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port);
        log.info("JedisPool Initialize ........");
        log.info("redis address---> {}:{}", host, port);
        return jedisPool;
    }
}
