package com.capgemini.banking.bean;

import java.io.Serializable;

public  class Account implements Serializable{

	protected static int accGen = 1000;
	
	

	protected int accountNo;
	protected String name; 
	protected double crBalance, openingBal;

	public Account(String name, Double openingBal) {
		this.name = name;
		accGen++;
		accountNo = accGen;
		this.openingBal = openingBal;
		this.crBalance = openingBal;
	}


	
	public static int getAccGen() {
		return accGen;
	}


	public static void setAccGen(int accGen) {
		Account.accGen = accGen;
	}
	
	
	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCrBalance() {
		return crBalance;
	}

	public void setCrBalance(double crBalance) {
		this.crBalance = crBalance;
	}

	public double getOpeningBal() {
		return openingBal;
	}

	public void setOpeningBal(double openingBal) {
		this.openingBal = openingBal;
	}

	@Override
	public String toString() {
		return "Account [accGen=" + accGen + ", accountNo=" + accountNo + ", name=" + name + ", crBalance=" + crBalance
				+ ", openingBal=" + openingBal + "]";
	}

	

	
}
