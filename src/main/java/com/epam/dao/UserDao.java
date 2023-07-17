package com.epam.dao;

import java.util.List;
import java.util.Map;

import com.epam.models.User;

public interface UserDao {
	
	public Map<Long, User> getUsers();
	
	public void insert(User user);
	
	public List<User> getUsersList();
}
