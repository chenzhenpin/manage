package org.fkit.entity.two;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.fkit.entity.one.Product;

@Entity
@Table(name = "f_product")
public class FProduct {
	@Id @Column(name="product_id")
	@GeneratedValue
	private Long productid;
	
	@Column(name="price")
	private String price;
	
	@ManyToMany(targetEntity=FPerson.class,cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="f_person_product",
	joinColumns=@JoinColumn(name="product_id",referencedColumnName="product_id"),
	inverseJoinColumns=@JoinColumn(name="person_id",referencedColumnName="person_id"))
	private Set<FPerson> person;
	
	public FProduct() {
		
	}
	public FProduct(String price) {
		this.price=price;
	}
	public Long getProductid() {
		return productid;
	}
	public void setProductid(Long productid) {
		this.productid = productid;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
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
		return "price"+this.price;
	}
	
	

}
