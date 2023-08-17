package com.lethanh219049.application.redis;

import java.util.List;

public interface JedisClient {

    /**
     * Associate string value to key
     */
    void set(String key, String value);

    /**
     * Returns the string value associated with the key.
     * If the key does not exist, the special value nil is returned.
     * If the value stored by key is not of string type, an error is returned, because GET can only be used to process string value.
     */
    String get(String key);

    /**
     * Check whether the given key exists.
     */
    Boolean exists(String key);

    /**
     * Set the lifetime for the given key. When the key expires (the lifetime is 0), it will be deleted automatically.
     * Unit: sec.
     */
    void expire(String key, int seconds);

    /**
     * Returns the TTL (time to live) of a given key in seconds.
     */
    Long ttl(String key);


    /**
     * Delete a given key.
     */
    Long del(String key);

    /**
     * Store the data in the cache, and determine the expiration time and whether to overwrite when the Key exists.
     * @param nxxx The value can only be NX or XX. If NX is selected, the value is set only when the key does not exist. If XX is selected, the value is set only when the key already exists
     * @param expx expx The value of can only take EX or Px, which represents the unit of data expiration time. EX represents seconds and PX represents milliseconds.
     * @param time Expiration time in the unit represented by expx.
     */
    String set(String key, String value, String nxxx, String expx, long time);


    List<String> lrange(String key, long start, long stop);

    String ltrim(String key, long start, long stop);

    /**
     * lấy 1 list dữ liệu trong ređis
     */
    Long lpush(String key, String strings);
    Long rpush(String key, String strings);

    /**
     * cout số bản ghi trong ređis
     */
    Long llen(String key);

}
