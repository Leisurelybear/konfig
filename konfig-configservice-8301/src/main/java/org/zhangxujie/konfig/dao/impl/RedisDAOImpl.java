/**
 * FileName: RedisDAO
 * Author:   jason
 * Date:     2021/5/21 13:34
 * Description:
 */
package org.zhangxujie.konfig.dao.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.zhangxujie.konfig.db.RedisClient;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository //DAO层
public class RedisDAOImpl implements RedisClient {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public int setString(String key, String value) {

        stringRedisTemplate.opsForValue().set(key, value);

        return 1;
    }

    @Override
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public List<String> keys(String pattern) {

        Set<String> set = stringRedisTemplate.keys(pattern);
        List<String> keys = new ArrayList<>();
        if (set == null){
            return keys;
        }
        for (Object o : set) {
            keys.add((String) o);
        }

        return keys;
    }

    @Override
    public int setExpireSeconds(String key, long expireTime) {
        stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        return 1;
    }

    @Override
    public int delete(String key) {
        Boolean status = stringRedisTemplate.delete(key);
        if (status == null){
            return 0;
        }
        return status ? 1 : 0;
    }

    @Override
    public int setObject(String key, Object obj) {

        redisTemplate.opsForValue().set(key, obj);

        return 1;
    }

    @Override
    public Object getObject(String key) {

        return redisTemplate.opsForValue().get(key);
    }

}
