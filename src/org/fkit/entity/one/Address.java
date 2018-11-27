package org.fkit.entity.one;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="test_address")
public class Address   {
	//主从表id不能一样
	@Id @Column(name="address_id")
	@GeneratedValue 
	private Long addressid;
	@Column(name = "addressDetail")
	private String addressDetail;
	
	public Address() {
		
	}
	public Address(String addressDetail) {
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "addressDetail:"+this.addressDetail;
	}
}
