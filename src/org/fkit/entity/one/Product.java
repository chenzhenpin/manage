package org.fkit.entity.one;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_product")
public class Product {
	@Id @Column(name="product_id")
	@GeneratedValue
	private Long productid;
	
	@Column(name="price")
	private String price;
	
	public Product() {
		
	}
	public Product(String price) {
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "price"+this.price;
	}
	

}
