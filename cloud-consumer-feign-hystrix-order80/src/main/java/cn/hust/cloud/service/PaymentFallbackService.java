package cn.hust.cloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService 兜底 For paymentInfo_OK";
    }

    @Override
    public String paymentInfo_timout(Integer id) {
        return "PaymentFallbackService 兜底 For paymentInfo_timout";
    }
}
