package cn.hust.cloud.controller;

import cn.hust.cloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod") // 默认的降级方法 核心的业务专属 fallback 可以自己定义
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {

        return paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfoTimeotFallbackMethod", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    }) // 消费者客户端兜底策略
    @HystrixCommand //不注明，使用 默认的全局 fallback
    public String paymentInfo_timout(@PathVariable("id") Integer id) {
        int cnt = 10 / 0;
        return paymentHystrixService.paymentInfo_timout(id);
    }

    public String paymentInfoTimeotFallbackMethod(@PathVariable("id") Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " 80 调用 8001 兜底策略返回";
    }

    // 全局 fallback 方法
    public String paymentGlobalFallbackMethod() {
        return "Global 异常处理信息，请稍后重试。";
    }

}
