package com.qa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Accounts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@Column(name = "first_name", length = 100)
	private String firstname;
	@Column(name = "last_name", length = 100)
	private String lastname;
	@Column(name = "account_num")
	private Long accountnum;
	
	public Accounts() {}
	
	public Accounts(Long Id, String firstname, String lastname, Long accountnum)
	{
		this.Id = Id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.accountnum = accountnum;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public Long getAccountnum() {
		return accountnum;
	}

	public void setAccountnum(Long accountnum) {
		this.accountnum = accountnum;
	}
	
}