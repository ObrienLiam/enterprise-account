package com.qa.service.repository;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Default
public class AccountDBRepository implements AccountRespository{

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
	
	
	public String createAccount(String accountToAdd)
	{
		Account newAccount = util.getObjectForJSON(accountToAdd, Account.class);
		manager.persist(newAccount);
		return "{\"message\": \"account has been successfully added\"}";
	}
	

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
