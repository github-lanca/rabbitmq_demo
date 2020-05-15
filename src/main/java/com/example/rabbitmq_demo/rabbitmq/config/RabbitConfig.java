package com.example.rabbitmq_demo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author meihongliang
 * @since 2020/5/11 14:50
 */
@Configuration
public class RabbitConfig {
    /**
     * 交换机名称
     */
    public static final String TEST_EXCHANGE = "test_exchange";

    /**
     * 声明publish/subscribe类型交换机
     */
    public static final String TEST_FANOUT_EXCHANGE = "test_fanout_exchange";

    /**
     * 消息队列名称
     */
    public static final String TEST_QUEUE = "test_queue";

    /**
     * 声明publish/subscribe类型的队列1
     */
    public static final String TEST_FANOUT_QUEUE1 = "test_fanout_queue1";

    /**
     * 声明publish/subscribe类型的队列2
     */
    public static final String TEST_FANOUT_QUEUE2 = "test_fanout_queue2";

    /**
     * 路由key名称
     */
    public static final String TEST_ROUTING_KEY = "test_routing_key";

    /**
     * 声明主题交换机
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TEST_EXCHANGE);
    }


    /**
     * 声明发布订阅模式的交换机
     *
     * @return FanoutExchange
     */
    @Bean
    public FanoutExchange declarePublishSubscribeExchange() {
        return new FanoutExchange(TEST_FANOUT_EXCHANGE);
    }

    /**
     * 声明发布订阅模式的队列1
     *
     * @return Queue
     */
    @Bean
    public Queue fanoutQueue1() {
        return new Queue(TEST_FANOUT_QUEUE1);
    }

    /**
     * 声明发布订阅模式的队列1
     *
     * @return Queue
     */
    @Bean
    public Queue fanoutQueue2() {
        return new Queue(TEST_FANOUT_QUEUE2);
    }

    /**
     * 绑定队列1
     *
     * @return
     */
    @Bean
    public Binding bindingFanoutQueue1() {
        return BindingBuilder.bind(fanoutQueue1()).to(declarePublishSubscribeExchange());
    }

    /**
     * 绑定队列2
     *
     * @return
     */
    @Bean
    public Binding bindingFanoutQueue2() {
        return BindingBuilder.bind(fanoutQueue2()).to(declarePublishSubscribeExchange());
    }

    /**
     * 声明配置测试的消息队列
     */
    @Bean
    public Queue testQueue() {
        return new org.springframework.amqp.core.Queue(TEST_QUEUE);
    }

    /**
     * 绑定路由
     * 将queue这个队列绑定到topExchange交换机上,通过路由TEST_ROUTING_KEY来绑定
     */
    @Bean
    public Binding bindingRoutingKey() {
        // to FanoutExchange HeadersExchange TopicExchange DirectExchange Exchange
        // 将队列和交换机通过路由key绑定起来
        return BindingBuilder.bind(testQueue()).to(exchange()).with(TEST_ROUTING_KEY);
    }
}
