package com.juanbai.core.fault.tolerant;

import cn.hutool.core.collection.CollUtil;
import com.juanbai.core.RpcApplication;
import com.juanbai.core.config.RpcConfig;
import com.juanbai.core.constant.RpcConstant;
import com.juanbai.core.fault.retry.RetryStrategy;
import com.juanbai.core.fault.retry.RetryStrategyFactory;
import com.juanbai.core.loadbalancer.LoadBalancer;
import com.juanbai.core.loadbalancer.LoadBalancerFactory;
import com.juanbai.core.model.RpcRequest;
import com.juanbai.core.model.RpcResponse;
import com.juanbai.core.model.ServiceMetaInfo;
import com.juanbai.core.registry.Registry;
import com.juanbai.core.registry.RegistryFactory;
import com.juanbai.core.server.tcp.VertxTcpClient;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
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
        ServiceMetaInfo selectedServiceMetaInfo = (ServiceMetaInfo)context.get("selectedServiceMetaInfo");
        RpcRequest rpcRequest = (RpcRequest)context.get("rpcRequest");
        // 从服务发现中获取所有可用的服务实例
        List<ServiceMetaInfo> serviceMetaInfoList = (List<ServiceMetaInfo>) context.get("serviceMetaInfoList");
        int idx=0;
        for(ServiceMetaInfo serviceMetaInfo : serviceMetaInfoList){
            if(serviceMetaInfo.getServiceNodeKey().equals(selectedServiceMetaInfo.getServiceNodeKey())){
                serviceMetaInfoList.remove(idx);
                break;
            }
            idx++;
        }
        if(serviceMetaInfoList.isEmpty()){
            throw new RuntimeException("服务报错", e);
        }

        RpcConfig rpcConfig = RpcApplication.getRpcConfig();

        LoadBalancer loadBalancer = LoadBalancerFactory.getInstance(rpcConfig.getLoadBalancer());

        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("methodName", rpcRequest.getMethodName());
        ServiceMetaInfo newSelectedServiceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);

        // rpc 请求
        // 使用重试机制
        RpcResponse rpcResponse;
        try {
            RetryStrategy retryStrategy = RetryStrategyFactory.getInstance(rpcConfig.getRetryStrategy());
            rpcResponse = retryStrategy.doRetry(() ->
                    VertxTcpClient.doRequest(rpcRequest, newSelectedServiceMetaInfo)
            );
        } catch (Exception err) {
            // 容错机制
            TolerantStrategy tolerantStrategy = TolerantStrategyFactory.getInstance(rpcConfig.getTolerantStrategy());
            HashMap<String, Object> tolerantContent = new HashMap<>();
            tolerantContent.put("selectedServiceMetaInfo", selectedServiceMetaInfo);
            tolerantContent.put("rpcRequest", rpcRequest);
            tolerantContent.put("serviceMetaInfoList", serviceMetaInfoList);
            rpcResponse = tolerantStrategy.doTolerant(null, e);
        }
        return rpcResponse;
    }
}
