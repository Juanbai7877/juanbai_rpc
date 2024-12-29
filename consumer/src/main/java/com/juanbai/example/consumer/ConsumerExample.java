package com.juanbai.example.consumer;


import com.juanbai.core.RpcApplication;
import com.juanbai.core.proxy.ServiceProxyFactory;
import com.juanbai.example.common.module.User;
import com.juanbai.example.common.service.UserService;

/**
 * 简易服务消费者示例
 *
 */
public class ConsumerExample {

    public static void main(String[] args) {

        RpcApplication.init();
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
        long number = userService.getNumber();
        System.out.println(number);

    }
}



