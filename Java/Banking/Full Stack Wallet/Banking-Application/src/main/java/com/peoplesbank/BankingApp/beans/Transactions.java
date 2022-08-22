package com.peoplesbank.BankingApp.beans;



import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Transactions {
	
	@Id
	@SequenceGenerator(name = "tranIdGen", sequenceName = "tranId", initialValue = 100000, allocationSize = 1)
	@GeneratedValue(generator = "tranIdGen", strategy = GenerationType.SEQUENCE)
	private int tranId;
	
	private int accId;
	
	@Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp tranDate;
	
	private String description;
	
	private String tranType;
	
	private double amount;

	public int getTranId() {
		return tranId;
	}

	public void setTranId(int tranId) {
		this.tranId = tranId;
	}

	public int getAccId() {
		return accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	

	public Timestamp getTranDate() {
		return tranDate;
	}

	public void setTranDate(Timestamp tranDate) {
		this.tranDate = tranDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
