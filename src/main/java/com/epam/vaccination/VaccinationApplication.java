package com.epam.vaccination;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.epam.ui.Menu;

@ComponentScan(basePackages = {
		"com.epam.dao",
		"com.epam.models",
		"com.epam.services",
		"com.epam.ui",
		"com.epam.utility",
		})
@Configuration
public class VaccinationApplication {
	private static final ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(VaccinationApplication.class);
	public static void main(String[] args) {
		Menu menu = context.getBean(Menu.class);
		menu.menu();
		context.close();
	}
	public static ConfigurableApplicationContext getContext() {
		return context;
	}
}
