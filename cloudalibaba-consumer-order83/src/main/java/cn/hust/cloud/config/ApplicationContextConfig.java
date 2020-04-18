package cn.hust.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced // 用 RestTemplate 结合 Ribbon 负载均衡，一定要加这个注解
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
