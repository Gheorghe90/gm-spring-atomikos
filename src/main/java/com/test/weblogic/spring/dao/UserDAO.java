package com.test.weblogic.spring.dao;

import java.util.List;

import com.test.weblogic.spring.model.User;

public interface UserDAO {

	void insertUser(User user);

	List findAllUsers();
}
