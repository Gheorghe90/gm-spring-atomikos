package com.test.weblogic.spring.user.impl;

import java.util.List;

import com.test.weblogic.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.weblogic.spring.dao.UserDAO;
import com.test.weblogic.spring.user.UserManager;

@Service
public class UserManagerImpl implements UserManager {

	@Autowired
	private UserDAO userDAO;

	@Transactional
	public void insertUser(User user) {
		userDAO.insertUser(user);
	}

	public List<User> findAllUsers() {
		return userDAO.findAllUsers();
	}

}
