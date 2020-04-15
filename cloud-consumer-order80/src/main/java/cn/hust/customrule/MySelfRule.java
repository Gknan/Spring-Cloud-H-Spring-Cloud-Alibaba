package cn.hust.customrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h1>Ribbon 负载均衡策略替换</h1>
 */
//@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule() {
        return new RandomRule();// 定义为随机策略
    }
}
