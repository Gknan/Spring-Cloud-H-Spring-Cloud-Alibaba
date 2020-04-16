package cn.hust.cloud;

import java.time.ZonedDateTime;

/**
 * AfterPredicate 时间生成
 */
public class AfterTest {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        // 2020-04-16T12:40:44.339+08:00[Asia/Shanghai]
        System.out.println(now);
    }
}
