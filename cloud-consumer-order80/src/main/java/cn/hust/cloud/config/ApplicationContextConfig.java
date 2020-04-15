package cn.hust.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 注入 RestTemplate 的配置类
 *
 */
@Configuration
public class ApplicationContextConfig {

    // applicationContext.xml <bean id="" class="">
    @Bean("myRestTempalte")
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
