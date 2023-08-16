package com.epam.project.service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.project.commontools.InvalidInputException;
import com.epam.project.dto.QuizDto;
import com.epam.project.entity.Question;
import com.epam.project.entity.Quiz;
import com.epam.project.repo.QuizRepo;

import jakarta.persistence.PersistenceException;

@Service
public class QuizService implements QuizServiceInterface {

	@Autowired
	ModelMapper mapper;
	@Autowired
	private QuizRepo dbOperations;
	@Autowired
	QuestionServiceInterface questionService;

	@Override
	public QuizDto add(QuizDto quizdto) {
		try {
			Quiz quiz = new Quiz(quizdto);
			quiz = dbOperations.save(quiz);
			return new QuizDto(quiz);
		} catch (NullPointerException | IllegalArgumentException | PersistenceException | NoSuchElementException e) {
			throw new InvalidInputException("Quiz Not Added");
		}

	}

	@Override
	public QuizDto update(QuizDto quizdto) {
		try {
			Quiz quiz = new Quiz(quizdto);
			quiz = dbOperations.save(quiz);
			return new QuizDto(quiz);
		} catch (NullPointerException | IllegalArgumentException | PersistenceException | NoSuchElementException e) {
			throw new InvalidInputException("Quiz Not Updated");
		}

	}

	@Override
	public QuizDto remove(Integer id) {

		try {
			Quiz quiz = dbOperations.findById(id).orElseThrow();
			dbOperations.deleteById(id);
			return new QuizDto(quiz);

		} catch (NullPointerException | IllegalArgumentException | PersistenceException | NoSuchElementException e) {
			throw new InvalidInputException("Quiz Not Removed");
		}

	}

	@Override
	public QuizDto view(Integer id) {
		try {
			return new QuizDto(dbOperations.findById(id).orElseThrow());

		} catch (NullPointerException | IllegalArgumentException | PersistenceException | NoSuchElementException e) {
			throw new InvalidInputException("No Quiz Found");
		}

	}

	@Override
	public List<Quiz> viewAll() {
		try {
			return dbOperations.findAll();
		} catch (NullPointerException | IllegalArgumentException | PersistenceException e) {
			throw new InvalidInputException("No Quizes Present");
		}

	}

	@Override
	public int checkAnswers(String answers, Integer id) {
		try {
			Quiz quiz = new Quiz(view(id));
			List<Question> questions = quiz.getQuestions();

			List<String> answersList = Arrays.stream(answers.split("\n")).map(String::trim).toList();
			int count = 0;
			int correctanswers = 0;
			for (Question question : questions) {
				if ((question.getAnswer() + "").equalsIgnoreCase(answersList.get(count))) {
					correctanswers++;
				}
				count++;
			}
			return correctanswers * quiz.getMarksPerQuestion();
		} catch (NullPointerException | IllegalArgumentException | PersistenceException | NoSuchElementException e) {
			throw new InvalidInputException("Invalid response from User");
		}

	}

	@Override
	public QuizDto addQuestionToQuiz(int questionid, int quizid) {
		QuizDto quizdto = view(quizid);
		boolean isnotpresent =true;
		Question question = new Question(questionService.view(questionid));
		List<Question> questionsList = quizdto.getQuestions();
		for (Question localquestion : questionsList) {
			if (localquestion.getId() == questionid) {
				isnotpresent = false;
				break;
			}
		}
		if (isnotpresent) {
			questionsList.add(question);
			quizdto.setQuestions(questionsList);
		}
		return quizdto;
	}

	@Override
	public QuizDto removeQuestionFromQuiz(int questionid, int quizid) {
		int counter = 0;
		QuizDto quizdto = view(quizid);
		List<Question> questionsList = quizdto.getQuestions();

		for (Question localquestion : questionsList) {

			if (localquestion.getId() == questionid) {
				questionsList.remove(counter);
				quizdto.setQuestions(questionsList);
				break;
			}
			counter++;
		}
		return quizdto;
	}

}
