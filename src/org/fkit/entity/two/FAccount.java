package org.fkit.entity.two;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.fkit.entity.one.Account;

@Entity
@Table(name="f_account")
public class FAccount {
	@Id @Column(name="account_id")
	@GeneratedValue
	private Long accountid;
	
	@Column(name="username")
	private String username;
	
	@OneToOne(cascade= CascadeType.REFRESH,mappedBy="account")
	private FPerson person;
	public FAccount() {
		
	}
	public FAccount(String username) {
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
	public FPerson getPerson() {
		return person;
	}
	public void setPerson(FPerson person) {
		this.person = person;
	}
	
}
