package com.popcornmate.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClientConfig {

    @Value("${tmdb.api.base-url}")
    String baseUrl;

//    @Bean
//    public WebClient tmdbWebClient(){
//        return WebClient
//    }
}
