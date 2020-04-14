package cn.hust.cloud.service.impl;

import cn.hust.cloud.dao.PaymentDao;
import cn.hust.cloud.entities.Payment;
import cn.hust.cloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <h1>Payment Service 实现类</h1>
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource // java 自带的依赖注入的 autowire 是spring 的
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
