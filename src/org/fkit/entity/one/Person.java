package org.fkit.entity.one;

import java.io.Serializable;
import java.util.HashSet;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;






@Entity
@Table(name = "test_person")
public class Person  {
	
    @Id @Column(name="person_id")
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_STORE")   
//    @SequenceGenerator(name="SEQ_STORE", sequenceName="Q_SQE_TEST_ID",allocationSize = 1) 
    @GeneratedValue
    private Long personid;

    @Column(name = "created")
    private Long created = System.currentTimeMillis();

    @Column(name = "username")
    private String username;
    
    @ManyToOne(targetEntity=Address.class,cascade=CascadeType.ALL)
    @JoinColumn(name="address_id", nullable=false)
    private Address address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "remark")
    private String remark;
    
	@OneToMany(targetEntity=Article.class,cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="person_id" )
	private Set<Article> Articles ;
	
	@ManyToMany(targetEntity=Product.class,cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="person_product",
	joinColumns=@JoinColumn(name="person_id",referencedColumnName="person_id"),
	inverseJoinColumns=@JoinColumn(name="product_id",referencedColumnName="product_id"))
	private Set<Product> products ;
	
	@OneToOne(targetEntity=Account.class,cascade=CascadeType.ALL)
    @JoinColumn(name="account_id", unique=true)
	private Account account;
	
	public Person(){
			
	}
	
	
	
	public Long getPersonid() {
		return personid;
	}


	public void setPersonid(Long personid) {
		this.personid = personid;
	}


	public Long getCreated() {
		return created;
	}


	public void setCreated(Long created) {
		this.created = created;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Set<Article> getArticles() {
		return Articles;
	}


	public void setArticles(Set<Article> articles) {
		Articles = articles;
	}






	public Set<Product> getProducts() {
		return products;
	}



	public void setProducts(Set<Product> products) {
		this.products = products;
	}



	public Account getAccount() {
		return account;
	}



	public void setAccount(Account account) {
		this.account = account;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name:"+this.username+",address:"+this.address+",phone:"+this.phone;
	}
    
}
