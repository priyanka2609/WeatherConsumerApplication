package com.spring.blog.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.blog.entities.CurrentLocation;
import com.spring.blog.service.CurrentLocationService;

@RestController
public class WeatherController {
	
	private static final Logger LOGGER = LogManager.getLogger(WeatherController.class);
	
	@Autowired
	CurrentLocationService currentLocationService;
	
	@PostMapping("/save")
	public CurrentLocation saveData(@RequestBody CurrentLocation currentLocation) {
		LOGGER.info("The saved data.....");
		return this.currentLocationService.saveCurrentLoc(currentLocation);
	}
	
	@GetMapping("/{city}")
	public CurrentLocation getTempByCity(@PathVariable String city) {
		LOGGER.info("The data when city is passed...");
		return this.currentLocationService.getTempByCity(city);	
	}
	
	@GetMapping("/temperature")
	public CurrentLocation getTempByCityAndDate(@RequestParam(value = "city") String city,
			                                    @RequestParam(value = "date") String date) {
		return this.currentLocationService.getTempByCityAndDate(city, date);
	}
	
	@GetMapping("/getAll")
	public  List<CurrentLocation> getReport() {
		return this.currentLocationService.getReport();
	}
	
	

}
