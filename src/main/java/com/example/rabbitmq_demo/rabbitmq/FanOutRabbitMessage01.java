package com.example.rabbitmq_demo.rabbitmq;

import com.example.rabbitmq_demo.rabbitmq.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author meihongliang
 * @since 2020/5/11 14:50
 * RabbitListener(queues = RabbitConfig.TEST_QUEUE) 监听哪一个队列
 */
@Component
@RabbitListener(queues = RabbitConfig.TEST_FANOUT_QUEUE1)
public class FanOutRabbitMessage01 {
    /**
     * 导入消息队列操作对象
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 处理消息队列中的消息(即消费方consume)
     */
    @RabbitHandler
    public void testHandlerMethod(String object) {
        Object o = rabbitTemplate.receiveAndConvert(RabbitConfig.TEST_FANOUT_QUEUE1);
        System.out.println("rabbitTemplate.receiveAndConvert() " + o);
        System.out.println("发布订阅监听队列1: " + object);
    }
}
