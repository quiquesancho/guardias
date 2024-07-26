package com.edu.quique.controllers.config;

import com.edu.quique.sse.impl.broker.SseEmiterBroker;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class SseConfig {

    @Bean
    public SseEmiterBroker getSseEmiterBroker() {
        return new SseEmiterBroker();
    }
}
