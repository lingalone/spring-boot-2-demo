package io.github.lingalone.springboottokenssoserverdemo.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @author <a href="https://github.com/lingalone">lingalone</a>
 * @version 1.0.0
 * @link
 * @since 2018/7/23
 */
@Component
public class RedisUtil {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private final static String namespace = ":";

    public void add(String key, String value){
        if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)){

            stringRedisTemplate.opsForValue().set(addNamespace(key, value), LocalDateTime.now().toString());
        }

    }

    public void del(String key, String value){
        if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)){

            stringRedisTemplate.delete(addNamespace(key, value));
        }

    }
    public boolean isMember(String key, String value){
        if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)){
            return stringRedisTemplate.hasKey(addNamespace(key, value));
        }
        return false;

    }
    private String addNamespace(String key, String value){
        return key + namespace + value;
    }

}
