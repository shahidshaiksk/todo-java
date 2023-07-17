package models;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Scope(value = "prototype")
public class Vaccine {
	@Value("COVID")
	private String name;
	private LocalDate receivedDate;
	@Value("0")
	private int initialCount;
	@Value("0")
	private int balance;
	
	public Vaccine() {
		super();
	}
	public Vaccine(String name, LocalDate receivedDate, int initialCount, int balance) {
		super();
		this.name = name;
		this.receivedDate = receivedDate;
		this.initialCount = initialCount;
		this.balance = balance;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(LocalDate receivedDate) {
		this.receivedDate = receivedDate;
	}
	public int getInitialCount() {
		return initialCount;
	}
	public void setInitialCount(int initialCount) {
		this.initialCount = initialCount;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vaccine other = (Vaccine) obj;
		return Objects.equals(name, other.name);
	}
	@Override
	public String toString() {
		return name;
	}
	
}
