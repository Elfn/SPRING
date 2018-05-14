package com.restclient.demo.rest_template_client.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Elimane on May, 2018, at 03:33
 */
@Configuration
public class RestTemplateConfig {

    //Spring's central class for synchronous client-side HTTP access.
    // It simplifies communication with HTTP servers,
    // and enforces RESTful principles. It handles HTTP connections,
    // leaving application code to provide URLs (with possible template variables)
    // and extract results
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){

        return builder.build();
    }
}
