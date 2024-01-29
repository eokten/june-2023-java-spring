package com.okten.springdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DemoConfiguration {

    @Primary
    @Bean
    public String testProductName1() {
        return "product1";
    }

    @Bean
    public String testProductName2() {
        return "product2";
    }
}
