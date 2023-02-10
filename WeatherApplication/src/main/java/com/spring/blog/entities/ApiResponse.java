package com.spring.blog.entities;

import lombok.Data;

@Data
public class ApiResponse {

	private Location location;
	private Current current;
}
