package cn.hust.cloud.service;

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

    public String paymentInfo_timout(Integer id) {
        int timeNum = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池: " +
                Thread.currentThread().getName()
                + " paymentInfo_timout, id" + id
                + " 耗时 " + timeNum + " s";
    }
}
