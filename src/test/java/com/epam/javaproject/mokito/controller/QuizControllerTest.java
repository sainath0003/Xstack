package com.epam.javaproject.mokito.controller;

import static org.hamcrest.CoreMatchers.anything;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import com.epam.project.commontools.InvalidInputException;
import com.epam.project.controller.QuizController;
import com.epam.project.dto.QuestionDto;
import com.epam.project.dto.QuizDto;
import com.epam.project.entity.Question;
import com.epam.project.entity.Quiz;
import com.epam.project.service.QuestionServiceInterface;
import com.epam.project.service.QuizServiceInterface;
import com.epam.project.service.UserServiceInterface;

@ExtendWith(MockitoExtension.class)
class QuizControllerTest {

	@Mock
	QuizServiceInterface quizService;
	@Mock
	QuestionServiceInterface questionService;
	@Mock
	UserServiceInterface userService;
	private MockMvc mockMvc;

//	MvcResult mvcResult = mockMvc.perform(get("/getQuiz"))
//	.andExpect(status().isOk())
//	.andExpect(model().attribute("quizzes",List.of(quiz)))
//	.andExpect(view().name("displayQuiz.jsp"))
//	.andReturn();
//assertNotNull(mvcResult);

	@Mock
	ModelAndView mv;
	Quiz quiz;
	QuizDto quizDto;
	Question question;
	@InjectMocks
	QuizController controller;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		quizDto = new QuizDto();
		quiz = new Quiz(quizDto);
		question = new Question();
		question.setId(6);
		question.setAnswer(2);
		List<Question> questions = new ArrayList<>();
		questions.add(question);

		quiz.setQuestions(questions);

	}

	@Test
	void testwelcome() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/quiz/welcome")).andExpect(status().isOk())
				.andExpect(view().name("welcomepage")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testlibrary() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/quiz/library")).andExpect(status().isOk())
				.andExpect(view().name("quizwelcomepage")).andReturn();
		assertNotNull(mvcResult);
	}

