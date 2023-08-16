package com.epam.javaproject.mokito.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.project.commontools.InvalidInputException;
import com.epam.project.dto.QuestionDto;
import com.epam.project.dto.QuizDto;
import com.epam.project.entity.Question;
import com.epam.project.entity.Quiz;
import com.epam.project.repo.QuizRepo;
import com.epam.project.service.QuestionServiceInterface;
import com.epam.project.service.QuizService;

import jakarta.persistence.NoResultException;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {
	@Mock
	private QuizRepo quizDao;

	@Mock
	private QuestionServiceInterface questionService;

	@InjectMocks
	private QuizService quizService;
	QuizDto quizDto;
	Quiz quiz;
	Question question;

	@BeforeEach
	void setup() {
		quizDto = new QuizDto();
		quizDto.setId(2);
		quizDto.setTitle("hello");
		quizDto.setDescription("lo");
		quizDto.setMarksPerQuestion(12);

		question = new Question();
		question.setId(6);
		question.setAnswer(2);
		List<Question> questions = new ArrayList<>();
		questions.add(question);

		quizDto.setQuestions(questions);

		quiz = new Quiz(quizDto);

	}

	@Test
	void testAddQuiz() {

		Mockito.when(quizDao.save(any())).thenReturn(quiz);
		QuizDto retrievedQuiz = quizService.add(quizDto);
		assertEquals(quiz.getId(), retrievedQuiz.getId());

	}

	@Test
	void testAddNullQuiz() {

		assertThrows(InvalidInputException.class, () -> quizService.add(null));

	}

	@Test
	void testUpdateQuiz() {

		Quiz quiz = new Quiz();
		Mockito.when(quizDao.save(any())).thenReturn(quiz);
		QuizDto retrievedQuiz = quizService.update(quizDto);
		assertEquals(quiz.getId(), retrievedQuiz.getId());

	}

	@Test
	void testUpadteNullQuiz() {

		assertThrows(InvalidInputException.class, () -> quizService.update(null));

	}

	@Test
	void testRemoveQuiz() {

		Optional<Quiz> quiz = Optional.ofNullable(new Quiz());
		Mockito.when(quizDao.findById(anyInt())).thenReturn(quiz);

		QuizDto retrievedQuiz = quizService.remove(1);
		Mockito.verify(quizDao).deleteById(anyInt());
		assertEquals(quiz.get().getId(), retrievedQuiz.getId());

	}

	@Test
	void testRemoveNullQuiz() {

		Mockito.when(quizDao.findById(null)).thenThrow(NullPointerException.class);
		assertThrows(RuntimeException.class, () -> quizService.remove(null));

	}

	@Test
	void testViewQuiz() {

		Optional<Quiz> quiz = Optional.ofNullable(new Quiz());
		quiz.get().setId(1);
		Mockito.when(quizDao.findById(anyInt())).thenReturn(quiz);
		QuizDto retrievedQuiz = quizService.view(1);
		assertEquals(quiz.get().getId(), retrievedQuiz.getId());

	}

	@Test
	void testViewNullQuiz() {

		Mockito.when(quizDao.findById(null)).thenThrow(NoResultException.class);
		assertThrows(InvalidInputException.class, () -> quizService.view(null));

	}

	@Test
	void testViewAllQuiz() {
		Quiz quiz1 = new Quiz();
		quiz1.setId(1);
		Quiz quiz2 = new Quiz();
		quiz2.setId(2);

		List<Quiz> quizsList = Arrays.asList(quiz1, quiz2);
		Mockito.when(quizDao.findAll()).thenReturn(quizsList);
		List<Quiz> quizs = quizService.viewAll();
		assertEquals(quizsList, quizs);

	}

	@Test
	void testViewAllwithNoQuiz() {

		Mockito.when(quizDao.findAll()).thenThrow(NullPointerException.class);
		assertThrows(InvalidInputException.class, () -> quizService.viewAll());

	}

	@Test
	void checkAnswers() {
		Optional<Quiz> quiz = Optional.ofNullable(new Quiz());
		Question question = new Question();
		question.setId(1);
		question.setAnswer(2);
		Question question1 = new Question();
		question.setId(2);
		question.setAnswer(1);
		quiz.get().setMarksPerQuestion(12);
		quiz.get().setQuestions(Arrays.asList(question, question1));
		Mockito.when(quizDao.findById(anyInt())).thenReturn(quiz);
		int marks = quizService.checkAnswers("1\n1", 1);
		assertEquals(12, marks);
	}

	@Test
	void checkAnswersNull() {
		Optional<Quiz> quiz = Optional.ofNullable(new Quiz());
		quiz.get().setMarksPerQuestion(12);
		Mockito.when(quizDao.findById(anyInt())).thenReturn(quiz);
		assertThrows(InvalidInputException.class, () -> quizService.checkAnswers(null, 1));
	}

	@Test
	void testaddQuestionToQuiz() {
		QuestionDto questionDto = new QuestionDto(question);
		System.out.println(quiz);
		Optional<Quiz> localQuiz = Optional.ofNullable(quiz);
		Mockito.when(quizDao.findById(anyInt())).thenReturn(localQuiz);
		Mockito.when(questionService.view(anyInt())).thenReturn(questionDto);

		QuizDto resultQuiz = quizService.addQuestionToQuiz(1, 1);

		assertEquals(resultQuiz.getId(), quizDto.getId());

	}

	@Test
	void testaddNullQuestionToQuiz() {
		Mockito.when(quizDao.findById(anyInt())).thenThrow(InvalidInputException.class);
		assertThrows(InvalidInputException.class, () -> quizService.addQuestionToQuiz(1, 1));

	}

	@Test
	void testremoveQuestionToQuiz() {

		System.out.println(quiz);
		Optional<Quiz> localQuiz = Optional.ofNullable(quiz);
		Mockito.when(quizDao.findById(anyInt())).thenReturn(localQuiz);
		QuizDto resultQuiz = quizService.removeQuestionFromQuiz(1, 1);

		assertEquals(resultQuiz.getId(), quizDto.getId());

	}

	@Test
	void testremoveNullQuestionToQuiz() {
		Mockito.when(quizDao.findById(anyInt())).thenThrow(InvalidInputException.class);
		assertThrows(InvalidInputException.class, () -> quizService.addQuestionToQuiz(1, 1));

	}

}
