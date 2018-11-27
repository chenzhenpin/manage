package org.fkit.entity.shiro;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="shiro_user")
public class ShiroUser implements Serializable {
	@Id @Column(name="user_id")
	@GeneratedValue
	private Long userid;
	@Column(name="user_name")
	@NaturalId
	private String username;
	@Column(name="user_md5pwd")
	private String usermd5pwd;
	private String salt;
	public ShiroUser() {
		
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsermd5pwd() {
		return usermd5pwd;
	}
	public void setUsermd5pwd(String usermd5pwd) {
		this.usermd5pwd = usermd5pwd;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
}
