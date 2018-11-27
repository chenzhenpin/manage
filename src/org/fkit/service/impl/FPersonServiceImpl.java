package org.fkit.service.impl;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.fkit.entity.one.Account;
import org.fkit.entity.one.Address;
import org.fkit.entity.one.Article;
import org.fkit.entity.one.Product;
import org.fkit.entity.two.FAccount;
import org.fkit.entity.two.FAddress;
import org.fkit.entity.two.FArticle;
import org.fkit.entity.two.FPerson;
import org.fkit.entity.two.FProduct;
import org.fkit.service.FPersonService;
import org.fkit.service.PersonService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class FPersonServiceImpl implements FPersonService {

	//双向连接
	
	@Autowired
    private SessionFactory sessionFactory;
	 FPersonServiceImpl() {
		
	}

	@Override
	public FPerson loadPerson(Long id) {
		Session session=sessionFactory.openSession();
		FPerson person=(FPerson)session.load(FPerson.class, id);
		return person; 
	}
    
	@Override
	public void OneToOne() {
		Session session = sessionFactory.openSession();
    	Transaction transaction=session.beginTransaction();
		
		FPerson person = new FPerson();
		person.setUsername("小新");
		person.setPhone("18381005946");
    	person.setRemark("this is bai");
    	FAddress address=new FAddress("海南乐东");
    	person.setAddress(address);
    	
    
    	FAccount account= new FAccount("chen");
    	person.setAccount(account);
    	
    	session.save(person);
    	transaction.commit();
	}
	
	@Override
	public void ManyToOne() {
		Session session = sessionFactory.openSession();
    	Transaction transaction=session.beginTransaction();
		FAddress address=new FAddress("海南海口");
		FPerson person1 = new FPerson();
		FPerson person2 = new FPerson();
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
		FPerson person = new FPerson();
		person.setUsername("小花");
    	person.setPhone("18381005946");
    	person.setRemark("this is hua");
    	FAddress address=new FAddress("广州天河");

    	person.setAddress(address);
    	FArticle article1=new FArticle("风流人物");
    	FArticle article2=new FArticle("草船借箭");
    	Set<FArticle> set=new HashSet<FArticle>();
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
    	FAccount account1=new FAccount("rich");
    	FArticle article1=new FArticle("1大江东去");
    	FArticle article2=new FArticle("1物是人非");
    	Set<FArticle> articles1=new HashSet<FArticle>();
    	articles1.add(article1);
    	articles1.add(article2);
		FPerson person1 = new FPerson();
		person1.setUsername("小叶");
    	person1.setPhone("18381005946");
    	person1.setRemark("this is ye");
    	FAddress address1=new FAddress("海南万宁");
    	person1.setAddress(address1);
    	person1.setAccount(account1);
    	person1.setArticles(articles1);
    	
    	
    	
    	FPerson person2 = new FPerson();
		person2.setUsername("小然");
    	person2.setPhone("18381005946");
    	person2.setRemark("this is ye");
    	FAddress address2=new FAddress("海南三亚");
    	person2.setAddress(address2);
    	
    	FAccount account2=new FAccount("rose");
    	FArticle article3=new FArticle("2大江东去");
    	FArticle article4=new FArticle("2物是人非");
    	Set<FArticle> articles2=new HashSet<FArticle>();
    	articles1.add(article3);
    	articles1.add(article4);
    	
    	person2.setAccount(account2);
    	person2.setArticles(articles2);
    	
    	FProduct product1=new FProduct("12");
    	FProduct product2=new FProduct("15");
    	Set<FProduct> set=new HashSet<FProduct>();
    	
    	set.add(product1);
    	set.add(product2);
    	person1.setProducts(set);
    	person2.setProducts(set);
    	
    	session.save(person1);
    	session.save(person2);
    	transaction.commit();
	}
	
	
    
}