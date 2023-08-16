package com.epam.javaproject.mokito.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

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
import org.springframework.dao.OptimisticLockingFailureException;

import com.epam.project.commontools.InvalidInputException;
import com.epam.project.dto.QuestionDto;
import com.epam.project.entity.Question;
import com.epam.project.repo.QuestionsRepo;
import com.epam.project.service.QuestionService;

import jakarta.persistence.NoResultException;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {
	@Mock
	private QuestionsRepo questionDao;

	@InjectMocks
	private QuestionService questionService;

	QuestionDto questionDto;

	@BeforeEach
	void setup() {
		questionDto = new QuestionDto();
		questionDto.setId(2);
		questionDto.setAnswer(1);
		questionDto.setDescription("p");
		questionDto.setDifficulty("POP");
		questionDto.setId(9);
		questionDto.setNumberOfOptions(3);
		questionDto.setTitle("hello");
	}

	@Test
	void testAddQuestion() {

		Question question = new Question(questionDto);

		Mockito.when(questionDao.save(any())).thenReturn(question);
		QuestionDto retrievedQuestion = questionService.add(questionDto);
		assertEquals(questionDto.getId(), retrievedQuestion.getId());

	}

	@Test
	void testAddNullQuestion() {
		
		Mockito.when(questionDao.save(any())).thenThrow(OptimisticLockingFailureException .class);

		assertThrows(OptimisticLockingFailureException .class, () -> questionService.add(questionDto));

	}

	@Test
	void testAddNullQuestions() {

		assertThrows(InvalidInputException.class, () -> questionService.add(null));

	}

	@Test
	void testUpdateQuestion() {

		questionDto.setId(2);
		Question question = new Question(questionDto);

		Mockito.when(questionDao.save(any())).thenReturn(question);
		QuestionDto retrievedQuestion = questionService.update(questionDto);
		assertEquals(questionDto.getId(), retrievedQuestion.getId());

	}

	@Test
	void testUpdateNullQuestion() {

		assertThrows(InvalidInputException.class, () -> questionService.update(null));

	}

	@Test
	void testUpdtaeNullQuestion() {

		Mockito.when(questionDao.save(any())).thenThrow(OptimisticLockingFailureException.class);

		assertThrows(InvalidInputException.class, () -> questionService.update(questionDto));

	}

	@Test
	void testRemoveQuestion() {

		Optional<Question> question = Optional.ofNullable(new Question());
		Mockito.when(questionDao.findById(anyInt())).thenReturn(question);

		QuestionDto retrievedQuestion = questionService.remove(1);
		Mockito.verify(questionDao).deleteById(anyInt());

		assertEquals(question.get().getId(), retrievedQuestion.getId());

	}

	@Test
	void testRemoveNullQuestion() {

		Mockito.when(questionDao.findById(null)).thenThrow(IllegalArgumentException.class);
		assertThrows(InvalidInputException.class, () -> questionService.remove(null));

	}
	@Test
	void testRemoveNullQuestions() {

		Mockito.when(questionDao.findById(any())).thenThrow(OptimisticLockingFailureException.class);
		assertThrows(InvalidInputException.class, () -> questionService.remove(null));
		//assertThrows(OptimisticLockingFailureException.class, () -> questionService.update(questionDto));

	}

	@Test
	void testViewQuestion() {

		Optional<Question> question = Optional.ofNullable(new Question());
		question.get().setId(1);
		Mockito.when(questionDao.findById(anyInt())).thenReturn(question);
		QuestionDto retrievedQuestion = questionService.view(1);

		assertEquals(question.get().getId(), retrievedQuestion.getId());

	}

	@Test
	void testViewNullQuestion() {

		Mockito.when(questionDao.findById(null)).thenThrow(IllegalArgumentException.class);
		assertThrows(InvalidInputException.class, () -> questionService.view(null));

	}
	@Test
	void testViewNullQuestions() {

		Optional<Question> question = Optional.ofNullable(new Question());
		question.get().setId(1);
		Mockito.when(questionDao.findById(anyInt())).thenReturn(null);
		assertThrows(InvalidInputException.class, () -> questionService.view(1));

	}

	@Test
	void testViewAllQuestion() {
		Question question1 = new Question();
		question1.setId(1);
		Question question2 = new Question();
		question2.setId(2);

		List<Question> questionsList = Arrays.asList(question1, question2);
		Mockito.when(questionDao.findAll()).thenReturn(questionsList);
		List<Question> questions = questionService.viewAll();
		assertEquals(questionsList, questions);

	}

	@Test
	void testViewAllwithNoQuestion() {

		Mockito.when(questionDao.findAll()).thenThrow(NoResultException.class);
		assertThrows(InvalidInputException.class, () -> questionService.viewAll());

	}

	

}
