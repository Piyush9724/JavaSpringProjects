package com.capgemini.banking.service;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.capgemini.banking.bean.Account;
import com.capgemini.banking.dao.AccountDAO;
import com.capgemini.banking.dao.AccountDAOImpl;
import com.capgemini.banking.exceptions.AccountNotFoundException;
import com.capgemini.banking.exceptions.InsufficientBalanceException;

public class AccountServiceImpl implements AccountService {

	AccountDAO accountDAO;
	
	public AccountServiceImpl() {
		super();
		 accountDAO = new AccountDAOImpl();
	}

	@Override
	public int createAccount(Account account) {
		return accountDAO.createAccount(account);
	}

	@Override
	public boolean deposit(int accNo, double amount) throws AccountNotFoundException {
		accountDAO.deposit(accNo, amount);
		return true;
	}

	@Override
	public boolean withdraw(int accNo, double amount) throws InsufficientBalanceException, AccountNotFoundException{
		accountDAO.withdraw(accNo,amount);
		return true;
	}

	@Override
	public double getBalance(int accNo) throws AccountNotFoundException {
		return accountDAO.getBalance(accNo);
	}

	@Override
	public boolean fundTransfer(int accFrom, int accTo, double amount) throws AccountNotFoundException, InsufficientBalanceException {
		accountDAO.fundTransfer(accFrom, accTo, amount);
		return true;
	}

	@Override
	public ResultSet showTransaction(int accNo)throws AccountNotFoundException {
		return (accountDAO.showTransaction(accNo));
		
	}
	
	@Override
	public void saveMap() {
		accountDAO.saveMap();
	}
	
	@Override
	public void readMap(){
		accountDAO.readMap();
	}

	
}
