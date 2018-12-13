package com.interestgroup.config;

import com.netflix.loadbalancer.*;
import org.springframework.context.annotation.Bean;

public class UserSerivceConfiguration {
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }

}