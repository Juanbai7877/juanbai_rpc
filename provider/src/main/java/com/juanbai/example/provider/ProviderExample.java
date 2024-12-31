package com.juanbai.example.provider;

import cn.hutool.core.net.NetUtil;
import com.juanbai.core.RpcApplication;
import com.juanbai.core.config.RegistryConfig;
import com.juanbai.core.config.RpcConfig;
import com.juanbai.core.model.ServiceMetaInfo;
import com.juanbai.core.registry.LocalRegistry;
import com.juanbai.core.registry.Registry;
import com.juanbai.core.registry.RegistryFactory;
import com.juanbai.core.server.tcp.VertxTcpServer;
import com.juanbai.example.common.service.UserService;


/**
 * 服务提供者示例
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @learn <a href="https://codefather.cn">编程宝典</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public class ProviderExample {

    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 启动 TCP 服务
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(8080);
    }
}
