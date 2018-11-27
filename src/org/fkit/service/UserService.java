package org.fkit.service;

import java.util.List;

import org.fkit.table.User;

public interface UserService {
	 public void setCahche(String msg);
	 public void removeCahche(String msg);
	 public void removeAllCahche(String msg);
	 public User selectUserByName(String name);
}
