package com.epam.utility;

import java.time.LocalDate;

import com.epam.models.Vaccine;

public class Validator {
	
	public static boolean checkDifference(LocalDate date1, LocalDate date2) {
		int m1 = date1.getYear() * 12 + date1.getMonthValue();
	    int m2 = date2.getYear() * 12 + date2.getMonthValue();
	    return (m1 - m2 + 1) >= 6;
	}
	
	public static boolean validateName(String name) {
		String valid = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";
		for(int index = 0; index < name.length(); index++) {
			if(!valid.contains(String.valueOf(name.charAt(index))))
				return false;
		}
		return true;
	}
	
	public static boolean validateAge(int age) {
		return age >= 18 && age <= 70;
	}
	
	public static boolean checkVaccineStockNotEmpty(Vaccine vaccine) {
		return vaccine.getBalance() > 0;
	}
	
	public static boolean validateProofID(long proofID) {
		return String.valueOf(proofID).length() == 12;
	}
}
