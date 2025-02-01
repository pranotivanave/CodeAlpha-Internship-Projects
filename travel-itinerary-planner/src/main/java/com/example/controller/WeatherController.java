package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.WeatherService;

@RestController
	public class WeatherController {

	 private final WeatherService weatherService;

	    public WeatherController(WeatherService weatherService) {
	        this.weatherService = weatherService;
	    }

	    @GetMapping("/weather")
	    public String getWeather(@RequestParam String city) {
	        return weatherService.getWeatherInfo(city);
	    }
	}
