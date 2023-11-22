package com.bear.rbac.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Component
@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class RedisService<T> {

    @Autowired
    private RedisTemplate redisTemplate;


    public void set(String key, T value) {

        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, T value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    public void set(String key, T value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    public T get(String key) {

        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public Boolean expire(final String key, final long timeout) {

        return expire(key, timeout, TimeUnit.SECONDS);
    }


    public Boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    public Boolean remove(String key) {
        return redisTemplate.delete(key);
    }

    public Collection<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    public Boolean exists(String key) {

        return redisTemplate.hasKey(key);
    }

    public Long incr(String key) {

        return redisTemplate.opsForValue().increment(key);
    }
}
