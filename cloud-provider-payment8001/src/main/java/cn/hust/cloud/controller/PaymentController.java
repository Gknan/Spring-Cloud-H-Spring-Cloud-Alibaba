package cn.hust.cloud.controller;

import cn.hust.cloud.entities.CommonResult;
import cn.hust.cloud.entities.Payment;
import cn.hust.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    // 注入 DiscoryClient
    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}") // 读取配置文件中的 server.port
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int ret = paymentService.create(payment);
        log.info("插入结果：{}", ret);

        if (ret > 0) {
            return new CommonResult(200, "插入数据库成功，serverPort: " + serverPort, ret);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")/**/
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果：{}", payment);

        if (payment != null) {
            return new CommonResult(200, "查询成功，serverPort: " + serverPort, payment);
        } else {
            return new CommonResult(444, "没有记录，查询 ID 为" + id, null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("********service name: {}", service);
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                log.info("***** instance: host {}, port {}, serviceId {}, uri {}",instance.getHost(), instance.getPort(), instance.getServiceId(), instance.getUri());
            }
        }

        return discoveryClient;
    }

    // 帮助自定义负载均衡，返回当前服务实例的服务端口号
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

    /* 模拟 服务提供方超时调用 */
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return serverPort;
    }
}
