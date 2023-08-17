package com.lethanh219049.application.redis.model;

public class RedisPoolConfigProperties {

    private int maxTotal;

    private int maxIdle;

    private int minIdle;

    private int maxWaitMillis;

    private Boolean testOnBorrow;

    private Boolean testOnReturn;

    private Boolean testWhileIdle;

    private int numTestsPerEvictionRun;

    private int timeBetweenEvictionRunsMillis;

    private int minEvictableIdleTimeMillis;

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Boolean getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public Boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(Boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public int getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public int getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public int getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    @Override
    public String toString() {
        return "RedisPoolConfigProperties{" +
                "maxTotal=" + maxTotal +
                ", maxIdle=" + maxIdle +
                ", minIdle=" + minIdle +
//                ", maxWaitMillis=" + maxWaitMillis +
//                ", testOnBorrow=" + testOnBorrow +
//                ", testOnReturn=" + testOnReturn +
//                ", testWhileIdle=" + testWhileIdle +
//                ", numTestsPerEvictionRun=" + numTestsPerEvictionRun +
//                ", timeBetweenEvictionRunsMillis=" + timeBetweenEvictionRunsMillis +
//                ", minEvictableIdleTimeMillis=" + minEvictableIdleTimeMillis +
                '}';
    }
}