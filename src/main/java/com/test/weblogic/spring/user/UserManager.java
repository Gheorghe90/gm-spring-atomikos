package com.test.weblogic.spring.user;

import java.util.List;

import com.test.weblogic.spring.model.User;

public interface UserManager {

	void insertUser(User user);

	List<User> findAllUsers();
}
