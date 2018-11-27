package org.fkit.service;

import java.util.List;

import org.fkit.entity.shiro.ShiroUser;

public interface ShiroUserService {
	public void registerUser(String name,String md5pwd ,String salt);
	public ShiroUser  findUserByUserName(String username);
}
