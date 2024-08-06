package com.weatherApp.weather.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherstackConfig {

    @Value("${weatherstack.api.key}")
    private String apiKey;

    @Value("${weatherstack.api.baseurl}")
    private String baseUrl;

    public String getApiKey() {
        return apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
