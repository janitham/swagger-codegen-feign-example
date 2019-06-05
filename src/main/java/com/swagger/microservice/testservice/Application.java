package com.swagger.microservice.testservice;

import io.swagger.client.api.PetApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        final PetApi api = (PetApi) context.getBean("petApi");
        api.findPetsByStatus(Arrays.asList("sold")).stream().forEach(System.out::println);

        ((ConfigurableApplicationContext) context).close();
    }

}
