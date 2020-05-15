package com.example.rabbitmq_demo.controller;

import com.example.rabbitmq_demo.rabbitmq.config.RabbitConfig;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author meihongliang
 * @since 2020/5/11 14:38
 */
@RestController
public class RabbitMQTestController {
    /**
     * 导入消息队列操作对象
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 消息队列测试,测试发送
     *
     * @return string
     */
    @ApiOperation("TopExchange/路由模式")
    @GetMapping("/test1")
    public String test(@ApiParam(value = "发送的具体消息") @RequestParam("object") String object) {
        // #参数1.exchange 交换机
        // #参数2.routingKey 路由key
        // #参数3.object 发送的具体消息
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertSendAndReceive(RabbitConfig.TEST_EXCHANGE, RabbitConfig.TEST_ROUTING_KEY, object + i);
        }
        return "消息队列已经发送,收到与否不清楚,到队列里面也不清楚";
    }

    /**
     * 消息队列测试,测试发送
     *
     * @return string
     */
    @ApiOperation("fanout/publishsubscribe模式")
    @GetMapping("/test2")
    public String test2(@ApiParam(value = "发送的具体消息") @RequestParam("object") String object) {
        // #参数1.exchange 交换机
        // #参数2.routingKey 路由key
        // #参数3.object 发送的具体消息
        for (int i = 0; i < 5; i++) {

            // rabbitTemplate.convertAndSend(RabbitConfig.TEST_FANOUT_EXCHANGE, null, object + i);
            Object o = rabbitTemplate.convertSendAndReceive(RabbitConfig.TEST_FANOUT_EXCHANGE, null, object + i);
            System.out.println("convertSendAndReceive()方法的返回值: " + o);

        }
        return "消息队列已经发送,收到与否不清楚,到队列里面也不清楚";
    }
}
