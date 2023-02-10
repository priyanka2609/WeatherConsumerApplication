package com.spring.blog.service;

import java.util.List;

import com.spring.blog.entities.CurrentLocation;

public interface CurrentLocationService {

	CurrentLocation saveCurrentLoc(CurrentLocation currentLocation);
	
	CurrentLocation getTempByCity(String city);
	
	CurrentLocation getTempByCityAndDate(String city, String date);
	
	List<CurrentLocation> getReport();
}
