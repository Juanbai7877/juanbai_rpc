package com.juanbai.core;

import com.juanbai.core.config.RegistryConfig;
import com.juanbai.core.config.RpcConfig;
import com.juanbai.core.constant.RpcConstant;
import com.juanbai.core.model.ServiceMetaInfo;
import com.juanbai.core.registry.Registry;
import com.juanbai.core.registry.RegistryFactory;
import com.juanbai.core.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

/**
 * RPC 框架应用
 * 相当于 holder，存放了项目全局用到的变量。双检锁单例模式实现
 */
@Slf4j
public class RpcApplication {

    private static volatile RpcConfig rpcConfig;

    private static volatile HashMap<String, ServiceMetaInfo> serviceMetaInfoList;


    /**
     * 框架初始化，支持传入自定义配置
     *
     * @param newRpcConfig
     */
    /**
     * 初始化
     */
    public static void init() {
        RpcConfig newRpcConfig;
        serviceMetaInfoList=new HashMap<>();
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            // 配置加载失败，使用默认值
            newRpcConfig = new RpcConfig();
            try {
                newRpcConfig.setServerHost(String.valueOf(InetAddress.getLocalHost().getHostAddress()));
            }catch (Exception intenetException){
                log.warn("获取本地地址失败");
            }
        }
        init(newRpcConfig);
    }

    /**
     * 框架初始化，支持传入自定义配置
     *
     * @param newRpcConfig
     */
    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc init, config = {}", newRpcConfig.toString());
        // 注册中心初始化
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        registry.init(registryConfig);
        log.info("registry init, config = {}", registryConfig);

        // 创建并注册 Shutdown Hook，JVM 退出时执行操作
        Runtime.getRuntime().addShutdownHook(new Thread(registry::destroy));
    }



    /**
     * 获取配置
     *
     * @return
     */
    public static RpcConfig getRpcConfig() {
        if (rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if (rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }

    /**
     * 获取配置
     *
     * @return
     */
    public static HashMap<String,ServiceMetaInfo> getServiceMetaInfo() {
        return serviceMetaInfoList;
    }
}
