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
@RabbitListener(queues = RabbitConfig.TEST_QUEUE)
public class HandlerRabbitMessage02 {
    /**
     * 处理消息队列中的消息(即消费方consume)
     */
    @RabbitHandler
    public void testHandlerMethod(String object) {
        System.out.println("消费者二: " + object);
    }
}
