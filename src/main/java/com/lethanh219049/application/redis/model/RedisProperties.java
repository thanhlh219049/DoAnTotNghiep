package com.lethanh219049.application.redis.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

    private String host;

    private String password;

    private int port;

    private int timeout;



    // The handle name of this object should correspond to Properties one by one
    // "Pool config" in Properties, then "poolConfig" should be used here "
    private RedisPoolConfigProperties poolConfig = new RedisPoolConfigProperties();

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public RedisPoolConfigProperties getPoolConfig() {
        return poolConfig;
    }

    public void setPoolConfig(RedisPoolConfigProperties poolConfig) {
        this.poolConfig = poolConfig;
    }

    @Override
    public String toString() {
        return "RedisProperties{" +
                "host='" + host + '\'' +
//                ", password='" + password + '\'' +
                ", port=" + port +
//                ", timeout=" + timeout +
                ", poolConfig=" + poolConfig +
                '}';
    }
}
