package cn.hust.cloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * <h1>自定义负载均衡接口</h1>
 */
public interface LoadBalancer {

    /**
     * 传入服务实例列表，根据轮询返回一个服务实例
     * @param serviceInstances
     * @return
     */
    ServiceInstance intances(List<ServiceInstance> serviceInstances);
}
