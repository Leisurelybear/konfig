package org.zhangxujie.konfig.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zhangxujie.konfig.model.Account;

import javax.annotation.Resource;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class RedisDAOImplTest {
    @Resource
    private RedisDAOImpl redisDAOImpl;

    @Test
    void setString() {

        String key = "name";
        String value = "value111";

        redisDAOImpl.setString(key, value);
        redisDAOImpl.setString("names", value);

        System.out.println("success!");

    }

    @Test
    void getString() {

        String key = "name";
        String valueGet = redisDAOImpl.getString(key);

        System.out.println("success!");

    }

    @Test
    void keys() {

        List<String> keys = redisDAOImpl.keys("*am*");

        keys.forEach(System.out::println);
    }

    @Test
    void setExpireSeconds() {

        redisDAOImpl.setExpireSeconds("names", 10);

    }

    @Test
    void delete() {
        redisDAOImpl.delete("name");
    }

    @Test
    void getAndSetObject() {

        Account account = new Account();
        account.setId(1);
        account.setUsername("zhangxujie");
        account.setEmail("123@abc.com");
        account.setPassword("pa$$word");

        redisDAOImpl.setObject("account", account);

        System.out.println("set");

        Object obj = redisDAOImpl.getObject("account");

        Account getObj = (Account)obj;

        System.out.println(getObj);

        redisDAOImpl.delete("account");

    }
}