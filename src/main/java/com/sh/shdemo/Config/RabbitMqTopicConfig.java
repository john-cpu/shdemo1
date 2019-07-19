package com.sh.shdemo.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqTopicConfig {
    static final String message1 = "topic.message1";
    static final String message2 = "topic.message2";
    @Bean
    public Queue queueMessage1(){
        return new Queue(RabbitMqTopicConfig.message1);
    }
    @Bean
    public Queue queueMessage2(){
        return new Queue(RabbitMqTopicConfig.message2);
    }
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("exchange");
    }
    @Bean
    Binding bindingExchangeMessage1(Queue queueMessage1,TopicExchange exchange){
        return BindingBuilder.bind(queueMessage1).to(exchange).with("topic.message1");//后面.with是binding key
    }
    @Bean
    Binding bindingExchangeMessage2(Queue queueMessage2, TopicExchange exchange) {
        //这里的#表示零个或多个词。
        return BindingBuilder.bind(queueMessage2).to(exchange).with("topic.#");
    }

}
