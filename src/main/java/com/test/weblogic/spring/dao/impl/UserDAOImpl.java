package com.test.weblogic.spring.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.test.weblogic.spring.dao.UserDAO;
import com.test.weblogic.spring.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertUser(User user) {
		entityManager.persist(user);
	}

	@Override
	public List<User> findAllUsers() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> cq = builder.createQuery(User.class);
		cq.select(cq.from(User.class));
		return entityManager.createQuery(cq).getResultList();
	}

}
