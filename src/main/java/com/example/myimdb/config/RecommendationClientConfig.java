package com.example.myimdb.config;

import com.example.myimdb.utils.RecommendationClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RecommendationClientConfig {

    @Bean
    public RecommendationClient recommendationClient() {
        return new RecommendationClient();
    }
}
