package com.mq.camel.demo;

import org.apache.camel.CamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
				
		// Start the Spring Boot application
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        
        // Get the Camel context from the Spring application context
        CamelContext camelContext = context.getBean(CamelContext.class);

        // Verify if Camel context is initialized
        if (camelContext != null) {
            System.out.println("Camel context initialized successfully.");
        } else {
            System.out.println("Failed to initialize Camel context.");
        }
}     
}
