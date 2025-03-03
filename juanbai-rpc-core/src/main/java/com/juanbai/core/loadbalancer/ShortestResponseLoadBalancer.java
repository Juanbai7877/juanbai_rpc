package com.juanbai.core.loadbalancer;

import com.juanbai.core.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;

/**
 * @author ALL
 * @date 2025/3/3
 * @Description
 */
public class ShortestResponseLoadBalancer {
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList)
    {
        if(serviceMetaInfoList.size()==0){
            return null;
        }
        ServiceMetaInfo serviceMetaInfoResult = serviceMetaInfoList.getFirst();
        for (ServiceMetaInfo serviceMetaInfo : serviceMetaInfoList) {
            if(serviceMetaInfo.getCallTimes()/serviceMetaInfo.getServiceCallList().size()<serviceMetaInfoResult.getCallTimes()/serviceMetaInfo.getServiceCallList().size()){
                serviceMetaInfoResult = serviceMetaInfo;
            }
        }
        return serviceMetaInfoResult;
    }
}
