package com.qa.service.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Alternative
public class AccountMapRepository implements AccountRespository{

	private Map<Long, Account> accountMap;
	
	@Inject
	private JSONUtil util;
	
	private Long counter = 1L;
	
	//@override?
	public AccountMapRepository()
	{
		this.accountMap = new HashMap<Long, Account>();
	}
	
	public String getAllAccounts() {
		Collection<Account> accounts = (Collection<Account>) accountMap.values();
		return util.getJSONForObject(accounts);
	}

	public String createAccount(String accountToAdd) {
		accountMap.put(counter, util.getObjectForJSON(accountToAdd, Account.class));
		counter++;
		return "{\"message\": \"account has been successfully added\"}";
	}

	public String updateAccount(Long id, String accountToUpdate) {
		accountMap.replace(id, util.getObjectForJSON(accountToUpdate, Account.class));
		return "{\"message\": \"account has been successfully updated\"}";
	}

	public String deleteAccount(Long id) {
		accountMap.remove(id);
		return "{\"message\": \"account has been successfully deleted\"}";
	}

}
