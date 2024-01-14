package com.bizcolab.bizcolab.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient() {

        return WebClient
            .builder()
            .baseUrl("https://api.monday.com/v2")
            .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
            .defaultHeader("API-version", "2023-10")
            .build();
    }
}
