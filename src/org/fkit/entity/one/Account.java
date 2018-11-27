package org.fkit.entity.one;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="test_account")
public class Account {
	@Id @Column(name="account_id")
	@GeneratedValue
	private Long accountid;
	
	@Column(name="username")
	private String username;
	
	public Account() {
		
	}
	public Account(String username) {
		this.username=username;
	}
	
	public Long getAccountid() {
		return accountid;
	}


	public void setAccountid(Long accountid) {
		this.accountid = accountid;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