//	@Test
//	void testadd() throws Exception {
//		MvcResult mvcResult = mockMvc.perform(get("/quiz/add")).andExpect(status().isOk())
//				.andExpect(view().name("createquiz")).andReturn();
//		assertNotNull(mvcResult);
//	}

	@Test
	void testattempt() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/quiz/attempt")).andExpect(status().isOk())
				.andExpect(view().name("attemptquiz")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testremove() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/quiz/delete")).andExpect(status().isOk())
				.andExpect(view().name("removequiz")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testview() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/quiz/display").param("id", "1")).andExpect(status().isOk())
				.andExpect(view().name("viewquiz")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testupdate() throws Exception {
		Mockito.when(quizService.view(anyInt())).thenReturn(quizDto);
		MvcResult mvcResult = mockMvc.perform(get("/quiz/modify").param("id", "1")).andExpect(status().isOk())
				.andExpect(model().attribute("quiz", quizService.view(anyInt()))).andExpect(view().name("updatequiz"))
				.andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testwrite() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/quiz/write")).andExpect(status().isOk())
				.andExpect(view().name("writequiz")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testwriteNull() throws Exception {
		Mockito.when(quizService.viewAll()).thenThrow(InvalidInputException.class);
		MvcResult mvcResult = mockMvc.perform(get("/quiz/write")).andExpect(status().isOk())
				.andExpect(model().attribute("message", "No Quiz found")).andExpect(view().name("writequiz"))
				.andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testwritepre() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/quiz/write2")).andExpect(status().isOk())
				.andExpect(view().name("writequiz2")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testsucess() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/quiz/sucess")).andExpect(status().isOk())
				.andExpect(view().name("sucesspage")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testgetallQuestions() throws Exception {
		Mockito.when(questionService.viewAll()).thenReturn(null);

		MvcResult mvcResult = mockMvc.perform(get("/quiz/add")).andExpect(status().isOk())
				.andExpect(model().attribute("questions", questionService.viewAll()))
				.andExpect(view().name("createquiz")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testgetNullQuestions() throws Exception {
		Mockito.when(questionService.viewAll()).thenThrow(InvalidInputException.class);

		MvcResult mvcResult = mockMvc.perform(get("/quiz/add")).andExpect(status().isOk())
				.andExpect(model().attribute("message", "No Questions found")).andExpect(view().name("createquiz"))
				.andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testgetallQuizs() throws Exception {
		Mockito.when(quizService.viewAll()).thenReturn(null);

		MvcResult mvcResult = mockMvc.perform(get("/quiz/displayall")).andExpect(status().isOk())
				.andExpect(model().attribute("quizs", quizService.viewAll())).andExpect(view().name("displayallquiz"))
				.andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testgetNullQuizs() throws Exception {
		Mockito.when(quizService.viewAll()).thenThrow(InvalidInputException.class);

		MvcResult mvcResult = mockMvc.perform(get("/quiz/displayall")).andExpect(status().isOk())
				.andExpect(model().attribute("message", "No Quiz found")).andExpect(view().name("displayallquiz"))
				.andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testaddQuizs() throws Exception {
		Mockito.when(quizService.add(any())).thenReturn(null);
		Mockito.when(questionService.view(any())).thenReturn(new QuestionDto(question));
		// Mockito.when(questionService.getQuestions(any())).thenReturn(null);
		Mockito.when(questionService.viewAll()).thenReturn(null);
		MvcResult mvcResult = mockMvc.perform(post("/quiz/add")).andExpect(status().isOk())
				.andExpect(model().attribute("quizs", quizService.add(any())))
				.andExpect(model().attribute("message", "quiz created Sucessfully"))
				.andExpect(model().attribute("questions", questionService.viewAll()))
				.andExpect(view().name("addquestiontoquiz")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testaddNullQuizs() throws Exception {
		Mockito.when(questionService.view(any())).thenThrow(InvalidInputException.class);

		// Mockito.when(questionService.getQuestions(any())).thenReturn(null);
		// Mockito.when(quizService.add(any())).thenThrow(InvalidInputException.class);

		MvcResult mvcResult = mockMvc.perform(post("/quiz/add")).andExpect(status().isOk())
				.andExpect(model().attribute("message", "No Quiz created")).andExpect(view().name("addquestiontoquiz"))
				.andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testupdateQuizs() throws Exception {
		// Mockito.when(quizService.view(anyInt())).thenReturn(null);
		MvcResult mvcResult = mockMvc.perform(post("/quiz/modify").param("id", "1")).andExpect(status().isOk())
				.andExpect(model().attribute("quiz", quizService.view(any())))
				// .andExpect(model().attribute("message", "quiz created Sucessfully"))
				.andExpect(view().name("updatequizpre")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testupdateNullQuizs() throws Exception {

		Mockito.when(quizService.view(anyInt())).thenThrow(InvalidInputException.class);

		MvcResult mvcResult = mockMvc.perform(get("/quiz/modify").param("id", "1")).andExpect(status().isOk())
				.andExpect(model().attribute("message", "No Quiz Found")).andExpect(view().name("updatequiz"))
				.andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testupdateQuiz() throws Exception {

		Mockito.when(quizService.view(any())).thenReturn(quizDto);

		Mockito.when(quizService.update(any())).thenReturn(null);
		// Mockito.when(questionService.getQuestions(any())).thenReturn(null);
		MvcResult mvcResult = mockMvc.perform(post("/quiz/modifyquiz").param("id", "1")).andExpect(status().isOk())
				.andExpect(model().attribute("quiz", quizService.update(any())))
				.andExpect(model().attribute("message", "quiz updated Sucessfully"))
				.andExpect(view().name("addquestiontoquiz")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testupdateNullQuiz() throws Exception {

		Mockito.when(quizService.view(any())).thenThrow(InvalidInputException.class);

		MvcResult mvcResult = mockMvc.perform(post("/quiz/modifyquiz").param("id", "1")).andExpect(status().isOk())
				.andExpect(model().attribute("message", "No Quiz present")).andExpect(view().name("addquestiontoquiz"))
				.andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testdisplayQuiz() throws Exception {
		Mockito.when(quizService.view(anyInt())).thenReturn(null);
		MvcResult mvcResult = mockMvc.perform(get("/quiz/display").param("id", "1")).andExpect(status().isOk())
				.andExpect(model().attribute("quiz", quizService.view(anyInt())))
				.andExpect(model().attribute("message", "quiz Found")).andExpect(view().name("viewquiz")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testdisplayNullQuiz() throws Exception {

		Mockito.when(quizService.view(any())).thenThrow(InvalidInputException.class);

		MvcResult mvcResult = mockMvc.perform(get("/quiz/display").param("id", "1")).andExpect(status().isOk())
				.andExpect(model().attribute("message", "No Quiz Found")).andExpect(view().name("viewquiz"))
				.andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testdeleteQuiz() throws Exception {
		// Mockito.when(quizService.view(anyInt())).thenReturn(null);
		MvcResult mvcResult = mockMvc.perform(post("/quiz/delete").param("id", "1")).andExpect(status().isOk())
				// .andExpect(model().attribute("quiz", quizService.view(anyInt())))
				.andExpect(model().attribute("message", anything())).andExpect(view().name("removequiz")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testdeleteNullQuiz() throws Exception {

		Mockito.when(quizService.remove(any())).thenThrow(InvalidInputException.class);

		MvcResult mvcResult = mockMvc.perform(post("/quiz/delete").param("id", "1")).andExpect(status().isOk())
				.andExpect(model().attribute("message", "Quiz Not removed")).andExpect(view().name("removequiz"))
				.andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testdeletefordisplayQuiz() throws Exception {
		Mockito.when(quizService.viewAll()).thenReturn(null);
		MvcResult mvcResult = mockMvc.perform(get("/quiz/deletefromdisplay").param("id", "1"))
				.andExpect(status().isOk()).andExpect(model().attribute("quizs", quizService.viewAll()))
				.andExpect(model().attribute("message", anything())).andExpect(view().name("displayallquiz"))
				.andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testdeletefordisplayNullQuiz() throws Exception {

		Mockito.when(quizService.remove(anyInt())).thenThrow(InvalidInputException.class);

		MvcResult mvcResult = mockMvc.perform(get("/quiz/deletefromdisplay").param("id", "1"))
				.andExpect(status().isOk()).andExpect(model().attribute("message", "Quiz Not removed"))
				.andExpect(view().name("displayallquiz")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testdisplayforupdateQuiz() throws Exception {
		Mockito.when(quizService.view(anyInt())).thenReturn(null);
		MvcResult mvcResult = mockMvc.perform(get("/quiz/displayquizforupdate").param("id", "1"))
				.andExpect(status().isOk()).andExpect(model().attribute("quizs", quizService.view(anyInt())))
				.andExpect(model().attribute("message", "quiz updated Sucessfully"))
				.andExpect(view().name("updatequiz")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testdisplayforupdateNullQuiz() throws Exception {

		Mockito.when(quizService.view(anyInt())).thenThrow(InvalidInputException.class);

		MvcResult mvcResult = mockMvc.perform(get("/quiz/displayquizforupdate").param("id", "1"))
				.andExpect(status().isOk()).andExpect(model().attribute("message", "No Quiz Found"))
				.andExpect(view().name("updatequiz")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testgetQuestionsQuiz() throws Exception {
		Mockito.when(quizService.view(anyInt())).thenReturn(quizDto);
		// Mockito.when(quizService.view(anyInt()).getQuestions()).thenReturn(null);

		MvcResult mvcResult = mockMvc.perform(get("/quiz/displayforquiz").param("id", "1")).andExpect(status().isOk())
				.andExpect(model().attribute("quiz", quizService.view(anyInt())))
				.andExpect(model().attribute("message", "displaying questions of quiz"))
				.andExpect(view().name("attemptquiz")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testgetQuestionsNullQuiz() throws Exception {

		Mockito.when(quizService.view(anyInt())).thenThrow(InvalidInputException.class);

		MvcResult mvcResult = mockMvc.perform(get("/quiz/displayforquiz").param("id", "1")).andExpect(status().isOk())
				.andExpect(model().attribute("message", "No Questions Found")).andExpect(view().name("attemptquiz"))
				.andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testgetanswersQuiz() throws Exception {
		Mockito.when(quizService.view(anyInt())).thenReturn(quizDto);
		Mockito.when(questionService.view(anyInt())).thenReturn(new QuestionDto(question));

		MvcResult mvcResult = mockMvc.perform(post("/quiz/checkanswers").param("quizid", "1").param("6", "2"))
				.andExpect(status().isOk()).andExpect(model().attribute("score", anything()))
				.andExpect(model().attribute("message", "displaying questions of quiz"))
				.andExpect(view().name("sucesspage")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testgetanswersNullQuiz() throws Exception {

		Mockito.when(quizService.view(anyInt())).thenThrow(InvalidInputException.class);

		MvcResult mvcResult = mockMvc.perform(post("/quiz/checkanswers").param("quizid", "1"))
				.andExpect(status().isOk()).andExpect(model().attribute("message", "Cannot get Quiz score"))
				.andExpect(view().name("sucesspage")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testgetQuestionForQuiz() throws Exception {
		List<Question> questions = new ArrayList<>();

		question = new Question();
		question.setId(3);
		question.setAnswer(5);
		quizDto.setQuestions(questions);
		questions.add(question);
		Mockito.when(quizService.addQuestionToQuiz(anyInt(),anyInt())).thenReturn(quizDto);

		MvcResult mvcResult = mockMvc.perform(get("/quiz/addquestion").param("quizid", "1").param("questionid", "2"))
				.andExpect(status().isOk()).andExpect(model().attribute("quiz", quizService.update(quizDto)))
				.andExpect(model().attribute("questions", questionService.viewAll()))
				.andExpect(model().attribute("message", "Question added Sucessfully"))
				.andExpect(view().name("addquestiontoquiz")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testgetNullQuestionForQuiz() throws Exception {

		Mockito.when(quizService.addQuestionToQuiz(anyInt(), anyInt()))
				.thenThrow(InvalidInputException.class);
		;
		// Mockito.when(questionService.view(anyInt())).thenThrow(InvalidInputException.class);

		MvcResult mvcResult = mockMvc.perform(get("/quiz/addquestion").param("quizid", "1").param("questionid", "2"))
				.andExpect(status().isOk())
				// .andExpect(model().attribute("quiz",quizService.update(quiz)))
				// .andExpect(model().attribute("questions", questionService.viewAll()))
				.andExpect(model().attribute("message", "No Questions Found"))
				.andExpect(view().name("addquestiontoquiz")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testdeleteQuestionForQuiz() throws Exception {
		question = new Question();
		question.setId(6);
		question.setAnswer(2);
		List<Question> questions = new ArrayList<>();
		questions.add(question);
		quizDto.setQuestions(questions);
		quiz.setQuestions(questions);

		Mockito.when(quizService.removeQuestionFromQuiz(anyInt(),anyInt())).thenReturn(quizDto);
// Mockito.when(questionService.view(anyInt())).thenReturn(new
		// QuestionDto(question));

		MvcResult mvcResult = mockMvc.perform(get("/quiz/deletequestion").param("quizid", "1").param("questionid", "2"))
				.andExpect(status().isOk()).andExpect(model().attribute("quiz", quizService.update(quizDto)))
				.andExpect(model().attribute("questions", questionService.viewAll()))
				.andExpect(model().attribute("message", "Question removed Sucessfully"))
				.andExpect(view().name("addquestiontoquiz")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testdeleteNullQuestionForQuiz() throws Exception {

		Mockito.when(quizService.removeQuestionFromQuiz(anyInt(), anyInt()))
				.thenThrow(InvalidInputException.class);

		// Mockito.when(questionService.view(anyInt())).thenThrow(InvalidInputException.class);

		MvcResult mvcResult = mockMvc
				.perform(get("/quiz/deletequestion").param("quizid", "1").param("questionid", "2"))
				.andExpect(status().isOk())
				// .andExpect(model().attribute("quiz",quizService.update(quiz)))
				// .andExpect(model().attribute("questions", questionService.viewAll()))
				.andExpect(model().attribute("message", "No Questions Found"))
				.andExpect(view().name("addquestiontoquiz")).andReturn();
		assertNotNull(mvcResult);
	}
}
