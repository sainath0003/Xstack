package com.epam.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.project.commontools.InvalidInputException;
import com.epam.project.dto.QuizDto;
import com.epam.project.entity.Question;
import com.epam.project.entity.Quiz;
import com.epam.project.service.QuestionServiceInterface;
import com.epam.project.service.QuizServiceInterface;
import com.epam.project.service.UserServiceInterface;

import jakarta.servlet.http.HttpServletRequest;


@RequestMapping("/quiz")
public class QuizController2 {

	@Autowired
	UserServiceInterface userService;
	@Autowired
	QuestionServiceInterface questionService;
	@Autowired
	QuizServiceInterface quizService;
	@Autowired
	ModelMapper mapper;

	String questionString = "question";
	String quizString = "quiz";
	String userString = "user";
	String message = "message";
	String updatequestion = "updatequestion";
	String updatequiz = "updatequiz";
	String writequiz = "writequiz";
	String createquizString = "createquiz";
	String quizs = "quizs";
	String quiznotfound = "No Quiz Found";
	String questions = "questions";
	String addquestiontoquiz = "addquestiontoquiz";
	String questionNotFound = "No Questions Found";
	String createQuestionSucessfully = "quiz created Sucessfully";

	@RequestMapping("/welcome")
	public String welcome() {

		return "welcomepage";
	}

	@RequestMapping("/library")
	public String quizlibrary() {

		return "quizwelcomepage";
	}

	@RequestMapping("/attempt")
	public String attemptquiz() {

		return "attemptquiz";
	}

	@RequestMapping("/delete")
	public String removequiz() {

		return "removequiz";
	}

	@RequestMapping("/display")
	public String viewquiz() {

		return "viewquiz";
	}

	@RequestMapping("/modify")
	public String updatequiz() {
		return "updatequizpre";
	}

	@RequestMapping("/write")
	public String writequiz() {
		return writequiz;
	}

	@RequestMapping("/write2")
	public String write2quiz() {
		return "writequiz2";
	}

	@RequestMapping("/sucess")
	public String sucess() {
		return "sucesspage";
	}

