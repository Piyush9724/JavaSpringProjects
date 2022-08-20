package com.peoplesbank.BankingApp.service;

import java.sql.Timestamp;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peoplesbank.BankingApp.beans.Accounts;
import com.peoplesbank.BankingApp.beans.Transactions;
import com.peoplesbank.BankingApp.dao.AccountRepository;
import com.peoplesbank.BankingApp.dao.TransactionRepository;
import com.peoplesbank.BankingApp.exceptions.AccountsException;

@Service
public class AccountServiceImplementation implements AccountService{

	@Autowired
	private AccountRepository accRepo;
	
	@Autowired
	private TransactionRepository tranRepo;

	@Override
	public List<Accounts> getAllAccounts() throws AccountsException {
		try {
			return accRepo.findAll();
		} catch (Exception e) {
			throw new AccountsException(e.getMessage());
		}
	}
	
	
	@Override
	public Accounts addAccount(Accounts ac) throws AccountsException {
		if(accRepo.existsById(ac.getAccId())) {
			throw new AccountsException("Account with id+ "+ac.getAccId()+" already Exists");
			
		}
		else
		{
			accRepo.save(ac);
			Transactions tr= new Transactions();
			tr.setAccId(ac.getAccId());
			tr.setAmount(ac.getCurrentBal());
			tr.setDescription("Account Created!");
			tr.setTranType("Create");
			tr.setTranDate(new Timestamp(System.currentTimeMillis()));
			ac.getTransactions().add(tr);
			tranRepo.save(tr);
			return getAccount(ac.getAccId());
		}
	}
	

	@Override
	public Accounts getAccount(int accId) throws AccountsException {
		if(!accRepo.existsById(accId)) {
			throw new AccountsException("Account with id "+ accId+" does not exists");
		}
		else {
			return accRepo.findById(accId).get();
		}
	}


	@Override
	public Accounts deposit(int accId, double amt) throws AccountsException {
		if(!accRepo.existsById(accId)) {
			throw new AccountsException("Account with account Id "+ accId +" does not exists");
		}
		else {
			Accounts ac = getAccount(accId);
			ac.setCurrentBal(ac.getCurrentBal()+amt);
			Transactions tr = new Transactions();
			tr.setAccId(ac.getAccId());
			tr.setAmount(ac.getCurrentBal());
			tr.setDescription("Account Credited");
			tr.setTranType("CR");
			tr.setTranDate(new Timestamp(System.currentTimeMillis()));
			ac.getTransactions().add(tr);
			tranRepo.save(tr);
			accRepo.save(ac);
			return getAccount(ac.getAccId());
		}
	}


	@Override
	public Accounts withdraw(int accId, double amt) throws AccountsException {
		if(!accRepo.existsById(accId)) {
			throw new AccountsException("Account with account Id "+ accId +" does not exists");
		}
		else {
			Accounts ac = getAccount(accId);
			if(ac.getCurrentBal()<amt) {
				throw new AccountsException("Insufficient Balance");
			}
			else {
			ac.setCurrentBal(ac.getCurrentBal()-amt);
			Transactions tr = new Transactions();
			tr.setAccId(ac.getAccId());
			tr.setAmount(ac.getCurrentBal());
			tr.setDescription("Account Debited");
			tr.setTranType("DB");
			tr.setTranDate(new Timestamp(System.currentTimeMillis()));
			ac.getTransactions().add(tr);
			tranRepo.save(tr);
			accRepo.save(ac);
			return getAccount(ac.getAccId());
			}
		}
	}


	@Override
	public Accounts fundTransfer(int accId1, int accId2, double amt) throws AccountsException {
		withdraw(accId1, amt);
		deposit(accId2, amt);
		return getAccount(accId1);
	}





	
	
	
}
