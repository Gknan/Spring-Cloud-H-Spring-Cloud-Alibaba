package cn.hust.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    /**
     * 正常访问，正确的方法
     *
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池: " +
                Thread.currentThread().getName()
                + " paymentInfo_OK, id" + id;
    }

    // 超过 3 s 降级；主启动类激活该注解
    @HystrixCommand(fallbackMethod = "paymentInfo_timoutHandler", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_timout(Integer id) {
        int timeNum = 5;
//        try {
//            TimeUnit.SECONDS.sleep(timeNum);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        int ret = 10 / 0; // 异常兜底
        return "线程池: " +
                Thread.currentThread().getName()
                + " paymentInfo_timout, id" + id
                + " 耗时 " + timeNum + " s";
    }

    public String paymentInfo_timoutHandler(Integer id) {

        return "线程池: " +
                Thread.currentThread().getName()
                + " paymentInfo_timoutHandler, id" + id
                + " 服务提供方 8001 兜底方法";
    }
}
