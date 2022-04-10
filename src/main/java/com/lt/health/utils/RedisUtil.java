package com.lt.health.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @description: reids工具类
 * @author: 狂小腾
 * @date: 2022/3/26 20:52
 */
@Slf4j
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 向redis中存值
     *
     * @param key   键
     * @param value 值
     * @return 是否存入成功
     */
    public boolean setValue(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("向redis中存入值时异常-->{}", e.getMessage());
            return false;
        }
    }

    /**
     * 向redis中存值并指定过期时间 分钟
     * SECONDS: 秒
     * MINUTES: 分
     * HOURS: 时
     * DAYS: 天
     */
    public boolean setValueTime(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.MINUTES);
            } else {
                redisTemplate.opsForValue().set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error("设置缓存并指定过期时间异常-->{}", e.getMessage());
            return false;
        }
    }

    /**
     * 根据key获取redis中的值
     */
    public Object getValue(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 根据key删除redis中的缓存
     */
    public void delKey(String... keys) {
        if (keys != null && keys.length > 0) {
            if (keys.length == 1) {
                redisTemplate.delete(keys[0]);
            } else {
                for (String key : keys) {
                    redisTemplate.delete(key);
                }
            }
        }
    }

    /**
     * 判断值是否存在
     */
    public boolean haskey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("redis值不存在-->{}", e.getMessage());
            return false;
        }
    }

    /**
     * 获取redis键的过期时间
     * 0代表永久有效
     * 大于0就剩多少分钟失效
     */
    public Long isExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.MINUTES);
    }

    /**
     * 给key加过期时间
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.MINUTES);
            }
            return true;
        } catch (Exception e) {
            log.error("给旧的缓存设置新的过期时间异常--> {}", e.getMessage());
            return false;
        }
    }
}
