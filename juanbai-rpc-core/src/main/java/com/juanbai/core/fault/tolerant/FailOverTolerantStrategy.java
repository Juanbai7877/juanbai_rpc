package com.juanbai.core.fault.tolerant;

import cn.hutool.core.collection.CollUtil;
import com.juanbai.core.RpcApplication;
import com.juanbai.core.config.RpcConfig;
import com.juanbai.core.constant.RpcConstant;
import com.juanbai.core.model.RpcResponse;
import com.juanbai.core.model.ServiceMetaInfo;
import com.juanbai.core.registry.Registry;
import com.juanbai.core.registry.RegistryFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * 转移到其他服务节点 - 容错策略
 *
 */
import java.util.Random;


@Slf4j
public class FailOverTolerantStrategy implements TolerantStrategy {


    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        return null;
//        log.error("Service call failed with exception: {}", e.getMessage(), e);
//
//        // 获取当前请求的信息，比如方法名、参数等
//        String methodName = (String) context.get("methodName");
//        Object[] args = (Object[]) context.get("args");
//        // 从注册中心获取服务提供者请求地址
//        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
//        Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
//        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
//        serviceMetaInfo.setServiceName(serviceName);
//        serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
//        List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
//        if (CollUtil.isEmpty(serviceMetaInfoList)) {
//            throw new RuntimeException("暂无服务地址");
//        }
//
//        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
//        Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
//        // 从服务发现中获取所有可用的服务实例
//        List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
//        if (instances == null || instances.isEmpty()) {
//            log.warn("No available service instances found.");
//            return null; // 或者返回一个默认的错误响应
//        }
//
//        // 排除当前已经尝试过的实例（假设context中有记录）
//        String currentInstanceId = (String) context.get("currentInstanceId");
//        instances.removeIf(instance -> instance.getId().equals(currentInstanceId));
//
//        // 随机选择一个新的实例进行重试
//        if (!instances.isEmpty()) {
//            Random random = new Random();
//            ServiceInstance nextInstance = instances.get(random.nextInt(instances.size()));
//
//            try {
//                log.info("Retrying on instance: {}", nextInstance.getId());
//                // 构建新的上下文，包括新选择的实例ID
//                context.put("currentInstanceId", nextInstance.getId());
//
//                // 发起RPC请求
//                return rpcClient.invoke(nextInstance, methodName, args);
//            } catch (Exception retryException) {
//                log.error("Retry failed on instance: {}. Error: {}", nextInstance.getId(), retryException.getMessage(), retryException);
//                // 如果重试也失败了，可以选择继续重试其他实例或者直接返回错误
//                return doTolerant(context, retryException); // 这里可以选择递归重试，也可以设置最大重试次数
//            }
//        } else {
//            log.warn("No other service instances available for retry.");
//            return null; // 或者返回一个默认的错误响应
//        }
    }
}
