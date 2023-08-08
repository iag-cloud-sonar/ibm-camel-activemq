package com.mq.camel.demo.config;

import javax.jms.JMSException;

import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import com.ibm.mq.jms.MQConnectionFactory;

@Configuration
public class IbmMqConfig {

    @Value("${ibmmq.host}")
    private String host;

    @Value("${ibmmq.port}")
    private int port;

    @Value("${ibmmq.queue-manager}")
    private String queueManager;

    @Value("${ibmmq.channel}")
    private String channel;
    
    @Value("${ibmmq.inbound-queue}")
	 private String ibmInboundQueue;

	/*
	 * @Value("${ibm.mq.username}") private String username;
	 * 
	 * @Value("${ibm.mq.password}") private String password;
	 */

    // Create a bean for the MQ connection factory
    @Bean
    public MQConnectionFactory mqConnectionFactory() throws JMSException {
        MQConnectionFactory connectionFactory = new MQConnectionFactory();
        connectionFactory.setHostName(host);
        connectionFactory.setPort(port);
        connectionFactory.setQueueManager(queueManager);
        connectionFactory.setChannel(channel);
        connectionFactory.setTransportType(1);
		/*
		 * connectionFactory.setUser(username); connectionFactory.setPassword(password);
		 */
        return connectionFactory;
    }

    // Create a bean for the JmsComponent
    @Bean(name = "ibmmq")
    public JmsComponent ibmMq() throws JMSException {
        JmsComponent component = new JmsComponent();
        component.setConnectionFactory(mqConnectionFactory());
        component.setDestinationResolver(new DynamicDestinationResolver());
        return component;
    }  
}
