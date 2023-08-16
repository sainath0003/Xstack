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

import com.epam.project.dao.QuizDao;
import com.epam.project.entity.Quiz;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

@ExtendWith(MockitoExtension.class)
class QuizDaoTest {

	@Mock
	private EntityManager entityManager;

	@Mock
	private TypedQuery<Quiz> query;

	@Mock
	private EntityTransaction transaction;

	@InjectMocks
	private QuizDao quizDao;

	@Test
	void testInsert() {

		Quiz quiz = new Quiz();
		
		Quiz updatedQuiz = quizDao.insert(quiz);

		Mockito.verify(entityManager).persist(quiz);
	
	

		assertEquals(quiz.getId(), updatedQuiz.getId());
	}

	@Test
	void updateTest() {
		Quiz quiz = new Quiz();
		
		Mockito.when(entityManager.merge(quiz)).thenReturn(quiz);
		Quiz updatedQuiz = quizDao.update(quiz);

		Mockito.verify(entityManager).merge(quiz);
	
	

		assertEquals(quiz.getId(), updatedQuiz.getId());
	}

	@Test
	void testRemove() {
		Quiz quiz = null;
		
		Mockito.doThrow(NullPointerException.class).when(entityManager).remove(quiz);
		assertThrows(NullPointerException.class, () -> quizDao.remove(null));

	

	}

	@Test
	void testView() {

		Quiz quiz = new Quiz();
		quiz.setId(1);
		int id = quiz.getId();
		Mockito.when(entityManager.find(Quiz.class, id)).thenReturn(quiz);
		Quiz result = quizDao.view(quiz.getId());

		
		assertEquals(quiz.getId(), result.getId());

	}

	@Test
	void findAllTest() {
		Quiz quiz1 = new Quiz();
		quiz1.setId(1);
		Quiz quiz2 = new Quiz();
		quiz2.setId(2);

		List<Quiz> quizsList = Arrays.asList(quiz1, quiz2);
		Mockito.when(entityManager.createQuery("from Quiz", Quiz.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(quizsList);

		List<Quiz>quizs = quizDao.viewAll();
		Mockito.verify(entityManager.createQuery("from Quiz", Quiz.class)).getResultList();
		assertEquals(quizsList,quizs);
	}

	@Test
	void testNullInsert() {
		Quiz quiz = null;
		
		Mockito.doThrow(NullPointerException.class).when(entityManager).persist(quiz);

		assertThrows(NullPointerException.class, () -> quizDao.insert(null));
	
	}

	@Test
	void updateNullTest() {
		Quiz quiz = null;
		
		Mockito.doThrow(NullPointerException.class).when(entityManager).merge(quiz);
		assertThrows(NullPointerException.class, () -> quizDao.update(null));

	
	}

	@Test
	void testNullRemove() {
		Quiz quiz = new Quiz();
		quiz.setId(12);
		

		Quiz updatedQuiz = quizDao.remove(quiz);

		Mockito.verify(entityManager).remove(entityManager.contains(quiz) ? quiz : entityManager.merge(quiz));
	
	

		assertEquals(quiz.getId(), updatedQuiz.getId());

	}

	@Test
	void testNullView() {
		int id =-1;
		Mockito.when(entityManager.find(Quiz.class, id)).thenThrow(NoResultException.class);
		assertThrows(NoResultException.class, () -> quizDao.view(-1));

	}

	@Test
	void findAllWithNoDataTest() {
		Mockito.when(entityManager.createQuery("from Quiz", Quiz.class)).thenReturn(null);

		assertThrows(NullPointerException.class, () -> quizDao.viewAll());
	}

}
