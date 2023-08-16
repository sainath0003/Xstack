package com.epam.project.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.epam.project.commontools.InvalidInputException;
import com.epam.project.dto.QuestionDto;
import com.epam.project.entity.Question;
import com.epam.project.repo.QuestionsRepo;

import jakarta.persistence.PersistenceException;

@Service("questionService")
public class QuestionService implements QuestionServiceInterface {
	@Autowired
	ModelMapper mapper;
	Logger logger = LogManager.getLogger(QuestionService.class);
	@Autowired
	private QuestionsRepo dbOperations;

	@Override
	public QuestionDto add(QuestionDto questiondto) {
		try {
			Question question = new Question(questiondto);
			question = dbOperations.save(question);

			return new QuestionDto(question);
		} catch (NullPointerException | IllegalArgumentException | PersistenceException | NoSuchElementException e) {
			throw new InvalidInputException("Question Not Added");
		}

	}

	@Override
	public QuestionDto update(QuestionDto questiondto) {
		try {
			Question question = new Question(questiondto);

			question = dbOperations.save(question);
			return new QuestionDto(question);
		} catch (NullPointerException | IllegalArgumentException | PersistenceException | NoSuchElementException
				| OptimisticLockingFailureException e) {
			throw new InvalidInputException("Question Not Updated");
		}
	}

	@Override
	public QuestionDto remove(Integer id) {

		Question question;
		try {
			question = dbOperations.findById(id).orElseThrow();
			dbOperations.deleteById(id);
			return new QuestionDto(question);
		} catch (NoSuchElementException | NullPointerException | IllegalArgumentException | PersistenceException e) {
			throw new InvalidInputException("Question Not Removed");
		} catch (Exception e) {
			if (e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
				throw new InvalidInputException("SQL Integrity Violation Occured ");
			} else
				throw new InvalidInputException(e.getMessage());
		}

	}

	@Override
	public QuestionDto view(Integer id) {
		Question question;
		try {
			question = dbOperations.findById(id).orElseThrow();
			return new QuestionDto(question);
		} catch (NullPointerException | IllegalArgumentException | PersistenceException | NoSuchElementException e) {
			throw new InvalidInputException("No Question Found");
		}

	}

	@Override
	public List<Question> viewAll() {
		try {
			return dbOperations.findAll();
		} catch (NullPointerException | IllegalArgumentException | PersistenceException e) {
			throw new InvalidInputException("No Questiones Present");
		}

	}

}
