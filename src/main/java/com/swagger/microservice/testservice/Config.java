package com.swagger.microservice.testservice;

import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import io.swagger.client.api.PetApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.swagger.microservice.testservice.*"})
public class Config {

    private String basePath = "https://petstore.swagger.io/v2";

    @Bean(name = "petApi")
    public PetApi getPetApi() {
        final PetApi api = Feign
                .builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logLevel(Logger.Level.HEADERS)
                .options(new Request.Options(1000, 2000))
                .target(PetApi.class, basePath);
        return api;
    }


}
