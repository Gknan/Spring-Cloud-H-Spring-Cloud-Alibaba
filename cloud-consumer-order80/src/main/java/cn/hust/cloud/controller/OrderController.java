package cn.hust.cloud.controller;

import cn.hust.cloud.entities.CommonResult;
import cn.hust.cloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    // 服务之间的调用，最原始 HTTPClient Resttemplate

//    public static final String PAYMENT_URL = "http://localhost:8001"; // 写死多个服务实例不能均衡

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource // 注入并实例化
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
}
