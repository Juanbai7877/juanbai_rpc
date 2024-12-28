package com.juanbai.example.consumer;


import com.juanbai.core.config.RpcConfig;
import com.juanbai.core.proxy.ServiceProxyFactory;
import com.juanbai.core.utils.ConfigUtils;
import com.juanbai.example.common.module.User;
import com.juanbai.example.common.service.UserService;

/**
 * 简易服务消费者示例
 *
 */
public class EasyConsumerExample {

    public static void main(String[] args) {

        RpcConfig rpcConfig = ConfigUtils.loadConfig(RpcConfig.class,"rpc");
        System.out.println(rpcConfig);
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



