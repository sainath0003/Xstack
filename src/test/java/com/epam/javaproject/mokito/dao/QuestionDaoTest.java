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

import com.epam.project.dao.QuestionsDao;
import com.epam.project.entity.Question;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

@ExtendWith(MockitoExtension.class)
class QuestionDaoTest {

	@Mock
	private EntityManager entityManager;

	@Mock
	private TypedQuery<Question> query;

	@Mock
	private EntityTransaction transaction;

	@InjectMocks
	private QuestionsDao questionDao;

	@Test
	void testInsert() {

		Question question = new Question();

		Question updatedQuestion = questionDao.insert(question);

		Mockito.verify(entityManager).persist(question);

		assertEquals(question.getId(), updatedQuestion.getId());
	}

	@Test
	void updateTest() {
		Question question = new Question();

		Mockito.when(entityManager.merge(question)).thenReturn(question);
		Question updatedQuestion = questionDao.update(question);

		Mockito.verify(entityManager).merge(question);
		assertEquals(question, updatedQuestion);
	}

	@Test
	void testRemove() {
		Question question = null;

		Mockito.doThrow(NullPointerException.class).when(entityManager).remove(question);
		assertThrows(NullPointerException.class, () -> questionDao.remove(null));

	}

	@Test
	void testView() {

		Question question = new Question();
		question.setId(1);
		int id = question.getId();
		Mockito.when(entityManager.find(Question.class, id)).thenReturn(question);
		Question result = questionDao.view(question.getId());

		assertEquals(question.getId(), result.getId());

	}

	@Test
	void findAllTest() {
		Question question1 = new Question();
		question1.setId(1);
		Question question2 = new Question();
		question2.setId(2);

		List<Question> questionsList = Arrays.asList(question1, question2);
		Mockito.when(entityManager.createQuery("from Question", Question.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(questionsList);

		List<Question> questions=questionDao.viewAll();

		Mockito.verify(entityManager.createQuery("from Question", Question.class)).getResultList();

		assertEquals(questionsList, questions);
	}

	@Test
	void testNullInsert() {
		Question question = null;

		Mockito.doThrow(NullPointerException.class).when(entityManager).persist(question);
		assertThrows(NullPointerException.class, () -> questionDao.insert(null));

	}

	@Test
	void updateNullTest() {
		Question question = null;

		Mockito.doThrow(NullPointerException.class).when(entityManager).merge(question);
		assertThrows(NullPointerException.class, () -> questionDao.update(null));

	}

	@Test
	void testNullRemove() {
		Question question = new Question();
		question.setId(12);

		Question updatedQuestion = questionDao.remove(question);

		Mockito.verify(entityManager)
				.remove(entityManager.contains(question) ? question : entityManager.merge(question));

		assertEquals(question.getId(), updatedQuestion.getId());

	}

	@Test
	void testNullView() {
		int id = -1;
		Mockito.when(entityManager.find(Question.class, id)).thenThrow(NoResultException.class);
		assertThrows(NoResultException.class, () -> questionDao.view(-1));

	}

	@Test
	void findAllWithNoDataTest() {
		Mockito.when(entityManager.createQuery("from Question", Question.class)).thenReturn(null);

		assertThrows(NullPointerException.class, () -> questionDao.viewAll());
	}

}
