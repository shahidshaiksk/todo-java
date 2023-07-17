package com.epam.utility;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.epam.models.User;
import com.epam.models.Vaccine;

@Component
public class Config {
	
	@Bean
	public static Scanner getScanner() {
		return new Scanner(System.in);
	}
	
	@Bean
	public static Map<Long, User> getUsersMap(){
		return new HashMap<>();
	}
	
	@Bean
	public static Map<String, Vaccine> getVaccinesMap(){
		return new HashMap<>();
	}
}
