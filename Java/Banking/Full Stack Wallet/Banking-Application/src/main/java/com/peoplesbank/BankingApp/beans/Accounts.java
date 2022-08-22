package com.peoplesbank.BankingApp.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
public class Accounts {

	@Id
	@SequenceGenerator(name = "accIdGen", sequenceName = "accId", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(generator = "accIdGen", strategy = GenerationType.SEQUENCE)
	private int accId;
	
	@NotEmpty(message = "Name is Empty")
	@Pattern(regexp = "[A-Z][A-Za-z]{2,}", message="First Letter should be Capital and must contain only letters")
	private String name;
	
	@Min(500)
	private double openingBal;
	
	private double currentBal;
	
	
	private long mobileNo;
	private String dob;
	
	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	@OneToMany
	private List<Transactions> transactions = new ArrayList<Transactions>();
	
	public List<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transactions> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "Accounts [id=" + accId + ", name=" + name + ", openingBal=" + openingBal + ", currentBal=" + currentBal
				+ "]";
	}

	public int getAccId() {
		return accId;
	}

	public void setAccId(int id) {
		this.accId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getOpeningBal() {
		return openingBal;
	}

	public void setOpeningBal(double openingBal) {
		this.openingBal = openingBal;
		this.currentBal = openingBal;
		
	}

	public double getCurrentBal() {
		return currentBal;
	}

	public void setCurrentBal(double currentBal) {
		this.currentBal = currentBal;
	}

}
