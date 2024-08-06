package com.weatherApp.weather.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;
import com.weatherApp.weather.service.*;
@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam("city") String city, Model model) {
        Mono<String> weatherMono = weatherService.getWeather(city);
        weatherMono.subscribe(weather -> model.addAttribute("weather", weather),
                throwable -> model.addAttribute("error", "Error fetching weather data"));

        model.addAttribute("city", city);
        return "weather";
    }
}