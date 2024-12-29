package com.juanbai.example.provider;


import com.juanbai.core.RpcApplication;
import com.juanbai.core.registry.LocalRegistry;
import com.juanbai.core.server.HttpServer;
import com.juanbai.core.server.VertxHttpServer;
import com.juanbai.example.common.service.UserService;

/**
 * 简易服务提供者示例
 *
 */
public class ProviderExample {

    public static void main(String[] args) {
        RpcApplication.init();

        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }

}
