package cn.hust.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * <h1>主启动类</h1>
 */
@EnableEurekaClient // 开启 Eureka Client
@SpringBootApplication
@EnableDiscoveryClient //
public class PaymentMain8001 {
    public static void main(String[] args) {

        SpringApplication.run(PaymentMain8001.class, args);
    }
}
