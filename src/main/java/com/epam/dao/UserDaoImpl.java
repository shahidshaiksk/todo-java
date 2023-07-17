package com.epam.dao;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.models.User;

@Component
public class UserDaoImpl implements UserDao{
	@Autowired
	public Map<Long, User> users;
	@Override
	public Map<Long, User> getUsers() {
		return users;
	}

	@Override
	public void insert(User user) {
		users.put(user.getProofID(), user);
	}

	@Override
	public List<User> getUsersList() {
		return users.values().stream().collect(Collectors.toList());
	}
}
