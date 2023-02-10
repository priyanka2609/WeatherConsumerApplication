package com.spring.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.blog.entities.CurrentLocation;
import com.spring.blog.repositories.WeatherRepo;

@Service
public class CurrentLocationServiceImpl implements CurrentLocationService{

		@Autowired
		WeatherRepo repo;
		
	@Override
	public CurrentLocation saveCurrentLoc(CurrentLocation currentLocation) {
		return repo.save(currentLocation);
	}

	@Override
	public CurrentLocation getTempByCity(String city) {
		return repo.findTempByCity(city);
	}

	@Override
	public CurrentLocation getTempByCityAndDate(String city, String date) {
		return repo.findTempByCityAndDate(city, date);
	}

	@Override
	public List<CurrentLocation> getReport() {
		return this.repo.findAll();
		
	}

	
}
