package cn.hust.cloud.service.impl;

import cn.hust.cloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

//@Service
@EnableBinding(Source.class) // 定义消息的推送管道 /这个注解给我们绑定消息通道的，Source是Stream给我们提供的，可以点进去看源码，可以看到output和input,这和配置文件中的output，input对应的。
@Slf4j
public class MessageProvider implements IMessageProvider {

    @Resource
    private Source source; // 消息发送管道

    @Override
    public String send() {

        String rerial = UUID.randomUUID().toString();
        source.output().send(MessageBuilder.withPayload(rerial).build());
        log.info("********** serial: {}", rerial);
        return null;
    }
}
