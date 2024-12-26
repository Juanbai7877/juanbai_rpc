package com.yupi.example.consumer;


import com.juanbai.easyrpc.proxy.ServiceProxyFactory;
import com.juanbai.example.common.module.User;
import com.juanbai.example.common.service.UserService;

/**
 * 简易服务消费者示例
 *
 */
public class EasyConsumerExample {

    public static void main(String[] args) {
        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("juan bai");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }

    }
}


