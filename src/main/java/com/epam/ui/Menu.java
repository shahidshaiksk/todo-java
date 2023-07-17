package com.epam.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.models.User;
import com.epam.models.Vaccine;
import com.epam.exceptions.UserException;
import com.epam.exceptions.VaccineException;
import com.epam.services.UserService;
import com.epam.services.VaccineService;
import com.epam.utility.InputUtility;


@Component
public class Menu {
	private static final Logger LOGGER = LogManager.getLogger(Menu.class);
	int choice;
	@Autowired
	VaccineService vaccineService;
	@Autowired
	UserService userService;
	@Autowired
	InputUtility inputUtility;
	public void menu() {
		LOGGER.info("Welcome!");
		LOGGER.info("\n1. Admin\n2. Customer\n3.Exit");
		LOGGER.info("\nEnter your choice:");
		choice  = inputUtility.getIntegerInput();
		switch (choice) {
		case 1: {
			adminMenu();
			break;
		}
		case 2: {
			customerMenu();
			break;
		}
		case 3: {
			exit();
			return;
		}
		default: {
			LOGGER.warn("Enter correct values");
		}
		inputUtility.clearBuffer();
		}
		menu();
	}
	private void adminMenu() {
		LOGGER.info("\nWelcome Admin!");
		LOGGER.info("\n1. Show Vaccine Details\n2. Show Persons\n3. Add Vaccine\n4. Add Vaccine Inventory\n5. Logout");
		choice = inputUtility.getIntegerInput();
		switch(choice) {
		case 1:{
			vaccineService.showVaccinesDetails();
			break;
		}
		case 2:{
			userService.showUsersDetails();
			break;
		}
		case 3:{
			addVaccineUI();
			break;
		}
		case 4:{
			addVaccineInventoryUI();
			break;
		}
		case 5:{
			logout();
			return;
		}
		default:{
			LOGGER.warn("Enter correct values");
		}
		inputUtility.clearBuffer();
		}
		adminMenu();
		
	}
	
	private void customerMenu() {
		LOGGER.info("\nWelcome!");
		LOGGER.info("\n1. Login\n2. Create Account\n3. Main Menu");
		choice = inputUtility.getIntegerInput();
		switch(choice) {
		case 1:{
			loginUI();
			break;
		}
		case 2:{
			signupUI();
			break;
		}
		case 3:{
			return;
		}
		default:{
			LOGGER.warn("Enter correct values");
		}
		inputUtility.clearBuffer();
		}
		customerMenu();
	}
	
	private void loggedCustomerMenu(User user) {
		LOGGER.info("\nWelcome {} !",user.getName());
		LOGGER.info("1.Select Dosage\n2.Logout");
		choice = inputUtility.getIntegerInput();
		switch(choice) {
		case 1:{
			selectDosageUI(user);
			break;
		}
		case 2:{
			logout();
			return;
		}
		default:{
			LOGGER.warn("Enter correct values");
		}
		inputUtility.clearBuffer();
		}
		loggedCustomerMenu(user);
	}
	
	private void signupUI() {
		LOGGER.info("Enter Details: ");
		LOGGER.info("Enter Name: ");
		inputUtility.clearBuffer();
		String name = inputUtility.getStringInput();
		LOGGER.info("Enter Age: ");
		int age = inputUtility.getIntegerInput();
		LOGGER.info("Enter Aadhar number: ");
		long proofID = inputUtility.getLongInput();
		userService.insert(name, age, proofID);
	}
	private void loginUI() {
		LOGGER.info("Enter Aadhar number: ");
		long proofID = inputUtility.getLongInput();
		User user = userService.login(proofID);
		if(user!=null) {
			loggedCustomerMenu(user);
		}
		else {
			LOGGER.warn("User not found");
		}
	}
	
	private void selectDosageUI(User user) {
		if(user.getFirstDoseDate()==null) {
			LOGGER.info("This is your first vaccination");
			LOGGER.info("Choose your vaccine:");
			vaccineService.showVaccines();
			LOGGER.info("Enter Choice:");
			choice = inputUtility.getIntegerInput();
			try {
				Vaccine vaccine = vaccineService.getVaccinesList().get(--choice);
				userService.selectFirstDosage(user, vaccine);
			}
			catch(VaccineException | IndexOutOfBoundsException e) {
				LOGGER.warn("Incorrect choice");
			}
		}
		else if(user.getSecondDoseDate()==null) {
			LOGGER.info("This is your second vaccination");
			try {
				userService.selectSecondDosage(user);
			} catch (UserException | VaccineException e) {
				LOGGER.warn(e.getMessage());
			}
		}
		else {
			LOGGER.info("You are completely vaccinated already");
		}
	}
	
	private void addVaccineInventoryUI() {
		vaccineService.showVaccines();
		LOGGER.info("Enter Choice:");
		choice = inputUtility.getIntegerInput();
		try {
			Vaccine vaccine = vaccineService.getVaccinesList().get(--choice);
			LOGGER.info("Enter Count:");
			int count = Math.abs(inputUtility.getIntegerInput());
			vaccineService.addQuantity(vaccine, count);
		}
		catch(IndexOutOfBoundsException e) {
			LOGGER.warn("Incorrect choice");
		}
	}
	
	private void addVaccineUI() {
		LOGGER.info("Enter Vaccine Details");
		inputUtility.clearBuffer();
		LOGGER.info("Name :");
		String name = inputUtility.getStringInput();
		LOGGER.info("Count :");
		int count = Math.abs(inputUtility.getIntegerInput());
		vaccineService.insert(name, count);
	}
	
	private void logout() {
		LOGGER.info("Logging Out..");
	}
	
	private void exit() {
		LOGGER.info("Exiting..");
	}
}
