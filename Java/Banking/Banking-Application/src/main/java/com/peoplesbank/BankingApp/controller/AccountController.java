package com.peoplesbank.BankingApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peoplesbank.BankingApp.beans.Accounts;
import com.peoplesbank.BankingApp.exceptions.AccountsException;
import com.peoplesbank.BankingApp.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	private AccountService accService;
	
	@RequestMapping("/accounts")
	public List<Accounts> getAllAccounts() throws AccountsException {
		return accService.getAllAccounts();
	}
	
	@PostMapping("/accounts/add")
	public Accounts addAccount(@Valid @RequestBody Accounts ac) throws AccountsException {
		return accService.addAccount(ac);
	}
	
	@GetMapping("/accounts/{accId}")
	public Accounts getAccountById(@PathVariable int accId) throws AccountsException {
		return accService.getAccount(accId);
	}

	@PutMapping("/accounts/dep/{accId}/{amt}")
	public Accounts deposit(@PathVariable int accId, @PathVariable double amt) throws AccountsException {
		return accService.deposit(accId, amt);
	}
	
	@PutMapping("/accounts/wid/{accId}/{amt}")
	public Accounts withdraw(@PathVariable int accId, @PathVariable double amt) throws AccountsException {
		return accService.withdraw(accId, amt);
	}
	
	@PutMapping("/accounts/fundtf/{accId1}/{accId2}/{amt}")
	public Accounts fundTransfer(@PathVariable int accId1,@PathVariable int accId2,@PathVariable double amt) throws AccountsException {
		return accService.fundTransfer(accId1, accId2, amt);
	}
	
	
}
