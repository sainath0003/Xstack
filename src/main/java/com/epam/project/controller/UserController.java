package com.epam.project.controller;

import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.project.commontools.InvalidInputException;
import com.epam.project.dto.UserDto;
import com.epam.project.entity.User;
import com.epam.project.service.UserServiceInterface;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServiceInterface userService;
	@Autowired
	ModelMapper mapper;

	String userString = "user";
	String message = "message";
	String loginString = "Login";
	String signup ="Signup";

	@RequestMapping("/login")
	public String login() {

		return loginString;
	}

	@RequestMapping("/signup")
	public String signup() {

		return signup;
	}

	@GetMapping("/adminwelcome")
	public String adminpage() {
		return "adminwelcomepage";
	}

	@GetMapping("/clientwelcome")
	public String clientpage() {
		return "clientwelcomepage";
	}

	@PostMapping("/checkuser")
	public ModelAndView login(UserDto user) {
		ModelAndView mv = new ModelAndView();

		try {
			User dbUser = new User(userService.view(user.getUserId()));

			validateUser(user, mv, dbUser);
			if (userService.checkUser(user)) {
				mv.addObject("user", userService.view(user.getUserId()));
				mv.addObject(message, "Login Sucessfull");
			} else {

				mv.setViewName(loginString);
				mv.addObject(message, "Login UnSucessfull,Enter Correct Details");
			}
		} catch (InvalidInputException | NullPointerException | NoSuchElementException e) {

			mv.setViewName(loginString);
			mv.addObject(message, "Login UnSucessfull ,Enter Correct Details");
		}
		return mv;
	}

	private void validateUser(UserDto user, ModelAndView mv, User dbUser) {
		if (!dbUser.getUserType().equalsIgnoreCase(user.getUserType())) {
			throw new InvalidInputException("Wrong credentials");
		}
		if (user.getUserType().equalsIgnoreCase("admin")) {

			mv.setViewName("adminwelcomepage");
		} else {

			mv.setViewName("clientwelcomepage");
		}
	}

	@PostMapping("/saveuser")
	public ModelAndView signup(UserDto user) {
		ModelAndView mv = new ModelAndView();
		try {
			user = userService.save(user);
			if (user != null) {
				mv.setViewName("Signupsucess");
				mv.addObject("user", userService.view(user.getUserId()));
				mv.addObject(message, "SignUp Sucessfull");
			} else {

				mv.setViewName(signup);
				mv.addObject(message, "SignUp UnSucessfull ,Enter Correct Details");
			}

		} catch (InvalidInputException e) {
			mv.setViewName(signup);
			mv.addObject(message,"SignUp UnSucessfull ,Enter Correct Details");
		}
		return mv;
	}

}
