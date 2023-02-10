package com.spring.blog.entities;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class Location {

	private String name;
	private String city;
	private String date;
}
