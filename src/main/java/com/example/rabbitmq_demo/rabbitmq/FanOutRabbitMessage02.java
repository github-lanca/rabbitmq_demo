package com.example.rabbitmq_demo.rabbitmq;

import com.example.rabbitmq_demo.rabbitmq.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author meihongliang
 * @since 2020/5/11 14:50
 * RabbitListener(queues = RabbitConfig.TEST_QUEUE) 监听哪一个队列
 */
@Component
@RabbitListener(queues = RabbitConfig.TEST_FANOUT_QUEUE2)
public class FanOutRabbitMessage02 {
    /**
     * 处理消息队列中的消息(即消费方consume)
     */
    @RabbitHandler
    public void testHandlerMethod(String object) {
        System.out.println("发布订阅监听队列2: " + object);
    }
}
