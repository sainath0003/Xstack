package com.epam.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.project.entity.User;

import jakarta.persistence.EntityManager;
//
//@Repository
//@Transactional
public class UsersDao implements UsersDaoInterface {

	@Autowired
	private EntityManager entityManager;

	public User insert(User user) {

		entityManager.persist(user);
		return Optional.ofNullable(user).orElseThrow();
	}

	public User update(User user) {

		user = entityManager.merge(user);
		return Optional.ofNullable(user).orElseThrow();
	}

	public User remove(User user) {

		entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
		return Optional.ofNullable(user).orElseThrow();
	}

	public User view(int id) {

		return Optional.ofNullable(entityManager.find(User.class, id)).orElseThrow();
	}

	public List<User> viewAll() {

		return entityManager.createQuery("from User", User.class).getResultList().stream().toList();

	}

}
