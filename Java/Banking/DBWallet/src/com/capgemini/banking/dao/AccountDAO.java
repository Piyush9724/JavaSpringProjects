package com.capgemini.banking.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.capgemini.banking.bean.Account;
import com.capgemini.banking.exceptions.AccountNotFoundException;
import com.capgemini.banking.exceptions.InsufficientBalanceException;

public interface AccountDAO {
	
	public int createAccount(Account account);
	
	public  boolean deposit(int accNo, double amount) throws AccountNotFoundException;
	
	public  boolean withdraw(int accNo, double amount) throws InsufficientBalanceException, AccountNotFoundException;
	
	public double getBalance(int accNo) throws AccountNotFoundException;
	
	public boolean fundTransfer(int accFrom,int accTo, double amount) throws AccountNotFoundException, InsufficientBalanceException;
	
	public ResultSet showTransaction(int accNo) throws AccountNotFoundException;
	
	
	public void saveMap();
	
	public void readMap();
	
}
