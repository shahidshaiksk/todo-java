package models;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class User {
	@Value("ABC")
	private String name;
	@Value("21")
	private int age;
	@Value("123123123123")
	private long proofID;// Aadhar number
	private LocalDate firstDoseDate;
	private LocalDate secondDoseDate;
	private Vaccine vaccine;
	
	public User() {
		super();
	}
	public User(String name, int age, long proofID) {
		super();
		this.name = name;
		this.age = age;
		this.proofID = proofID;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public long getProofID() {
		return proofID;
	}
	public void setProofID(long proofID) {
		this.proofID = proofID;
	}
	public LocalDate getFirstDoseDate() {
		return firstDoseDate;
	}
	public void setFirstDoseDate(LocalDate firstDoseDate) {
		this.firstDoseDate = firstDoseDate;
	}
	public LocalDate getSecondDoseDate() {
		return secondDoseDate;
	}
	public void setSecondDoseDate(LocalDate secondDoseDate) {
		this.secondDoseDate = secondDoseDate;
	}
	public Vaccine getVaccine() {
		return vaccine;
	}
	public void setVaccine(Vaccine vaccine) {
		this.vaccine = vaccine;
	}
	@Override
	public int hashCode() {
		return Objects.hash(age, name, proofID);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return age == other.age && Objects.equals(name, other.name) && proofID == other.proofID;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", proofID=" + proofID + "]";
	}
	
	
}
