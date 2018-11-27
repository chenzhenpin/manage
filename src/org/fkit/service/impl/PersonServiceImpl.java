package org.fkit.service.impl;


import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.fkit.entity.one.Account;
import org.fkit.entity.one.Address;
import org.fkit.entity.one.Article;
import org.fkit.entity.one.Person;
import org.fkit.entity.one.Product;
import org.fkit.service.PersonService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class PersonServiceImpl implements PersonService {

	//单向连接
	
	@Autowired
    private SessionFactory sessionFactory;
	public PersonServiceImpl() {
		
	}
    public void savePerson()  {
    	Session session = sessionFactory.openSession();
    	Transaction transaction=session.beginTransaction();
    	Person person = new Person();
    	person.setUsername("XRog");
    	person.setPhone("18381005946");
    	person.setRemark("this is XRog");
    	Address address=new Address("广州天河");

    	person.setAddress(address);
	
    	Article article1=new Article("大江东去");
    	Article article2=new Article("此去经年");
    	
    	Set<Article> set=new HashSet<Article>();
    	set.add(article1);
    	set.add(article2);
	  
    	person.setArticles(set);
    	session.save(person);
    	transaction.commit();
    }

	
    
	@Override
	public void OneToOne() {
		Session session = sessionFactory.openSession();
    	Transaction transaction=session.beginTransaction();
		
		Person person = new Person();
		person.setUsername("小新");
		person.setPhone("18381005946");
    	person.setRemark("this is bai");
    	Address address=new Address("海南乐东");
    	person.setAddress(address);
    	
    
    	Account account= new Account("chen");
    	person.setAccount(account);
    	
    	session.save(person);
    	transaction.commit();
	}
	
	@Override
	public void ManyToOne() {
		Session session = sessionFactory.openSession();
    	Transaction transaction=session.beginTransaction();
		Address address=new Address("海南海口");
		Person person1 = new Person();
		Person person2 = new Person();
		person1.setUsername("小白");
    	person1.setPhone("18381005946");
    	person1.setRemark("this is bai");
    	person1.setAddress(address);
    	
    	person2.setUsername("小明");
    	person2.setPhone("18381005946");
    	person2.setRemark("this is ming");
    	person2.setAddress(address);
    	session.save(person1);
    	session.save(person2);
    	transaction.commit();
	}
    
	@Override
	public void OneToMany() {
		Session session = sessionFactory.openSession();
    	Transaction transaction=session.beginTransaction();
		Person person = new Person();
		person.setUsername("小花");
    	person.setPhone("18381005946");
    	person.setRemark("this is hua");
    	Address address=new Address("广州天河");

    	person.setAddress(address);
    	Article article1=new Article("风流人物");
    	Article article2=new Article("草船借箭");
    	Set<Article> set=new HashSet<Article>();
    	set.add(article1);
    	set.add(article2);
    	person.setArticles(set);
    	
    	session.save(person);
    	transaction.commit();
	}
	
	@Override
	public void ManyToMany() {
		Session session = sessionFactory.openSession();
    	Transaction transaction=session.beginTransaction();
		Person person1 = new Person();
		person1.setUsername("小叶");
    	person1.setPhone("18381005946");
    	person1.setRemark("this is ye");
    	Address address1=new Address("海南万宁");
    	person1.setAddress(address1);
    	
    	Person person2 = new Person();
		person2.setUsername("小然");
    	person2.setPhone("18381005946");
    	person2.setRemark("this is ye");
    	Address address2=new Address("海南三亚");
    	person2.setAddress(address2);
    	
    	Product product1=new Product("12");
    	Product product2=new Product("15");
    	Set<Product> set=new HashSet<Product>();
    	
    	set.add(product1);
    	set.add(product2);
    	person1.setProducts(set);
    	person2.setProducts(set);
    	
    	session.save(person1);
    	session.save(person2);
    	transaction.commit();
	}
	
	
	
	@Override
	public Person loadPerson(Long id) {
		Session session=sessionFactory.openSession();
		Person person=(Person)session.load(Person.class, id);
		//String username=person.getAccount().getUsername();
		String AdddressDetail=person.getAddress().getAddressDetail();
		Set<Article> articles=person.getArticles();
		Set<Product> products=person.getProducts();
		System.out.println("用户名-----------------");
		//System.out.println(username);
		System.out.println("地址-----------------");
		System.out.println(AdddressDetail);
		for(Article article:articles) {
			System.out.println("内容-----------------");
			System.out.println(article.getContent());
		}
		for(Product product :products) {
			System.out.println("商品-----------------");
			System.out.println(product);
		}
		return person; 
	}
    
}