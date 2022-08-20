package com.peoplesbank.BankingApp.service;

import java.util.List;



import com.peoplesbank.BankingApp.beans.Accounts;
import com.peoplesbank.BankingApp.exceptions.AccountsException;

public interface AccountService {
	
	List<Accounts> getAllAccounts() throws AccountsException;
	Accounts  addAccount(Accounts ac) throws AccountsException;
	Accounts getAccount(int accId) throws AccountsException;
	Accounts deposit(int accId, double amt) throws AccountsException;
	Accounts withdraw(int accId, double amt) throws AccountsException;
	Accounts fundTransfer(int accId1, int accId2, double amt) throws AccountsException;
	
}
