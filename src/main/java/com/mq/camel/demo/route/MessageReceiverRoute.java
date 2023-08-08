package com.mq.camel.demo.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiverRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("ibmmq:queue:TEST_CAMEL_SRC") // Replace with your IBM MQ queue
            .log("Received message: ${body}")
            .to("activemq:queue:cargostack-wcsd") // Replace with your ActiveMQ queue
            .log("Forwarded message to ActiveMQ");
    }
}
