package com.juanbai.core.model;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.util.LinkedList;

/**
 * 服务元信息（注册信息）
 */
@Data
public class ServiceMetaInfo {


    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务版本号
     */
    private String serviceVersion = "1.0";

    /**
     * 服务域名
     */
    private String serviceHost;

    /**
     * 服务端口号
     */
    private Integer servicePort;

    /**
     * 服务分组（暂未实现）
     */
    private String serviceGroup = "default";

    /**
     * 服务调用次数
     */
    private int serviceCallCount = 0 ;

    /**
     * 服务失败次数
     */
    private int serviceFailedCount = 0;

    /**
     * 服务近期调用费时列表
     */
    private LinkedList<long[]> serviceCallList = new LinkedList<>();

    /**
     * 服务近期总调用费时
     */
    private Long callTimes = 0L;

    /**
     * 增加调用费事记录
     *
     * @return
     */
    public Boolean  addServiceCall(long[] times) {
        long l = System.currentTimeMillis();
        for(long[] x :serviceCallList) {
            if(l-x[1]>1000*30L) {
                callTimes -= x[1]-x[0];
                serviceCallList.removeFirst();
            }
        }
        serviceCallList.addLast(times);
        callTimes += times[1]-times[0];
        return true;
    }

    /**
     * 增加调用次数
     *
     * @return
     */
    public Boolean  addServiceCallCount() {
        serviceCallCount++;
        return true;
    }

    /**
     * 增加调用失败次数
     *
     * @return
     */
    public Boolean  addServiceFailedCount() {
        serviceFailedCount++;
        return true;
    }

    /**
     * 获取服务键名
     *
     * @return
     */
    public String getServiceKey() {
        // 后续可扩展服务分组
        // return String.format("%s:%s:%s", serviceName, serviceVersion, serviceGroup);
        return String.format("%s:%s", serviceName, serviceVersion);
    }

    /**
     * 获取服务注册节点键名
     *
     * @return
     */
    public String getServiceNodeKey() {
        return String.format("%s/%s:%s", getServiceKey(), serviceHost, servicePort);
    }

    /**
     * 获取完整服务地址
     *
     * @return
     */
    public String getServiceAddress() {
        if (!StrUtil.contains(serviceHost, "http")) {
            return String.format("http://%s:%s", serviceHost, servicePort);
        }
        return String.format("%s:%s", serviceHost, servicePort);
    }


}
