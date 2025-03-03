package com.juanbai.rpc.juanrpcspringbootprovider.getInstance;

import com.juanbai.rpc.juanrpcspringbootprovider.mapper.UserMapper;
import lombok.Data;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @author ALL
 * @date 2025/2/13
 * @Description
 */
@Data
public class GetInstance {
    private StringRedisTemplate stringRedisTemplate;

    private UserMapper userMapper;

    GetInstance(){

    }



}
