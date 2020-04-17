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
@EnableBinding(Source.class) // 定义消息的推送管道
@Slf4j
public class MessageProvider implements IMessageProvider {

    @Resource
    private MessageChannel output; // 消息发送管道

    @Override
    public String send() {

        String rerial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(rerial).build());
        log.info("********** serial: {}", rerial);
        return null;
    }
}
