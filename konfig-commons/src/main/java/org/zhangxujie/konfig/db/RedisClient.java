package org.zhangxujie.konfig.db;

import java.util.List;

public interface RedisClient {

    int setString(String key, String value);

    String getString(String key);

    List<String> keys(String pattern);

    int setExpireSeconds(String key, long expireTime);

    int delete(String key);

    int setObject(String key, Object obj);

    Object getObject(String key);


}
