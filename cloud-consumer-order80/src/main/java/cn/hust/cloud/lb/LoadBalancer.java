package cn.hust.cloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * <h1>自定义负载均衡接口</h1>
 */
public interface LoadBalancer {

    ServiceInstance intances(List<ServiceInstance> serviceInstances);
}
