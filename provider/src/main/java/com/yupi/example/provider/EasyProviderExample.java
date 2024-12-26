package com.yupi.example.provider;


import com.juanbai.easyrpc.registry.LocalRegistry;
import com.juanbai.easyrpc.server.HttpServer;
import com.juanbai.easyrpc.server.VertxHttpServer;
import com.juanbai.example.common.service.UserService;

/**
 * 简易服务提供者示例
 *
 */
public class EasyProviderExample {

    public static void main(String[] args) {
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }

}
