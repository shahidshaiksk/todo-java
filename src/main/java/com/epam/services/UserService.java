package com.epam.services;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.dao.UserDao;
import com.epam.models.User;
import com.epam.models.Vaccine;
import com.epam.exceptions.UserException;
import com.epam.exceptions.VaccineException;
import com.epam.utility.Validator;
import com.epam.vaccination.VaccinationApplication;

@Component
public class UserService {
	private static final Logger LOGGER = LogManager.getLogger(UserService.class);
	@Autowired
	private UserDao userDao;
	
	public void showUsers() {
		List<User> users  = userDao.getUsersList();
		if(users.isEmpty()) {
			LOGGER.info("No users yet.");
		}
		users.forEach(LOGGER::info);
	}
	
	public void showUsersDetails() {
		List<User> users  = userDao.getUsersList();
		if(users.isEmpty()) {
			LOGGER.info("No users yet.");
		}
		else {
			LOGGER.info("Name\tAge\tProofID\tFirstDoseDate\tSecondDoseDate\tVaccine");
			users.forEach(user -> LOGGER.info(
					user.getName()
					.concat("\t")
					.concat(String.valueOf(user.getAge()))
					.concat("\t")
					.concat(String.valueOf(user.getProofID()))
					.concat("\t")
					.concat(String.valueOf(user.getFirstDoseDate()))
					.concat("\t")
					.concat(String.valueOf(user.getSecondDoseDate()))
					.concat("\t")
					.concat(String.valueOf(user.getVaccine()))
					));
		}
	}
	
	public void insert(String name, int age, long proofID) {
		try {
			if(userDao.getUsers().containsKey(proofID)) {
				LOGGER.info("User already exists.");
			}
			else if(!Validator.validateName(name)) {
				throw new UserException("Name should have only characters");
			}
			else if(!Validator.validateAge(age)) {
				throw new UserException("Age should be in 18-70 range");
			}
			else if(!Validator.validateProofID(proofID)) {
				throw new UserException("ProofID must be 12 digits");
			}
			else {
				User user = getUser();
				user.setName(name);
				user.setAge(age);
				user.setProofID(proofID);
				insert(user);
			}
		}
		catch(UserException e) {
			LOGGER.warn(e.getMessage());
		}
	}
	
	private void insert(User user) {
		userDao.insert(user);
	}
	
	public User login(long proofID) {
		return userDao.getUsers().containsKey(proofID)?userDao.getUsers().get(proofID):null;
	}
	
	public void selectFirstDosage(User user, Vaccine vaccine) throws VaccineException{
		VaccineService.reduceQuantity(vaccine);
		user.setFirstDoseDate(LocalDate.now());
		user.setVaccine(vaccine);
	}
	
	public void selectSecondDosage(User user) throws UserException, VaccineException{
		if(Validator.checkDifference(user.getFirstDoseDate(), LocalDate.now())) {
			user.setSecondDoseDate(LocalDate.now());
			VaccineService.reduceQuantity(user.getVaccine());
		}
		else {
			LocalDate futureDate = user.getFirstDoseDate().plusMonths(6);
			throw new UserException("You are not yet eligible for second dosage of vaccination. Come after".concat(String.valueOf(futureDate)));
		}
	}
	
	private User getUser() {
		return VaccinationApplication.getContext().getBean(User.class);
	}
}