	@GetMapping("/add")
	public ModelAndView getAllQuestion() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName(createquizString);
		try {
			mv.addObject(questions, questionService.viewAll());
		} catch (InvalidInputException e) {
			mv.addObject(message, "No Questions found");
		}
		return mv;

	}

	@GetMapping("/displayall")
	public ModelAndView getAllQuiz() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("displayallquiz");
		try {
			mv.addObject(quizs, quizService.viewAll());
		} catch (InvalidInputException e) {
			mv.addObject(message, "No Quiz found");
		}
		return mv;
	}

	@GetMapping("/write")
	public ModelAndView getAllQuizforuser() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName(writequiz);
		try {
			mv.addObject(quizs, quizService.viewAll());
		} catch (InvalidInputException e) {
			mv.addObject(message, "No Quiz found");
		}
		return mv;
	}

	@PostMapping("/add")
	public ModelAndView createQuiz(QuizDto quizdto) {
		

		ModelAndView mv = new ModelAndView();
		mv.setViewName(addquestiontoquiz);
		try {
			List<Question> questionsList = new ArrayList<>();
			questionsList.add(mapper.map(questionService.view(1), Question.class));
			quizdto.setQuestions(questionsList);
			mv.addObject("quiz", quizService.add(quizdto));
			mv.addObject(questions, questionService.viewAll());
			mv.addObject(message, createQuestionSucessfully);
		} catch (InvalidInputException e) {
			mv.addObject(message, "No Quiz created");
		}
		return mv;
	}

	@GetMapping("/modify")
	public ModelAndView updatequiz(int id) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName(updatequiz);
		try {
			QuizDto quizdto =quizService.view(id);
			mv.addObject(quizString, quizdto);
		} catch (InvalidInputException e) {
			mv.addObject(message, quiznotfound);
		}
		return mv;
	}

	@PostMapping("/modifyquiz")
	public ModelAndView updatequiz(QuizDto quizdto) {
		Quiz quiz = new Quiz(quizdto);

		ModelAndView mv = new ModelAndView();
		mv.setViewName(addquestiontoquiz);
		try {
			List<Question> questionsList = quizService.view(quizdto.getId()).getQuestions();
			quiz.setQuestions(questionsList);
			mv.addObject(quizString, quizService.update(mapper.map(quiz, QuizDto.class)));
			mv.addObject(message, "quiz updated Sucessfully");

			mv.addObject(questions, questionService.viewAll());

		} catch (InvalidInputException e) {
			mv.addObject(message, "No Quiz present");
		}
		return mv;
	}

	@GetMapping("/display")
	public ModelAndView viewquiz(int id) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("viewquiz");
		try {
			mv.addObject(quizString, quizService.view(id));
			mv.addObject(message, "quiz Found");
		} catch (InvalidInputException e) {
			mv.addObject(message, quiznotfound);
		}
		return mv;
	}

	@PostMapping("/delete")
	public ModelAndView removequiz(int id) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("removequiz");
		try {
			quizService.remove(id);
			mv.addObject(message, "quiz removed Sucessfully with id " + id);
		} catch (InvalidInputException e) {
			mv.addObject(message, "Quiz Not removed");
		}
		return mv;
	}

	@GetMapping("/deletefromdisplay")
	public ModelAndView removequizfromdisplay(int id) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("displayallquiz");
		try {
			quizService.remove(id);
			mv.addObject(quizs, quizService.viewAll());
			mv.addObject(message, "quiz removed Sucessfully with id " + id);
		} catch (InvalidInputException e) {
			mv.addObject(message, "Quiz Not removed");
		}
		return mv;
	}

	@GetMapping("/displayquizforupdate")
	public ModelAndView getAllQuiz(int id) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName(updatequiz);
		try {
			mv.addObject(quizs, quizService.view(id));
			mv.addObject(message, "quiz updated Sucessfully");
		} catch (InvalidInputException e) {
			mv.addObject(message, "No Quiz Found");
		}
		return mv;
	}

	@GetMapping("/displayforquiz")
	public ModelAndView getquestionsForQuiz(int id) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("attemptquiz");
		try {
			mv.addObject("quiz", quizService.view(id));
			mv.addObject(questions, quizService.view(id).getQuestions());
			mv.addObject(message, "displaying questions of quiz");

		} catch (InvalidInputException e) {
			mv.addObject(message, questionNotFound);
		}
		return mv;

	}

	@GetMapping("/addquestion")
	public ModelAndView addQuestiontoQuiz(int questionid, int quizid) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName(addquestiontoquiz);
		try {
			Quiz quiz = mapper.map(quizService.view(quizid), Quiz.class);
			Question question = mapper.map(questionService.view(questionid), Question.class);
			List<Question> questionsList = quiz.getQuestions();
			if (!questionsList.contains(question)) {
				questionsList.add(question);
				quiz.setQuestions(questionsList);
			}
			quiz = mapper.map(quizService.update(mapper.map(quiz, QuizDto.class)), Quiz.class);
			mv.addObject("quiz", quiz);
			mv.addObject(questions, questionService.viewAll());
			mv.addObject(message, createQuestionSucessfully);
		} catch (InvalidInputException e) {
			mv.addObject(message, questionNotFound);
		}
		return mv;

	}

	@GetMapping("/deletequestion")
	public ModelAndView removeQuestionfromQuiz(int questionid, int quizid) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(addquestiontoquiz);
		try {
			Quiz quiz = mapper.map(quizService.view(quizid), Quiz.class);
			Question question = mapper.map(questionService.view(questionid), Question.class);
			List<Question> questionsList = quiz.getQuestions();
			if (questionsList.contains(question)) {
				questionsList.remove(question);
				quiz.setQuestions(questionsList);
			}
			quiz = mapper.map(quizService.update(mapper.map(quiz, QuizDto.class)), Quiz.class);
			mv.addObject("quiz", quiz);
			mv.addObject(questions, questionService.viewAll());
			mv.addObject(message, createQuestionSucessfully);
		} catch (InvalidInputException e) {
			mv.addObject(message, questionNotFound);
		}
		return mv;

	}

	@PostMapping("/checkanswers")
	public ModelAndView getanswersForQuiz(int quizid, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sucesspage");
		try {

			final int[] userScore = { 0 };
			Quiz quiz = mapper.map(quizService.view(quizid), Quiz.class);
			Map<String, String[]> parameterMap = request.getParameterMap();
			parameterMap.entrySet().forEach(x -> {
				if (!x.getKey().equalsIgnoreCase("quizid")) {
					int answer = Integer.parseInt(x.getValue()[0]);
					int questionId = Integer.parseInt(x.getKey());
					int questionAnswer = questionService.view(questionId).getAnswer();
					if (answer == questionAnswer) {
						userScore[0]++;
					}
				}
			});

			mv.addObject("score", userScore[0] * quiz.getMarksPerQuestion());
			mv.addObject(message, "displaying questions of quiz");

		} catch (NumberFormatException | InvalidInputException e) {
			mv.addObject(message, "Cannot get Quiz score");
		}

		return mv;
	}

}