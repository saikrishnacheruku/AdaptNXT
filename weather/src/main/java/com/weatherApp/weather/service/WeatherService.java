package com.weatherApp.weather.service;

import com.weatherApp.weather.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    @Autowired
    private WeatherstackConfig weatherstackConfig;

    private final WebClient webClient;

    public WeatherService() {
        this.webClient = WebClient.builder().build();
    }

    public Mono<String> getWeather(String city) {
        String url = String.format("%s/current?access_key=%s&query=%s", weatherstackConfig.getBaseUrl(), weatherstackConfig.getApiKey(), city);

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(WebClientResponseException.class, e -> Mono.error(new RuntimeException("Error fetching weather data")));
    }
}