package com.mq.camel.demo.config;

import javax.jms.ConnectionFactory;

import org.apache.camel.component.jms.JmsComponent;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.support.destination.DynamicDestinationResolver;


@Configuration
public class AwsMqConfig {

    @Value("${amazonmq.broker-url}")
    private String amazonMqBrokerUrl;

    @Value("${amazonmq.user}")
    private String amazonMqUser;

    @Value("${amazonmq.password}")
    private String amazonMqPassword;

    @Value("${amazonmq.queue-name}")
    private String amazonMqQueueName;

    // Define the Amazon MQ ConnectionFactory bean
    @Bean
    public ConnectionFactory amazonMqConnectionFactory() {
        ConnectionFactory connectionFactory = new org.apache.activemq.ActiveMQConnectionFactory(amazonMqBrokerUrl);
        ((org.apache.activemq.ActiveMQConnectionFactory) connectionFactory).setUserName(amazonMqUser);
        ((org.apache.activemq.ActiveMQConnectionFactory) connectionFactory).setPassword(amazonMqPassword);
        return new CachingConnectionFactory(connectionFactory);
    }


    // Define the Amazon MQ JmsComponent bean
    @Bean(name = "awsmq")
    public ActiveMQComponent activeMQComponent() {
    	ActiveMQComponent component = new ActiveMQComponent ();
        component.setConnectionFactory(amazonMqConnectionFactory());
        component.setBrokerURL(amazonMqBrokerUrl);
        component.setDestinationResolver(new DynamicDestinationResolver());
        return component;
    }
}
