package cn.hust.cloud.controller;

import cn.hust.cloud.entities.CommonResult;
import cn.hust.cloud.entities.Payment;
import cn.hust.cloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
    // 服务之间的调用，最原始 HTTPClient Resttemplate

//    public static final String PAYMENT_URL = "http://localhost:8001"; // 写死多个服务实例不能均衡

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

//    @Resource // 注入并实例化
    @Autowired // 按照类型注入
    @Qualifier(value = "myRestTempalte") // 辅助 Autowired ，合起来就是按照类型 + 名称注入
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create") // 浏览器只能发 get 请求
    public CommonResult<Payment> create(Payment payment) {
        // rul  请求参数  响应类型
        return restTemplate.postForObject(PAYMENT_URL +
                "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id,
                CommonResult.class);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> retEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/"
                + id, CommonResult.class);

        if (retEntity.getStatusCode().is2xxSuccessful()) {
            log.info("template.getForEntity 调用成功");
            return retEntity.getBody();
        } else {
            return new CommonResult<>(444, "操作失败");
        }
    }

    @GetMapping("/consumer/payment/create2") // 浏览器只能发 get 请求
    public CommonResult<Payment> create2(Payment payment) {
        // rul  请求参数  响应类型
        ResponseEntity<CommonResult> responseEntity = restTemplate.postForEntity(PAYMENT_URL +
                "/payment/create", payment, CommonResult.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            log.info("template.postForEntity 调用成功");
            return responseEntity.getBody();
        } else {
            return new CommonResult<>(444, "创建失败");
        }
    }

    // 测试手写的 RandomLoadBalancer
    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }

        ServiceInstance serviceInstance = loadBalancer.intances(instances);
        return restTemplate.getForObject(serviceInstance.getUri() + "/payment/lb", String.class);
    }

    // Sleuth 链路追踪
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }

        ServiceInstance serviceInstance = loadBalancer.intances(instances);
        return restTemplate.getForObject(serviceInstance.getUri() + "/payment/zipkin", String.class);
//        String result = restTemplate.getForObject("http://localhost:8001/payment/zipkin", String.class);
//        return result;
    }
}
