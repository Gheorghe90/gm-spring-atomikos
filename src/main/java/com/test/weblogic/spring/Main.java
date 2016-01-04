package com.test.weblogic.spring;

import com.test.weblogic.spring.config.PersistenceContext;
import com.test.weblogic.spring.model.User;
import com.test.weblogic.spring.user.UserManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
	public static void main(String[] args) {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(PersistenceContext.class);

		UserManager userManager = (UserManager) ctx.getBean("userManagerImpl");

		List<User> list = userManager.findAllUsers();
		System.out.println("User count: " + list.size());

		User user = new User();
		user.setUsername("johndoe");
		user.setName("John Doe");
		userManager.insertUser(user);
		System.out.println("User inserted!");

		list = userManager.findAllUsers();
		System.out.println("User count: " + list.size());

	}
}
