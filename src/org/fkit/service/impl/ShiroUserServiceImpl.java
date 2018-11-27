package org.fkit.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.fkit.entity.shiro.ShiroUser;
import org.fkit.service.ShiroUserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ShiroUserServiceImpl implements ShiroUserService {
	@Autowired
    private SessionFactory sessionFactory;
	public ShiroUserServiceImpl() {}
	
	public void registerUser(String username ,String md5pwd ,String salt) {
		Session session = sessionFactory.openSession();
    	Transaction transaction=session.beginTransaction();
    	ShiroUser shiroUser= new ShiroUser();
    	shiroUser.setUsername(username);
    	shiroUser.setUsermd5pwd(md5pwd);
    	shiroUser.setSalt(salt);
    	session.save(shiroUser);
    	transaction.commit();
	}

	@Override
	public ShiroUser findUserByUserName(String username) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
    	Transaction transaction=session.beginTransaction();
    	ShiroUser shiroUser= new ShiroUser();
    	shiroUser.setUsername(username);
    	ShiroUser loginuser=(ShiroUser)session.byNaturalId(ShiroUser.class)
    			.using("username", username)
    			.load();
    	transaction.commit();
    	return loginuser;
	}
	
}
