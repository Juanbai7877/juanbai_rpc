package com.juanbai.core.loadbalancer;

/**
 * 负载均衡器键名常量
 *
 */
public interface LoadBalancerKeys {

    /**
     * 轮询
     */
    String ROUND_ROBIN = "roundRobin";

    String RANDOM = "random";

    String CONSISTENT_HASH = "consistentHash";

    String LEAST_ACTIVE = "leastActive";

    String SHORTEST_RESPONSE = "shortestResponse";

}
