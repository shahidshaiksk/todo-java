package com.epam.utility;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InputUtility {
	private static final Logger LOGGER = LogManager.getLogger(InputUtility.class);
	@Autowired
	private Scanner SCAN;
	
	public int getIntegerInput() throws NullPointerException{
		Integer result = 0;
		try {
			result = SCAN.nextInt();
		}
		catch(InputMismatchException ime) {
			LOGGER.warn("Please give input in integers");
		}
		return result;
	}
	
	public long getLongInput() throws NullPointerException{
		Long result = (long) 0;
		try {
			result = SCAN.nextLong();
		}
		catch(InputMismatchException ime) {
			LOGGER.warn("Please give input in numbers");
		}
		return result;
	}
	
	public String getStringInput() {
		return SCAN.nextLine(); 
	}
	
	public void clearBuffer() {
		SCAN.nextLine();
	}
}
