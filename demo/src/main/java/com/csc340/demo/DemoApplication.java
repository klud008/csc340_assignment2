package com.csc340.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello, World!";
	}

	/**
	 * Gets a weather report on a specified query (currently should be North Carolina, but sometimes it's North Berwick instead)
	 * Uses the weatherstack API.
	 * @return a short weather report
	 */
	@GetMapping("/weather")
	public Object weather() {
		try {
			String url = "https://api.weatherstack.com/current?access_key=877241e46fbeedf4d9a4e544af376a72&query=North%20Carolina";
			RestTemplate restTemplate = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();

			String jsonListResponse = restTemplate.getForObject(url, String.class);
			JsonNode root = mapper.readTree(jsonListResponse);

			//The response from the above API is a JSON Array, which we loop through.
			for (JsonNode rt : root) {
				//Extract relevant info from the response and use it for what you want, in this case just print to the console.
				System.out.println(rt);
			}

			// Extracts region name and weather conditions for use in WeatherReport object.
			String query = ( root.at("/location").get("name").asText() );
			String time = ( root.at("/current").get("observation_time").asText() );
			String weather = "";
			for (JsonNode rt : root.at("/current").at("/weather_descriptions") ) {
				weather += (rt);
			}

			WeatherReport weatherReport = new WeatherReport(query, time, weather);

			System.out.println(weatherReport.toString());

			return weatherReport;

		} catch (JsonProcessingException ex) {
			Logger.getLogger(DemoApplication.class.getName()).log(Level.SEVERE,
					null, ex);
			return "error in /weather";
		}

	}

}

