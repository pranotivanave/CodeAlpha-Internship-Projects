package com.example.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
	public class WeatherService {

	   

	private final String API_KEY = "c878b80e222d1a8def724e408bce0b6a";  
    private final String URL = "https://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}";

    public String getWeatherInfo(String city) {
        RestTemplate restTemplate = new RestTemplate();

       
        Map<String, String> params = new HashMap<>();
        params.put("city", city);
        params.put("apiKey", API_KEY);

      
        String response = restTemplate.getForObject(URL, String.class, params);

       
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);

          
            String weatherDescription = root.path("weather").get(0).path("description").asText();
            double temperature = root.path("main").path("temp").asDouble() - 273.15; 
            String cityName = root.path("name").asText();
            String country = root.path("sys").path("country").asText();

            return String.format("Weather in %s, %s: %s, Temperature: %.2f°C", cityName, country, weatherDescription, temperature);
        } catch (Exception e) {
            return "Error processing weather data: " + e.getMessage();
        }
    }

}