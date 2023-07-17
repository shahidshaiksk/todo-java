package com.epam.vaccination;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

//import java.util.InputMismatchException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.models.User;
import com.epam.models.Vaccine;
import com.epam.services.UserService;
import com.epam.services.VaccineService;

class VaccinationTest {
	private final static Logger LOGGER = LogManager.getLogger(VaccinationTest.class);
	static UserService userService;
	static User person;
	static VaccineService vaccineService;
	static Vaccine vaccine;
	@BeforeAll
	static void setup() {
		userService = new UserService();
		person = null;
		vaccineService = new VaccineService();
		vaccine = null;
	}
	@Test
	void showTest() {
		assertNotNull(vaccineService.getVaccinesList());
	}
//	@Test
//	@Order(1)
//	@DisplayName("Adding Person")
//	void addPersonTest() {
//		try {
//			person = userService.signup();
//		}
//		catch (Exception e){
//			LOGGER.error(e.getMessage());
//		}
//		assertTrue(userService.getUsersList().contains(person), "Unable to enter data into the personDAO.");
//	}
//	@Test
//	@Order(2)
//	@DisplayName("Adding Vaccine")
//	void addVaccineTest() {
//		int initialCount = vaccineService.getVaccines().size();
//		vaccineService.insert();
//		int finalCount = vaccineService.getVaccines().size();
//		assertTrue(finalCount > initialCount, "Vaccine not added");
//	}
//	@Test
//	@Order(3)
//	@DisplayName("Adding Vaccine Inventory")
//	void addVaccineInventoryTest() {
//		vaccine = vaccineService.getVaccinesList().get(0);
//		int oldVaccineInventory = vaccine.getBalance();
//		vaccineService.addVaccineInventory(); // update the first vaccine
//		int newVaccineInventory = vaccine.getBalance();
//		assertTrue(newVaccineInventory >= oldVaccineInventory , "Vaccine Inventory not updated");
//	}
//	
//	@Test
//	@Order(4)
//	@DisplayName("Selecting first Dosage")
//	void selectFirstDosageTest() {
//		List<Vaccine> vaccines  = vaccineService.getVaccinesList();
//		User user = userService.signup();
//		userService.selectFirstDosage(vaccines);
//		assertNotNull(user.getFirstDoseDate(), "Failed to select 1st dosage");
//	}
//	@Test
//	@Order(5)
//	@DisplayName("Selecting second Dosage")
//	void selectSecondDosageTest() {
//		User user = userService.login();
//		userService.selectSecondDosage();
//		assertNotNull(user.getSecondDoseDate(), "Failed to select 2nd dosage");
//	}
}
