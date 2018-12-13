package com.interestgroup.config;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfiguration {
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }

}