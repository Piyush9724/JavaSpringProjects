package com.peoplesbank.BankingApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peoplesbank.BankingApp.beans.Accounts;

@Repository
public interface AccountRepository extends JpaRepository<Accounts,Integer> {

}
