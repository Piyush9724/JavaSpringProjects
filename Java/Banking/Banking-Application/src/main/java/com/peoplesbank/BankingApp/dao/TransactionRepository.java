package com.peoplesbank.BankingApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peoplesbank.BankingApp.beans.Transactions;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Integer> {

}
