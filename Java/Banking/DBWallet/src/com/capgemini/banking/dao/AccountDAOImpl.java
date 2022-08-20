package com.capgemini.banking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.capgemini.banking.bean.Account;
import com.capgemini.banking.exceptions.AccountNotFoundException;
import com.capgemini.banking.exceptions.InsufficientBalanceException;

public class AccountDAOImpl implements AccountDAO {

	int lastAccNo;
	Connection connection;

	public AccountDAOImpl() {
		try{  
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			  
			connection=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/Wallet", "root", "Piyush@Root");
			connection.setAutoCommit(false);
			 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	}

	
	private Connection getConnection() {
		return connection;
	}
	
	public void exCommit() {
		Statement stmt;
		try {
			stmt = getConnection().createStatement();
			stmt.executeQuery("commit");
		
		} catch (SQLException e) {
			e.printStackTrace();
		}  	
	}

	public void exCommitMySql(){
		try {
			getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public ResultSet exQuery(String str){
		Statement stmt;
		try {
			stmt = getConnection().createStatement();
			ResultSet rs=stmt.executeQuery(str); 
			stmt.executeQuery("commit");
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}  	
		return null;
	}
	
	
	private static Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new Timestamp(today.getTime());

	}
	
	public ResultSet exTQuery(String str1,String str2,String str3,String[] str4,Account account) {
		PreparedStatement stmt;
		try {
			stmt = getConnection().prepareStatement(str1, str4);
			stmt.setInt(1,account.getAccountNo());
			stmt.setTimestamp(2,getCurrentTimeStamp());
			stmt.setString(3,str2);
			stmt.setString(4, str3);
			stmt.setDouble(5,account.getCrBalance());
			stmt.executeUpdate();
			ResultSet set = stmt.getGeneratedKeys();
			//exCommit();
			exCommitMySql();
			return set;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	public ResultSet exTQuery(String str1,String str2,String str3,int accNo,double amount) {
		PreparedStatement stmt;
		try {
			stmt = getConnection().prepareStatement(str1, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1,accNo);
			stmt.setTimestamp(2,getCurrentTimeStamp());
			stmt.setString(3,str2);
			stmt.setString(4, str3);
			stmt.setDouble(5,amount);
			int i = stmt.executeUpdate();
			System.out.println(i);
			ResultSet set = stmt.getGeneratedKeys();
			//exCommit();
			exCommitMySql();
			return set;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ResultSet exAQuery(String str1,int accNo,double amount) {
		PreparedStatement stmt;
		try {
			//stmt = getConnection().prepareStatement(str1);
			stmt = getConnection().prepareStatement(str1, Statement.RETURN_GENERATED_KEYS);
			stmt.setDouble(1, amount);
			stmt.setInt(2, accNo);
			stmt.executeUpdate();
			ResultSet set = stmt.getGeneratedKeys();
			//exCommit();
			exCommitMySql();
			return set;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ResultSet exAQuery(String str1,int accNo){
		PreparedStatement stmt;
		try {
			stmt = getConnection().prepareStatement(str1);
			stmt.setInt(1, accNo);
			
			ResultSet set =stmt.executeQuery();
			exCommitMySql();
			//exCommit();
			return set;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	public double checkBalance(int accNo) throws AccountNotFoundException {
		PreparedStatement stmt;
		double amt=-1;
		//int id =0;
		String str1 = "SELECT currentbal FROM account WHERE accountId=?";
		try {
			stmt = getConnection().prepareStatement(str1);
			stmt.setInt(1, accNo);
			ResultSet set = stmt.executeQuery();
			while(set.next()){
				amt = set.getDouble(1);
			}
			//System.out.println(amt);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		if(amt==-1){
			throw new AccountNotFoundException("The Specified Account Could not be found");
		}
		return amt;
	}
	
	  

	@Override
	public int createAccount(Account account) {
		//For Oracle
		//String str = "INSERT INTO account(accountId,accountName,openingBal,currentBal) VALUES(ACCNO.nextval,?,?,?)";
		//String str1 = "INSERT INTO transaction(transactionId,accountId,tran_date,description,tran_type,amount) VALUES(TRANNO.nextVal,?,?,?,?,?)";
		String str = "INSERT INTO account(accountName,openingBal,currentBal) VALUES(?,?,?)";
		String str1 = "INSERT INTO transaction(accountId,tran_date,description,tran_type,amount) VALUES(?,?,?,?,?)";
		
		String[] str2 = {"transactionId","accountId","tran_date","description","tran_type","amount"};
		int acnum=0;
		try {
			PreparedStatement stmt = getConnection().prepareStatement(str, new String[] {"accountid"});
			stmt.setString(1, account.getName());
			stmt.setDouble(2,account.getOpeningBal());
			stmt.setDouble(3, account.getOpeningBal());
			stmt.executeUpdate();
			ResultSet set = stmt.getGeneratedKeys();
			while(set.next())
				acnum=set.getInt(1);
			System.out.println(acnum);
			account.setAccountNo(acnum);
			
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet set1 = exTQuery(str1,"ACCOUNT CREATED","CREATED",str2,account);
		return acnum;
	}

	@Override
	public boolean deposit(int accNo, double amount) throws AccountNotFoundException {
		
		checkBalance(accNo);
		
		String str1 = "UPDATE account SET currentBal = currentBal+? WHERE accountId = ?";
		ResultSet rs = exAQuery(str1, accNo,amount);
		
		//String str2 = "INSERT INTO transaction(transactionId,accountId,tran_date,description,tran_type,amount) VALUES(TRANNO.nextVal,?,?,?,?,?)";
		String str2 = "INSERT INTO transaction(accountId,tran_date,description,tran_type,amount) VALUES(?,?,?,?,?)";
		ResultSet rs2 = exTQuery(str2,"ACCOUNT CREDITED","CR",accNo,amount);
		
		return true;
	}

	@Override
	public boolean withdraw(int accNo, double amount) throws InsufficientBalanceException, AccountNotFoundException {
		
		checkBalance(accNo);
		
		if(checkBalance(accNo)>amount) {
			String str1 = "UPDATE account SET currentBal = currentBal-? WHERE accountId = ?";
			ResultSet rs = exAQuery(str1, accNo,amount);
			
			//String str2 = "INSERT INTO transaction(transactionId,accountId,tran_date,description,tran_type,amount) VALUES(TRANNO.nextVal,?,?,?,?,?)";
			String str2 = "INSERT INTO transaction(transactionId,accountId,tran_date,description,tran_type,amount) VALUES(?,?,?,?,?)";
			ResultSet rs2 = exTQuery(str2,"ACCOUNT DEBITED","DB",accNo,amount);
			
		}
		else{
			throw new InsufficientBalanceException("Balance not Enough");
		}
		return true;
	}

	@Override
	public double getBalance(int accNo) throws AccountNotFoundException {
		
		return checkBalance(accNo);
	}

	@Override
	public boolean fundTransfer(int accFrom, int accTo, double amount)
			throws AccountNotFoundException, InsufficientBalanceException {
		
		checkBalance(accFrom);
		checkBalance(accTo);
		
		if(checkBalance(accFrom)>amount) {
			String str1 = "UPDATE account SET currentBal = currentBal-? WHERE accountId = ?";
			ResultSet rs = exAQuery(str1, accFrom,amount);
			
			String str2 = "INSERT INTO transaction(accountId,tran_date,description,tran_type,amount) VALUES(?,?,?,?,?)";
			ResultSet rs2 = exTQuery(str2,"ACCOUNT DEBITED","DB",accFrom,amount);
			
			String str3 = "UPDATE account SET currentBal = currentBal+? WHERE accountId = ?";
			ResultSet rs3 = exAQuery(str3, accTo,amount);
			
			String str4 = "INSERT INTO transaction(accountId,tran_date,description,tran_type,amount) VALUES(?,?,?,?,?)";
			ResultSet rs4 = exTQuery(str4,"ACCOUNT CREDITED","CR",accTo,amount);
			
		}
		else{
			throw new InsufficientBalanceException("Balance not Enough");
		}
		
		return true;
	}

	@Override
	public ResultSet showTransaction(int accNo) throws AccountNotFoundException {
		
		checkBalance(accNo);
		
		String str1 = "SELECT * FROM transaction WHERE accountId=?";
		ResultSet rs = exAQuery(str1,accNo);
	
		
		return rs;
	}

	@Override
	public void saveMap() {

	}

	@Override
	public void readMap() {

	}

}
