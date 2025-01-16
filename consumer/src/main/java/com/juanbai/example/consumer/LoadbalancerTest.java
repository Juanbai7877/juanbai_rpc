package com.juanbai.example.consumer;

import com.juanbai.core.bootstrap.ConsumerBootstrap;
import com.juanbai.core.proxy.ServiceProxyFactory;
import com.juanbai.example.common.module.User;
import com.juanbai.example.common.service.UserService;

/**
 * @author ALL
 * @date 2025/1/16
 * @Description
 */
public class LoadbalancerTest {
    public static void main(String[] args) {
        // 服务提供者初始化
        ConsumerBootstrap.init();
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        for(int i =0 ; i<100;i++){
            user.setName("user:"+ i);
            // 调用
            User newUser = userService.getUser(user);
            if (newUser != null) {
                System.out.println(newUser.getName());
            } else {
                System.out.println("user == null");
            }
        }
    }
}
