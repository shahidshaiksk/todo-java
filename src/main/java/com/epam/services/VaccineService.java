package com.epam.services;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.dao.VaccineDao;
import com.epam.models.Vaccine;
import com.epam.exceptions.VaccineException;
import com.epam.utility.Validator;
import com.epam.vaccination.VaccinationApplication;

@Component
public class VaccineService {
	private static final Logger LOGGER = LogManager.getLogger(VaccineService.class);
	@Autowired
	private VaccineDao vaccineDao;
	
	public void showVaccines() {
		List<Vaccine> vaccines = vaccineDao.getVaccinesList();
		if (vaccines.isEmpty()) {
			LOGGER.info("No vaccines yet.");
		}
		vaccines.forEach(vaccine -> LOGGER.info(
				String.valueOf(vaccines.indexOf(vaccine)+1)
				.concat("\t")
				.concat(String.valueOf(vaccine))
				));
	}
	
	public void showVaccinesDetails() {
		List<Vaccine> vaccines = vaccineDao.getVaccinesList();
		if (vaccines.isEmpty()) {
			LOGGER.info("No vaccines yet.");
		}
		else {
			LOGGER.info("Name\tInitialCount\tBalance\tReceivedDate");
			vaccines.forEach(vaccine -> LOGGER.info(
					vaccine.getName()
					.concat("\t")
					.concat(String.valueOf(vaccine.getInitialCount()))
					.concat("\t")
					.concat(String.valueOf(vaccine.getBalance()))
					.concat("\t")
					.concat(String.valueOf(vaccine.getReceivedDate()))
					));
		}
	}
	
	public List<Vaccine> getVaccinesList(){
		return vaccineDao.getVaccinesList();
	}
	
	public void insert(String name, int initialCount) {
		if(vaccineDao.getVaccines().containsKey(name)) {
			LOGGER.info("Vaccine already exists");
			Vaccine vaccine = vaccineDao.getVaccines().get(name);
			LOGGER.info("Adding givent count to existing vaccine");
			addQuantity(vaccine, initialCount);
		}
		else {
			Vaccine vaccine = getVaccine();
			vaccine.setName(name);
			vaccine.setInitialCount(initialCount);
			vaccine.setBalance(initialCount);
			vaccine.setReceivedDate(LocalDate.now());
			insert(vaccine);
		}
	}
	
	private void insert(Vaccine vaccine) {
		vaccineDao.insert(vaccine);
	}
	
	public void addQuantity(Vaccine vaccine, int count) {
		int newCount = vaccine.getBalance() + count;
		vaccine.setBalance(newCount);
		vaccine.setInitialCount(newCount);
		vaccine.setReceivedDate(LocalDate.now());
	}
	
	protected static void reduceQuantity(Vaccine vaccine) throws VaccineException{
		if (Validator.checkVaccineStockNotEmpty(vaccine)) {
			vaccine.setBalance(vaccine.getBalance() - 1);
		}
		else {
			throw new VaccineException("Vaccine Stock Empty!");
		}
	}
	
	private Vaccine getVaccine() {
		return VaccinationApplication.getContext().getBean(Vaccine.class);
	}
	
}
