package com.epam.dao;

import java.util.List;
import java.util.Map;

import com.epam.models.Vaccine;

public interface VaccineDao {
	
	public Map<String, Vaccine> getVaccines();
	
	public void insert(Vaccine vaccine);

	public List<Vaccine> getVaccinesList();
}
