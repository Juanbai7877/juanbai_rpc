package com.juanbai.core.loadbalancer;

import com.juanbai.core.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;

/**
 * @author ALL
 * @date 2025/3/3
 * @Description
 */
public class LeastActiveLoadBalancer {

    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList)
    {
        if(serviceMetaInfoList.size()==0){
            return null;
        }
        ServiceMetaInfo serviceMetaInfoResult = serviceMetaInfoList.get(0);
        for(ServiceMetaInfo serviceMetaInfo:serviceMetaInfoList){
            if(serviceMetaInfo.getServiceFailedCount()/serviceMetaInfo.getServiceCallCount()<
                    serviceMetaInfoResult.getServiceFailedCount()/serviceMetaInfoResult.getServiceCallCount()){
                serviceMetaInfoResult=serviceMetaInfo;
            }
        }
        return serviceMetaInfoResult;
    }

}
