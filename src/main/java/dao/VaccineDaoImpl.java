package dao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.Vaccine;

@Component
public class VaccineDaoImpl implements VaccineDao{
	@Autowired
	private Map<String, Vaccine> vaccines;
	@Override
	public Map<String, Vaccine> getVaccines() {
		return vaccines;
	}

	@Override
	public void insert(Vaccine vaccine) {
		vaccines.put(vaccine.getName(), vaccine);
		
	}

	@Override
	public List<Vaccine> getVaccinesList() {
		return vaccines.values().stream().collect(Collectors.toList());
	}
}
