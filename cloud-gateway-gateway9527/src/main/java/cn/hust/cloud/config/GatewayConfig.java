package cn.hust.cloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    /**
     * 配置了一个id 为 route_name 的路由规则
     * 当访问 localhost:9527/guonei 时会自动转发到地址： https://news.baidu.com/guonei
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRuoteLocater(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        // https://news.baidu.com/guonei
        routes.route("path_route_hust1", // id
                r -> r.path("/guonei") // predicator
                .uri("https://news.baidu.com/guonei")).build(); // uri 转发地址

        return routes.build();
    }
}
