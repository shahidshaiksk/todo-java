package com.epam.todo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.todo.dto.Task;

@Configuration
public class Config {
	@Bean
	public List<Task> getTasks(){
		List<Task> tasks = new ArrayList<>();
		tasks.clear();
		return tasks;
	}
}
