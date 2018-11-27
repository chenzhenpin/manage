package org.fkit.entity.two;

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

import org.fkit.entity.one.Account;
import org.fkit.entity.one.Address;
import org.fkit.entity.one.Article;
import org.fkit.entity.one.Product;
import org.hibernate.annotations.Cascade;






@Entity
@Table(name = "f_person")
public class FPerson  {
	
    @Id @Column(name="person_id")
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_STORE")   
//    @SequenceGenerator(name="SEQ_STORE", sequenceName="Q_SQE_TEST_ID",allocationSize = 1) 
    @GeneratedValue
    private Long personid;

    @Column(name = "created")
    private Long created = System.currentTimeMillis();

    @Column(name = "username")
    private String username;
    
    @ManyToOne(targetEntity=FAddress.class)
    @JoinColumn(name="address_id", nullable=false)
    private FAddress address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "remark")
    private String remark;
    
	@OneToMany(targetEntity=FArticle.class,cascade= {CascadeType.PERSIST,CascadeType.PERSIST},mappedBy="person")
	private Set<FArticle> articles ;
	
	@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinTable(name="f_person_product",
	joinColumns=@JoinColumn(name="person_id",referencedColumnName="person_id"),
	inverseJoinColumns=@JoinColumn(name="product_id",referencedColumnName="product_id"))
	private Set<FProduct> products ;
	
	@OneToOne(targetEntity=FAccount.class,cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name="account_id")
	private FAccount account;
	
	public FPerson(){
			
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







	public FAddress getAddress() {
		return address;
	}







	public void setAddress(FAddress address) {
		this.address = address;
	}







	public String getPhone() {
		return phone;
	}







	public void setPhone(String phone) {
		this.phone = phone;
	}







	public String getRemark() {
		return remark;
	}







	public void setRemark(String remark) {
		this.remark = remark;
	}















	public Set<FArticle> getArticles() {
		return articles;
	}







	public void setArticles(Set<FArticle> articles) {
		this.articles = articles;
	}







	public Set<FProduct> getProducts() {
		return products;
	}







	public void setProducts(Set<FProduct> products) {
		this.products = products;
	}







	public FAccount getAccount() {
		return account;
	}







	public void setAccount(FAccount account) {
		this.account = account;
	}







	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name:"+this.username+",address:"+this.address+",phone:"+this.phone;
	}
    
}
