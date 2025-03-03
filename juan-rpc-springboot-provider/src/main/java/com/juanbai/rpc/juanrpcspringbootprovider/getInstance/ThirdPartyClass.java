package com.juanbai.rpc.juanrpcspringbootprovider.getInstance;

import com.juanbai.rpc.juanrpcspringbootprovider.mapper.UserMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author ALL
 * @date 2025/2/13
 * @Description
 */

@Component
public class ThirdPartyClass implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    private StringRedisTemplate stringRedisTemplate;

    private UserMapper userMapper;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ThirdPartyClass.applicationContext=applicationContext;
    }

    public static Object getVarInstance( String name) {
        Object bean = applicationContext.getBean(name);
        return bean;
    }

}
