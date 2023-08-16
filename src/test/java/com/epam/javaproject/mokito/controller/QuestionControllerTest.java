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
import com.epam.project.controller.QuestionController;
import com.epam.project.dto.QuestionDto;
import com.epam.project.entity.Question;
import com.epam.project.service.QuestionServiceInterface;

@ExtendWith(MockitoExtension.class)
class QuestionControllerTest {

	@Mock
	QuestionServiceInterface questionService;

	@Mock
	ModelAndView mv;

	@InjectMocks
	QuestionController controller;

	private MockMvc mockMvc;
	Question question;
	QuestionDto questionDto;

//	MvcResult mvcResult = mockMvc.perform(get("/getQuiz"))
//	.andExpect(status().isOk())
//	.andExpect(model().attribute("quizzes",List.of(quiz)))
//	.andExpect(view().name("displayQuiz.jsp"))
//	.andReturn();
//assertNotNull(mvcResult);

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		questionDto = new QuestionDto();

		question = new Question(questionDto);
	}

	@Test
	void testlibrary() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/question/library")).andExpect(status().isOk())
				.andExpect(view().name("questionwelcomepage")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testadd() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/question/add")).andExpect(status().isOk())
				.andExpect(view().name("createquestion")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testremove() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/question/delete")).andExpect(status().isOk())
				.andExpect(view().name("removequestion")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testview() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/question/display")).andExpect(status().isOk())
				.andExpect(view().name("viewquestion")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testmodify() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/question/modify")).andExpect(status().isOk())
				.andExpect(view().name("updatequestionPre")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testdisplayall() throws Exception {
		Mockito.when(questionService.viewAll()).thenReturn(null);
		MvcResult mvcResult = mockMvc.perform(get("/question/displayall")).andExpect(status().isOk())
				.andExpect(status().isOk())
				.andExpect(model().attribute("questions", questionService.viewAll()))
				.andExpect(view().name("displayallquestions")).andReturn();
		assertNotNull(mvcResult);
	}
	@Test
	void testdisplayallNull() throws Exception {
		Mockito.when(questionService.viewAll()).thenThrow(InvalidInputException.class);
		MvcResult mvcResult = mockMvc.perform(get("/question/displayall")).andExpect(status().isOk())
				.andExpect(status().isOk())
				.andExpect(model().attribute("message", "No Questions Found"))
				.andExpect(view().name("displayallquestions")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testcreateQuestion() throws Exception {
		Mockito.when(questionService.add(any())).thenReturn(questionDto);
		
		MvcResult mvcResult = mockMvc.perform(post("/question/add")).andExpect(status().isOk())
				.andExpect(status().isOk())
				.andExpect(model().attribute("question", questionService.add(any())))
				.andExpect(model().attribute("message", "question created Sucessfully"))
				.andExpect(view().name("createquestion")).andReturn();
		assertNotNull(mvcResult);
		
	}
	@Test
	void testcreateNullQuestion() throws Exception {
		Mockito.when(questionService.add(any())).thenThrow(InvalidInputException.class);
		
		MvcResult mvcResult = mockMvc.perform(post("/question/add")).andExpect(status().isOk())
				.andExpect(status().isOk())
				.andExpect(model().attribute("message", anything()))
				.andExpect(view().name("createquestion")).andReturn();
		assertNotNull(mvcResult);
		
	}
	
	@Test
	void testUpdateQuestion() throws Exception {
		Mockito.when(questionService.update(any())).thenReturn(questionDto);
		
		MvcResult mvcResult = mockMvc.perform(post("/question/modify")).andExpect(status().isOk())
				.andExpect(status().isOk())
				.andExpect(model().attribute("question", questionService.update(any())))
				.andExpect(model().attribute("message", "question updated Sucessfully"))
				.andExpect(view().name("updatesucess")).andReturn();
		assertNotNull(mvcResult);
		
	}
	@Test
	void testUpdateNullQuestion() throws Exception {
		Mockito.when(questionService.update(any())).thenThrow(InvalidInputException.class);
		
		MvcResult mvcResult = mockMvc.perform(post("/question/modify")).andExpect(status().isOk())
				.andExpect(status().isOk())
				.andExpect(model().attribute("message", anything()))
				.andExpect(view().name("updatesucess")).andReturn();
		assertNotNull(mvcResult);
		
	}
	
	@Test
	void testdisplayQuestion() throws Exception {
		Mockito.when(questionService.view(anyInt())).thenReturn(questionDto);
		
		MvcResult mvcResult = mockMvc.perform(post("/question/display").param("id", "1")).andExpect(status().isOk())
				.andExpect(status().isOk())
				.andExpect(model().attribute("question", questionService.view(anyInt())))
				.andExpect(model().attribute("message", "question Found"))
				.andExpect(view().name("viewquestion")).andReturn();
		assertNotNull(mvcResult);
		
	}
	@Test
	void testdisplayNullQuestion() throws Exception {
		Mockito.when(questionService.view(anyInt())).thenThrow(InvalidInputException.class);
		
		MvcResult mvcResult = mockMvc.perform(post("/question/display").param("id", "1")).andExpect(status().isOk())
				.andExpect(status().isOk())
				.andExpect(model().attribute("message", anything()))
				.andExpect(view().name("viewquestion")).andReturn();
		assertNotNull(mvcResult);
		
	}
	
	@Test
	void testdeletefromdisplayQuestion() throws Exception {
		Mockito.when(questionService.viewAll()).thenReturn(null);
		
		MvcResult mvcResult = mockMvc.perform(get("/question/deletefromdisplay").param("id", "1")).andExpect(status().isOk())
				.andExpect(status().isOk())
				.andExpect(model().attribute("questions", questionService.viewAll()))
				.andExpect(model().attribute("message", anything()))
				.andExpect(view().name("displayallquestions")).andReturn();
		assertNotNull(mvcResult);
		
	}
	@Test
	void testdeletefromdisplayNullQuestion() throws Exception {
Mockito.when(questionService.viewAll()).thenThrow(InvalidInputException.class);
		
		MvcResult mvcResult = mockMvc.perform(get("/question/deletefromdisplay").param("id", "1")).andExpect(status().isOk())
				.andExpect(status().isOk())
				//.andExpect(model().attribute("questions", questionService.viewAll()))
				.andExpect(model().attribute("message", "Question cannot be deleted"))
				.andExpect(view().name("removefailure")).andReturn();
		assertNotNull(mvcResult);
		
	}
	@Test
	void testdeleteQuestion() throws Exception {
		Mockito.when(questionService.remove(anyInt())).thenReturn(questionDto);
		
		MvcResult mvcResult = mockMvc.perform(post("/question/delete").param("id", "1")).andExpect(status().isOk())
				.andExpect(status().isOk())
				//.andExpect(model().attribute("question", questionService.view(anyInt())))
				.andExpect(model().attribute("message", anything()))
				.andExpect(view().name("removequestion")).andReturn();
		assertNotNull(mvcResult);
		
	}
	@Test
	void testdeleteNullQuestion() throws Exception {
		Mockito.when(questionService.remove(anyInt())).thenThrow(InvalidInputException.class);
		
		MvcResult mvcResult = mockMvc.perform(post("/question/delete").param("id", "1")).andExpect(status().isOk())
				.andExpect(status().isOk())
				.andExpect(model().attribute("message", "Question cannot be deleted"))
				.andExpect(view().name("removefailure")).andReturn();
		assertNotNull(mvcResult);
		
	}
	@Test
	void testgetQuestion() throws Exception {
		Mockito.when(questionService.view(anyInt())).thenReturn(questionDto);
		
		MvcResult mvcResult = mockMvc.perform(get("/question/displayquestionforupdate").param("id", "1")).andExpect(status().isOk())
				.andExpect(status().isOk())
				.andExpect(model().attribute("question", questionService.view(anyInt())))
				//.andExpect(model().attribute("message", "question Found"))
				.andExpect(view().name("updatequestion")).andReturn();
		assertNotNull(mvcResult);
		
	}
	@Test
	void testgetNullQuestion() throws Exception {
		Mockito.when(questionService.view(anyInt())).thenThrow(InvalidInputException.class);
		
		MvcResult mvcResult = mockMvc.perform(get("/question/displayquestionforupdate").param("id", "1")).andExpect(status().isOk())
				.andExpect(status().isOk())
				.andExpect(model().attribute("message", "No Question with given id found create the questions"))
				.andExpect(view().name("updatequestion")).andReturn();
		assertNotNull(mvcResult);
		
	}

}
