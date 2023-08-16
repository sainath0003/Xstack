package com.epam.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.project.entity.Question;

import jakarta.persistence.EntityManager;

////@Repository
//@Transactional
public class QuestionsDao implements QuestionsDaoInterface {
	@Autowired
	private EntityManager entityManager;

	public Question insert(Question question) {

		entityManager.persist(question);
		return Optional.ofNullable(question).orElseThrow();

	}

	public Question update(Question question) {

		entityManager.merge(question);
		return Optional.ofNullable(question).orElseThrow();
	}

	public Question remove(Question question) {

		entityManager.remove(entityManager.contains(question) ? question : entityManager.merge(question));
		return Optional.ofNullable(question).orElseThrow();
	}

	public Question view(int id) {
		return Optional.ofNullable(entityManager.find(Question.class, id)).orElseThrow();
	}

	public List<Question> viewAll() {

		return entityManager.createQuery("from Question", Question.class).getResultList().stream().toList();

	}

}
