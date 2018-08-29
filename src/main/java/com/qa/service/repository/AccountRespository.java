package com.qa.service.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.transaction.Transactional;

@Transactional(SUPPORTS)
public interface AccountRespository {

	public String getAllAccounts();
	
	@Transactional(REQUIRED)
	public String createAccount(String accountToAdd);
	
	@Transactional(REQUIRED)
	public String updateAccount(Long id, String accountToUpdate);
	
	@Transactional(REQUIRED)
	public String deleteAccount(Long id);
	
}
