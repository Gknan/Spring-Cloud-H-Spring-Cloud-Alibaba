package cn.hust.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer // 开启配置中心服务端
public class AppConfigCenterMain3344 {

    public static void main(String[] args) {
        SpringApplication.run(AppConfigCenterMain3344.class, args);
    }
}
