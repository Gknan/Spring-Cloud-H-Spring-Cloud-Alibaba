package cn.hust.cloud.controller;

import cn.hust.cloud.entities.CommonResult;
import cn.hust.cloud.entities.Payment;
import cn.hust.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int ret = paymentService.create(payment);
        log.info("插入结果：{}", ret);

        if (ret > 0) {
            return new CommonResult(200, "插入数据库成功", ret);
        } else {
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")/**/
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果：{}", payment);

        if (payment != null) {
            return new CommonResult(200, "查询成功", payment);
        } else {
            return new CommonResult(444, "没有记录，查询 ID 为" + id, null);
        }
    }
}
