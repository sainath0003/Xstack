package com.epam.javaproject.mokito.controller;

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
import com.epam.project.controller.UserController;
import com.epam.project.dto.UserDto;
import com.epam.project.entity.User;
import com.epam.project.service.UserServiceInterface;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
	@Mock
	UserServiceInterface userService;

	@Mock
	ModelAndView mv;

	@InjectMocks
	UserController controller;

	private MockMvc mockMvc;
	User user;
	UserDto userDto;

//	MvcResult mvcResult = mockMvc.perform(get("/getQuiz"))
//			.andExpect(status().isOk())
//			.andExpect(model().attribute("quizzes",List.of(quiz)))
//			.andExpect(view().name("displayQuiz.jsp"))
//			.andReturn();
//	assertNotNull(mvcResult);

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		user = new User();
		user.setUserId(3);
		user.setUserName("hello");
		user.setUserType("admin");
		user.setPassword("123456");
		user.setEmailId("123@gmail.com");
		userDto = new UserDto(user);
	}

	@Test
	void testlogin() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/user/login")).andExpect(status().isOk())
				.andExpect(view().name("Login")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testsignup() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/user/signup")).andExpect(status().isOk())
				.andExpect(view().name("Signup")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testadminpage() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/user/adminwelcome")).andExpect(status().isOk())
				.andExpect(view().name("adminwelcomepage")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testclientpage() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/user/clientwelcome")).andExpect(status().isOk())
				.andExpect(view().name("clientwelcomepage")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testLoginpage() throws Exception {

		user.setUserType("admin");
		Mockito.when(userService.view(anyInt())).thenReturn(userDto);
		Mockito.when(userService.checkUser(any())).thenReturn(true);

		MvcResult mvcResult = mockMvc.perform(post("/user/checkuser").param("userType", "admin"))
				.andExpect(status().isOk())
				// .andExpect(model().attribute("user", userService.view(anyInt())))
				.andExpect(model().attribute("message", "Login Sucessfull")).andExpect(view().name("adminwelcomepage"))
				.andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testLoginpageforclient() throws Exception {

		user.setUserType("client");
		userDto.setUserType("client");
		Mockito.when(userService.view(anyInt())).thenReturn(userDto);
		Mockito.when(userService.checkUser(any())).thenReturn(true);

		MvcResult mvcResult = mockMvc.perform(post("/user/checkuser").param("userType", "client"))
				.andExpect(status().isOk())
				// .andExpect(model().attribute("user", userService.view(anyInt())))
				.andExpect(model().attribute("message", "Login Sucessfull")).andExpect(view().name("clientwelcomepage"))
				.andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testLoginpageforWrongUser() throws Exception {

		user.setUserType("admin");
		Mockito.when(userService.view(anyInt())).thenReturn(userDto);
		Mockito.when(userService.checkUser(any())).thenReturn(false);

		MvcResult mvcResult = mockMvc.perform(post("/user/checkuser").param("userType", "admin"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("message", "Login UnSucessfull,Enter Correct Details"))
				.andExpect(view().name("Login")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testLoginpageforWrongUserType() throws Exception {

		user.setUserType("admin");
		Mockito.when(userService.view(anyInt())).thenReturn(userDto);

		MvcResult mvcResult = mockMvc.perform(post("/user/checkuser").param("userType", "client"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("message", "Login UnSucessfull ,Enter Correct Details"))
				.andExpect(view().name("Login")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testLoginpageforNullUser() throws Exception {

		user.setUserType("admin");
		Mockito.when(userService.view(anyInt())).thenThrow(InvalidInputException.class);

		MvcResult mvcResult = mockMvc.perform(post("/user/checkuser").param("userType", "admin"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("message", "Login UnSucessfull ,Enter Correct Details"))
				.andExpect(view().name("Login")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testLoginpageforNullClientUser() throws Exception {

		user.setUserType("client");
		Mockito.when(userService.view(anyInt())).thenThrow(InvalidInputException.class);

		MvcResult mvcResult = mockMvc.perform(post("/user/checkuser").param("userType", "admin"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("message", "Login UnSucessfull ,Enter Correct Details"))
				.andExpect(view().name("Login")).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testSignup() throws Exception {
		Mockito.when(userService.save(any())).thenReturn(userDto);
		MvcResult mvcResult = mockMvc.perform(post("/user/saveuser")).andExpect(status().isOk())
				.andExpect(model().attribute("user", userService.view(user.getUserId())))
				.andExpect(model().attribute("message", "SignUp Sucessfull")).andExpect(view().name("Signupsucess"))
				.andReturn();
		assertNotNull(mvcResult);

	}

	@Test
	void testSignupNull() throws Exception {
		User userlocal= new User();
		userlocal.setUserId(1);
		Mockito.when(userService.save(any())).thenReturn(userDto);
		Mockito.when(userService.view(any())).thenThrow(InvalidInputException.class);
		MvcResult mvcResult = mockMvc.perform(post("/user/saveuser")).andExpect(status().isOk())
				//.andExpect(model().attribute("user", userService.view(anyInt())))
				.andExpect(model().attribute("message", "SignUp UnSucessfull ,Enter Correct Details"))
				.andExpect(view().name("Signup")).andReturn();
		assertNotNull(mvcResult);

	}

	@Test
	void testSignupNullInserted() throws Exception {
		Mockito.when(userService.save(any())).thenReturn(userDto);
		Mockito.when(userService.view(anyInt())).thenThrow(InvalidInputException.class);
		MvcResult mvcResult = mockMvc.perform(post("/user/saveuser")).andExpect(status().isOk())
				.andExpect(view().name("Signup")).andReturn();
		assertNotNull(mvcResult);

	}
	
	@Test
	void testSignupNullInsert() throws Exception {
		Mockito.when(userService.save(any())).thenReturn(null);
		//Mockito.when(userService.view(anyInt())).thenThrow(InvalidInputException.class);
		MvcResult mvcResult = mockMvc.perform(post("/user/saveuser")).andExpect(status().isOk())
				.andExpect(model().attribute("message", "SignUp UnSucessfull ,Enter Correct Details"))
				.andExpect(view().name("Signup")).
				andReturn();
		assertNotNull(mvcResult);
	
	}

}
