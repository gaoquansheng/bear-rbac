package com.bear.rbac.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Component
public class RedisService<T> {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public void set(String key, T value) {

        redisTemplate.opsForValue().set(key,value);
    }

    public void set(String key, T value, long timeout) {
        redisTemplate.opsForValue().set(key,value,timeout, TimeUnit.SECONDS);
    }

    public void set(String key, T value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key,value,timeout,timeUnit);
    }

    public T get(String key) {

        return (T) redisTemplate.opsForValue().get(key);
    }

    public Boolean expire(final String key, final long timeout) {

        return expire(key, timeout, TimeUnit.SECONDS);
    }


    public Boolean expire(final String key, final long timeout, final TimeUnit unit)
    {
        return redisTemplate.expire(key, timeout, unit);
    }

    public Boolean remove(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> keys(String pattern)
    {
        return redisTemplate.keys(pattern);
    }

    public Boolean exists(String key) {

        return redisTemplate.hasKey(key);
    }

    public long incr(String key) {

        return redisTemplate.opsForValue().increment(key);
    }
}
