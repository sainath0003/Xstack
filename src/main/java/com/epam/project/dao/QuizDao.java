package com.epam.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.project.entity.Quiz;

import jakarta.persistence.EntityManager;
//
//@Repository
//@Transactional
public class QuizDao implements QuizDaoInterface {

	@Autowired
	private EntityManager entityManager;

	public Quiz insert(Quiz quiz) {

		entityManager.persist(quiz);
		return Optional.ofNullable(quiz).orElseThrow();
	}

	public Quiz update(Quiz quiz) {

		quiz = entityManager.merge(quiz);
		return Optional.ofNullable(quiz).orElseThrow();
	}

	public Quiz remove(Quiz quiz) {

		entityManager.remove(entityManager.contains(quiz) ? quiz : entityManager.merge(quiz));
		return Optional.ofNullable(quiz).orElseThrow();
	}

	public Quiz view(int id) {
		return Optional.ofNullable(entityManager.find(Quiz.class, id)).orElseThrow();
	}

	public List<Quiz> viewAll() {

		return entityManager.createQuery("from Quiz", Quiz.class).getResultList().stream().toList();
	}

}
