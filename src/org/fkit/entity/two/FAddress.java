package org.fkit.entity.two;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="f_address")
public class FAddress   {
	//主从表id不能一样
	@Id @Column(name="address_id")
	@GeneratedValue 
	private Long addressid;
	@Column(name = "addressDetail")
	private String addressDetail;
	
	@OneToMany(cascade= {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE}, fetch=FetchType.LAZY)
	@JoinColumn(name="address_id" )
	private Set<FPerson> person;
	public FAddress() {
		
	}
	public FAddress(String addressDetail) {
		this.addressDetail=addressDetail;
	}
	

	public Long getAddressid() {
		return addressid;
	}


	public void setAddressid(Long addressid) {
		this.addressid = addressid;
	}


	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	
	
	public Set<FPerson> getPerson() {
		return person;
	}
	public void setPerson(Set<FPerson> person) {
		this.person = person;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "addressDetail:"+this.addressDetail;
	}
}
