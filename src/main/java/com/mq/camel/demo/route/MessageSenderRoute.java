package com.mq.camel.demo.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessageSenderRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:sendMessages?period=20000") // Sends a message every 5 seconds
            .setBody(constant("Hello from sender!"))
            .to("ibmmq:TEST_CAMEL_SRC") // Replace with your IBM MQ queue
            .log("Sent message: ${body}");
    }
}
