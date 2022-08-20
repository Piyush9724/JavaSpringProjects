package com.capgemini.banking.ui;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.capgemini.banking.bean.Account;
import com.capgemini.banking.exceptions.AccountNotFoundException;
import com.capgemini.banking.exceptions.InsufficientBalanceException;
import com.capgemini.banking.service.AccountServiceImpl;

/*
DB WALLET TABLES
 CREATE TABLE account (accountId INTEGER PRIMARY KEY auto_increment, accountName VARCHAR(100), openingBal DOUBLE(6,2), currentBal DOUBLE(6,2));
INSERT INTO account(accountName,openingBal,currentBal) VALUES("Piyush",1000.0,1000.0);

CREATE TABLE transaction (transactionId INTEGER PRIMARY KEY auto_increment ,accountId INTEGER,tran_date DATE ,description VARCHAR(200),tran_type VARCHAR(100) ,amount DOUBLE(6,2));
INSERT INTO transaction(transactionId,accountId,tran_date,description,tran_type,amount) VALUES(TRANNO.nextVal,?,?,?,?,?)
 */
public class Driver {
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
				System.out.println("Enter Your Name");
				String name = sc.next();
				System.out.println(" and Openeing Balance");
				double bal = sc.nextDouble();
				asi.createAccount(new Account(name,bal));
				break;
			case 2:
				System.out.println("Enter the account number and Amount :");
				int accno = sc.nextInt();
				double amt = sc.nextDouble();
				try {
					if(asi.deposit(accno, amt)) {
						System.out.println("Suucessful");
					}
				} catch (AccountNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				System.out.println("Enter the account number and Amount :");
				accno = sc.nextInt();
				amt = sc.nextDouble();
				try {
					if(asi.withdraw(accno, amt)) {
						System.out.println("Successfull");
					}
				} catch (AccountNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (InsufficientBalanceException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.println("Enter the account number");
				accno=sc.nextInt();
				try {
					System.out.println(asi.getBalance(accno));
				} catch (AccountNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5: 
				System.out.println("Enter your Account Number and Account Number to be credited and Amount :");
				int accFrom=sc.nextInt();
				int accTo = sc.nextInt();
				amt= sc.nextDouble();
				try {
					if(asi.fundTransfer(accFrom, accTo, amt)) {
						System.out.println("Successful");
					}
					break;

				} catch (AccountNotFoundException e) {
					System.out.println(e.getMessage());
					
				} catch (InsufficientBalanceException e) {
					System.out.println(e.getMessage());
				}
			case 6:
				System.out.println("Enter the Account number");
				accno=sc.nextInt();
				try {
					ResultSet s = asi.showTransaction(accno);
					while(s.next()){
						System.out.println(s.getInt(1)+"\t"+s.getInt(2)+"\t"+s.getTimestamp(3)+"\t"+s.getString(4)+"\t"+s.getString(5)+"\t"+s.getDouble(6));
					}
				} catch (AccountNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 7 :
				System.exit(0);
			default:
					System.out.println("Please enter Valid Choice");
					break;
			}
		}
		sc.close();
	}
}

