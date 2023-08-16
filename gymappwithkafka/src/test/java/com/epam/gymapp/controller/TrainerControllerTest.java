package com.epam.gymapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.epam.gymapp.dto.TraineeDto;
import com.epam.gymapp.dto.TraineeDtoForRead;
import com.epam.gymapp.dto.TraineeDtoForTrainerUpdate;
import com.epam.gymapp.dto.TrainerDto;
import com.epam.gymapp.dto.TrainerDtoForRead;
import com.epam.gymapp.dto.TrainerDtoForWrite;
import com.epam.gymapp.dto.TrainingDto;
import com.epam.gymapp.dto.TrainingDtoForWrite;
import com.epam.gymapp.dto.UserDto;
import com.epam.gymapp.exception.TraineeException;
import com.epam.gymapp.exception.TrainerNotFoundException;
import com.epam.gymapp.exception.TrainingNotFoundException;
import com.epam.gymapp.kafka.KafkaProducer;
import com.epam.gymapp.model.Trainee;
import com.epam.gymapp.model.Trainer;
import com.epam.gymapp.model.Training;
import com.epam.gymapp.model.User;
import com.epam.gymapp.service.Notificationservice;
import com.epam.gymapp.service.TrainerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = TrainerController.class)
class TrainerControllerTest {

	@MockBean
	TrainerService trainerService;
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	Notificationservice notificationService;
	@MockBean
	KafkaProducer kafkaProducer;

	Trainee trainee;
	User user;
	Trainer trainer;
	TraineeDto traineeDto;
	TrainerDto trainerDto;
	Training training;
	TrainingDto trainingDto;
	TrainerDtoForWrite trainerDtoForWrite;
	TrainingDtoForWrite trainingDtoForWrite;
	TraineeDtoForRead traineeDtoForRead;
	TrainerDtoForRead trainerDtoForRead;
	TraineeDtoForTrainerUpdate traineeDtoForTrainerUpdate;
	List<Trainer> trainers;
	List<Training> trainings;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		traineeDto = new TraineeDto("sai", "sai", "12-12-2001", "hello", "sai@gmail.com");
		trainerDto = new TrainerDto("sai", "sai", "Yoga", "sai@gmail.com");
		user = new User(1, "sai", "sai", "sai@gmail.com", "123456", true, null, null, null);
		trainer = new Trainer(2, user, "sai", null, null, null);
		trainee = new Trainee(1, user, "sai@gmail.com", "hello", "hello", true, null, null);
		training = new Training(2, trainer, trainee, trainer.getUserName(), null, null, 34);
		trainers = new ArrayList<>();
		trainers.add(trainer);
		trainings = new ArrayList<>();
		trainings.add(training);

		trainee.setTrainersList(trainers);
		trainee.setTrainingList(trainings);
		user.setTrainee(trainee);
		List<String> trainerNames = new ArrayList<>();
		trainerNames.add("hello");

		trainerDtoForRead = new TrainerDtoForRead("sai", "sai", "Yoga", "sai@gmail.com", true, null);
		trainingDto = new TrainingDto("sai", "1", "2", "sai1", "Yoga");
		traineeDtoForTrainerUpdate = new TraineeDtoForTrainerUpdate("sai", trainerNames);
		traineeDtoForRead = new TraineeDtoForRead("sai", "sai", "1-1-2001", "lol", "sai@gmail.com", true, trainers);
		trainerDtoForWrite = new TrainerDtoForWrite(1, trainer.getUserName(), null);
		trainingDtoForWrite = new TrainingDtoForWrite(trainer.getUserName(), trainee.getUserName(),
				training.getTrainingName(), null, null, 12);

	}

	@Test
	void testsave() throws Exception {
		UserDto userDto = new UserDto("sai", "123456");

		Mockito.when(trainerService.save(any())).thenReturn(userDto);

		MvcResult mvcResult = mockMvc.perform(post("/gymapp/trainer/register")
				.content(new ObjectMapper().writeValueAsString(userDto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String user = new ObjectMapper().writeValueAsString(userDto);
		assertEquals(mvcResult.getResponse().getContentAsString(), user);
		assertNotNull(mvcResult);

	}

	@Test
	void testsaveForNullUser() throws Exception {
		UserDto userDto = new UserDto("sai", "123456");

		Mockito.when(trainerService.save(any())).thenThrow(TraineeException.class);

		MvcResult mvcResult = mockMvc.perform(post("/gymapp/trainer/register")
				.content(new ObjectMapper().writeValueAsString(userDto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andReturn();
		assertNotNull(mvcResult);

	}

	@Test
	void testupdate() throws Exception {

		Mockito.when(trainerService.update(any())).thenReturn(trainerDto);

		MvcResult mvcResult = mockMvc.perform(put("/gymapp/trainer/update")
				.content(new ObjectMapper().writeValueAsString(trainerDto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String user = new ObjectMapper().writeValueAsString(trainerDto);
		assertEquals(mvcResult.getResponse().getContentAsString(), user);
		assertNotNull(mvcResult);

	}

	@Test
	void testupdateForNullUsers() throws Exception {

		Mockito.when(trainerService.update(any())).thenThrow(TrainerNotFoundException.class);

		MvcResult mvcResult = mockMvc.perform(put("/gymapp/trainer/update")
				.content(new ObjectMapper().writeValueAsString(trainerDto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();
		assertNotNull(mvcResult);

	}

	@Test
	void testView() throws Exception {
		Mockito.when(trainerService.view(anyString())).thenReturn(trainerDtoForRead);

		MvcResult mvcResult = mockMvc.perform(get("/gymapp/trainer/view").param("userName", "sai"))
				.andExpect(status().isOk()).andReturn();

		String result = mvcResult.getResponse().getContentAsString();
		String question = new ObjectMapper().writeValueAsString(trainerDtoForRead);

		assertEquals(result, question);
	}

	@Test
	void testViewForNullUsers() throws Exception {
		Mockito.when(trainerService.view(any())).thenThrow(TrainerNotFoundException.class);
		MvcResult mvcResult = mockMvc.perform(get("/gymapp/trainer/view").param("userName", "sai"))
				.andExpect(status().isNotFound()).andReturn();
		assertNotNull(mvcResult);
	}

	@Test
	void testdelete() throws Exception {

		MvcResult mvcResult = mockMvc.perform(delete("/gymapp/trainer/delete").param("userName", "sai"))
				.andExpect(status().isNoContent()).andReturn();
		assertNotNull(mvcResult);

	}

	@Test
	void testviewTrainersTrainingsList() throws Exception {

		Mockito.when(trainerService.viewTrainerTrainingList(any())).thenReturn(List.of(trainingDtoForWrite));

		MvcResult mvcResult = mockMvc.perform(get("/gymapp/trainer/view/trainingList")
				.content(new ObjectMapper().writeValueAsString(trainingDto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String user = new ObjectMapper().writeValueAsString(List.of(trainingDtoForWrite));
		assertEquals(mvcResult.getResponse().getContentAsString(), user);
		assertNotNull(mvcResult);

	}

	@Test
	void testviewTrainersTrainingsListForNullUsers() throws Exception {
		Mockito.when(trainerService.viewTrainerTrainingList(any())).thenThrow(TrainingNotFoundException.class);

		MvcResult mvcResult = mockMvc.perform(get("/gymapp/trainer/view/trainingList")
				.content(new ObjectMapper().writeValueAsString(trainingDto)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();
		assertNotNull(mvcResult);

	}

	
}
