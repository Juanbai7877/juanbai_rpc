package com.juanbai.examplespringbootprovider;

import com.juanbai.example.common.module.User;
import com.juanbai.example.common.service.UserService;
import com.juanbai.juanrpcspingbootstarter.annotation.RpcService;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 *
 */
@Service
@RpcService
public class UserServiceImpl implements UserService {

    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
