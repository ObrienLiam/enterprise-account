package com.qa.service.repository;

import javax.transaction.Transactional;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import static javax.transaction.Transactional.TxType.REQUIRED;

@Transactional(SUPPORTS)
public class AccountDBRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;
	
	public String getAllAccounts()
	{
		Query query = manager.createQuery("select a From Account a");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(accounts);
	}
	
	@Transactional(REQUIRED)
	public String createAccount(String account)
	{
		Account newAccount = util.getObjectForJSON(account, Account.class);
		manager.persist(newAccount);
		return "{\"message\": \"account has been successfully added\"}";
	}
	
	@Transactional(REQUIRED)
	public String updateAccount(Long id, String accountToUpdate)
	{
		Account updatedAccount = util.getObjectForJSON(accountToUpdate, Account.class);
		Account accountFromDB = findAccount(id);
		if (accountToUpdate!=null )
		{
			//Overriding primary key
			accountFromDB = updatedAccount;
			manager.merge(accountFromDB);
		}
		return "{\"message\": \"account has been successfully updated\"}";
	}
	
	@Transactional(REQUIRED)
	public String deleteAccount(Long id)
	{
		Account accountInDB = findAccount(id);
		
		//Why if statement for accountInDB when not needed for update?
		if (accountInDB != null )
		{
			manager.remove(accountInDB);
		}
		
		return "{\"message\": \"account has been successfully deleted\"}";
	}
	
	private Account findAccount(Long id)
	{
		return manager.find(Account.class, id);
	}
}
