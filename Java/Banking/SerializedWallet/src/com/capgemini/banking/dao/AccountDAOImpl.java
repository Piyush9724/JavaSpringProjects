package com.capgemini.banking.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.capgemini.banking.bean.Account;
import com.capgemini.banking.exceptions.AccountNotFoundException;
import com.capgemini.banking.exceptions.InsufficientBalanceException;

public class AccountDAOImpl  implements AccountDAO, Serializable {

	protected Map<Integer, Account> accountMap;;
	protected Map<Integer, ArrayList<String>> transMap;
	
	int lastAccNo;
	
	File file = new File("Java/Banking/SerializedWallet/resources/Repo.txt");
	File file1 = new File("Java/Banking/SerializedWallet/resources/Trans.txt");
	
	public AccountDAOImpl() {
		accountMap = new HashMap<Integer, Account>();
		transMap = new HashMap<Integer, ArrayList<String>>();
		readMap();
	}
	
	
	@Override
	public int createAccount(Account account) {
		accountMap.put(account.getAccountNo(), account);
		String crTran = account.getAccountNo()+"\t"+ account.getName()+"\t"+" Account Created "+ new Date() +"\t"+
						account.getCrBalance();
		ArrayList<String> transList = new ArrayList<String>();
		transList.add(crTran);
		transMap.put(account.getAccountNo(),transList);
		
		return account.getAccountNo();
	}

	@Override
	public boolean deposit(int accNo, double amount) throws AccountNotFoundException {
		if(accountMap.containsKey(accNo)) {
			accountMap.get(accNo).setCrBalance(accountMap.get(accNo).getCrBalance()+amount);
			String dpTran = accountMap.get(accNo).getAccountNo()+"\t"+ amount +" CREDITED "+ new Date() +"\t"+
					accountMap.get(accNo).getCrBalance();
			transMap.get(accNo).add(dpTran);
			return true;
		}
		else
			throw new AccountNotFoundException("The Specified Account was not Found");
	}

	@Override
	public boolean withdraw(int accNo, double amount) throws InsufficientBalanceException, AccountNotFoundException {
		if(accountMap.containsKey(accNo)) {
			if(accountMap.get(accNo).getCrBalance()>amount){
				accountMap.get(accNo).setCrBalance(accountMap.get(accNo).getCrBalance()-amount);
				String WTran = accountMap.get(accNo).getAccountNo()+"\t"+ amount +" DEBITED "+ new Date() +"\t"+
						accountMap.get(accNo).getCrBalance();
				transMap.get(accNo).add(WTran);
				return true;
			}
			else
			{
				throw new InsufficientBalanceException("The Current Balance is Not Suffiecient!");
			}
		}
		else
		{
			throw new AccountNotFoundException("The Specified Account was not Found");
		}
		
	}

	@Override
	public double getBalance(int accNo) throws AccountNotFoundException {
		if(accountMap.containsKey(accNo)) {
			
			return accountMap.get(accNo).getCrBalance();
		}
		else
			throw new AccountNotFoundException("The Specified Account was not Found");
	}

	@Override
	public boolean fundTransfer(int accFrom,int accTo, double amount) throws AccountNotFoundException, InsufficientBalanceException {
		if(accountMap.containsKey(accFrom)) {
			if(accountMap.containsKey(accTo)) {
				if(accountMap.get(accFrom).getCrBalance()>amount){
					accountMap.get(accFrom).setCrBalance(accountMap.get(accFrom).getCrBalance()-amount);
					accountMap.get(accTo).setCrBalance(accountMap.get(accTo).getCrBalance()+amount);
					String dpTran = accountMap.get(accFrom).getAccountNo()+"\t"+ amount +" DEBITED "+ new Date() +"\t"+
							accountMap.get(accFrom).getCrBalance();
					transMap.get(accFrom).add(dpTran);
					
					String WTran = accountMap.get(accTo).getAccountNo()+"\t"+ amount +" CREDITED "+ new Date() +"\t"+
							accountMap.get(accTo).getCrBalance();
					transMap.get(accTo).add(WTran);
					return true;
				}
				else
				{
					throw new InsufficientBalanceException("The Current Balance is Not Suffiecient!");
				}
			}
			else
			{
				throw new AccountNotFoundException("The Specified Account was not Found");
			}
		}
		else
		{
			throw new AccountNotFoundException("The Specified Account was not Found");
		}
	}

	@Override
	public ArrayList<String> showTransaction(int accNo) throws AccountNotFoundException {
		if(accountMap.containsKey(accNo)) {
			ArrayList<String> ar = transMap.get(accNo);
			return ar;
		}
		else
			throw new AccountNotFoundException("The Specified Account was not Found");
	}
	
	
	@Override
	public void saveMap() {
		FileOutputStream fout,fout1;
		try {

			fout = new FileOutputStream(file);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(accountMap);
			fout.flush();
			oout.close();
			fout.close();
			
			fout1 = new FileOutputStream(file1);
			ObjectOutputStream oout1 = new ObjectOutputStream(fout1);
			oout1.writeObject(transMap);
			fout1.flush();
			oout1.close();
			fout1.close();
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void readMap() {
		try (FileInputStream fin = new FileInputStream(file)) {
			ObjectInputStream oin = new ObjectInputStream(fin);
			accountMap = (HashMap<Integer, Account>) oin.readObject();
			List<Entry<Integer, Account>> entries = new ArrayList<Entry<Integer, Account>>(accountMap.entrySet());
			Entry<Integer, Account> lastEntry = entries.get(entries.size() - 1);
			lastAccNo = lastEntry.getKey();
			Account.setAccGen(lastAccNo);
			// System.out.println(lastAccNo);
			oin.close();
			fin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

		} catch (Exception e) {

		}
		
		try (FileInputStream fin1 = new FileInputStream(file1)) {
			ObjectInputStream oin1 = new ObjectInputStream(fin1);
			transMap = (HashMap<Integer, ArrayList<String>>) oin1.readObject();
			oin1.close();
			fin1.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

		} catch (Exception e) {

		}
		
		

	}
	
}
