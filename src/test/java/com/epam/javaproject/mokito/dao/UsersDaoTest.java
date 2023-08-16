package com.epam.javaproject.mokito.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.project.dao.UsersDao;
import com.epam.project.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

@ExtendWith(MockitoExtension.class)
class UsersDaoTest {

	@Mock
	private EntityManager entityManager;

	@Mock
	private TypedQuery<User> query;

	@Mock
	private EntityTransaction transaction;

	@InjectMocks
	private UsersDao userDao;

	@Test
	void testInsert() {

		User user = new User();

		User updatedUser = userDao.insert(user);

		Mockito.verify(entityManager).persist(user);

		assertEquals(user.getUserId(), updatedUser.getUserId());
	}

	@Test
	void updateTest() {
		User user = new User();

		Mockito.when(entityManager.merge(user)).thenReturn(user);
		User updatedUser = userDao.update(user);

		Mockito.verify(entityManager).merge(user);

		assertEquals(user.getUserId(), updatedUser.getUserId());
	}

	@Test
	void testRemove() {
		User user = null;

		Mockito.doThrow(NullPointerException.class).when(entityManager).remove(user);
		assertThrows(NullPointerException.class, () -> userDao.remove(null));

	}

	@Test
	void testView() {

		User user = new User();
		user.setUserId(1);
		Mockito.when(entityManager.find(User.class, user.getUserId())).thenReturn(user);
		User result = userDao.view(user.getUserId());

		Mockito.verify(entityManager).find(User.class, user.getUserId());
		assertEquals(user.getUserId(), result.getUserId());

	}

	@Test
	void findAllTest() {
		User user1 = new User();
		user1.setUserId(1);
		User user2 = new User();
		user2.setUserId(2);

		List<User> usersList = Arrays.asList(user1, user2);
		Mockito.when(entityManager.createQuery("from User", User.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(usersList);

		List<User> users = userDao.viewAll();
		Mockito.verify(entityManager.createQuery("from User", User.class)).getResultList();
		assertEquals(usersList, users);
	}

	@Test
	void testNullInsert() {
		User user = null;

		Mockito.doThrow(NullPointerException.class).when(entityManager).persist(user);
		assertThrows(NullPointerException.class, () -> userDao.insert(null));

	}

	@Test
	void updateNullTest() {
		User user = null;
		Mockito.doThrow(NullPointerException.class).when(entityManager).merge(user);
		assertThrows(NullPointerException.class, () -> userDao.update(null));

	}

	@Test
	void testNullRemove() {
		User user = new User();
		user.setUserId(12);

		User updatedUser = userDao.remove(user);

		Mockito.verify(entityManager).remove(entityManager.contains(user) ? user : entityManager.merge(user));

		assertEquals(user.getUserId(), updatedUser.getUserId());

	}

	@Test
	void testNullView() {

		Mockito.when(entityManager.find(User.class, -1)).thenThrow(NoResultException.class);
		assertThrows(NoResultException.class, () -> userDao.view(-1));

	}

	@Test
	void findAllWithNoDataTest() {
		Mockito.when(entityManager.createQuery("from User", User.class)).thenReturn(null);

		assertThrows(NullPointerException.class, () -> userDao.viewAll());
	}

}
