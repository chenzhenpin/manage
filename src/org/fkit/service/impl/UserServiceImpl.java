package org.fkit.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.fkit.Mapper.TestMapper;
import org.fkit.common.DB;
import org.fkit.service.UserService;
import org.fkit.table.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	//构造方法初始后执行该方法
	@PostConstruct
	private void init(){
		System.out.println("构造方法初始后执行该方法");
	}
	public void userServiceImpl(){
		System.out.println("开始初始化");
	}
	@Cacheable(value="myCache",key="#msg") 
	public void setCahche(String msg){
		System.out.println("设置缓存:"+msg);
	}
	
	@CacheEvict(value="myCache",key="#msg")  
	public void removeCahche(String msg){
		System.out.println("删除缓存："+msg);
	}
	@CacheEvict(value="myCache", allEntries=true)
	public void removeAllCahche(String msg){
		System.out.println(msg);
	}
	
	@Override
	public User selectUserByName(String name) {
		// TODO Auto-generated method stub
		User user=(User)DB.selectOne(TestMapper.class, "selectUser", name);
		return user;
	}

}
