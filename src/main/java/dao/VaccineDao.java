package dao;

import java.util.List;
import java.util.Map;

import models.Vaccine;

public interface VaccineDao {
	
	public Map<String, Vaccine> getVaccines();
	
	public void insert(Vaccine vaccine);

	public List<Vaccine> getVaccinesList();
}
