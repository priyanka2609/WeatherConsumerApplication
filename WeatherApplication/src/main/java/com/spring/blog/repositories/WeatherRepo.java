package com.spring.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.blog.entities.CurrentLocation;

public interface WeatherRepo extends JpaRepository<CurrentLocation, Integer>{

	public CurrentLocation findTempByCity(String city);
	public CurrentLocation findTempByCityAndDate(String city,String date);
}
