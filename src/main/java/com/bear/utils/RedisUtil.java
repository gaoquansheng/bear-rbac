package com.bear.utils;

import com.bear.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil<T> {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

    public T get(String key) {
        try {
            return(T) redisTemplate.opsForValue().get(key);
        }catch (Exception e) {
            throw new BusinessException("");
        }
    }

    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}
