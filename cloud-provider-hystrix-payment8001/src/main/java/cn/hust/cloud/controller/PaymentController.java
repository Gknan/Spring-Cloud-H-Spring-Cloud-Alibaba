package cn.hust.cloud.controller;

import cn.hust.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {

        String ret = paymentService.paymentInfo_OK(id);
        log.info("*****result:" + ret);
        return ret;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_timout(@PathVariable("id") Integer id) {

        String ret = paymentService.paymentInfo_timout(id);
        log.info("*****result:" + ret);
        return ret;
    }
}
