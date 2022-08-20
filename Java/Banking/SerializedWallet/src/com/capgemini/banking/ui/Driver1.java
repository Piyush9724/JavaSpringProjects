package com.capgemini.banking.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import com.capgemini.banking.bean.Account;
import com.capgemini.banking.exceptions.AccountNotFoundException;
import com.capgemini.banking.exceptions.InsufficientBalanceException;
import com.capgemini.banking.service.AccountServiceImpl;

public class Driver1 implements Serializable {
	public static void main(String[] args) {
		AccountServiceImpl asi = new AccountServiceImpl();
		int ch=9;
		Scanner sc = new Scanner(System.in);
		while (ch != 0) {
			System.out.println("Welcome to Payement Wallet");
			System.out.println("=======Choose from the Below Menu=======");
			System.out.println("1. Create a new Bank Account");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Show Balance");
			System.out.println("5. Fund Transfer");
			System.out.println("6. Print Transactions");
			System.out.println("7. Exit");
			ch = sc.nextInt();
			switch(ch) {
			case 1:
				System.out.println("Enter Your Name and Openeing Balance");
				String name = sc.next();
				int accNo = asi.createAccount(new Account(name,sc.nextDouble()));
				System.out.println("Successfully Created Account with :" + name + "  Account Number is :"+ accNo );
				break;
			case 2:
				System.out.println("Enter the account number and Amount :");
				try{
					if(asi.deposit(sc.nextInt(), sc.nextDouble())){
						System.out.println("Deposit Successfull");
					}
				}
				catch(AccountNotFoundException e){
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Enter the account number and Amount :");
				try{
					if(asi.withdraw(sc.nextInt(), sc.nextDouble())){
						System.out.println("Withdraw Successfull");
					}
				}
				catch(AccountNotFoundException e){
					System.out.println(e.getMessage());
				}
				catch(InsufficientBalanceException e){
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				
				System.out.println("Enter the account number");
				try{
					System.out.println(asi.getBalance(sc.nextInt()));
				}
				catch(AccountNotFoundException e){
					System.out.println(e.getMessage());
				}
				break;
			case 5: 
				System.out.println("Enter your Account Number and Account Number to be credited and Amount :");
				try{
					if(asi.fundTransfer(sc.nextInt(), sc.nextInt(), sc.nextDouble())){
						System.out.println("Fund Transfer SuccessFull");
					}
				}
				catch(AccountNotFoundException e){
					System.out.println(e.getMessage());
				}
				catch(InsufficientBalanceException e){
					System.out.println(e.getMessage());
				}
				break;
			case 6:
				System.out.println("Enter the Account number");
				try{
					ArrayList<String> ar = asi.showTransaction(sc.nextInt());
					
					for(String str : ar) {
						System.out.println(str);
					}
				}
				catch(AccountNotFoundException e){
					System.out.println(e.getMessage());
				}
				break;
			case 7 :
				asi.saveMap();
				System.exit(0);
			default:
					System.out.println("Please enter Valid Choice");
					break;
			}
		}
	}
}

